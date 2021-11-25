package Classes;
import java.util.ArrayList;

public class BankAccount {


    private String accountNo;
    private double balance;
    private ArrayList<Transaction> transactions;
    private boolean active;
    private double loan;
    private double expanse;
    private double budget;
    private ArrayList<String> userInbox; // Don't know if we are using the message function

    public BankAccount(String accountNo, double balance) {
        this.accountNo= accountNo;
        this.balance = 0;
        this.transactions= new ArrayList<>();
        this.userInbox = new ArrayList<>();
        this.active = true;
        this.loan = 0.00;
        this.expanse = 0.00;
        this.budget = 0.00;

    }

  /*  public void createBankAccount(String accountNumber, int balance){
        BankAccount bankAccount = new BankAccount(accountNumber,balance);
    }*/



    public final String EOL = System.lineSeparator();

    public String getAccountNo() {return accountNo;}
    public double getBudget() {return budget;}
    public double getBalance() {return balance;}
    public String getTransaction(){return transactions.toString();}
    public double getLoan() {return loan;}
    public boolean getActive(){return active;}

    public void setActive(boolean active){this.active = active;}
    public void setBudget(double budget) {this.budget = budget;}
    public void setBalance(double balance) {this.balance = balance;}


   public double depositMoney(double amount) throws Exception { // 2.1  Deposit Money
       // if(active) {
            if (amount > 0) {
                this.balance += amount;
                addTransaction(amount);
            } else {
                throw new Exception("You cannot add an amount with a negative value. ");
            }
       // } else {
           // System.out.println("Your account is deactivated.");
       // }
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

    public double transferMoney(double amount, String  anotherBankAccountNo) throws Exception{// 2.3 Transfer Money

            for (Customer customer : Bank.customers) {
                if (customer.getBankAccount().accountNo.equals(anotherBankAccountNo)) {
                    if (amount < this.balance && amount > 0) {
                        withdrawMoney(amount);
                        customer.getBankAccount().depositMoney(amount);
                        expanse+=amount;
                        if( budget>0){
                            checkBudget();
                        }
                }
            }
        }
        return this.balance; //?? */
    }


    public String checkBudget(){
        String message = "";
        if(expanse>budget){
            message= " You have excided your monthly budget. ";

        }
        return message;
    }

/*  For the controller

    public String transactionHistory(BankAccount customer) {
        String message= " Transaction history: " + EOL;
        String message1= "";
        for( Transaction transaction : customer.transactions){
            message1 += transaction.toString()+ EOL;
        }
        return message+ message1;

    }*/
    public void addTransaction(double amount){

        if (active) {

            Transaction transaction1 = new Transaction(amount);
            transactions.add(transaction1);
        }
        else {
            System.out.println("Your account is deactivated.");
        }
    }

 /*    public String contactEmployee(String message){
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
    }*/




}
//2.5 See Transaction History
// 2.7 Deactivating customer account
// 2.7 Contact Employee
//2.8
//2.9
//2.10
//2.11
