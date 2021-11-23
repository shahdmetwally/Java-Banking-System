package Classes;
import java.util.ArrayList;

public class Customer {

    String fullName;
    final int personalNumber;
    String userName;
    double monthlyGrossSalary;
    double balance = 0.00;
    String password;
    ArrayList<Transaction> transactions;

    public Customer(String fullName, int personalNumber, String userName, double monthlyGrossSalary, double balance) {
        this.fullName = fullName;
        this.personalNumber = personalNumber;
        this.userName = userName;
        this.monthlyGrossSalary = monthlyGrossSalary;
        this.balance = balance;
        this.password = null;
        this.transactions= new ArrayList<>();
    }

    public String getFullName() {
        return fullName;
    }

    public String getUserName() {
        return userName;
    }

    public int getPersonalNumber() {
        return personalNumber;
    }

    public double getMonthlyGrossSalary() {
        return monthlyGrossSalary;
    }

    public double getBalance() {
        return balance;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMonthlyGrossSalary(double monthlyGrossSalary) {
        this.monthlyGrossSalary = monthlyGrossSalary;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    // method for creating the userpassword

    public void creatUserpassword(String password) {
        this.password = password;
    }

    public double checkBalance() { // 2.0  Check Balance
        return this.balance;
    }

    //add + infront of the amount
    public double depositMoney(double amount) throws Exception { // 2.1  Deposit Money
        if (amount > 0) {
            this.balance += amount;
            addTransaction(amount);
        } else {
            throw new Exception("You cannot add an amount with a negative value. ");
        }
        return this.balance;
    }

    public double withdrawMoney(double amount) throws Exception { //2.2 Withdraw Money
        if (amount < balance && amount > 0) {
            this.balance -= amount;
            addTransaction(-amount);
        } else {
            throw new Exception("The withdrawal amount must be less than your account balance.");
        }
        return balance;
    }

    public double transferMoney(double amount, int anotherPersonalID) {// 2.3 Transfer Money
        if (amount < balance && amount > 0) {
            for (Customer customer : Bank.customers) {
                if (customer.getPersonalNumber() == anotherPersonalID) {
                    if (amount < this.balance && amount > 0) {
                        double newBalance = customer.getBalance() + amount;
                        this.balance = balance - amount;
                        customer.setBalance(newBalance);

                    }
                }
            }


        }
        return this.balance; //?? */
    }

    public String updateName(String newName) { //2.4 Update personal information
        this.fullName = newName;
        return this.fullName;
    }

    public double updateMonthlySalary(double newSalary) {
        this.monthlyGrossSalary = newSalary;
        return this.monthlyGrossSalary;
    }

    public String updateUserName(String newUserName) {
        this.userName = newUserName;
        return this.userName;
    }

    // public String updateUserPassword(String newPassword)
    public double applyForALoan(double loan) { // 2.6 Apply for a loan
        if ((getMonthlyGrossSalary() * 12) > loan) {
            // System.out.println("Loan approved.");
            this.balance += loan;
        }
        return this.balance;
    }

    public String transactionHistory() {
        return "";

    }
    public void addTransaction(double amount){

        Transaction transaction1= new Transaction(amount);
        transactions.add(transaction1);
    }
}
//2.5 See Transaction History
// 2.7 Deactivating customer account
// 2.7 Contact Employee
//2.8
//2.9
//2.10
//2.11
