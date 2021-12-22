package Classes;
import Utilities.Utilities;

import java.util.ArrayList;
import java.util.Random;

public class BankAccount {


    private String accountNo;
    private double balance;
    private ArrayList<Transaction> transactions;
    private boolean active;
    private double loan;
    private double expanse;
    private double budget;
    private ArrayList<String> userInbox; // Don't know if we are using the message function
    private String date;

    public BankAccount(){

    }
    public BankAccount(String bankAccountNo) {
        this.accountNo = bankAccountNo;
        this.balance = 0;
        this.transactions = new ArrayList<>();
        this.userInbox = new ArrayList<>();// maybe not here
        this.active = true;
        this.loan = 0.00;
        this.expanse = 0.00;
        this.budget = 0.00;
        this.date = "";

    }


    public String getAccountNo() {
        return accountNo;
    }

    public double getBudget() {
        return budget;
    }

    public double getBalance() {
        return balance;
    }

    public String getTransaction() {
        return transactions.toString();
    }

    public double getLoan() {
        return loan;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setActive(boolean active) {
        this.active = true;
    }

    public void setDeactivate(boolean deactivate) {
        this.active = false;
    }

    public void setBudget(double budget) {
        date = Utilities.date();
        this.budget = budget;
    }

    public void addTransaction(double amount) {
        //if (active) {                                      // Jeniffer - Activation/ Deactivation of account
        Transaction transaction1 = new Transaction(amount);
        transactions.add(transaction1);
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void budgetMonth() {
        String setYearMonth = date.substring(0, 5);

        String currentYearMonth = Utilities.date().substring(0, 5);

        if (!setYearMonth.equals(currentYearMonth)) {
            date = Utilities.date();
            expanse = 0;

        }
    }

    public String checkBudget() {
        String message = "";
        if (expanse > budget) {
            message = "You have exceeded your monthly budget. ";
        }
        return message;
    }
    //else {
    //    System.out.println("Your account is deactivated.");
    //}

    public String toString() {
        return "Your balance is " + Utilities.truncateDouble(balance);
    }

    public String depositMoney(double amount) throws Exception { // 2.1  Deposit Money

        if (active == false) {
            throw new Exception("The account is deactivated");
        } else if (amount <= 0) {
            throw new Exception("You cannot add an amount with a negative value. ");
        } else {
            this.balance += amount;
            addTransaction(amount);
        }
        return toString();
    }


    public String withdrawMoney(double amount) throws Exception { //2.2 Withdraw Money
        if (amount < balance && amount > 0 && active) {
            this.balance -= amount;
            addTransaction(-amount);

            if (budget > 0) {
                budgetMonth();
            }
            expanse += amount;

            if (budget > 0) {
                return checkBudget() + Utilities.EOL + this;
            }
            return toString();
        } else {
            throw new Exception("The withdrawal amount must be less than your account balance.");
        }

    }
}