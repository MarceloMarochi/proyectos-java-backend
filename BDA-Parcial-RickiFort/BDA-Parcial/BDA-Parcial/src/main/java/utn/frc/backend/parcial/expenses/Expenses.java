package utn.frc.backend.parcial.expenses;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import utn.frc.backend.parcial.expenses.domain.entities.*;
import jakarta.persistence.EntityManager;

import java.net.DatagramPacket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Expenses {
    private static final int EMPLOYEE_ID_INDEX = 0, EMPLOYEE_NAME_INDEX = 1;
    private static final int DEPARTMENT_ID_INDEX = 2, DEPARTMENT_NAME_INDEX = 3;
    private static final int EXPENSE_SUBMISSION_ID_INDEX = 4, EXPENSE_SUBMISSION_DATE_INDEX = 5;
    private static final int EXPENSE_ID_INDEX = 7, EXPENSE_NAME_INDEX = 8;
    private static final int DET_ID_INDEX = 6, DET_AMOUNT_INDEX = 9;
    private static final String PERSISTENCE_UNIT_NAME = "h2ExpPU";
    private static final String DATE_FORMAT = "yyyy-MM-dd";

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

    public record Department(Integer id, String name) {}
    public record Employee(Integer id, String name, Department department) {}
    public record ExpenseSubmission(Integer id, Date date, Employee employee, List<ExpenseSubmissionDetail> expenseSubmissionDetails) {}
    public record ExpenseSubmissionDetail(Integer id, Double amount, ExpenseSubmission expenseSubmission, Expense expense) {}
    public record Expense(Integer id, String name) {}


    private static Map<Integer, Department> departmentMap = new HashMap<>();
    private static Map<Integer, Employee> employeeMap = new HashMap<>();
    private static Map<Integer, ExpenseSubmission> expenseSubmissionMap = new HashMap<>();
    private static Map<Integer, ExpenseSubmissionDetail> expenseSubmissionDetailMap = new HashMap<>();
    private static Map<Integer, Expense> expenseMap = new HashMap<>();


    public static void main(String[] args) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        String fPath = Expenses.class.getResource("/expenses/Expenses.csv").getFile();
        loadCollections(fPath);
        em.getTransaction().begin();
        String sqlScriptPath = Expenses.class.getResource("/expenses/Expenses.sql").getFile();
        executeScript(em, sqlScriptPath);
        populateDB(em);
        em.getTransaction().commit();

        List<ExpenseSubmissionDetailEntity> departments = em
                .createQuery("SELECT d FROM ExpenseSubmissionDetailEntity d", ExpenseSubmissionDetailEntity.class)
                .getResultList();
        //departments.forEach(System.out::println);

        //empleados x departamento
        List<Object[]> results = em.createQuery(
                "SELECT e.empname, d.dname FROM EmployeeEntity e JOIN e.department d"
        ).getResultList();
        results.forEach(result ->
                System.out.println("Department: " + result[1] + " - Employee: " + result[0])
        );
        //viaticos por empleados
        List<Object[]> results2 = em.createQuery(
                "SELECT e.empname, SUM(d.amount) FROM ExpenseSubmissionDetailEntity d JOIN d.expenseSubmission es JOIN es.employee e GROUP BY e.empname"
        ).getResultList();

        results2.forEach(result ->
                System.out.println("Employee: " + result[0] + " - Total Amount: " + result[1])
        );
        //viaticos por departamento
        List<Object[]> results3 = em.createQuery(
                "SELECT dept.dname, SUM(d.amount) FROM ExpenseSubmissionDetailEntity d " +
                        "JOIN d.expenseSubmission es JOIN es.employee e JOIN e.department dept " +
                        "GROUP BY dept.did"
        ).getResultList();

        results3.forEach(result ->
                System.out.println("Department: " + result[0] + " - Total Amount: " + result[1])
        );









        em.close();
        emf.close();
    }

    private static void loadCollections(String csvPath) {
        rowList = readLines(csvPath)
                .stream()
                .skip(1)
                .map(line -> csvRowMapper.apply(parseCsvLine(line)))
                .peek(Expenses::updateCollections)
                .toList();
    }

    private static List<String> readLines(String path) {
        try (Stream<String> lines = Files.lines(Paths.get(normalizePath(path)))) {
            return lines.toList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private static void updateCollections(CsvRow row) {
        // 1. Departamento
        Department department = departmentMap.computeIfAbsent(row.dptId(), id -> new Department(id, row.dptName()));
        // 2. Expenses
        Expense expense = expenseMap.computeIfAbsent(row.expId(),id -> new Expense(id, row.expName()));
        // 3. Employee
        Employee employee = employeeMap.computeIfAbsent(row.empId(), id -> new Employee(id, row.empName(), department));
        // 4. ExpenseSubmission
        ExpenseSubmission expenseSubmission = expenseSubmissionMap.computeIfAbsent(row.subId(), id -> new ExpenseSubmission(id,row.subDate(),employee, new ArrayList<>()));
        // 4. ExpenseSubmission
        ExpenseSubmissionDetail expenseDetail = new ExpenseSubmissionDetail(row.detId(),row.detAmmount(),expenseSubmission,expense);
        // 6. Añadir el detalle a la lista en la presentación
        expenseSubmission.expenseSubmissionDetails().add(expenseDetail);
        // 7. Guardar el detalle en el mapa
        expenseSubmissionDetailMap.put(expenseDetail.id(), expenseDetail);

    }

    private static void executeScript(EntityManager em, String sqlScriptPath) throws Exception {
        String script = Files.readString(Paths.get(normalizePath(sqlScriptPath)));
        em.createNativeQuery(script).executeUpdate();
    }
    private static void populateDB(EntityManager em) {
        cleanDB(em);

        departmentMap.values().forEach(department ->
                em.createNativeQuery("INSERT INTO department (did, dname) VALUES (?, ?)")
                        .setParameter(1, department.id())
                        .setParameter(2, department.name())
                        .executeUpdate());

        employeeMap.values().forEach(employee ->
                em.createNativeQuery("INSERT INTO employee (empid, empname, did) VALUES (?, ?, ?)")
                        .setParameter(1, employee.id())
                        .setParameter(2, employee.name())
                        .setParameter(3, employee.department().id())
                        .executeUpdate());

        expenseMap.values().forEach(expense ->
                em.createNativeQuery("INSERT INTO expense (expid, expname) VALUES (?, ?)")
                        .setParameter(1, expense.id())
                        .setParameter(2, expense.name())
                        .executeUpdate());

        expenseSubmissionMap.values().forEach(submission ->
                em.createNativeQuery("INSERT INTO expense_submission (sid, empid, sdate) VALUES (?, ?, ?)")
                        .setParameter(1, submission.id())
                        .setParameter(2, submission.employee().id())
                        .setParameter(3, submission.date())
                        .executeUpdate());

        expenseSubmissionDetailMap.values().forEach(detail ->
                em.createNativeQuery("INSERT INTO expense_submission_details (id, sid, expid, amount) VALUES (?, ?, ?, ?)")
                        .setParameter(1, detail.id())
                        .setParameter(2, detail.expenseSubmission().id())
                        .setParameter(3, detail.expense().id())
                        .setParameter(4, detail.amount())
                        .executeUpdate());
    }
    private static void cleanDB(EntityManager em) {
        List.of("expense_submission_details", "expense_submission", "employee", "expense", "department")
                .forEach(table -> em.createNativeQuery("DELETE FROM " + table).executeUpdate());
    }
    private static String normalizePath(String path) {
        return path.startsWith("/") ? path.substring(1) : path;
    }
    private static String[] parseCsvLine(String line) {
        return line.split("(?<!\\\\),", -1);
    }
}