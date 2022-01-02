package MainController;
import Bank.Bank;
import Inbox.*;
import Loans.*;
import Request.*;
import Classes.*;
import Utilities.Utilities;

import java.util.*;

public class Controller {
    private final Bank bank;
    private final HashMap<String, User> users;
    private final HashMap<String, User> bankAccounts;
    private final User user;
    private EmployeeInbox employeeInbox;
    private ManagerInbox managerInbox;


    public Controller(String userName, String password, Bank bank) throws Exception {
        this.bank = bank;
        this.users = bank.getUsers();

        if (users.containsKey(userName)) {
            User user = users.get(userName);
            if (user.isSamePassword(password)) {
                this.user = user;
            } else {
                throw new Exception("Wrong password.");
            }
        } else {
            throw new Exception("Username not found.");
        }
        this.bankAccounts = bank.getBankAccounts();
        this.employeeInbox = bank.getEmployeeInbox();
        this.managerInbox = bank.getManagerInbox();

    }
    // CUSTOMER CONTROLLER
    //----------------------------------------
    public User getUser(){
        return user;
    }

    public String viewAccountNo () {
        return "Account Number: " + ((Customer) user).getAccountNo();
    }

    public String viewAccountBalance () {
        return "Your balance is " + Utilities.truncateForPrint(((Customer) user).getBalance());
    }

    public String depositMoney (double amount) throws Exception {
        return ((Customer) user).depositMoney(amount);
    }

    public String withdrawMoney (double amount) throws Exception {
        return ((Customer) user).withdrawMoney(amount);
    }

    public String transferMoney (double amount, String anotherBankAccountNo) throws Exception {
        String message="";

        if (bankAccounts.containsKey(anotherBankAccountNo)) {
            Customer anotherCustomer = (Customer) bankAccounts.get(anotherBankAccountNo);
            if(((Customer) user).getAccountNo().equals(anotherCustomer.getAccountNo())){
                message = "You cannot make a transfer to your own account.";
            } else {
                if(amount < ((Customer) user).getBalance() && amount > 0){
                    ((Customer) user).withdrawMoney(amount);
                    anotherCustomer.depositMoney(amount);
                } else if(amount<0){
                    throw new Exception("Transfer amount cannot be negative.");
                } else if(amount>((Customer) user).getBalance()){
                    throw new Exception("Transfer amount cannot exceed your account balance.");
                }


                message = "Transaction successful to " + anotherBankAccountNo;
            }
        }
        return message;
    }


    public String FiveLatestTransaction () throws Exception {
        String message = "Five latest transaction: " + Utilities.EOL;
        StringBuilder message1 = new StringBuilder();
        if(((Customer) user).getTransactions().size() < 5){
            throw new Exception(" You have less than 5 transaction");
        }
        for (int i = 0; i < ((Customer) user).getTransactions().size(); i++) {
            message1.append(((Customer) user).getTransactions().get(i)).append(Utilities.EOL);
        }
        return message + message1;
    }

    public String transactionHistory(){
        String message = " Transaction history: " + Utilities.EOL;
        String message1 = "";
        for (int i=0; i< ((Customer) user).getTransactions().size();i++) {
            message1 += ((Customer) user).getTransactions().get(i) + Utilities.EOL;
        }
        return message + message1;
    }

    public void updateBudget ( double budget) throws Exception {
        ((Customer) user).getBankAccount().setBudget(budget);
    }

    public String toString(){
        return getUser().toString();
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
        return "Customer " + ((Customer) user).getPersonalNo() + " name has successfully update to " + newName;
    }

    public String updateSalary(double salary) {
        ((Customer)user).setSalary(salary);
        return "Salary has been updated to " + Utilities.truncateForPrint(salary);
    }

