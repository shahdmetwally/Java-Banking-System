package MainController;

import Classes.*;
import Utilities.Utilities;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class Controller {
    private Bank bank;
    private User customer;
    private User manager;
    private User employee;

    public Controller() {
        bank = new Bank();
    }

    public String showAllManager(){
        String message = "";
        for( User user : bank.getAllUsers()){
            if( user instanceof Manager){
                message += manager.toString() + Utilities.EOL;
            }
        } return message;
    }


// LOGIN AUTHORIZATION CONTROLLER
//-----------------------------------
    public User logIn(String inputPersonNo, String inputPassword) {
        for (User currentPerson : bank.getAllUsers()) { // clone the list for safety // encapsulation
            if (currentPerson.isSamePersonNo(inputPersonNo) && currentPerson.isSamePassword(inputPassword)) {
                return currentPerson;
            }
        }
        return null;
    }

    public Customer logInCustomer(String inputPersonNo, String inputPassword) {
        for (User currentPerson : bank.getAllUsers()) { // clone the list for safety // encapsulation
            if (currentPerson instanceof Customer) {
                if (currentPerson.isSamePersonNo(inputPersonNo) && currentPerson.isSamePassword(inputPassword)) {
                    return (Customer) currentPerson;
                }
            }
        }
        return null;
    }

    public Employee logInEmployee(String inputPersonNumber, String inputPassword) {
        for (User currentPerson : bank.getAllUsers()) {
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
    public boolean accessManagerOption(Employee employee){
        if(employee instanceof Manager){
            return true;
        }
        return false;
    }


    public boolean alreadyExistUser(String inputUsername) {
        boolean repeated = false;
        for (User currentUser : bank.getAllUsers()) {
            if (currentUser.isSamePersonNo(inputUsername)) {
                repeated = true;
            }
        }
        return false;
    }


    // CUSTOMER CONTROLLER
    //----------------------------------------


    public Customer getCustomer(String inputPersonNumber) {
        for (User currentUser : Bank.users) {
            if (currentUser instanceof Customer) {
                if (currentUser.isSamePersonNo(inputPersonNumber)) {
                    return (Customer) currentUser;
                }
            }
        }
        return null;
    }
   public String viewAccountNo(Customer customer){
        return "Account Number: " + customer.getAccountNo();
   }

    public String viewAccountBalance(Customer customer) {
        return "Your balance is " + Utilities.truncateForPrint(customer.getBalance());
    }

    public String depositMoney(Customer customer, double amount) throws Exception {
        return customer.depositMoney(amount);
    }

    public String withdrawMoney(Customer customer, double amount) throws Exception {
        return customer.withdrawMoney(amount);
    }

    public String transferMoney(Customer customer, double amount, String anotherBankAccountNo) throws Exception {
        return customer.transferMoney(amount, anotherBankAccountNo);
    }

    public String FiveLatestTransaction(Customer customer) {
        String message = "Five latest transaction: " + Utilities.EOL;
        StringBuilder message1 = new StringBuilder();
        for (int i = customer.getTransactions().size() - 6 ; i < customer.getTransactions().size()-1 ; i++) {
            message1.append(customer.getTransactions().get(i).toString()).append(Utilities.EOL);
        }
        return message + message1;
    }

    public String transactionHistory(Customer customer) {
        String message = " Transaction history: " + Utilities.EOL;
        String message1 = "";
        for (Transaction transaction : customer.getTransactions()) {
            message1 += transaction.toString() + Utilities.EOL;
        }
        return message + message1;
    }

    public void updateBudget(Customer customer, double budget) {
        customer.getBankAccount().setBudget(budget);
    }

    /*
      otherService.addOptions(0," Update name.");
        otherService.addOptions(1,"Apply for card.");
        otherService.addOptions(2,"Block payment card.");
        otherService.addOptions(3, "Deactivate account.");
        otherService.addOptions(4,"Request loan and apply for mortgages.");
        otherService.addOptions(5,"Go back to Customer menu.");
     */
    public String updateCustomerName(Customer customer, String newName) throws Exception{
        customer.setName(newName);
        return "Customer " + customer.getPersonalNo() + "name has successfully update to " + newName;
    }

    public String ApplyForCard(){

        return "Card request has been send.";
    }

    public String blockCard(){

        return "Payment card has been blocked";
    }

    public String deactivateAccount(){

        return "Account has been deactivated ";
    }

    public String loanRequest(){

        return "Loan request has been send.";
    }




    // For the set budget we can use the update budget in th menu


    // ADMINISTRATION CONTROLLER
    //------------------------------------

    // change administration password is already in the menu

    public void createManager(String name, Integer personalNo, String password, double salary, double bonus) throws Exception {
        User manager = new Manager(name, personalNo, password, salary, bonus);
        Bank.users.add(manager);
    }


    public String removeManager(String personalNo) throws Exception {
        String removeResult = "";
        Manager manager = getManager(personalNo);
        if (manager == null) {
            throw new Exception("Manager with personal number " + personalNo + "was not registered yet.");
        } else {
            removeResult = "Manager: " + personalNo + " was successfully removed.";
            Bank.users.remove(manager);
        }
        return removeResult;
    }


    public void setManagerSalary(double newSalary, String personalNo) {
        getManager(personalNo).setSalary(newSalary);
    }

    public String setManagerPassword(String personalNo,String newPassword) {
        getManager(personalNo).setPassword(newPassword);
        return "The password was updated ";
    }

    // EMPLOYEE CONTROLLER
    //--------------------------------------

    public String viewSalary(Employee employee){
        return "Salary: "+ employee.getSalary();
    }

    public String updateCustomerPassword(String personalNo, String newPassword) {
        Customer customer = getCustomer(personalNo);
        customer.setPassword(newPassword);
        return "the password was updated.";
    }

    public String removeCustomerAccount(String personalNo) throws Exception {
        String removeResult = "";
        Customer customer = getCustomer(personalNo);
        if (customer == null) {
            throw new Exception("A customer with personal number " + personalNo + " was not registered yet.");
        } else {
            removeResult = "Customer: " + personalNo + " was successfully removed.";
            Bank.users.remove(customer);
        }
        return removeResult;
    }


    public String getCustomerInfo(String personalNumer) {
        String infoCustomer = "";
        Customer customer = getCustomer(personalNumer);
        return infoCustomer = customer.getBankAccount().getTransaction() + "Loan: " + customer.getBankAccount().getLoan();
    }


    public Employee getEmployee(String inputPersonNumber) {
        for (User currentPerson : bank.getAllUsers()) {
            if (currentPerson instanceof Employee) {
                if (currentPerson.isSamePersonNo(inputPersonNumber)) {
                    return (Employee) currentPerson;
                }
            }
        }
        return null;
    }

    public void takeDaysOff(String personalNo, int amountOfDays) {
        Employee employee = getEmployee(personalNo);
        int vacationDays = employee.getVacationDays();
        vacationDays -= amountOfDays;

    }

    public String createCustomer(String fullName, Integer personalNo, String password) throws Exception {
        User customer = new Customer(fullName, personalNo, password);
        Bank.users.add(customer);
        return "Customer " + fullName + "with personal number " + personalNo + " has successfully register.";
    }
    public boolean desactive(){  //personNr){
        // get customer
        // booalen desactive = false
        // forward the aget active method from bank to customer
        // customer...... (desactive);
        return false; // I put this just to avoid the error

    }


    // MANAGER CONTROLLER


    /*2
     manager.addOptions(0,"Show Bank Balace");
*/
    public String getTotalBalance() {
        double message = 0;
        String message1 = "Banks total balance: ";
        for (User currentUser : Bank.users) {
            if (currentUser instanceof Customer) {
                Customer customer = (Customer) currentUser;
                message += customer.getBankAccount().getBalance();
            }
        }
        return message1 + message;
    }

    /*
    manager.addOptions(1, "Show total loaned amount");
    */
    public String getTotalLoan() {
        String message = "Total amount of loan giving out: ";
        double totalLoan = 0;
        for (User user : Bank.users) {
            if (user instanceof Customer) {
                Customer customer = (Customer) user;
                totalLoan += customer.getBankAccount().getLoan();
            }
        }
        return message + totalLoan;
    }

        /*
        manager.addOptions(2,"Create employee");
         */


    public String createEmployee(String fullName, Integer personalNo, String password, double salary) throws Exception {
        User employee = new Employee(fullName, personalNo, password, salary);
        Bank.users.add(employee);
        return "Employee " + fullName + " was registered successfully.";
    }

    /*
    manager.addOptions(3, "Remove employee");
    */
    public String removeEmployee(String personalNo) throws Exception {
        String removalResult = "";
        Employee employee = getEmployee(personalNo);
        if (employee == null) {
            throw new Exception("Employee with personal number " + personalNo + " was not registered yet.");
        } else {

            removalResult = "Employee " + personalNo + " was successfully removed.";
            Bank.users.remove(employee);
        }

        return removalResult;
    }

    /*
   manager.addOptions(4,"update employee salary");
   */
    public String setEmployeeSalary(String personalNo,double newSalary) {
        getEmployee(personalNo).setSalary(newSalary);
        return "The salary was updated. ";
    }


    /*
    manager.addOptions(5,"Update employee password");
    */
    public String setEmployeePassword(String newPassword, Integer personalNo) {
        getEmployee(String.valueOf(personalNo)).setPassword(newPassword);
        return "The password was updated. ";
    }


    public Manager getManager(String personalNo) {
        for (User currentUser : Bank.users) {
            if (currentUser instanceof Manager) {
                if (currentUser.isSamePersonNo(personalNo)) {
                    return (Manager) currentUser;
                }

            }
        }
        return null;
    }

// show the manager or the admin do this?
    public void promoteEmployee(Integer personalNo, double newSalary, double bonus) throws Exception {
        Employee employee = getEmployee(String.valueOf(personalNo));
        if (employee.getPersonalNo() == Integer.parseInt(String.valueOf(personalNo))) {
            String name = employee.getFullName();
            String password = employee.getPassword();
            Employee emp1 = new Manager(name, personalNo, password, newSalary, bonus);
            Bank.users.remove(employee);
            Bank.users.add(emp1);// Example on how to find specific attribute, also need to give it more access
        }
    }
}
//(employee.getPersonalNo().equals(personalNo))



    //--------------------------------------
            //OLD CODE below

    /* private BankAccount customer;
    public Controller(BankAccount customer){
        this.customer = customer;
    }
    public void logInCustomer(int personNumber, String password){

    }

     */


    //Employee
/*



    public void takeDaysOff(String ID,int amountOfDays){
         for( Employee employee: Bank.employees){
             if(employee.getID().equals(ID)){
                 //employee.getVacationDays() -= amountOfDays;
             }
         }

    }
    public void approveMortageRequest(String customerID){}
*/



 /*

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
    public double checkBalance() { // 2.0  Check Balance }


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

