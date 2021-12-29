package Classes;

import Request.LoanRequest;
import Inbox.Inbox;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class Customer extends User {
    private DebitCard debitCard;
    private BankAccount bankAccount;
    private double salary;
    private Inbox inbox;

    public Customer(){}

    public Customer(String fullName, String personalNo, double salary, String password, String bankAccount,String cardNr, int cvc, String expirationDate,int code)throws Exception{
        super(fullName, personalNo, password, Role.CUSTOMER);
        this.bankAccount = new BankAccount(bankAccount);
        this.debitCard = new DebitCard(cardNr, cvc, expirationDate, code);
        this.salary = salary;
        this.inbox = new Inbox();
    }
    public void addLoanRequest(LoanRequest loanRequest){
        inbox.addLoanRequest(loanRequest);
    }
    public void removeLoanRequest(LoanRequest loanRequest){
        inbox.removeLoanRequest(loanRequest);
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    @JsonIgnore
    public String getAccountNo() {
        return bankAccount.getAccountNo();
    }

    public String depositMoney(double amount) throws Exception {
        bankAccount.depositMoney(amount);
        return bankAccount.toString();
    }


    public String withdrawMoney(double amount) throws Exception {
        return bankAccount.withdrawMoney(amount);
    }

    @JsonIgnore
    public double getBalance() {
        return bankAccount.getBalance();
    }

    public void setBudget(double budget) throws Exception {
        bankAccount.setBudget(budget);
    }

    @JsonIgnore
    public ArrayList<String> getTransactions() {
        return bankAccount.getTransactions();
    }


    public String checkBudget(){
        return bankAccount.checkBudget();
    }

    public DebitCard getDebitCard() {
        return debitCard;
    }

    public boolean equals(Object anotherObject){
        if(this == anotherObject){
            return true;
        }else if(anotherObject == null){
            return false;
        }else if( anotherObject instanceof Customer){
            Customer anotherCustomer = (Customer) anotherObject;
            boolean isSameCustomer = this.getPersonalNo() == anotherCustomer.getPersonalNo() && this.getPassword().equals(anotherCustomer.getPassword())&& this.getAccountNo().equals(anotherCustomer.getAccountNo());
            return false;
        }else{
            return false;
        }
    }

    // Debitcard methods - below

    public boolean getCardStatus(){
       return debitCard.getStatus();
    }
    public void  deactivateCard(){
        debitCard.setDeactivate();
    }

    public void activeCard(){
        debitCard.setActive();
    }
    public String toString(){
        return "Customer " + getFullName() + " with personal number " + getPersonalNo() + "  Bank account: " + getBankAccount();
    }


    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    //inbox methods.

    public String getAllMessageInbox(){
        return inbox.getAllMessageInbox();
    }
    public void addMessageToEmployee(String message) {
        inbox.addMessageToEmployee(message);
    }
}
