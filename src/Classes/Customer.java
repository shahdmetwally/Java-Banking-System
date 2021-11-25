package Classes;

public class Customer extends Person{
    private BankAccount bankAccount;

    public Customer(String fullName, int personalNo, String ID, String password, double salary){
        super(fullName, personalNo, ID, password, salary);
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public double depositMoney(double amount) throws Exception {
      return bankAccount.depositMoney(amount);
    }

    public double withdrawMoney(double amount) throws Exception {
        return bankAccount.withdrawMoney(amount);
    }

    public double transferMoney(double amount, String  anotherBankAccountNo) throws Exception{
        return bankAccount.transferMoney(amount, anotherBankAccountNo);
    }

    public String checkBudget(){
        return bankAccount.checkBudget();
    }
}
