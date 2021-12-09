package Classes;

import java.util.ArrayList;

public class Customer extends User {
    private BankAccount bankAccount;
    private String accountNo;
    private double balance;

    public Customer(String fullName, String personalNo, String password)throws Exception{
        super(fullName, personalNo,password);
        bankAccount = new BankAccount();
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public String depositMoney(double amount) throws Exception {
      return bankAccount.depositMoney(amount);
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

   /* public boolean equals(Object anotherObject){
        if(this == anotherObject){
            return true;
        }else if(anotherObject == null){
            return false;
        }else if( anotherObject instanceof Customer){
            Customer anotherCustomer = (Customer) anotherObject;
            boolean isSameCustomer = this.getPersonalNo().equals(anotherCustomer.getPersonalNo()) && this.getPassword().equals(anotherCustomer.getPassword());
            return false;
        }else{
            return false;
        }
    }

    */
}
