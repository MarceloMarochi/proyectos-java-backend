package utn.frc.backend.parcial.expenses;

import utn.frc.backend.parcial.expenses.domain.entities.*;

import java.util.Date;

public class Pruebas {
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


        Departament departamet = new Departament(dptid, dptname);

        Employee employee = new Employee(empid, empname, departamet);
        departamet.setEmployees( employee);

        ExpenseSubmission expSub = new ExpenseSubmission(expsubid, expdate, employee);


        ExpensesSubmissionDetail expSubDet = new ExpensesSubmissionDetail(detid, detamount);
        expSub.setExpSubDet(expSubDet);
        expSubDet.setExpSub(expSub);

        Expense expense = new Expense(expid, expname);
        expSubDet.setExpense(expense);
        expense.setExpSubDet(expSubDet);

        System.out.println(departamet.toString());
        System.out.println(employee.toString());
        System.out.println(expSub.toString());
        System.out.println(expSubDet.toString());
        System.out.println(expense.toString());



    }


}
