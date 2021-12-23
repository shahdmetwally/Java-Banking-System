package Classes;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    private  HashMap<String, User> users = new HashMap<String, User>();
    private  HashMap<String, User> bankAccounts = new HashMap<>();
    private HashMap<String, Loan> loans = new HashMap<>();
    private double equity;
    private double loan;

    public Bank(){
        this.equity = 0;
    }

    public double getTotalCustomerBalance(){
        double balance = 0;
        String message = "Banks total balance: ";
        for (Map.Entry<String, User> entry : users.entrySet()) {
            balance += ((Customer) entry.getValue()).getBalance();
        }
        return balance;
    }

    public void showAllUser(){
        users.forEach((personNo, user) -> System.out.println(user.getFullName() + " : " + personNo  + ": " + user.getPassword()));
    }

    public HashMap<String, User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        this.users.put(user.getPersonalNo(), user);
    }

    public void removeUser(User user) {
        this.users.remove(user.getPersonalNo(), user);
    }

    public HashMap<String, User> getBankAccounts() {
        return bankAccounts;
    }

    public void addBankAccount(String bankAccount, Customer customer) {
        this.bankAccounts.put(customer.getAccountNo(), customer);
    }

    public void removeBankAccount(Customer customer) {
        this.bankAccounts.remove(customer.getAccountNo(), customer);
    }

    /*
    the mortgage depends on the size of the loan, the house worth and the income of the household.
 House loan over 70 % of the value of the house worth will mortgage at least 2 % per year.
House loan between 50-70% of the house worth will mortgage 1 % per year. This is apart of the interest of the loan.
at least 15% of the hole loan.
     */

    public double getEquity() {
        return equity;
    }

    public void setEquity(double equity) {
        this.equity = equity;
    }
}

