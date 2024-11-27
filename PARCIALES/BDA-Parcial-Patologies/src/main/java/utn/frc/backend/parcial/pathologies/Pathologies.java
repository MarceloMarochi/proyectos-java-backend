package utn.frc.backend.parcial.pathologies;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import utn.frc.backend.parcial.pathologies.domain.entities.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;

public class Pathologies {
    private static final int DOCTOR_ID_INDEX = 0, DOCTOR_NAME_INDEX = 1;
    private static final int HOSPITAL_ID_INDEX = 2, HOSPITAL_NAME_INDEX = 3;
    private static final int REPORT_ID_INDEX = 4, REPORT_DATE_INDEX = 5;
    private static final int PATHOLOGY_ID_INDEX = 7, PATHOLOGY_NAME_INDEX = 8;
    private static final int DET_ID_INDEX = 6, CASES_INDEX = 9;
    private static final String PERSISTENCE_UNIT_NAME = "paatologies";
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private static final String filePath = "D:\\Documentos\\INGENIERÍA EN SISTEMAS\\3° Año\\BACKEND\\Proyectos\\BDA-Parcial-Patologies\\src\\main\\resources\\pathologies\\Pathologies.csv";

    record CsvRow(
            Integer did, String dname, Integer hid, String hname,
            Integer rid, Date rdate, Integer pid, String pname,
            Integer rdid, Integer cases
    ) {}

    private static List<CsvRow> rowList;

