package Main;

import Classes.*;

public class Controller {
    private Bank bank;

    public Controller() {
        bank = new Bank();
    }
// LOGIN AUTHORIZATION CONTROLLER
//-----------------------------------

    public Customer logInCustomer(String inputPersonNo, String inputPassword) {
        for (User currentPerson : bank.getUsers()) { // clone the list for safety // encapsulation
            if (currentPerson instanceof Customer) {
                if (currentPerson.isSamePersonNo(inputPersonNo) && currentPerson.isSamePassword(inputPassword)) {
                    return (Customer) currentPerson;
                }
            }
        }
        return null;
    }

    public Employee logInEmployee(String inputPersonNumber, String inputPassword) {
        for (User currentPerson : bank.getUsers()) {
            if (currentPerson instanceof Employee) {
                if (currentPerson.isSamePersonNo(inputPersonNumber) && currentPerson.isSamePassword(inputPassword)) {
                    return (Employee) currentPerson;
                }
            } else if (currentPerson instanceof Manager) {
                return (Manager) currentPerson;
            }
        }
        return null;
    }


    public boolean alreadyExistUser(String inputUsername) {
        boolean repeated = false;
        for (User currentUser : bank.getUsers()) {
            if (currentUser.isSamePersonNo(inputUsername)) {
                repeated = true;
            }
        }
        return false;
    }


    // CUSTOMER CONTROLLER
    //----------------------------------------


    // ADMINISTRATION CONTROLLER
    //------------------------------------

    /*
      administration.addOptions(0, "Change administration password.");
        administration.addOptions(1,"Create manager.");
        administration.addOptions(2, "Remove manager.");
        administration.addOptions(3,"Update manager salary.");
        administration.addOptions(4,"Update manager password.");
        administration.addOptions(5,"Go back to main menu.");
     */


    // EMPLOYEE CONTROLLER
    //--------------------------------------





    // MANAGER CONTROLLER

    /*
     manager.addOptions(0,"Show Bank Balace");
        manager.addOptions(1, "Show total loaned amount");
        manager.addOptions(2,"Create employee");
        manager.addOptions(3, "Remove employee");
        manager.addOptions(4,"update employee salary");
        manager.addOptions(5,"Update employee password");
     */

    public String createEmployee(String fullName, String personalNo, String password, double salary) throws Exception {
        Employee employee = new Employee(fullName, personalNo, password, salary) {
        };
        Bank.users.add(employee);
        return "Employee " + fullName + " was registered successfully.";
    }
    public Employee getEmployee(String inputPersonNumber) {
        for (User currentPerson : bank.getUsers()) {
            if (currentPerson instanceof Employee) {
                if (currentPerson.isSamePersonNo(inputPersonNumber)) {
                    return (Employee) currentPerson;
                }
            }
        }
        return null;
    }

    public String removeEmployee(String personalNo)throws Exception{
        String removalResult = "";
        Employee employee = getEmployee(personalNo);
        if (employee == null) {
            throw new Exception("Employee with personal number " + personalNo + " was not registered yet.");
        } else {

            removalResult = "Employee " + personalNo + " was successfully removed.";
        }

        return removalResult;
    }
    //--------------------------------------



}





    /* private BankAccount customer;
    public Controller(BankAccount customer){
        this.customer = customer;
    }
    public void logInCustomer(int personNumber, String password){

    }

     */


    //Employee
/*


    public void createManager(String emName,int personalNo, String emID, String password, int birthYear, double grossSalary){
        Manager manager= new Manager(emName,personalNo, emID,password,birthYear,grossSalary);

    }

    public void takeDaysOff(String ID,int amountOfDays){
         for( Employee employee: Bank.employees){
             if(employee.getID().equals(ID)){
                 //employee.getVacationDays() -= amountOfDays;
             }
         }

    }
    public void approveMortageRequest(String customerID){}



    public void promoteEmployee(String emID, double newSalary){
        for(Employee employee : Bank.employees){
            if(employee.getID().equals(emID)){

                String name= employee.getFullName();
                String ID = employee.getID();
                String password = employee.getPassword();
                int birthYear= employee.getBirthYear();
                int personalNo= employee.getPersonalNo();
                int vaccationDays = employee.getVacationDays();
                Employee emp1 = new Manager(name,personalNo,ID,password,birthYear,newSalary);
                Bank.employees.remove(employee);
                Bank.employees.add(emp1);// Example on how to find specific attribute, also need to give it more access

            }
        }
    }

 */

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

  //  public final String EOL = System.lineSeparator();

   /* public void createBankAccount(String fullName, int personalNumber, String userName, String password, double monthlyGrossSalary, double balance){
        BankAccount customer = new BankAccount(fullName, personalNumber, userName,password,monthlyGrossSalary,balance);
    }*/

    //public void createBankAccount(String name, int birthYear, String customerID, String userName, String userPassword){}

  /*  public void createBankAccount(){
        String clearNumber= "5051";
        // check for ramdom number functions in java. 8
        // as an example:
        String bankNumber = "1234567890"; // 10 numbers
        // we need a list for all the bankaccounts.
        // option: if we use map for the list. key: personNumber, bankaccount number
        /* for( BankAcoount bankacount: banckAccountList){
           do{ if( bankaccount.equals(banksAccunt){
           // create a 8 new ramdon numbers
           while(! bankAccount.equals(BankAccount))
    }

   */

/*
    public double checkBalance() { // 2.0  Check Balance

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


    Need to look through this again, I will work more on this as more methods are created.

    */