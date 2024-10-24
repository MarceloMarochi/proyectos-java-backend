package utn.frc.backend.parcial.expenses;

import utn.frc.backend.parcial.expenses.domain.entities.*;

import java.util.Date;

public class PruebasMain {
    public static void main(String[] args) {
        int empid = 12;
        String empname = "jose";
        int dptid = 2;
        String dptname = "IT";
        int expsubid = 123;
        Date expdate = new Date();
        int detid = 31;
        int expid = 23;
        String expname = "Impuestos";
        double detamount = 23213.2;

        Department departament = new Department(dptid, dptname);

        Employee employee = new Employee(empid, empname, departament);

        departament.setEmployees(employee);

        ExpenseSubmission expSub = new ExpenseSubmission(expsubid, expdate, employee);

        ExpenseSubmissionDetail expsubdet = new ExpenseSubmissionDetail(detid, detamount, expSub);

        Expense expense = new Expense(expid, expname);

        expSub.setExpsubdet(expsubdet);
        expsubdet.setExpesense(expense);
        expense.setdetalle(expsubdet);

        System.out.println(departament);
        System.out.println(employee);
        System.out.println(expSub);
        System.out.println(expsubdet);
        System.out.println(expense);
    }
}
