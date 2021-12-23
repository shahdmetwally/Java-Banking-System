package MainController;

import Classes.*;
import Utilities.Utilities;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Controller {
    private final Bank bank;
    private final HashMap<String, User> users;
    private final HashMap<String, User> bankAccounts;
    private final User user;

    public Controller(String userName, String password, Bank bank) throws Exception {
            this.bank = new Bank();
            this.users = bank.getUsers();
            this.bankAccounts = bank.getBankAccounts();

            if (users.containsKey(userName)) {
                User user = users.get(userName);
                if (user.isSamePassword(password)) {
                    this.user = user;
                } else {
                    throw new Exception("Wrong password.");
                }
            } else {
                throw new Exception("Username not found. Im in the controller");
            }
        }

        // CUSTOMER CONTROLLER
        //----------------------------------------

        public String viewAccountNo () {
            return "Account Number: " + ((Customer) user).getAccountNo();
        }

        public String viewAccountBalance () {
            return "Your balance is " + Utilities.truncateForPrint(((Customer) user).getBalance());
        }

        public String depositMoney ( double amount) throws Exception {
            return ((Customer) user).depositMoney(amount);
        }

        public String withdrawMoney ( double amount) throws Exception {
            return ((Customer) user).withdrawMoney(amount);
        }

        public String transferMoney ( double amount, String anotherBankAccountNo) throws Exception {
            String message;
            if (((Customer) bankAccounts.values()).getBankAccount().equals(anotherBankAccountNo)) {
                ((Customer) user).withdrawMoney(amount);
                ((Customer) bankAccounts.values()).depositMoney(amount);
            }
            return message = "Transaction successful to " + anotherBankAccountNo;
        }


        public String FiveLatestTransaction () {
            String message = "Five latest transaction: " + Utilities.EOL;
            StringBuilder message1 = new StringBuilder();
            for (int i = ((Customer) user).getTransactions().size() - 6; i < ((Customer) user).getTransactions().size() - 1; i++) {
                message1.append(((Customer) user).getTransactions().get(i).toString()).append(Utilities.EOL);
            }
            return message + message1;
        }

        public String transactionHistory () {
            String message = " Transaction history: " + Utilities.EOL;
            String message1 = "";
            for (Transaction transaction : ((Customer) user).getTransactions()) {
                message1 += transaction.toString() + Utilities.EOL;
            }
            return message + message1;
        }

        public void updateBudget ( double budget){
            ((Customer) user).getBankAccount().setBudget(budget);
        }

    /*
      otherService.addOptions(0," Update name.");
        otherService.addOptions(1,"Apply for card.");
        otherService.addOptions(2,"Block payment card.");
        otherService.addOptions(3, "Deactivate account.");
        otherService.addOptions(4,"Request loan and apply for mortgages.");
        otherService.addOptions(5,"Go back to Customer menu.");
     */
        public String updateCustomerName (String newName) throws Exception {
            ((Customer) user).setName(newName);
            return "Customer " + ((Customer) user).getPersonalNo() + "name has successfully update to " + newName;
        }

        public String ApplyForCard () {

            return "Card request has been send.";
        }

        public String blockCard () {
            ((Customer) user).deactivateCard();
            return "Payment card has been blocked";
        }


        public String loanRequest () {

            return "Loan request has been send.";
        }

        // INBOX


        // ADMINISTRATION CONTROLLER
        //------------------------------------

        // change administration password is already in the menu

        public void createManager (String name, String personalNo, String password,double salary, double bonus) throws
        Exception {
            User manager = new Manager(name, personalNo, password, salary, bonus);
            bank.addUser(manager);
        }


        public String removeManager (String personalNo) throws Exception {
            String removeResult = "";
            Manager manager = getManager(personalNo);
            if (manager == null) {
                throw new Exception("Manager with personal number " + personalNo + "was not registered yet.");
            } else {
                removeResult = "Manager: " + personalNo + " was successfully removed.";
                bank.removeUser(manager);
            }
            return removeResult;
        }


        public void setManagerSalary ( double newSalary, String personalNo){
            getManager(personalNo).setSalary(newSalary);
        }

        public String setManagerPassword (String personalNo, String newPassword){
            getManager(personalNo).setPassword(newPassword);
            return "The password was updated ";
        }
        public String setAdminPassword (String password){
            user.setPassword(password);
            return "Admin password has been updated";
        }

        // EMPLOYEE CONTROLLER
        //--------------------------------------
        public Customer getCustomer (String inputPersonNumber){
            if (users.containsKey(inputPersonNumber)) {
                return (Customer) users.values();
            }
            return null;
        }

        public String viewSalary () {
            return "Salary: " + ((Employee) user).getSalary();
        }

        public String updateCustomerPassword (String personalNo, String newPassword){
            Customer customer = getCustomer(personalNo);
            customer.setPassword(newPassword);
            return "The password was updated.";
        }

        public String removeCustomerAccount (String personalNo) throws Exception {
            String removeResult = "";
            Customer customer = getCustomer(personalNo);
            if (customer == null) {
                throw new Exception("A customer with personal number " + personalNo + " was not registered yet.");
            } else {
                removeResult = "Customer: " + personalNo + " was successfully removed.";
                bank.removeUser(customer);
            }
            return removeResult;
        }


        public String getCustomerInfo (String personalNumer){
            String infoCustomer = "";
            Customer customer = getCustomer(personalNumer);
            return infoCustomer = customer.getBankAccount().getTransaction() + "Loan: " + customer.getBankAccount().getLoan();
        }


        public Employee getEmployee (String inputPersonNumber){
            if (user instanceof Employee) {
                return (Employee) user;
            }
            return null;
        }

        public void takeDaysOff (String personalNo,int amountOfDays){
            Employee employee = getEmployee(personalNo);
            int vacationDays = employee.getVacationDays();
            vacationDays -= amountOfDays;

        }

        public String createCustomer (String fullName, String personalNo, String password,int cardNr, int cvc, String
        expirationDate,int code) throws Exception {
            String bankAccount = accountNoGenerator();
            Customer customer = new Customer(fullName, personalNo, password, bankAccount, cardNr, cvc, expirationDate, code);
            bank.addUser(customer);
            bank.addBankAccount(bankAccount, customer);
            return "Customers details: " + Utilities.EOL +
                    "--------------------" + Utilities.EOL +
                    "Name: " + fullName + Utilities.EOL +
                    "Personal nr: " + personalNo + Utilities.EOL +
                    "Bank number: " + bankAccount + Utilities.EOL +
                    "Card number: " + cardNr + Utilities.EOL +
                    "Expiration date: " + expirationDate + Utilities.EOL;
        }

        public String accountNoGenerator () {
            int clearingNumber = 5051;
            int account = 0;
            String bankAccount;
            do {

                Random accountGenerator = new Random();
                for (int i = 0; i < 11; i++) {
                    account = accountGenerator.nextInt();
                }
                bankAccount = Integer.toString(account);
            } while (bankAccounts.containsKey(bankAccount));

            return clearingNumber + "-" + Math.abs(account);
        }

        public boolean desactive () {  //personNr){
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
        public String getTotalBalance () {
            double balance = 0;
            String message = "Banks total balance: ";
            for (Map.Entry<String, User> entry : users.entrySet()) {
                balance += ((Customer) entry.getValue()).getBalance();
            }
            return message + balance;
        }


    /*
    manager.addOptions(1, "Show total loaned amount"); // maybe we can have total loan in a list.
    */
        public String getTotalLoan () {
            String message = "Total amount of loan giving out: ";
            double totalLoan = 0;

            for (Map.Entry<String, User> entry : users.entrySet()) {
                totalLoan += ((Customer) entry.getValue()).getBankAccount().getLoan();
            }

            return message + totalLoan;
        }



        /*
        manager.addOptions(2,"Create employee");
         */


        public String createEmployee (String fullName, String personalNo, String password,double salary) throws
        Exception {
            User employee = new Employee(fullName, personalNo, password, salary);
            bank.addUser(employee);
            return "Employee " + fullName + " was registered successfully.";
        }

    /*
    manager.addOptions(3, "Remove employee");
    */
        public String removeEmployee (String personalNo) throws Exception {
            String removalResult = "";
            Employee employee = getEmployee(personalNo);
            if (employee == null) {
                throw new Exception("Employee with personal number " + personalNo + " was not registered yet.");
            } else {

                removalResult = "Employee " + personalNo + " was successfully removed.";
                bank.removeUser(employee);
            }

            return removalResult;
        }

    /*
   manager.addOptions(4,"update employee salary");
   */
        public String setEmployeeSalary (String personalNo,double newSalary){
            getEmployee(personalNo).setSalary(newSalary);
            return "The salary was updated. ";
        }


    /*
    manager.addOptions(5,"Update employee password");
    */
        public String setEmployeePassword (String newPassword, String personalNo){
            getEmployee(personalNo).setPassword(newPassword);
            return "The password was updated. ";
        }


        public Manager getManager (String personalNo){
            if (users.containsKey(personalNo)) {
                return ((Manager) user);
            } else {
                return null;
            }
        }

        // show the manager or the admin do this?
        public void promoteEmployee (String personalNo,double newSalary, double bonus) throws Exception {
            Employee employee = getEmployee(personalNo);
            if (employee.getPersonalNo().equals(personalNo)) {
                String name = employee.getFullName();
                String password = employee.getPassword();
                Manager newEmployee = new Manager(name, personalNo, password, newSalary, bonus);
                bank.removeUser(employee);
                bank.addUser(newEmployee);// Example on how to find specific attribute, also need to give it more access
            }
        }

    }

