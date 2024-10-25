package utn.frc.backend.parcial.pathologies;

import jakarta.persistence.EntityManager;
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


    public static void main(String[] args) throws Exception {
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        //EntityManager em = emf.createEntityManager();

        hospitalCases(null, null);
        doctorCases(null, null);
        pathologySummary(null, null, "1900-01-01", "2100-12-31");

        //em.close();
        //emf.close();
    }

    private static void hospitalCases(EntityManager em, Integer hid) {
        System.out.printf("Hospital: %d, %s\n", 999, "Hospital's Name");
        System.out.println("-------------------------------------------------------");
        System.out.printf("\t\t%3d, %36s: %4d\n", 999, "Doctor's Name", 9999);
        System.out.printf("\t\t%3d, %36s: %4d\n", 999, "Doctor's Name", 9999);
        System.out.printf("\t\t%3d, %36s: %4d\n", 999, "Doctor's Name", 9999);
        System.out.println("\t\t===============================================");
        System.out.printf("TOTAL: %48d\n\n", 99999);
    }

    private static void doctorCases(EntityManager em, Integer did) {
        System.out.printf("Doctor: %d, %s\n", 999, "Doctor's Name");
        System.out.println("-------------------------------------------------------");
        System.out.printf("\t%d, %s\n", 999, "yyyy-mm-dd");
        System.out.printf("\t\t%3d, %36s: %4d\n",
                999,
                "Pathology name",
                9999
        );
        System.out.printf("\t\t%3d, %36s: %4d\n",
                999,
                "Pathology name",
                9999
        );
        System.out.println("\t\t===============================================");
        System.out.printf("\t\tTOTAL: %40d\n\n", 9999);
        System.out.printf("\t%d, %s\n", 999, "yyyy-mm-dd");
        System.out.printf("\t\t%3d, %36s: %4d\n",
                999,
                "Pathology name",
                9999
        );
        System.out.printf("\t\t%3d, %36s: %4d\n",
                999,
                "Pathology name",
                9999
        );
        System.out.println("\t\t===============================================");
        System.out.printf("\t\tTOTAL: %40d\n\n", 9999);
        System.out.printf("TOTAL: %48d\n\n", 99999);
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