    public String applyForCard () {
        // If the card status is true, that means that the card is still active.
        if(((Customer)user).getStatus()){
            return "This card has not been blocked.";
        }else{
            CardRequest cardRequest = new CardRequest(user);
            bank.addCardRequest(user.getPersonalNo(),cardRequest);
            StartProgram.jsonCardRequests.add(cardRequest);
            employeeInbox.addCardRequest(cardRequest);
            // should we add this to the customer as well? so they can see the sended request? // Shahd
            return "Card request with ID "+ cardRequest.getId()+ " has been sent. ";
        }
    }

    public String blockCard () {
        // This method changes the status of the debit card to false, which means is not active
        ((Customer) user).deactivateCard();
        return "Payment card has been blocked";
    }

    //Update loanRequest
    /*
     updateLoanRequest.addOptions(0,"Update amount");
        updateLoanRequest.addOptions(1,"Update type of loan");// DONE
        updateLoanRequest.addOptions(2,"Update the time period of the loan"); //done
        updateLoanRequest.addOptions(3,"Update other equities"); // TO DO
        updateLoanRequest.addOptions(4,"Update cash contribution"); //done
        updateLoanRequest.addOptions(5,"Update Co-Signer name"); //done
        updateLoanRequest.addOptions(6,"Update Co-Signer personal number"); //done
        updateLoanRequest.addOptions(7,"Update Co-Signers salary"); //done
          updateLoanRequest.addOptions(8,"Update Interest type"); //done
     */


    public String updateTypeOfLoan(String loanRequestID, TypesOfLoan typesOfLoan) {
        if(bank.getLoanRequests().containsKey(loanRequestID)) {
            LoanRequest loanRequest =  bank.getSpecificLoanRequest(loanRequestID);
            loanRequest.setTypesOfLoan(typesOfLoan); // here is the setter.
            return "Type of loan has been updated.";
        }else {
            return "The loan request was not found.";
        }
    }

    public String updateEquities(String loanRequestID, String otherEquity,double equityNewValue){
        if(bank.getLoanRequests().containsKey(loanRequestID)) {
            LoanRequest loanRequest =  bank.getSpecificLoanRequest(loanRequestID);
            for(Map.Entry<String,Double> equity : loanRequest.getEquities().entrySet())
                if(equity.getKey().equals(otherEquity)){
                    loanRequest.getEquities().remove(otherEquity);
                    loanRequest.getEquities().put(otherEquity,equityNewValue);
                    return loanRequest.printEquities();
                }else{
                    return "This equity doesn't exist";
                }

        }else {
            return "This LoanRequest has not been found.";
        }
        return "";
    }

    public String updateTimePeriod(String loanRequestID, int loanPeriod ) {
        if(bank.getLoanRequests().containsKey(loanRequestID)) {
            LoanRequest loanRequest = bank.getSpecificLoanRequest(loanRequestID);
            loanRequest.setLoanPeriod(loanPeriod);
            return "The time period has been updated.";
        }else {
            return "This LoanRequest has not been found";
        }
    }
    public String updateCashContribution(String loanRequestID, double cashContribution){
        if(bank.getLoanRequests().containsKey(loanRequestID)) {
            LoanRequest loanRequest = bank.getSpecificLoanRequest(loanRequestID);
            loanRequest.setCashContribution(cashContribution);
            return "Cash contribution has been updated";
        } else {
            return "This LoanRequest has not been found";
        }
    }
    public String updateCoSigner_name(String loanRequestID, String coSigner_name){
        if(bank.getLoanRequests().containsKey(loanRequestID)) {
            LoanRequest loanRequest = bank.getSpecificLoanRequest(loanRequestID);
            loanRequest.setCoSigner_name(coSigner_name);
            return "Co-Signer name has been updated";
        } else {
            return "This LoanRequest has not been found";
        }
    }
    public String updateCoSigner_personalNr(String loanRequestID, String personalNr){
        if(bank.getLoanRequests().containsKey(loanRequestID)) {
            LoanRequest loanRequest = bank.getSpecificLoanRequest(loanRequestID);
            loanRequest.setCoSigner_personalNr(personalNr);
            return "Co-Signer personal number has been updated";
        } else {
            return "This LoanRequest has not been found";
        }
    }
    public String updateCoSigner_salary(String loanRequestID,double coSigner_salary){
        if(bank.getLoanRequests().containsKey(loanRequestID)) {
            LoanRequest loanRequest = bank.getSpecificLoanRequest(loanRequestID);
            loanRequest.setCoSigner_salary(coSigner_salary);
            return "Co-Signer salary has been updated";
        } else{
            return "This LoanRequest has not been found";
        }
    }
    public String updateInterestType(String loanRequestID, TypeOfInterest interestType){
        if(bank.getLoanRequests().containsKey(loanRequestID)) {
            LoanRequest loanRequest = bank.getSpecificLoanRequest(loanRequestID);
            loanRequest.setInterestType(interestType);
            return "The interest type has been updated";
        } else {
            return "This LoanRequest has not be found";
        }
    }


