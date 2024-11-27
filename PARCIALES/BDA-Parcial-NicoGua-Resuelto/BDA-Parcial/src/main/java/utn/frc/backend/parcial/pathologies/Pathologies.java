package utn.frc.backend.parcial.pathologies;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import utn.frc.backend.parcial.pathologies.domain.entities.*;

import java.io.*;
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
    private static final String PERSISTENCE_UNIT_NAME = "h2PathPU";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String PATH_FILE_CSV = "pathologies/Pathologies.csv";

    record CsvRow(
            Integer docId, String docName, Integer hptId, String hptName,
            Integer repId, Date repDate, Integer pthId, String pthName,
            Integer rdetId, Integer rdetCase
    ) {
    }

    private static List<CsvRow> rowList = new ArrayList<>();

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

    public static void readFileCsv(String pathFile, Integer offside, Integer limit) {
        try {
            ClassLoader cl = Pathologies.class.getClassLoader();
            InputStream inputStream = cl.getResourceAsStream(pathFile);

            if (inputStream == null) {
                throw new FileNotFoundException(" Archivo no encontrado: " + pathFile);
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            int cont = 0;
            while ((line = reader.readLine()) != null) {
                if (cont < offside) {
                    cont++;
                    continue;
                }

                if (cont >= limit + offside) {
                    break;
                }

                CsvRow csvRow = csvRowMapper.apply(line.split(","));
                rowList.add(csvRow);


                cont++;
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Nota: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static List<ReportDetail> parseToEntity() {
        List<ReportDetail> lista = new ArrayList<>();
        for (CsvRow row : rowList) {
            Hospital hpt = new Hospital(row.hptId, row.hptName);
            Doctor doc = new Doctor(row.docId, row.docName, hpt);
            Report rep = new Report(row.repId, row.repDate, doc);
            Pathology pth = new Pathology(row.pthId, row.pthName);
            ReportDetail rdet = new ReportDetail(row.rdetId, row.rdetCase, rep, pth);
            lista.add(rdet);
        }
        return lista;
    }

    public static void main(String[] args) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        readFileCsv(PATH_FILE_CSV, 1, 1005);

        saveEntityOnDataBase(em, parseToEntity());


//        hospitalCases(em, 12);
//        doctorCases(em, 12);
//        pathologySummary(em, 20, "1900-01-01", "2100-12-31");

        Scanner sc;

        boolean enEjecucion = true;

        while (enEjecucion){
            System.out.println("Menu de Opciones");
            System.out.println("1. Detalle de casos para un centro de salud (hospital) determinado");
            System.out.println("2. Detalle de patologías para un médico determinado");
            System.out.println("3. Detalle de casos para una patología y rango de fechas determinados");
            System.out.println("4. Salir \t\t");
            System.out.print("Seleccione una opcion: \t\t");
            sc = new Scanner(System.in);
            switch(sc.nextLine()){
                case "1":
                    Scanner hptId = new Scanner(System.in);
                    System.out.print("Ingrese un numero de hospital: \t\t");
                    while (!hptId.hasNextInt()) System.out.printf("Ingrese un NUMERO de hospital:\t\t", hptId.next());
                    hospitalCases(em, hptId.nextInt());
                    break;
                case "2":
                    Scanner docId = new Scanner(System.in);
                    System.out.print("Ingrese un numero de doctor \t\t");
                    while (!docId.hasNextInt()) System.out.printf("Ingrese un NUMERO de doctort\t", docId.next());
                    doctorCases(em, docId.nextInt());
                    break;
                case "3":
                    System.out.print("Ingrese un numero de patologia \t\t");
                    Scanner pthId = new Scanner(System.in);
                    while (!pthId.hasNextInt()) System.out.printf("Ingrese un NUMERO de patologia\t", pthId.next());

                    System.out.print("Ingrese una fecha desde: (yyyy-mm-dd) \t\t");
                    String fDesdeStr = "";
                    Scanner fDesde = new Scanner(System.in);
                    while (!(fDesdeStr = fDesde.nextLine()).matches("\\d{4}-\\d{2}-\\d{2}") || !isValidDate(fDesdeStr)) {
                        System.out.printf("Ingrese una FECHA VALIDA: (Ejemplo: 2023-01-01)\t\t");
                    }

                    System.out.print("Ingrese una fecha hasta: (yyyy-mm-dd) \t\t");
                    String fHastaStr = "";
                    Scanner fHasta = new Scanner(System.in);
                    while (!(fHastaStr = fHasta.nextLine()).matches("\\d{4}-\\d{2}-\\d{2}") || !isValidDate(fHastaStr)) {
                        System.out.printf("Ingrese una FECHA VALIDA: (Ejemplo: 2023-01-01)\t\t");
                    }

                    System.out.println();
                    pathologySummary(em, pthId.nextInt(), fDesdeStr, fHastaStr);
                    break;
                case "4":
                    System.out.println("saliendo... Gracias por pasar por aqui!");
                    enEjecucion = false;
                    em.close();
                    emf.close();
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        }
//      em.close();
//      emf.close();
    }

    public static boolean isValidDate(String dateStr) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        try {
            format.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private static void saveEntityOnDataBase(EntityManager em, List<ReportDetail> lista) {
        em.getTransaction().begin();

        for (ReportDetail rd : lista) {
            em.merge(rd);
        }
        em.getTransaction().commit();
    }

    private static void hospitalCases(EntityManager em, Integer hptid) {
        List<Hospital> h = Hospital.findById(em, hptid);
        if (h.isEmpty()) {
            System.out.println("No hay un hospital con ese id");

        } else {
            Hospital hospital = h.get(0);
            double total = 0.0;
            System.out.printf("Hospital: %d, %s\n", hospital.getHid(), hospital.getHname());
            for (Doctor doctor : hospital.getDoctors()) {
                double subtotal = 0.0;
                for (Report report : doctor.getReports()) {
                    for (ReportDetail detail : report.getDetails()) {
                        subtotal += detail.getCases();
                    }
                }
                System.out.println("-------------------------------------------------------");
                System.out.printf("\t\t%3d, %32s: %8.2f\n",
                        doctor.getDid(),
                        doctor.getDname(),
                        subtotal
                );
                total += subtotal;
            }
            System.out.println("\t\t===============================================");
            System.out.printf("TOTAL: %48.2f\n", total);
        }
    }

    private static void doctorCases(EntityManager em, Integer docId) {
        List<Doctor> d = Doctor.findById(em, docId);
        if (d.isEmpty()) {
            System.out.println("No hay un Doctor con ese id.");
        } else {
            Doctor doctor = d.get(0);
            System.out.printf("Doctor: %3d, %s\n", doctor.getDid(), doctor.getDname());
            int totalCasesDoctor = 0;
            for (Report report : doctor.getReports()) {
                System.out.println("-------------------------------------------------------");
                System.out.printf("\t%3d, %s\n", report.getRid(), report.getRdate());
                int totalCasesReport = 0;
                for (ReportDetail detail : report.getDetails()) {
                    Pathology pathology = detail.getPathology();
                    int cases = detail.getCases();
                    System.out.printf("\t\t%3d, Pathology name: %30s: %4d\n",
                            pathology.getPid(),
                            pathology.getPname(),
                            cases
                    );
                    totalCasesReport += cases;
                }
                System.out.println("\t\t===============================================");
                System.out.printf("\t\tTOTAL: %40d\n\n", totalCasesReport);
                totalCasesDoctor += totalCasesReport;
            }
            System.out.printf("TOTAL: %48d\n\n", totalCasesDoctor);
        }
    }

    private static void pathologySummary(EntityManager em, Integer pthId, String fDesde, String fHasta) {
        DateFormat format = new SimpleDateFormat(DATE_FORMAT);
        try {
            pathologySummary(em, pthId, format.parse(fDesde), format.parse(fHasta));
        } catch (ParseException pe) {
            throw new RuntimeException(pe.getMessage());
        }
    }

    private static void pathologySummary(EntityManager em, Integer pthId, Date fDesde, Date fHasta) {
        List<Pathology> pathologies = Pathology.findById(em, pthId);
        if (pathologies.isEmpty()) {
            System.out.println("No existe una patologia");
        } else {
            Pathology pathology = pathologies.get(0);
            int totalCases = 0;
//            List<Doctor> doctors = em.createQuery(
//                            "SELECT DISTINCT doc FROM ReportDetail det JOIN det.report rep JOIN rep.doctor doc WHERE det.pathology = :pat AND rep.rdate >= :fdesde AND rep.rdate <= :fhasta", Doctor.class)
            List<Hospital> hospitals = em.createQuery(
                            "SELECT DISTINCT h FROM Hospital h JOIN h.doctors d JOIN d.reports r JOIN r.details rd " +
                                    "WHERE rd.pathology = :pat AND r.rdate BETWEEN :fdesde AND :fhasta", Hospital.class)
                    .setParameter("pat", pathology)
                    .setParameter("fdesde", fDesde)
                    .setParameter("fhasta", fHasta)
                    .getResultList();

            System.out.printf(
                    "%d, %s, desde %s hasta %s\n",
                    pathology.getPid(),
                    pathology.getPname(),
                    fDesde.toString(),
                    fHasta.toString(),
                    "-------------------------------------------------------"
            );

            for (Hospital hospital : hospitals) {
                int subtotalCases = 0;
                System.out.printf("\t%3d, %s\n", hospital.getHid(), hospital.getHname());

                for (Doctor doctor : hospital.getDoctors()) {
                    for (Report report : doctor.getReports()) {
                        if (!report.getRdate().before(fDesde) && !report.getRdate().after(fHasta)) {
                            int casesInReport = 0;
                            for (ReportDetail detail : report.getDetails()) {
                                if (detail.getPathology().equals(pathology)) {
                                    casesInReport += detail.getCases();
                                    System.out.printf("\t\t%s: %d\n", report.getRdate().toString(), detail.getCases());
                                }

                            }
                            subtotalCases += casesInReport;
                        }
                    }
                }
                System.out.println("\t\t===============================================");
                System.out.printf("\t\tTotal: %40d\n\n", subtotalCases);

                totalCases += subtotalCases;
            }
            System.out.printf("TOTAL: %48d\n\n", totalCases);
        }
    }

}