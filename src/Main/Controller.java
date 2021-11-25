package Main;

import Classes.*;

import Classes.BankAccount;


import Classes.Employee;
import Classes.Manager;



public class Controller {

    private BankAccount customer;
    public Controller(BankAccount customer){
        this.customer = customer;
    }
    public void logInCustomer(int personNumber, String password){

    }



    //Employee

     public void createEmployee(String empID, int personalNo, String password, String empName, int birthYear, double grossSalary){
        Employee emp1 = new Employee(empID, personalNo, password, empName, birthYear, grossSalary);
    }

    public void createManager(String emName, String emID, String password, int birthYear, double grossSalary){
        Manager manager= new Manager(emName,emID,password,birthYear,grossSalary);

    }

    public void takeDaysOff(String ID,int amountOfDays){
         for( Employee employee: Bank.employees){
             if(employee.getID().equals(ID)){
                 //employee.getVacationDays() -= amountOfDays;
             }
         }

    }
    public void approveMortageRequest(String customerID){}

    public void removeEmployee(String emID){
        for(int i = 0; i < Bank.employees.size(); i++){
            if(Bank.employees.get(i).equals(emID)){
                Bank.employees.remove(i);
            }
        }
    }

    public void promoteEmployee(String emID, double newSalary){
        for(Employee employee : Bank.employees){
            if(employee.getID().equals(emID)){

                String name= employee.getFullName();
                String ID = employee.getID();
                String password = employee.getPassword();
                int birthYear= employee.getBirthYear();
                int vaccationDays = employee.getVacationDays();
                Employee emp1 = new Manager(name,ID,password,birthYear,newSalary);
                Bank.employees.remove(employee);
                Bank.employees.add(emp1);// Example on how to find specific attribute, also need to give it more access

            }
        }
    }

   /* public void removeCustomerAccount(int personalNumber){
        for(int i = 0; i < Bank.customers.size(); i++){
            if(Bank.customers.get(i).getPersonalNumber()==personalNumber){
                Bank.customers.remove(i);
            }
        }
    }

    public void sendMessageToCustomer(String customerID){} //another argument

    public String getCustomerInfo(String userName, int personalNumer){
        String infoCustomer = "";
        for(BankAccount customer : Bank.customers){
            if(customer.getFullName().equals(userName)&& (customer.getPersonalNumber() == personalNumer)) {
                infoCustomer =  customer.getTransaction() + "Loan: "+ customer.getLoan();

            }
        }
        return infoCustomer;
    }

    public void updateEmployeName(String emID, String newName){}

    public void updateEmployeSalary(String emID, double newGrossSalary){}*/










    //Customer. Should I add all the set and get also for customer?

    public final String EOL = System.lineSeparator();

   /* public void createBankAccount(String fullName, int personalNumber, String userName, String password, double monthlyGrossSalary, double balance){
        BankAccount customer = new BankAccount(fullName, personalNumber, userName,password,monthlyGrossSalary,balance);
    }*/

    //public void createBankAccount(String name, int birthYear, String customerID, String userName, String userPassword){}

    public void createBankAccount(){
        String clearNumber= "5051";
        // how many number a bank account has.
        // check for ramdom number functions in java. 8
        // as an example:
        String bankNumber = "12345678";
        // we need a list for all the bankaccounts.
        // option: if we use map for the list. key: personNumber, bankaccount number
        /* for( BankAcoount bankacount: banckAccountList){
           do{ if( bankaccount.equals(banksAccunt){
           // create a 8 new ramdon numbers
           while(! bankAccount.equals(BankAccount))
         */

    }

   /* public double checkBalance() { // 2.0  Check Balance

    }
    //public double checkBalance(){return 0;}

    public double depositMoney(String ID, double amount) throws Exception {
        double balance = 0;
        for(BankAccount customer: Bank.customers) {
            if (customer.getID().equals(ID)) {
                if (customer.getActive()) {
                    if (amount > 0) {
                        balance+= amount;
                        customer.updateBalance(balance);
                        customer.addTransaction(amount);
                    } else {
                        throw new Exception("You cannot add an amount with a negative value. ");
                    }
                } else {
                    System.out.println("Your account is deactivated.");
                }
            }
        }return balance ;

    }

    public String transactionHistory(BankAccount customer) {
        String message= " Transaction history: " + EOL;
        String message1= "";
        for( Transaction transaction : customer.transactions){
            message1 += transaction.toString()+ EOL;
        }
        return message+ message1;

    }




    public void withdrawMoney(double amount) {
    }

    public void transferMoney(double amount) {
    }

    public void updateName(String newName){
    }

    public void updateSalary(double salary){}

    public void updatePassword(String password){}

    public void updateUserName(String newUserName){}

    public String transactionHistory(){
        return "";
    }

    public void applyForLoan(double amountToLoan){}

    public void textEmployee(String textMessage){}

    public void controlExpenses(){}


    //Need to look thorugh this again, I will work more on this as more methods are created.



 //ysahuduasid*/


}
