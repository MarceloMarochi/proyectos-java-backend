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
    private static final String PERSISTENCE_UNIT_NAME = "expensesdb";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String CSV_PATH = "C:\\Users\\Usuario\\Desktop\\Escritorio\\Nico\\UTN\\Backend 2024\\PARCIALES\\K5\\Expenses.csv";

    record CsvRow(
            Integer empId, String empName, Integer dptId, String dptName,
            Integer subId, Date subDate, Integer expId, String expName,
            Integer detId, Double detAmmount
    ) {}

    private static List<CsvRow> rowList;

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

    private static void lectorCSV(String filePath, EntityManager em) {
        int cont = 0;
        rowList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while(((line = br.readLine()) != null)) {
                cont++;
                if (cont > 1) {
                    String[] splitline = line.split(",");

                    CsvRow row = csvRowMapper.apply(splitline);
                    rowList.add(row);

                    crearInstancias(row, em);
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void crearInstancias(CsvRow row, EntityManager em) {
        /**
         *      En cada instancia de entidad (Department, Employee, etc.), se realiza una verificación
         *      utilizando el método 'em.find(EntityClass.class, id)' antes de crear un nuevo objeto.
         *      Esto permite determinar si un registro con la misma clave primaria ya existe en la base de datos.
         *
         *      Si el objeto ya existe, la variable contendrá la referencia a ese objeto existente,
         *      evitando la creación de un nuevo objeto con la misma clave primaria. Si el objeto no existe
         *      (es null), entonces se crea una nueva instancia y se asocian las relaciones correspondientes.
         *
         *      Esto evita violaciones de unicidad o restricciones de clave primaria, asegurando
         *      que cada entidad se maneje de forma correcta y consistente sin duplicar registros en la base de datos.
         *
         *      Forma de creación con inconsistencia y errores:
         *          Department department = new Department(row.dptId(), row.dptName());
         *          Employee employee = new Employee(row.empId(), row.empName(), department);
         *          ExpenseSubmission expenseSubmission = new ExpenseSubmission(row.subId(), row.subDate(), employee);
         *          ExpenseSubmissionDetail expenseSubmissionDetail = new ExpenseSubmissionDetail(row.detId(), row.detAmmount(), expenseSubmission);
         *          Expense expense = new Expense(row.expId(), row.expName());
         */

        em.getTransaction().begin();

        Department department = em.find(Department.class, row.dptId());
        if (department == null) {
            department = new Department(row.dptId(), row.dptName());
        }

        Employee employee = em.find(Employee.class, row.empId());
        if(employee == null) {
            employee = new Employee(row.empId(), row.empName(), department);
        }

        ExpenseSubmission expenseSubmission = em.find(ExpenseSubmission.class, row.subId());
        if (expenseSubmission == null) {
            expenseSubmission = new ExpenseSubmission(row.subId(), row.subDate(), employee);
        }

        ExpenseSubmissionDetail expenseSubmissionDetail = em.find(ExpenseSubmissionDetail.class, row.detId());
        if(expenseSubmissionDetail == null) {
            expenseSubmissionDetail = new ExpenseSubmissionDetail(row.detId(), row.detAmmount(), expenseSubmission);
        }

        Expense expense = em.find(Expense.class, row.expId());
        if (expense == null) {
            expense = new Expense(row.expId(), row.expName());
        }

        /**
         *      Relaciones con las intancias creadas: Asegura que no se creen objetos duplicados.
         *      Al vincular las entidades de esta forma, te aseguras de que cada registro esté relacionado correctamente
         *      con las instancias existentes, evitando así problemas de duplicación o inconsistencias en la base de datos.
         */

        department.setEmployees(employee);
        expenseSubmission.setExpsubdet(expenseSubmissionDetail);
        expenseSubmissionDetail.setExpesense(expense);
        expense.setdetalle(expenseSubmissionDetail);

        /**
         *      Pruebas sobre los objetos que se van creando:
         *
         *      System.out.println("------------------------------------------------------");
         *      System.out.println(department);
         *      System.out.println(employee);
         *      System.out.println(expenseSubmission);
         *      System.out.println(expenseSubmissionDetail);
         *      System.out.println(expense);
         */

        try {
            em.persist(department);
            em.persist(employee);
            em.persist(expenseSubmission);
            em.persist(expenseSubmissionDetail);
            em.persist(expense);
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }

        em.getTransaction().commit();
    }

    public static void main(String[] args) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

//        Función para cargar la base a partir del csv (comentada porque ya se cargó)

          lectorCSV(CSV_PATH, em);

//        Requerimientos a implementar:

//        employeeExpenses(null, null);
//        departmentExpenses(null, null);
//        expenseSummary(null, null, "1900-01-01", "2100-12-31");

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
