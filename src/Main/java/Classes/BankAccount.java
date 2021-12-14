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

    public BankAccount(String accountNo) {
        this.accountNo = accountNo;
        this.balance = 0;
        this.transactions = new ArrayList<>();
        this.userInbox = new ArrayList<>();
        this.active = true;
        this.loan = 0.00;
        this.expanse = 0.00;
        this.budget = 0.00;

    }
    /*public void createBankAccount(String accountNumber, int balance){
        BankAccount bankAccount = new BankAccount(accountNumber,balance);
    }
*/

    public static String accountNoGenerator() {
        int clearingNumber = 5051;
        int account = 0;
        Random accountGenerator = new Random();
        for (int i = 0; i < 11; i++) {
            account = accountGenerator.nextInt();
        }

        return clearingNumber + "-" + Math.abs(account);
    }

    public static String uniqueAccountNoGenerator() {
        String accountNo = accountNoGenerator();
        for (BankAccount bankAccount : Bank.bankAccounts) {
            do {
                if (bankAccount.getAccountNo().equals(accountNo))
                    accountNo = accountNoGenerator();
            } while (!bankAccount.getAccountNo().equals(accountNoGenerator()));

        }
        return accountNo;
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

    public boolean getActive() {
        return active;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public String depositMoney(double amount) throws Exception { // 2.1  Deposit Money
        // if(active) {
        if (amount > 0) {
            this.balance += amount;
            addTransaction(amount);
            return toString();
        } else {
            throw new Exception("You cannot add an amount with a negative value. ");
        }
        // } else {
        // System.out.println("Your account is deactivated.");
        // }

    }


    public String withdrawMoney(double amount) throws Exception { //2.2 Withdraw Money
        if (amount < balance && amount > 0) {
            this.balance -= amount;
            addTransaction(-amount);
            expanse += amount;

            if (budget > 0) {
                checkBudget();
            }
            return toString();

        } else {
            throw new Exception("The withdrawal amount must be less than your account balance.");
        }

    }

    // something is wrong here. I fixed it , check
    public String transferMoney(double amount, String anotherBankAccountNo) throws Exception {// 2.3 Transfer Money
        if (amount < balance && amount > 0) {
            for (User user : Bank.users) {                        // I couldn't put this in the controller
                if (user instanceof Customer) {
                    Customer customer = (Customer) user;

                    if (customer.getAccountNo().equals(anotherBankAccountNo)) {
                        withdrawMoney(amount);
                        customer.depositMoney(amount);
                        expanse += amount;
                        if (budget > 0) {
                            checkBudget();
                        }

                    } else {
                        return "The customer does not exists. ";
                    }
                }
            }
            return toString();
        } else {
            throw new Exception("The withdrawal amount must be less than your account balance.");
        }

    }


    public String checkBudget() {
        String message = "";
        if (expanse > budget) {
            message = " You have exceeded your monthly budget. ";
        }
        return message;
    }


    public void addTransaction(double amount) {
        //if (active) {                                      // Jeniffer - Activation/ Deactivation of account
        Transaction transaction1 = new Transaction(amount);
        transactions.add(transaction1);
    }
    //else {
    //    System.out.println("Your account is deactivated.");
    //}

    public String toString() {
        return "Your balance is " + Utilities.truncateDouble(balance);
    }
}




    /*    public String contactEmployee(String message){      //  Don't know if we are using the message function
        String message1 = super.getID() + ": " + message;
        userInbox.add(message1);

        return "Your message was sent successfully.";
    }


    public void applyForLoan(double amount) throws Exception{
        if((super.getSalary() * 12) > amount ){
            depositMoney(amount);
            loan = amount;
        } else {
            throw new Exception("Loan is not approved");
        }
    }*/







