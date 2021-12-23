package MainController;
import Bank.Bank;
import Bank.LoanApplication;
import Bank.TypesOfLoan;
import Classes.*;
import Utilities.Utilities;
import Utilities.UserInput;
import java.util.*;

public class Controller {
    private final Bank bank;
    private final HashMap<String, User> users;
    private final HashMap<String, User> bankAccounts;
    private final User user;
    private Inbox inbox;

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
            this.inbox = new Inbox();
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

            if (bankAccounts.containsKey(anotherBankAccountNo)) {
                Customer anotherCustomer = (Customer) bankAccounts.get(anotherBankAccountNo);
                ((Customer) user).withdrawMoney(amount);
               anotherCustomer.depositMoney(amount);
            }
            return message = "Transaction successful to " + anotherBankAccountNo;
        }


        public String FiveLatestTransaction () throws Exception {
            String message = "Five latest transaction: " + Utilities.EOL;
            StringBuilder message1 = new StringBuilder();
            if(((Customer) user).getTransactions().size() < 5){
                throw new Exception(" You have less than 5 transaction");
            }
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
        otherService.addOptions(1,"Update Salary");
        otherService.addOptions(2,"Apply for new card.");
        otherService.addOptions(3,"Block payment card.");
        otherService.addOptions(4,"Loan request");
        otherService.addOptions(5,"Loan status"); //? should we?
        otherService.addOptions(6,"Go back to Customer menu.");
     */
        public String updateCustomerName (String newName) throws Exception {
            ((Customer) user).setName(newName);
            return "Customer " + ((Customer) user).getPersonalNo() + "name has successfully update to " + newName;
        }

        public String updateSalary(double salary) {
            ((Customer)user).setSalary(salary);
            return "Salary has been updated to " + salary;
        }

        public String ApplyForCard () {

            return "Card request has been send.";
        }

        public String blockCard () {
            ((Customer) user).deactivateCard();
            return "Payment card has been blocked";
        }

        public TypesOfLoan selectTypeOfLoan(int option){
            TypesOfLoan typesOfLoan = null;
            switch (option) {
                case 1:
                    typesOfLoan = TypesOfLoan.PERSONAL_LOAN;
                    break;
                case 2:
                    typesOfLoan = TypesOfLoan.HOUSE_LOAN;
                    break;
                case 3:
                    typesOfLoan = TypesOfLoan.CAR_LOAN;
                    break;
                case 4:
                    typesOfLoan = TypesOfLoan.UNSECURED_LOAN;
                    break;
                default:
                    System.out.println("Invalid choice. Please select between option 1 to 4. ");
            }
            return typesOfLoan;

        }
    public String loanRequestWithOutCoSigner (double amount, TypesOfLoan typesOfLoan, double time, String otherEquity, double otherEquityvalue, double cashContribution) {

        String message = "Loan Application from : " + user.getFullName() + " with personal nr: " + user.getPersonalNo() + Utilities.EOL;
        String inboxMessage;
        LoanApplication loanApplication = new LoanApplication(user.getPersonalNo(), amount, typesOfLoan, time, otherEquity,otherEquityvalue, cashContribution);
        bank.addLoanApplication(user.getPersonalNo(),loanApplication);
        String loanMessage = loanApplication.toString();
        inboxMessage = message + loanMessage;
        System.out.println(inboxMessage);
        inbox.addLoanApplicationMessage(inboxMessage);

        return "Loan request has been send. The loan application ID is: LA"+user.getPersonalNo();
    }

        public String loanRequestWithCoSigner (double amount, TypesOfLoan typesOfLoan, double time, String otherEquity, double otherEquityvalue, double cashContribution , String coSigner_name, String coSigner_personalNr, double coSigner_salary) {

            String message = "Loan Application from : " + user.getFullName() + " with personal nr: " + user.getPersonalNo() + Utilities.EOL;
            String inboxMessage;
            LoanApplication loanApplication = new LoanApplication(user.getPersonalNo(), amount, typesOfLoan, time, otherEquity,otherEquityvalue, cashContribution,coSigner_name,coSigner_personalNr,coSigner_salary);
            bank.addLoanApplication(user.getPersonalNo(),loanApplication);
            String loanMessage = loanApplication.toString();
            inboxMessage = message + loanMessage;
            System.out.println(inboxMessage);
            inbox.addLoanApplicationMessage(inboxMessage);

            return "Loan request has been send. The loan application ID is: LA"+ user.getPersonalNo();
        }
        // To Dimitrios:
    /*
        - The Employee has two choices to approve the loan or to decline.
        If the loan is aprrove, Create a loan and add it to the Loan Hashmap
        and send a message with the details of the loan and the loan ID
        to the user and insert the loan amount in the user account.

        If the loan is decline, delete the request and send message to the user.
     */


        // INBOX

    //Methods For Customers--------
    public void sendMessageToEmployees(Customer customer){
        String message = UserInput.readLine("Please type the message that you would like to send to the Customer Support: ");
        if (message == null){
            System.out.println("Your message cannot be empty.");
        }else{
          //  messageInbox.add(message);
            System.out.println("Your message has been sent successfully.");
        }
    }

    //QUEUE FOR CUSTOMER LOAN APPLICATIONS-------------- "if" statements need to be applied based on customer budget

    public void sendApplicationForLoan(Customer customer){
        int loanAmount = UserInput.readInt("Please type the amount you would like to borrow from the bank: ");

        String message = "Customer with name: " + customer.getFullName() + " and account balance: "
                + customer.getBalance() + " Would like to apply for a loan of amount: " + loanAmount + ".";
      //  loanApplications.add(message);
        System.out.println("Your loan application has been sent successfully.");
    }

    public void applyForVacation(){
    //    String message = "Employee with name: " + employee.getFullName() + " Would like to apply for vacation.";
     //   vacationApplications.add(message);
        System.out.println("Your vacation application has been sent successfully.");
    }

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

        public String createCustomer (String fullName, String personalNo, double salary ,String password,String cardNr, int cvc, String
        expirationDate,int code) throws Exception {
            String bankAccount = accountNoGenerator();
            Customer customer = new Customer(fullName, personalNo, salary, password, bankAccount, cardNr, cvc, expirationDate, code);
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

             // MANAGER CONTROLLER


    /*2
     manager.addOptions(0,"Show Bank Balace");
*/
        public String getTotalBalance () {
            return "Banks total balance: " + bank.getTotalCustomerBalance();
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

