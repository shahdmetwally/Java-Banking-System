package Classes;

import java.util.ArrayList;
import java.util.Random;

public class Customer extends User {
    private BankAccount bankAccount;

    public Customer(String fullName, int personalNo, String password)throws Exception{
        super(fullName, personalNo,password);
        //bankAccount = new BankAccount();
    }

    public Customer(){}

    public BankAccount getBankAccount() {
        return bankAccount;
    }

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

    public String transferMoney(double amount, String  anotherBankAccountNo) throws Exception{
        return bankAccount.transferMoney(amount, anotherBankAccountNo);
    }

    public double getBalance() {
        return bankAccount.getBalance();
    }

    public void setBudget(double budget){
        bankAccount.setBudget(budget);
    }

    public ArrayList<Transaction> getTransactions() {
        return bankAccount.getTransactions();
    }


    public String checkBudget(){
        return bankAccount.checkBudget();
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


}
