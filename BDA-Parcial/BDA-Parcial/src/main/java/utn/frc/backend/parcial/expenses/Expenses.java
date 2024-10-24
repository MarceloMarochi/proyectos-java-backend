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

//        Departament departament = new Departament(row.detId(), row.dptName());
//
//        Employee employee = new Employee(row.empId(), row.empName(), departament);
//        departament.setEmployees(employee);
//
//        ExpenseSubmission expenseSubmission = new ExpenseSubmission(row.subId(), row.subDate(), employee);
//
//        ExpensesSubmissionDetail expenseSubDet = new ExpensesSubmissionDetail(row.detId(), row.detAmmount());
//        expenseSubmission.setExpSubDet(expenseSubDet);
//        expenseSubDet.setExpSub(expenseSubmission);
//
//        Expense expense = new Expense(row.expId(), row.expName());
//        expenseSubDet.setExpense(expense);
//        expense.setExpSubDet(expenseSubDet);


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
//        employee.setExpenses(expense);
//        expense.setEmployee(employee);


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

//        employeeExpenses(null, null);
//        departmentExpenses(null, null);
//        expenseSummary(null, null, "1900-01-01", "2100-12-31");

        lectorCSV(CSV_PATH, em);

        em.close();
        emf.close();
    }

    private static void employeeExpenses(EntityManager em, Integer empId) {
        System.out.printf("Employee: %3d, %s\n", 999, "Employee's Name");
        System.out.printf("\t%d, %s\n", 999, "yyyy-mm-dd");
        System.out.printf("\t\t%3d, %32s: %8.2f\n",
                999,
                "Expense Name",
                999.99d
        );
        System.out.printf("\t\t%3d, %32s: %8.2f\n",
                999,
                "Expense Name",
                999.99d
        );
        System.out.println("\t\t===============================================");
        System.out.printf("\t\tTOTAL: %40.2f\n\n", 999.99d);
        System.out.printf("\t%d, %s\n", 999, "yyyy-mm-dd");
        System.out.printf("\t\t%3d, %32s: %8.2f\n",
                999,
                "Expense Name",
                999.99d
        );
        System.out.printf("\t\t%3d, %32s: %8.2f\n",
                999,
                "Expense Name",
                999.99d
        );
        System.out.println("\t\t===============================================");
        System.out.printf("\t\tTOTAL: %40.2f\n\n", 999.99d);
        System.out.printf("TOTAL: %48.2f\n", 9999.99d);

    }

    private static void departmentExpenses(EntityManager em, Integer dptId) {
        System.out.printf("Department: %3d, %s\n", 999, "Department Name");
        System.out.printf("\t\t%3d, %32s: %8.2f\n", 999, "Employee's Name", 999.99d);
        System.out.printf("\t\t%3d, %32s: %8.2f\n", 999, "Employee's Name", 999.99d);
        System.out.printf("\t\t%3d, %32s: %8.2f\n", 999, "Employee's Name", 999.99d);
        System.out.println("\t\t===============================================");
        System.out.printf("TOTAL: %48.2f\n", 9999.99d);

    }

    private static void expenseSummary(EntityManager em, Integer expId, String fDesde, String fHasta) {
        DateFormat format = new SimpleDateFormat(DATE_FORMAT);
        try {
            expenseSummary(em, expId, format.parse(fDesde), format.parse(fHasta));
        } catch (ParseException pe) {
            throw new RuntimeException(pe.getMessage());
        }
    }

    private static void expenseSummary(EntityManager em, Integer expId, Date fDesde, Date fHasta) {
        System.out.printf(
                "%d, %s, desde %s hasta %s\n",
                999, "Expense Name",
                "yyyy-mm-dd", "yyyy-mm-dd"
        );
        System.out.printf("\t%3d, %s\n", 999, "Employee's Name");
        System.out.printf("\t\t%s: %35.2f\n", "yyyy-mm-dd", 999.99d);
        System.out.printf("\t\t%s: %35.2f\n", "yyyy-mm-dd", 999.99d);
        System.out.println("\t\t===============================================");
        System.out.printf("\t\tTotal: %40.2f\n\n", 9999.99d); // Employee Total
        System.out.printf("\t%3d, %s\n", 999, "Employee's Name");
        System.out.printf("\t\t%s: %35.2f\n", "yyyy-mm-dd", 999.99d);
        System.out.printf("\t\t%s: %35.2f\n", "yyyy-mm-dd", 999.99d);
        System.out.println("\t\t===============================================");
        System.out.printf("\t\tTotal: %40.2f\n\n", 9999.99d); // Employee Total
        System.out.printf("TOTAL: %48.2f\n", 99999.99d);

    }

}
