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
    ArrayList<String> userInbox;
    boolean active;
    double loan;
    double expanse;
    double budget;

    public double getBudget() {
        return budget;
    }

    public Customer(String fullName, int personalNumber, String userName, double monthlyGrossSalary, double balance) {
        this.fullName = fullName;
        this.personalNumber = personalNumber;
        this.userName = userName;
        this.monthlyGrossSalary = monthlyGrossSalary;
        this.balance = 0;
        this.password = null;
        this.transactions= new ArrayList<>();
        this.userInbox = new ArrayList<>();
        this.active = true;
        this.loan = 0.00;
        this.expanse = 0.00;
        this.budget = 0.00;
    }

    public final String EOL = System.lineSeparator();

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

    public void updateFullName(String fullName) {
        this.fullName = fullName;
    }

    public void updateUserName(String userName) {
        this.userName = userName;
    }

    public void updateMonthlyGrossSalary(double monthlyGrossSalary) {
        this.monthlyGrossSalary = monthlyGrossSalary;
    }

    public void updateBalance(double balance) {
        this.balance = balance;
    }


    public void updatePassword(String password) {
        this.password = password;
    }

     public String getTransaction(){
            return transactions.toString();
        }

    public double getLoan() {
        return loan;
    }

    public boolean getActive(){
        return active;
    }

    public void setActive(boolean active){
        this.active = active;
    }



    // method for creating the userpassword

    public void createUserPassword(String password) {
        this.password = password;
    }



    public double checkBalance() { // 2.0  Check Balance
        return this.balance;
    }

    public double depositMoney(double amount) throws Exception { // 2.1  Deposit Money
        if(active) {
            if (amount > 0) {
                this.balance += amount;
                addTransaction(amount);
            } else {
                throw new Exception("You cannot add an amount with a negative value. ");
            }
        } else {
            System.out.println("Your account is deactivated.");
        }
        return this.balance;
    }




    public double withdrawMoney(double amount) throws Exception { //2.2 Withdraw Money
        if (amount < balance && amount > 0) {
            this.balance -= amount;
            addTransaction(-amount);
            expanse+=amount;
            checkBudget();
        } else {
            throw new Exception("The withdrawal amount must be less than your account balance.");
        }
        return balance;
    }

    public double transferMoney(double amount, int anotherPersonalID) throws Exception{// 2.3 Transfer Money

            for (Customer customer : Bank.customers) {
                if (customer.getPersonalNumber() == anotherPersonalID) {
                    if (amount < this.balance && amount > 0) {
                        withdrawMoney(amount);
                        customer.depositMoney(amount);
                        expanse+=amount;
                        checkBudget();



                }
            }


        }
        return this.balance; //?? */
    }



    public String transactionHistory(Customer customer) {
        String message= " Transaction history: " + EOL;
        String message1= "";
        for( Transaction transaction : customer.transactions){
            message1 += transaction.toString()+ EOL;
        }
        return message+ message1;

    }
    public void addTransaction(double amount){

        if (active) {

            Transaction transaction1 = new Transaction(amount);
            transactions.add(transaction1);
        }
        else {
            System.out.println("Your account is deactivated.");
        }
    }

    public String contactEmployee(String message){
        String message1 = userName + ": " + message;
        userInbox.add(message1);

        return "Your message was sent successfully.";
    }


    public void applyForLoan(double amount) throws Exception{
        if((monthlyGrossSalary * 12) > amount ){
            depositMoney(amount);
            loan = amount;
        } else {
            throw new Exception("Loan is not approved");
        }
    }


    public String checkBudget(){
        String message = "";
        if(expanse>budget){
            message= " You have excided your monthly budget. ";

        }
        return message;
    }


    //that

    //hfpasocdj

    // Apply for card discuss

    // To delete account contact employee

}
//2.5 See Transaction History
// 2.7 Deactivating customer account
// 2.7 Contact Employee
//2.8
//2.9
//2.10
//2.11
