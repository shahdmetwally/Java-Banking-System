package Classes;

public class BankAccount {

    private String accountNumber;
    private double balance; // NEVER!!!! initialise an attibute here!

    public BankAccount(String accountNumber){
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    public double depositMoney(double amount) throws Exception { // 2.1  Deposit Money
        if (amount > 0) {
                this.balance += amount;
              //  addTransaction(amount); to controller
        } else {
                throw new Exception("You cannot add an amount with a negative value. ");

       // } else {
         //   System.out.println("Your account is deactivated."); // menu
        }
        return this.balance;
    }


}
