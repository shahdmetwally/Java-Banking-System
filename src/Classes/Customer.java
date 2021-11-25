package Classes;
import java.util.ArrayList;

public class Customer extends Account{


    final int personalNumber;

    double balance = 0.00;

    ArrayList<Transaction> transactions;
    ArrayList<String> userInbox;
    boolean active;
    double loan;
    double expanse;
    double budget;

    public Customer(String fullName, int personalNumber, String userName, String password, double monthlyGrossSalary, double balance) {
        super(fullName,userName,password,monthlyGrossSalary);
        this.personalNumber = personalNumber;
        this.balance = 0;
        this.transactions= new ArrayList<>();
        this.userInbox = new ArrayList<>();
        this.active = true;
        this.loan = 0.00;
        this.expanse = 0.00;
        this.budget = 0.00;
    }

    public double getBudget() {
        return budget;
    }


    public final String EOL = System.lineSeparator();

    public int getPersonalNumber() {
        return personalNumber;
    }



    public double getBalance() {
        return balance;
    }

    public void updateBalance(double balance) {
        this.balance = balance;
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
            if(budget>0){
                checkBudget();
            }

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
                        if( budget>0){
                            checkBudget();
                        }




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
        String message1 = super.getID() + ": " + message;
        userInbox.add(message1);

        return "Your message was sent successfully.";
    }


    public void applyForLoan(double amount) throws Exception{
        if((super.getSalary() * 12) > amount ){
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
    //jsdhfkds

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

//hello
// hi
//helllllo