    // INBOX
    //Customer methods



    public String loanRequestWithOutCoSigner (double amount, TypesOfLoan typesOfLoan, TypeOfInterest typeOfInterest, double houseWorth, double time,HashMap<String, Double> hashMap ,double cashContribution) {
        // A loan request without Co-Signer is created and added to the bank back up and the inbox in to the customers and employee inbox
        LoanRequest loanRequest = new LoanRequest(((Customer) user), amount, typesOfLoan,houseWorth, typeOfInterest, time, hashMap, cashContribution);
        bank.addLoanApplication(user.getPersonalNo(),loanRequest);
        bank.addLoanRequest(loanRequest);
        StartProgram.jsonLoanRequests.add(loanRequest);
        employeeInbox.addLoanRequest(loanRequest);

        return "Loan request has been sent. The loan application ID is: LR" + user.getPersonalNo();
    }
    public HashMap<String,Double> temporaryHashMap(){
        // a temporary hashmap is created to store the inputs from the user
        HashMap<String , Double> tempHashmap = new HashMap<>();
        return tempHashmap;
    }
    public String addEquities(String equityName, double equityValue,HashMap<String,Double> tempHashmap){
        if(tempHashmap.containsKey(equityName)){
            return "The equity name cannot be the same, therefore not added";
        }
        tempHashmap.put(equityName,equityValue);
        return "Successfully added";
    }

    public String loanRequestWithCoSigner (double amount, TypesOfLoan typesOfLoan, TypeOfInterest typeOfInterest, double houseWorth, double time,HashMap<String, Double> hashMap ,double cashContribution, String coSigner_name, String coSigner_personalNr, double coSigner_salary) {

        LoanRequest loanRequest = new LoanRequest(((Customer) user), amount, typesOfLoan,houseWorth, typeOfInterest, time, hashMap, cashContribution,coSigner_name,coSigner_personalNr,coSigner_salary);
        bank.addLoanApplication(user.getPersonalNo(),loanRequest);
        bank.addLoanRequest(loanRequest);
        StartProgram.jsonLoanRequests.add(loanRequest);
        return "Loan request has been sent. The loan application ID is: LR"+ user.getPersonalNo();
    }

    public String sendMessageToEmployees(String title, String message){
        if (message == null) {
            return "Your message cannot be empty.";
        } else {
            MessageFormat textMessage = new MessageFormat(title, message);
            employeeInbox.addMessageToEmployee(textMessage);
            ((Customer)user).addSentMessage(textMessage);
            return "Your message has been sent successfully.";
        }
    }

    public String viewCustomerMessageInbox() {
        String message = "Message Inbox: " + Utilities.EOL;
        return message + ((Customer)user).getAllMessageInbox();
    }
    public String removeMessageFromCustomer() {
        employeeInbox.removeMessage();
        return "The message has been removed.";
    }
    public String viewCustomerMessageHistory(){
        String message = "Message History: " + Utilities.EOL;
        String message1 = "";
        for (int i = 0; i < ((Customer)user).getMessageHistory().size(); i++) {
            message1 += String.format(message) + Utilities.EOL;
        }
        return message + message1;
    }

