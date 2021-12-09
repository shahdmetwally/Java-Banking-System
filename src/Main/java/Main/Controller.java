package Main;

import Classes.*;
import Utilities.Utilities;

public class Controller {
    private Bank bank;

    public Controller() {
        bank = new Bank();
    }
// LOGIN AUTHORIZATION CONTROLLER
//-----------------------------------hi


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


    // What to do for the deposit, withdraw and transfer money ? Idk how to put the code in the controller


    public String viewAccountBalance(Customer customer) {
        customer.getBankAccount();
        return "Your balance is " + customer.getBalance();
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
        String message1 = "";
        for (int i = customer.getTransactions().size() - 6; i > customer.getTransactions().size() - 6; i++) {
            customer.getTransactions().get(i);
            message1 = customer.getTransactions().get(i).toString() + Utilities.EOL;
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


    // For the set budget we can use the update budget in th menu


    // ADMINISTRATION CONTROLLER
    //------------------------------------

    /*
      administration.addOptions(0, "Change administration password.");
     */
    public void changePassword(AdministrationOptions administrationOptions, String newPassword) throws Exception {
        administrationOptions.setPassword(newPassword);
    }
        /*
        administration.addOptions(1,"Create manager.");
        */
    public void createManager(String name, String personalNo, String password, double salary, double bonus) throws Exception {
        User manager = new Manager(name, personalNo, password, salary, bonus);
        Bank.users.add(manager);
    }

    /*
    administration.addOptions(2, "Remove manager."); /
    */
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


    /*
    administration.addOptions(3,"Update manager salary.");
    */
    public void setManagerSalary(double newSalary, String personalNo) {
        getManager(personalNo).setSalary(newSalary);
    }

    /*
   administration.addOptions(4,"Update manager password.");
   */
    public void setManagerPassword(String newPassword, String personalNo) {
        getManager(personalNo).setPassword(newPassword);
    }

        /*
        administration.addOptions(5,"Go back to main menu.");
        */


    // EMPLOYEE CONTROLLER
    //--------------------------------------

    public String applyForVacation(Employee employee, int amountDays){
    employee.setAmountOfDays(amountDays);
    return "Your vacation was applied successfully";
    }

    public void takeDaysOff(String ID,int amountOfDays){
        for( Employee employee: Bank.employees){
            if(employee.getID().equals(ID)){
                //employee.getVacationDays() -= amountOfDays;
            }
        }

    }

    public String viewSalary(Employee employee){
        return "Salary: "+ employee.getSalary();
    }

    public void updateCustomerPassword(String personalNo, String newPassword){
        Customer customer = getCustomer(personalNo);
        customer.setPassword(newPassword);
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
        for (User currentPerson : bank.getUsers()) {
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


    // MANAGER CONTROLLER


    public void createCustomer(String fullName, String personalNo, String password) throws Exception {
        User customer = new Customer(fullName, personalNo, password);
        Bank.users.add(customer);
    }

    /*
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


    public String createEmployee(String fullName, String personalNo, String password, double salary) throws Exception {
        User employee = new Employee(fullName, personalNo, password, salary) ;
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
    public void setEmployeeSalary(double newSalary, String personalNo) {
        getEmployee(personalNo).setSalary(newSalary);
    }


    /*
    manager.addOptions(5,"Update employee password");
    */
    public void setEmployeePassword(String newPassword, String personalNo) {
        getEmployee(personalNo).setPassword(newPassword);
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


    public void promoteEmployee(String personalNo, double newSalary, double bonus) throws Exception {
        Employee employee = getEmployee(personalNo);
        if (employee.getPersonalNo().equals(personalNo)) {
            String name = employee.getFullName();
            String password = employee.getPassword();
            Employee emp1 = new Manager(name, personalNo, password, newSalary, bonus);
            Bank.users.remove(employee);
            Bank.users.add(emp1);// Example on how to find specific attribute, also need to give it more access

        }
    }
}



    //--------------------------------------









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
*/



 /*
   public void removeCustomerAccount(int personalNumber){
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