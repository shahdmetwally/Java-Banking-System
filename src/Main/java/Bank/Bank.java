package Bank;

import Classes.Customer;
import Classes.User;

import java.util.HashMap;
import java.util.Map;

public class Bank {


    private  HashMap<String, User> users; // key is the person Nr
    private  HashMap<String, User> bankAccounts; // the key is the bankaccount
    private  HashMap<String, Loan>  loans;
    private  HashMap<String, LoanApplication> loanApplications;
    private double equity;

    public Bank(){
        this.users = new HashMap<>();
        this.bankAccounts = new HashMap<>();
        this.loans = new HashMap<>();
        this.loanApplications = new HashMap<>();
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

    // we don't allow the original HashMap to increase the encapsulation of the list.
    public HashMap<String, Loan > getLoans(){
        HashMap<String,Loan> loanClone = loans;
        return loanClone;
    }

    public HashMap<String, LoanApplication> getLoanApplications(){
        HashMap<String, LoanApplication> loanApplicationClone = loanApplications;
        return loanApplicationClone;
    }

    public HashMap<String, User> getUsers() {
        HashMap<String,User> usersClone = users;
        return usersClone;
    }

    public void addUser(User user) {
        this.users.put(user.getPersonalNo(), user);
    }
    public void addLoan(Loan loan, String personalNr ){
       String key = "L" + personalNr;
        this.loans.put(key,loan);
    }
    public void addLoanApplication(String personalNr, LoanApplication loanApplication){
        String key = "LA" + personalNr;
        this.loanApplications.put(key,loanApplication);
    }
    public void removeLoan(Loan loan, String personalNr ){
        String key = "L" + personalNr;
        this.loans.remove(key,loan);
    }
    public void removeLoanApplication(String personalNr, LoanApplication loanApplication){
        String key = "LA" + personalNr;
        this.loanApplications.remove(key,loanApplication);
    }
    public String viewLoanApplication(String loanApplicationID){
        return loanApplications.get(loanApplicationID).toString();
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