    // Employee methods

    public String applyForVacation(int days){
        VacationRequest vacRequest = new VacationRequest(user,days);
        managerInbox.addVacationApplication(vacRequest);
        return "Vacation request has been send.";
    }


    public String sendMessageToCustomers(String message, String title) {
        if (message == null) {
            return "Your message cannot be empty.";
        } else {
            MessageFormat textMessage = new MessageFormat(title, message);
            employeeInbox.addMessageToCustomer(textMessage);
            bank.addSentMessage(textMessage);
            return "Your message has been sent successfully.";
        }
    }

    public String viewEmployeeMessageInbox(){
        String message = "Message Inbox: " + Utilities.EOL;
        return message + employeeInbox.getAllMessageInbox();
    }

    public String removeMessageFromEmployee(){
        ((Customer)user).removeMessage();
        return "The message has been removed.";
    }

    public void viewEmployeeMessageHistory(){
        //wouldn't the employee message history be the same as the customer message history because it is the same queue being shared?
        //do we really need two methods? or do i create two queues, one for employee and one for customer?

    }

    public void takeDaysOff (String personalNo,int amountOfDays){
        Employee employee = getEmployee(personalNo);
        int vacationDays = employee.getVacationDays();
        vacationDays -= amountOfDays;
    }

    public boolean checkLoanRequest(String loanRequestID) throws Exception{
        if(bank.getLoanRequests().containsKey(loanRequestID)){
            return true;
        }
        throw new Exception("The loan request was not found");
    }

    // check the decline loan request.
    // if it is inside in side the user remember that you are log in as an employee so you need to get the user.

    public String declineLoanRequest(String loanRequestID,String message) throws Exception {
        if (checkLoanRequest(loanRequestID)) {
            LoanRequest loanRequest = bank.getSpecificLoanRequest(loanRequestID);
            Customer customer = (Customer) users.get(loanRequest.getPersonalNr());

            String totalMessage = message + Utilities.EOL + loanRequest.printRequest();
            bank.removeLoanRequest(loanRequest.getPersonalNr(), loanRequest);
            bank.removeLoanRequest(loanRequest);
            // remove from employee request?
            String title = "Decline loan request. ID: " + loanRequestID;
            sendMessageToCustomers(title, totalMessage);
        }
        return "Loan decline message has been send";
    }
    // add the connection to the inbox. remove from queue in the employee.

    public String approveLoan(String loanRequestID, String message, double interestRate, String typeOfInterest)throws Exception {

        if (checkLoanRequest(loanRequestID)) {
            LoanRequest loanRequest = bank.getSpecificLoanRequest(loanRequestID);
            Customer customer = (Customer) users.get(loanRequest.getPersonalNr());


            double amount = loanRequest.getAmount();
            TypesOfLoan typesOfLoan = loanRequest.getTypesOfLoan();
            double houseWorth = loanRequest.getHouseWorth();
            double loanPeriod = loanRequest.getLoanPeriod();
            HashMap<String,Double> hashMap = loanRequest.getEquities();
            double cashContribution = loanRequest.getCashContribution();
            String coSigner_name = loanRequest.getCoSigner_name();
            String coSigner_personalNr = loanRequest.getCoSigner_personalNr();
            double coSigner_Salary = loanRequest.getCoSigner_salary();
            double houseValue = loanRequest.getHouseWorth();

            TypeOfInterest interestType = null;
            if(typeOfInterest.equalsIgnoreCase("Fix")){
                interestType = TypeOfInterest.FIX_RATE;
            }
            if(typeOfInterest.equalsIgnoreCase("variable")){
                interestType = TypeOfInterest.VARIABLE_RATE;
            }
            Loan loan = new Loan(customer,amount,typesOfLoan,houseValue,interestRate,interestType,loanPeriod,hashMap,cashContribution,coSigner_name,coSigner_personalNr,coSigner_Salary);
            bank.addLoan(customer.getPersonalNo(),loan);
            StartProgram.jsonLoans.add(loan);
            bank.removeLoanRequest(loanRequest.getPersonalNr(),loanRequest);
            bank.removeLoanRequest(loanRequest);
            StartProgram.jsonLoanRequests.remove(loanRequest);
            // Remove from eployees request queue.

            String totalMessage = message + Utilities.EOL + loan.printRequest();
            String tittle = "Decline loan request. ID: " + loanRequestID;
            sendMessageToCustomers(tittle, totalMessage);
        }    return "The loan has been approved.";
    }

