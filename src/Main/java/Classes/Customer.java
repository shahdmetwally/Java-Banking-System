package Classes;

import java.util.ArrayList;
import java.util.Random;

public class Customer extends User {
    private BankAccount bankAccount;

    public Customer(String fullName, String personalNo, String password)throws Exception{
        super(fullName, personalNo,password);
        String accountNo = uniqueAccountNoGenerator();
        bankAccount = new BankAccount(accountNo);
    }

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

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public String getAccountNo() {
        return bankAccount.getAccountNo();
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

    public boolean equals(Object anotherObject){
        if(this == anotherObject){
            return true;
        }else if(anotherObject == null){
            return false;
        }else if( anotherObject instanceof Customer){
            Customer anotherCustomer = (Customer) anotherObject;
            boolean isSameCustomer = this.getPersonalNo().equals(anotherCustomer.getPersonalNo()) && this.getPassword().equals(anotherCustomer.getPassword())&& this.getAccountNo().equals(anotherCustomer.getAccountNo());
            return false;
        }else{
            return false;
        }
    }


}
