package Classes;
import Classes.Transaction;
import Loans.Loan;
import Utilities.Utilities;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class BankAccount {


    private String accountNo;
    private double balance;
    private ArrayList<String> transactions;
    private boolean active;
    private Loan loan;
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
        this.loan = null;
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

    public double getExpanse() {
        return expanse;
    }

    public ArrayList<String> getUserInbox() {
        return userInbox;
    }

    @JsonIgnore
    public String getTransaction() {
        return transactions.toString();
    }

    public Loan getLoan() {
        return loan;
    }

    public Loan setLoan(Loan loan){
        return this.loan = loan;
    }

    public ArrayList<String> getTransactions() {
        return transactions;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setDeactivate(boolean deactivate) {
        this.active = false;
    }

    public void setBudget(double budget) throws Exception {
        if(budget<0){
            throw new Exception("Budget cannot be negative.");
        }
        date = Utilities.date();
        this.budget = budget;
    }

    public void addTransaction(double amount) {
        //if (active) {                                      // Jeniffer - Activation/ Deactivation of account
        Transaction transaction = new Transaction(amount);
        transactions.add(transaction.toString());
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
        return "Your balance is " + Utilities.truncateForPrint(balance);
    }

    public String depositMoney(double amount) throws Exception { // 2.1  Deposit Money

        if (!active) {
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
        }
        else if (amount<0){
            throw new Exception("The withdrawal amount cannot be less than zero.");

            }else {
            throw new Exception("The withdrawal amount must be less than your account balance.");
        }

    }
}