    // connect to  inbox
    public String interestOffer(String loanRequestID, double interestRate, String message ) throws Exception{

        String totalMessage = "";
        if(checkLoanRequest(loanRequestID)) {
            LoanRequest loanRequest = bank.getSpecificLoanRequest(loanRequestID);
            Customer customer = (Customer) users.get(loanRequest.getPersonalNr());
            String title = "Interest offert " +Utilities.EOL+ "Loan request ID: " + loanRequestID;
            String textMessage =  " Interest offer: " + interestRate+  Utilities.EOL +
                    "Type of interest: " + loanRequest.getInterestType() + Utilities.EOL;
            // ADD MESSAGE TO CUSTOMER INBOX
            totalMessage= title + textMessage + message;
        }
        return totalMessage;
    }

    //Manager Inbox
    public String seeVacationApplications(){
        return bank.viewVacationApps();
    }

    public String approveVacationApplication(){
        return bank.approveVacationApps();
    }

    // ADMINISTRATION CONTROLLER
    //------------------------------------

    // change administration password is already in the menu

    public String createManager (String name, String personalNo, String password, double salary, double bonus) throws
            Exception {
        User manager = new Manager(name, personalNo, password,  salary, bonus);
        bank.addUser(manager);
        StartProgram.jsonManagers.add((Manager) manager);
        return "The manager was created succesfully";
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

    public String applyForVacation(String personalNo, int amountOfDays) throws Exception{
        Employee employee = getEmployee(personalNo);
        String message = "I would like to take " + amountOfDays + " days off.";
        if(employee.getVacationDays() <= amountOfDays) {
        }
        return  null;
    } //   String message = "Vacation approved";
    //} else{
    //  throw new Exception("Vacation is not approved");
    //}
    //return  "";
    //}

    //so we should have a message saying that we want a vacation
    //before sending the request we need to specify how many days we wanna take
    //then it should be sent to the inbox
    //customer.addOption(option number, h, optionname "message"
    //connect controller to the menu



    public String setManagerSalary (double newSalary, String personalNo){
        getManager(personalNo).setSalary(newSalary);
        return "Salary was successfully updated";
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
    public Customer getCustomer (String personalNo) {
        Customer customer=null;
        if (users.containsKey(personalNo)) {
            if (users.get(personalNo) instanceof Customer) {
                customer = ((Customer) users.get(personalNo));
            }
        } return customer;
    }

    public String viewSalary () {
        return "Your salary is " + ((Employee) user).getSalary();
    }

    public String updateCustomerPassword (String personalNo, String newPassword){
        Customer customer = getCustomer(personalNo);
        customer.setPassword(newPassword);
        return "The password was updated.";
    }
    // DO we need to remove the account? Method is not been used.
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


    public String getCustomerInfo (String personalNumber){
        Customer customer = getCustomer(personalNumber);
        return "--------------------" + Utilities.EOL +
                "Account information for " + customer.getFullName() + Utilities.EOL +
                "Transactions: " + Utilities.EOL +
                customer.getBankAccount().getTransactions() + Utilities.EOL + Utilities.EOL +
                "Loans: " + customer.getBankAccount().getLoan();
    }

    public String calculateDTI(double monthlyDebt, double grossIncome) {
        double dti = monthlyDebt/grossIncome;
        return "The DTI ratio is: " + Utilities.truncateForPrint(dti);
    }


    public String calculateMonthlyMortgage(double loan, double yearlyInterestRate, double loanPeriod){
            /* Montly mortgage formula:
                 m= p * r (1+r)^n
                   ----------------
                   ((1+r)^n ) - 1

                   this method calculates the monthly mortgage of a loan.
                   The mortgage of a house loan is different see the code below, it depeneds of the value of the property
             */
        double monthlyRate = yearlyInterestRate/ 12;
        double loanXInterest = loan* monthlyRate;
        double interestToThePower = Math.pow((1+monthlyRate),loanPeriod);
        double numerator = loanXInterest * interestToThePower;
        double denominator = interestToThePower -1;
        double monthlyMortgage = numerator/denominator;
        return "The monthly mortgage for this loan is: " + Utilities.truncateForPrint(monthlyMortgage);
    }
    // this method gives then annual rate for the mortgage of a house loan.
    public String getMortgagePercentage_HouseLoan(String loanID) {
        Loan loan = bank.getSpecificLoan(loanID);
        double houseMortgage = 0;
        if (loan.getTypesOfLoan() == TypesOfLoan.HOUSE_LOAN) {
            houseMortgage = loan.getMortgagePercentage();
        }
        return "The annual mortage of this loan is: " + Utilities.truncateForPrint(houseMortgage) + Utilities.EOL+
                "This rate is apart from the interest rate";
    }

    public Employee getEmployee (String inputPersonNumber){
        Employee employee=null;
        if (users.containsKey(inputPersonNumber)) {
            if (users.get(inputPersonNumber) instanceof Employee) {
                employee = ((Employee) users.get(inputPersonNumber));
            }
        } return employee;
    }



    public String createCustomer (String fullName, String personalNo, double salary ,String password) throws Exception {
        String bankAccount = accountNoGenerator();
        Customer customer = new Customer(fullName, personalNo, salary, password, bankAccount);
        bank.addUser(customer);
        bank.addBankAccount(bankAccount, customer);
        StartProgram.jsonCustomers.add(customer);
        return "Customers details: " + Utilities.EOL +
                "--------------------" + Utilities.EOL +
                "Name: " + fullName + Utilities.EOL +
                "Personal nr: " + personalNo + Utilities.EOL +
                "Bank number: " + bankAccount + Utilities.EOL;
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

    public String setVariableInterestRate(double interestRate){
        bank.setVariableInterestRate(interestRate);
        bank.setAllVariableInterest(interestRate);
        return "The variable interest rate has been changed to " + Utilities.truncateForPrint(interestRate);
    }

    public String approveCardRequest(String cardRequestID, String cardNr, int cvc, String expirationDate, int code) throws Exception {
        String message="";
        if(bank.getCardRequests().containsKey(cardRequestID)){
            CardRequest cardRequest = bank.getCardRequests().get(cardRequestID);

            Customer customer = (Customer) bank.getUsers().get(cardRequest.getPersonalNr());
            customer.createDebitCard(cardNr, cvc, expirationDate, code);
            bank.removeCardRequest(cardRequest.getPersonalNr(), cardRequest);
            StartProgram.jsonCardRequests.remove(cardRequest);

            message = "The card request has been approved.";
        } else {
            message = "There are no pending card requests with ID " + cardRequestID + ".";
        }
        return message;
    }
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
            if(entry.getValue() instanceof Customer){
                totalLoan += ((Customer) entry.getValue()).getBankAccount().getLoan().getLoanAmount();
            }
        }
        return message + totalLoan;
    }



        /*
        manager.addOptions(2,"Create employee");
         */


    public String createEmployee (String fullName, String personalNo, String password, double salary) throws
            Exception {
        User employee = new Employee(fullName, personalNo, password, salary);
        bank.addUser(employee);
        StartProgram.jsonEmployees.add((Employee) employee);
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
        String message="There is no registered employee with personal number " + personalNo + ".";
        if(users.containsKey(personalNo)){
            if (users.get(personalNo) instanceof Employee){
                getEmployee(personalNo).setSalary(newSalary);
                message = "The salary was successfully updated.";
            } else {
                message = "The user with personal number " + personalNo + " is not an employee.";
            }
        }

        return message;
    }


    /*
    manager.addOptions(5,"Update employee password");
    */
    public String setEmployeePassword (String newPassword, String personalNo){
        String message="There is no registered employee with personal number " + personalNo + ".";
        if(users.containsKey(personalNo)){
            if (users.get(personalNo) instanceof Employee){
                getEmployee(personalNo).setPassword(newPassword);
                message = "The password was successfully updated.";
            } else {
                message = "The user with personal number " + personalNo + " is not an employee.";
            }
        }

        return message;
    }


    public Manager getManager (String personalNo) {
        Manager manager=null;
        if (users.containsKey(personalNo)) {
            return ((Manager) users.get(personalNo));
        } else {
            return null;
        }
    }

    // show the manager or the admin do this?
    public String promoteEmployee (String personalNo,double newSalary, double bonus) throws Exception {
        String message="";
        Employee employee = getEmployee(personalNo);
        if (employee.getPersonalNo().equals(personalNo)) {
            String name = employee.getFullName();
            String password = employee.getPassword();
            Manager newEmployee = new Manager(name, personalNo, password, newSalary, bonus);
            bank.removeUser(employee);
            bank.addUser(newEmployee);// Example on how to find specific attribute, also need to give it more access
            message = "Employee was successfully promoted. ";
        }
        return message;
    }

    public EmployeeInbox getEmployeeInbox(){
        return employeeInbox;
    }

        // METHODS TO CHECK IN THE MAIN MENU

    public boolean isBlank(String name) throws Exception {
        if(name.isBlank()){
            return true;
        }
        return false;
    }

    public boolean isPersonNrCorrect(String personalNo) {
        if (personalNo.length() == 12) {
            String yearStr = personalNo.substring(0, 4);
            int year = Integer.parseInt(yearStr);
            String monthStr = personalNo.substring(4, 6);
            int month = Integer.parseInt(monthStr);
            String dayStr = personalNo.substring(6, 8);
            int day = Integer.parseInt(dayStr);

            if (year > 2003 || year < 1900) {
                return false;
            }
            if (month > 12 || month < 1) {
                return false;
            }
            if (day > 31 || day < 1) {
                return false;
            }
            return true;

        } else {
            return false;
        }
    }

    public boolean isCashContributionCorrect(double amount,double loanAmount){
        float percentage = (float) (loanAmount*0.15);
        if(amount<percentage){
            return false;
        }
        return true;
    }

    public boolean isStrongPassword(String password){
        boolean hasDigits = password.matches(".*\\d+.*");
        boolean hasUpperCase = password.matches(".*[A-Z]+.*");
        boolean hasLowerCase = password.matches(".*[a-z]+.*");
        boolean isLong = password.length() > 7;
        return hasDigits && hasLowerCase && hasUpperCase && isLong;
    }

    public boolean isCustomer (String personalNo){
        User user = users.get(personalNo);
        if(user instanceof Customer){
            return true;
        }
        return false;
    }

    public boolean isEmployee (String personalNo){
        User user = users.get(personalNo);
        if(user instanceof Employee){
            return true;
        }
        return false;
    }

    public boolean isManager (String personalNo){
        User user = users.get(personalNo);
        if(user instanceof Manager){
            return true;
        }
        return false;
    }

}