    private static Function<String[], CsvRow> csvRowMapper = (arr) -> {
        Date date = null;
        try {
            DateFormat format = new SimpleDateFormat(DATE_FORMAT);
            date = format.parse(arr[REPORT_DATE_INDEX]);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage() + " DATE: " + arr[REPORT_DATE_INDEX]);
        }
        return new CsvRow(
                Integer.parseInt(arr[DOCTOR_ID_INDEX]),
                arr[DOCTOR_NAME_INDEX],
                Integer.parseInt(arr[HOSPITAL_ID_INDEX]),
                arr[HOSPITAL_NAME_INDEX],
                Integer.parseInt(arr[REPORT_ID_INDEX]),
                date,
                Integer.parseInt(arr[PATHOLOGY_ID_INDEX]),
                arr[PATHOLOGY_NAME_INDEX],
                Integer.parseInt(arr[DET_ID_INDEX]),
                Integer.parseInt(arr[CASES_INDEX])
        );
    };

    public static void lectorCsv(String fpath, EntityManager em) {

        try (BufferedReader br = new BufferedReader(new FileReader(fpath))) {
            String line;
            Boolean firstLine = true;

            while ( (line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] splitLine = line.split(",");
                CsvRow row = csvRowMapper.apply(splitLine);

                crearInstancia(row, em);

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void crearInstancia(CsvRow row, EntityManager em) {

        em.getTransaction().begin();


        Hospital hospital = em.find(Hospital.class, row.hid());
        if (hospital == null) {
            hospital = new Hospital(row.hid(), row.hname());
        }

        Doctor doctor = em.find(Doctor.class, row.did());
        if (doctor == null) {
            doctor = new Doctor(row.did(), row.dname(), hospital);
        }
        hospital.setDoctors(doctor);

        Report report = em.find(Report.class, row.rid());
        if (report == null) {
            report = new Report(row.rid(),doctor, row.rdate());
        }


        Pathology patology = em.find(Pathology.class, row.pid());
        if (patology == null) {
            patology = new Pathology(row.pid(), row.pname());
        }

        ReportDetail reportDetail = em.find(ReportDetail.class, row.rdid());
        if (reportDetail == null) {
            reportDetail = new ReportDetail(row.rdid(), row.cases(), report, patology);
        }

//        System.out.println(hospital);
//        System.out.println(doctor);
//        System.out.println(report);
//        System.out.println(patology);
//        System.out.println(reportDetail);

        try {
            em.persist(hospital);
            em.persist(doctor);
            em.persist(report);
            em.persist(reportDetail);
            em.persist(patology);
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }

        em.getTransaction().commit();

    }

    public static void main(String[] args) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();


        lectorCsv(filePath, em);

        hospitalCases(em, 25);
        doctorCases(em, 1);
//        pathologySummary(null, null, "1900-01-01", "2100-12-31");

        em.close();
        emf.close();
    }

    private static void hospitalCases(EntityManager em, Integer hid) {
        System.out.println("-------------------------------------------------------");
        System.out.println("            PUNTO 1");
        System.out.println("-------------------------------------------------------");

        Hospital hospital = em.find(Hospital.class, hid);
        System.out.printf("Hospital: %d, %s\n", hid, hospital.getHname());

        System.out.println("-------------------------------------------------------");

        List<Doctor> doctorXHospital = buscarDoctoresPorHospital(em, hid);

        int totalHospital = 0;

        for (Doctor doctor : doctorXHospital) {
          int contadorXReporte = 0;
          List<Report> reportXDoctor = buscarReportesPorDoctor(em, doctor.getDid());

          for (Report repor : reportXDoctor) {

              List<ReportDetail> detalles = buscarDetallesPorReporte(em, repor.getRid());
              for (ReportDetail detail : detalles) {
                  contadorXReporte += detail.getCases();
              }

          }
          totalHospital += contadorXReporte;
          System.out.printf("\t\t%3d, %36s: %4d\n", doctor.getDid(), doctor.getDname(), contadorXReporte);
        }

        System.out.println("\t\t===============================================");
        System.out.printf("TOTAL DE CASOS DEL HOSPITAL: %25d\n\n", totalHospital);

    }

    private static List<Doctor> buscarDoctoresPorHospital(EntityManager em, Integer hid) {
        return em.createQuery("SELECT D FROM Doctor D WHERE D.hospital.hid=:id", Doctor.class).setParameter("id", hid).getResultList();
    }

    private static List<Report> buscarReportesPorDoctor(EntityManager em, int did) {
        return em.createQuery("SELECT R FROM Report R WHERE R.doctor.did=:id", Report.class).setParameter("id", did).getResultList();
    }

    private static List<ReportDetail> buscarDetallesPorReporte(EntityManager em, int rid) {
        return em.createQuery("SELECT R FROM ReportDetail R WHERE R.report.rid=:id", ReportDetail.class).setParameter("id", rid).getResultList();
    }


    private static void doctorCases(EntityManager em, Integer did) {
        System.out.println("-------------------------------------------------------");
        System.out.println("            PUNTO 2");
        System.out.println("-------------------------------------------------------");

        Doctor doctor = em.find(Doctor.class, did);

        System.out.printf("Doctor: %d, %s\n", doctor.getDid(), doctor.getDname());
        System.out.println("-------------------------------------------------------");

        List<Report> reportesXDoctor = buscarReportesPorDoctor(em, did);
        int totalDoctor = 0;

        for (Report repo : reportesXDoctor) {
            int totalReport = 0;
            System.out.printf("\t%d, %s\n", repo.getRid(), repo.getRdate());

            List<ReportDetail> details = buscarDetallesPorReporte(em, repo.getRid());

            for (ReportDetail deta : details) {
                System.out.printf("\t\t%3d, %36s: %4d\n",
                        deta.getPathology().getPid(),
                        deta.getPathology().getPname(),
                        deta.getCases()
                );
                totalReport += deta.getCases();
            }
            System.out.println("\t\t===============================================");
            System.out.printf("\t\tTOTAL: %40d\n\n", totalReport);
            totalDoctor += totalReport;
        }
        System.out.printf("TOTAL: %48d\n\n", totalDoctor);
    }


    private static void pathologySummary(EntityManager em, Integer pid, String fDesde, String fHasta) {
        DateFormat format = new SimpleDateFormat(DATE_FORMAT);
        try {
            pathologySummary(em, pid, format.parse(fDesde), format.parse(fHasta));
        } catch (ParseException pe) {
            throw new RuntimeException(pe.getMessage());
        }
    }

    private static void pathologySummary(EntityManager em, Integer pid, Date fDesde, Date fHasta) {
        System.out.printf(
                "%d, %s\ndesde %s\nhasta %s\n%s\n",
                999, "Pathology Name",
                "yyyy-mm-dd", "yyyy-mm-dd",
                "-------------------------------------------------------"
        );
        System.out.printf("\t%3d, %s\n", 999, "Hospital Name");
        System.out.printf("\t\t%s: %35d\n", "yyyy-mm-dd", 9999);
        System.out.printf("\t\t%s: %35d\n", "yyyy-mm-dd", 9999);
        System.out.println("\t\t===============================================");
        System.out.printf("\t\tTotal: %40d\n\n", 99999); // Hospital Total
        System.out.printf("\t%3d, %s\n", 999, "Hospital Name");
        System.out.printf("\t\t%s: %35d\n", "yyyy-mm-dd", 9999);
        System.out.printf("\t\t%s: %35d\n", "yyyy-mm-dd", 9999);
        System.out.println("\t\t===============================================");
        System.out.printf("\t\tTotal: %40d\n\n", 99999); // Hospital Total
        System.out.printf("TOTAL: %48d\n\n", 999999);
    }

}
