package utn.frc.backend.parcial.expenses;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import utn.frc.backend.parcial.expenses.domain.entities.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;

public class Expenses {
    private static final int EMPLOYEE_ID_INDEX = 0, EMPLOYEE_NAME_INDEX = 1;
    private static final int DEPARTMENT_ID_INDEX = 2, DEPARTMENT_NAME_INDEX = 3;
    private static final int EXPENSE_SUBMISSION_ID_INDEX = 4, EXPENSE_SUBMISSION_DATE_INDEX = 5;
    private static final int EXPENSE_ID_INDEX = 7, EXPENSE_NAME_INDEX = 8;
    private static final int DET_ID_INDEX = 6, DET_AMOUNT_INDEX = 9;
    private static final String PERSISTENCE_UNIT_NAME = "expensescdb";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String CSV_PATH = "D:\\Documentos\\INGENIERÍA EN SISTEMAS\\3° Año\\BACKEND\\Proyectos\\BDA-Parcial\\BDA-Parcial\\Expenses.csv";


    record CsvRow(
            Integer empId, String empName, Integer dptId, String dptName,
            Integer subId, Date subDate, Integer expId, String expName,
            Integer detId, Double detAmmount
    ) {}

    private static List<CsvRow> rowList;

    // Recibe un array de string que en este caso sería el splitline del metodo lectorCSV y devuelve un objeto de tipo CsvRow
    private static Function<String[], CsvRow> csvRowMapper = (arr) -> {
        Date date = null;
        try {
            DateFormat format = new SimpleDateFormat(DATE_FORMAT);
            date = format.parse(arr[EXPENSE_SUBMISSION_DATE_INDEX]);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage() + " DATE: " + arr[EXPENSE_SUBMISSION_DATE_INDEX]);
        }
        return new CsvRow(
                Integer.parseInt(arr[EMPLOYEE_ID_INDEX]),
                arr[EMPLOYEE_NAME_INDEX],
                Integer.parseInt(arr[DEPARTMENT_ID_INDEX]),
                arr[DEPARTMENT_NAME_INDEX],
                Integer.parseInt(arr[EXPENSE_SUBMISSION_ID_INDEX]),
                date,
                Integer.parseInt(arr[EXPENSE_ID_INDEX]),
                arr[EXPENSE_NAME_INDEX],
                Integer.parseInt(arr[DET_ID_INDEX]),
                Double.valueOf(arr[DET_AMOUNT_INDEX])
        );
    };

    private static void lectorCSV(String filPath, EntityManager em) {
        rowList = new ArrayList<>();
        int count = 1;

        try (BufferedReader br = new BufferedReader(new FileReader(filPath))) {
            String line;


            while ((line = br.readLine()) != null) {
                if (count > 1) {
                    String[] splitline = line.split(",");
                    CsvRow row = csvRowMapper.apply(splitline);
                    rowList.add(row);

                    crearInstancia(row, em);
                }
                count++;

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            }
        }

    private static void crearInstancia(CsvRow row, EntityManager em) {

        em.getTransaction().begin();

        Departament departament = em.find(Departament.class, row.dptId());
        if (departament == null) {
            departament = new Departament(row.dptId(), row.dptName());
        }

        Employee employee = em.find(Employee.class, row.empId());
        if(employee == null) {
            employee = new Employee(row.empId(), row.empName(), departament);
        }

        ExpenseSubmission expenseSubmission = em.find(ExpenseSubmission.class, row.subId());
        if (expenseSubmission == null) {
            expenseSubmission = new ExpenseSubmission(row.subId(), row.subDate(), employee);
        }

        ExpensesSubmissionDetail expenseSubmissionDetail = em.find(ExpensesSubmissionDetail.class, row.detId());
        if(expenseSubmissionDetail == null) {
            expenseSubmissionDetail = new ExpensesSubmissionDetail(row.detId(), row.detAmmount());
        }

        Expense expense = em.find(Expense.class, row.expId());
        if (expense == null) {
            expense = new Expense(row.expId(), row.expName());
        }

        departament.setEmployees(employee);
        expenseSubmission.setExpSubDet(expenseSubmissionDetail);
        expenseSubmissionDetail.setExpSub(expenseSubmission);
        expenseSubmissionDetail.setExpense(expense);
        expense.setExpSubDet(expenseSubmissionDetail);

        System.out.println("-------------------------------------");
        System.out.println(departament);
        System.out.println(employee);
        System.out.println(expense);
        System.out.println(expenseSubmission);
        System.out.println(expenseSubmissionDetail);

        try {
            em.persist(departament);
            em.persist(employee);
            em.persist(expense);
            em.persist(expenseSubmissionDetail);
            em.persist(expenseSubmission);
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }

        em.getTransaction().commit();
    }

    public static void main(String[] args) throws Exception {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        employeeExpenses(em, 6);
        departmentExpenses(em, 1);
        expenseSummary(em, 1, "1900-01-05", "2025-01-07");

        // lectorCSV(CSV_PATH, em);

        em.close();
        emf.close();
    }

    private static List<ExpensesSubmissionDetail> consultarDetalles(int sid, EntityManager em) {
        List<ExpensesSubmissionDetail> expenseSubDet = em.createQuery("SELECT S FROM ExpensesSubmissionDetail S WHERE S.expSub.sid =:id", ExpensesSubmissionDetail.class)
                .setParameter("id", sid)
                .getResultList();
        return expenseSubDet;
    }

    private static List<ExpenseSubmission> consultarSubmission(int empId, EntityManager em) {
        List<ExpenseSubmission> expenseSubmissions = em.createQuery("SELECT S FROM ExpenseSubmission S WHERE S.employee.empid =:id", ExpenseSubmission.class)
                .setParameter("id", empId)
                .getResultList();
        return expenseSubmissions;
    }

    private static double employeeExpenses(EntityManager em, Integer empId) {

        Employee employee = em.find(Employee.class, empId);

        int empleadoid = employee.getEmpid();
        String empleadoname = employee.getEmpname();

        double totalTotal = 0;

        List<ExpenseSubmission> expenseSubmissions = consultarSubmission(empId, em);

        // COLUMNA CON ID DE EMPLEADO Y NOMBRE DEL EMPLEADO
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("Employee: %3d, %s\n", empleadoid, empleadoname);

        // FILAS CON ID DE EXPENSESUBMISSION Y LA FECHA DE CADA EXPENSESUBMISSION
        for (ExpenseSubmission expenseSubmission : expenseSubmissions) {
            double total = 0;
            System.out.printf("\t%d, %s\n", expenseSubmission.getSid(), expenseSubmission.getSdate());

            List<ExpensesSubmissionDetail> detalles = consultarDetalles(expenseSubmission.getSid(), em);

            // DE CADA EXPENSE SUBMISSION MOSTRAR SUS DETALLES
            for (ExpensesSubmissionDetail detail : detalles) {

                // MUESTRA EL ID DEL DETALLE, EL NOMBRE DE LA EXPENSA Y EL MONTO
                System.out.printf("\t\t%3d, %32s: %8.2f\n",
                        detail.getId(),
                        detail.getExpense().getExpname(),
                        detail.getAmount()

                );
                total += detail.getAmount();
            }
            System.out.println("\t\t===============================================");
            System.out.printf("\t\tTOTAL: %40.2f\n\n", total);
            totalTotal += total;
        }
        System.out.printf("TOTAL EMPLEADO: %48.2f\n", totalTotal);

        return totalTotal;

    }



    private static void departmentExpenses(EntityManager em, Integer dptId) {
        Departament departament = em.find(Departament.class, dptId);
        double totalDepartamento = 0;
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("Department: %3d, %s\n", departament.getDid(), departament.getDname());

        List<Employee> employes = consultarEmpleados(em, departament.getDid());

        // RECORRO LOS EMPLEADOS
        for (Employee employee : employes) {

            double totalEmpleado = 0;
            List<ExpenseSubmission> expenseSubmissions = consultarSubmission(employee.getEmpid(), em);

            // RECORRO LOS SUBMISION DE CADA EMPLEADO
            for (ExpenseSubmission expenseSubmission : expenseSubmissions) {
                double totalExpense = 0;
                List<ExpensesSubmissionDetail> detalles = consultarDetalles(expenseSubmission.getSid(), em);

                // RECORRO LOS DETALLES DE CADA SUBMISION DE CADA EMPLEADO
                for (ExpensesSubmissionDetail detail : detalles) {
                    totalExpense += detail.getAmount();
                }
                totalEmpleado += totalExpense;
            }

            totalDepartamento += totalEmpleado;
            System.out.printf("\t\t%3d, %32s: %8.2f\n", employee.getEmpid(),employee.getEmpname(), totalEmpleado);
        }

        System.out.println("\t\t===============================================");
        System.out.printf("TOTAL DEPARTAMENTO: %48.2f\n", totalDepartamento);
        System.out.println("--------------------------------------------------------------------------");

    }

    private static List<Employee> consultarEmpleados(EntityManager em, int did) {
         List<Employee> empleadosFilter = em.createQuery("SELECT E FROM Employee E WHERE E.departament.did = :dptid", Employee.class)
                 .setParameter("dptid", did)
                 .getResultList();
         return empleadosFilter;
    }

    private static void expenseSummary(EntityManager em, Integer expId, String fDesde, String fHasta) {
        DateFormat format = new SimpleDateFormat(DATE_FORMAT);
        try {
            expenseSummary(em, expId, format.parse(fDesde), format.parse(fHasta));
        } catch (ParseException pe) {
            throw new RuntimeException(pe.getMessage());
        }
    }

    private static List<ExpenseSubmission> consultarExpByDate(EntityManager em, Date fechaDesde, Date fechaHata) {
        ArrayList<ExpenseSubmission> expenseSubmissionFiltradas = new ArrayList<>();
        List<ExpenseSubmission> expSubSinFiltro = em.createQuery("SELECT ES FROM ExpenseSubmission ES").getResultList();

        for (ExpenseSubmission expSub : expSubSinFiltro) {
            if (fechaDesde.compareTo(expSub.getSdate()) < 0 && fechaHata.compareTo(expSub.getSdate()) > 0) {
                expenseSubmissionFiltradas.add(expSub);
            }
        }
        return expenseSubmissionFiltradas;
    }

    private static void expenseSummary(EntityManager em, Integer expId, Date fDesde, Date fHasta) {

        List<ExpenseSubmission> expFiltradas = consultarExpByDate(em, fDesde, fHasta);
        // System.out.println(expFiltradas.toString());

        for (ExpenseSubmission expSub : expFiltradas) {
            List<ExpensesSubmissionDetail> detailsFilt = consultarDetailsByExpSub( em, expSub.getSid(), expId);

            //System.out.printf("\t%3d, %s\n", expSub.getEmployee().getEmpid(), expSub.getEmployee().getEmpname());

            for ()

            for (ExpensesSubmissionDetail detail : detailsFilt) {
                System.out.printf("\t%3d, %s\n", expSub.getEmployee().getEmpid(), expSub.getEmployee().getEmpname());
                System.out.println(detail);
            }
        }

//        System.out.printf(
//                "%d, %s, desde %s hasta %s\n",
//                999, "Expense Name",
//                "yyyy-mm-dd", "yyyy-mm-dd"
//        );
//        System.out.printf("\t%3d, %s\n", 999, "Employee's Name");
//        System.out.printf("\t\t%s: %35.2f\n", "yyyy-mm-dd", 999.99d);
//        System.out.printf("\t\t%s: %35.2f\n", "yyyy-mm-dd", 999.99d);
//        System.out.println("\t\t===============================================");
//        System.out.printf("\t\tTotal: %40.2f\n\n", 9999.99d); // Employee Total
//        System.out.printf("\t%3d, %s\n", 999, "Employee's Name");
//        System.out.printf("\t\t%s: %35.2f\n", "yyyy-mm-dd", 999.99d);
//        System.out.printf("\t\t%s: %35.2f\n", "yyyy-mm-dd", 999.99d);
//        System.out.println("\t\t===============================================");
//        System.out.printf("\t\tTotal: %40.2f\n\n", 9999.99d); // Employee Total
//        System.out.printf("TOTAL: %48.2f\n", 99999.99d);

        Expense exp = em.find(Expense.class, expId);
        System.out.println("----------------------------------------------------------------");
        System.out.printf(
                "%d, %s, desde %s hasta %s\n",
                exp.getExpid(), exp.getExpname(),
                fDesde, fHasta
        );


        System.out.println("----------------------------------------------------------------");
    }

    private static List<ExpensesSubmissionDetail> consultarDetailsByExpSub(EntityManager em, int expSubDet, int expId) {
        List<ExpensesSubmissionDetail> detailsSinFiltro = em.createQuery("SELECT esd FROM ExpensesSubmissionDetail esd WHERE esd.expSub.sid = :expId").setParameter("expId", expSubDet).getResultList();

        List<ExpensesSubmissionDetail> detailsConFiltro = new ArrayList<>();

        for (ExpensesSubmissionDetail expSubDetail : detailsSinFiltro) {
            if ( expSubDetail.getExpense().getExpid() == expId ) {
                detailsConFiltro.add(expSubDetail);
            }
        }

        return detailsConFiltro;
    }

}
