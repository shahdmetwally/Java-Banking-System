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
        for (int i = ((Customer) user).getTransactions().size()-1; i > ((Customer) user).getTransactions().size()-5 ; i--) {
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
        String message="";
        // If the card status is true, that means that the card is still active.
        if(bank.getCardRequests().containsKey("C"+user.getPersonalNo())){
            message = "You already have a pending card request.";
        } else {
        if(((Customer)user).getDebitCard()==null ){
            CardRequest cardRequest = new CardRequest(user);
            bank.addCardRequest(user.getPersonalNo(),cardRequest);
            StartProgram.jsonCardRequests.add(cardRequest);
            employeeInbox.addCardRequest(cardRequest);
            // should we add this to the customer as well? so they can see sent request? // Shahd
            message = "Card request with ID "+ cardRequest.getId()+ " has been sent. ";
        } else if(((Customer)user).getDebitCard().getStatus()){ //make a method for reactivation of card
            message = "You cannot apply for a new card, you already have an active card.";

        }
        }
        return message;
    }

    public String blockCard () {
        // This method changes the status of the debit card to false, which means is not active
        String message="";
        if(((Customer) user).getDebitCard()==null){
            message = "You do not have a card to block.";
        }else if(!((Customer) user).getDebitCard().getStatus()){
            message = "Your card has already been blocked.";
        } else if(((Customer)user).getDebitCard().getStatus()){
            ((Customer) user).deactivateCard();
            message = "Payment card has been blocked";
        }
        return message;
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
                    return loanRequest.printEquities() + "Equity value successfully changed";
                }else{
                    return "Please enter an existing equity: ";
                }
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
        String output="";
        MessageFormat textMessage = new MessageFormat(title, message);
        if (textMessage.isEmpty()) {
            output = "Your message cannot be empty.";//add these to the others
        } else {
            employeeInbox.addMessageToUnreadMessages(textMessage);
            StartProgram.jsonEmployeeUnreadMessages.add(textMessage);
            ((Customer)user).getInbox().addToSentMessage(textMessage);
            output = "Your message has been sent.";
        }
        return output;
    }
    public String removeMessageFromCustomer(int index) {
        employeeInbox.removeFromUnreadMessages(index);
        return "The message has been removed.";
    }
    public String viewCustomerMessageHistory(){
        String message = "Message History: " + Utilities.EOL;
        String message1 = "";
        if(((Customer)user).getInbox().getMessageHistory().isEmpty()){
            message1 = "There are no messages available in message history.";
        } else {
            for (int i = 0; i < ((Customer) user).getInbox().getMessageHistory().size(); i++) {
                message1 += i + ": " + ((Customer) user).getInbox().getMessageHistory().get(i) + Utilities.EOL;
            }
        }
        return message + message1;
    }

    // Employee methods

    public String applyForVacation(int days){
        String message = "";
        if(days>((Employee)user).getVacationDays()){
            message = "You only have " + ((Employee)user).getVacationDays() + " vacation days left.";
        } else if (days<0 || days==0){
            message = "Your vacation days cannot be equal to or less than 0.";
        } else {
            VacationRequest vacRequest = new VacationRequest(user, days);
            managerInbox.addVacationApplication(vacRequest);
            StartProgram.jsonVacationApplication.add(vacRequest);
            message = "Vacation request has been sent.";
        }
        return message;
    }


    public String sendMessageToACustomer(String personalNr, String message, String title) {
        String output = "";
        if(getCustomer(personalNr)==null){
            output="A customer with this personal number does not exist.";
        } else {
            MessageFormat textMessage = new MessageFormat(title, message);
            Customer customer = getCustomer(personalNr);
            customer.getInbox().addMessageToUnreadMessages(textMessage);
            bank.getEmployeeInbox().addMessageToSentMessages(textMessage);
            StartProgram.jsonEmployeeSentMessages.add(textMessage);
            output = "The message has been sent.";
        }
        return output;//add another option in menu, view
    }


    public String viewEmployeeMessageHistory(){
        String printMessageHistory="";
        ArrayList<MessageFormat> messageHistory = new ArrayList<MessageFormat>(employeeInbox.getMessageHistory());
        if(messageHistory.isEmpty()){
            printMessageHistory = "There are no messages available in message history.";
        } else {
            for (int i = 0; i < messageHistory.size(); i++) {
                printMessageHistory += i + ": " + messageHistory.get(i) + Utilities.EOL;
            }
        }
        return printMessageHistory;
    }

    public String readMessageEmployeeMessageHistory(int index){
        ArrayList<MessageFormat> messageHistory = new ArrayList<MessageFormat>(employeeInbox.getMessageHistory());
        String printMessage = messageHistory.get(index).getMessage();
        return printMessage;
    }

    public String readMessageCustomerMessageHistory(int index){
        ArrayList<MessageFormat> messageHistory = new ArrayList<MessageFormat>(((Customer)user).getInbox().getMessageHistory());
        String printMessage = messageHistory.get(index).getMessage();
        return printMessage;
    }

    public void removeFromEmployeeMessageHistory(int index){
        bank.getEmployeeInbox().removeFromMessageHistory(index);
        StartProgram.jsonEmployeeMessageHistory.remove(index);
    }

    public String viewCustomerUnreadMessages(){
        String printUnreadMessages="";
        ArrayList<MessageFormat> unreadMessages = new ArrayList<MessageFormat>(((Customer)user).getInbox().getUnreadMessageInbox());
        if(unreadMessages.isEmpty()){
            printUnreadMessages = "There are no unread messages.";
        } else {
            for (int i = 0; i < unreadMessages.size(); i++) {
                printUnreadMessages += i + ": " + unreadMessages.get(i) + Utilities.EOL;
            }
        }
        return printUnreadMessages;
    }

    public String viewEmployeeUnreadMessages(){
        String printUnreadMessages="";
        ArrayList<MessageFormat> unreadMessages = new ArrayList<MessageFormat>(employeeInbox.getUnreadMessageInbox());
        if(unreadMessages.isEmpty()){
            printUnreadMessages = "There are no unread messages.";
        } else {
            for (int i = 0; i < unreadMessages.size(); i++) {
                printUnreadMessages += i + ": " + unreadMessages.get(i) + Utilities.EOL;
            }
        }
        return printUnreadMessages;
    }

    public String readEmployeeUnreadMessage(int index){
        ArrayList<MessageFormat> unreadMessages = new ArrayList<MessageFormat>(employeeInbox.getUnreadMessageInbox());
        String printUnreadMessage = unreadMessages.get(index).getMessage();
        return printUnreadMessage;
    }

    public String readCustomerUnreadMessage(int index) {
        ArrayList<MessageFormat> unreadMessages = new ArrayList<MessageFormat>(((Customer) user).getInbox().getUnreadMessageInbox());
        String printUnreadMessage = unreadMessages.get(index).getMessage();
        return printUnreadMessage;
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
    // if it is inside in side the user remember that you are log in as an employee, so you need to get the user.

    public String declineLoanRequest(String loanRequestID,String message) throws Exception {
        if (checkLoanRequest(loanRequestID)) {
            LoanRequest loanRequest = bank.getSpecificLoanRequest(loanRequestID);
            Customer customer = (Customer) users.get(loanRequest.getPersonalNr());

            String totalMessage = message + Utilities.EOL + loanRequest.printRequest();
            bank.removeLoanRequest(loanRequest.getPersonalNr(), loanRequest);
            bank.removeLoanRequest(loanRequest);
            StartProgram.jsonLoanRequests.remove(loanRequest);
            // remove from employee request?
            String title = "Decline loan request. ID: " + loanRequestID;
            String personalNr = customer.getPersonalNo();
            sendMessageToACustomer(personalNr, title, totalMessage);
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
            employeeInbox.removeLoanRequest(loanRequest);
            StartProgram.jsonLoanRequests.remove(loanRequest);
            // Remove from employees request queue.
            String personalNr = customer.getPersonalNo();
            String totalMessage = message + Utilities.EOL + loan.printRequest();
            String title = "Decline loan request. ID: " + loanRequestID;
            sendMessageToACustomer(personalNr, title, totalMessage);
        }    return "The loan has been approved.";
    }

    // connect to  inbox
    public String interestOffer(String loanRequestID, double interestRate, String message ) throws Exception{

        String totalMessage = "";
        if(checkLoanRequest(loanRequestID)) {
            LoanRequest loanRequest = bank.getSpecificLoanRequest(loanRequestID);
            Customer customer = (Customer) users.get(loanRequest.getPersonalNr());
            String title = "Interest offer " +Utilities.EOL+ "Loan request ID: " + loanRequestID;
            String textMessage =  " Interest offer: " + interestRate+  Utilities.EOL +
                    "Type of interest: " + loanRequest.getInterestType() + Utilities.EOL;
            // ADD MESSAGE TO CUSTOMER INBOX
            totalMessage= title + textMessage + message;
        }
        return totalMessage;
    }

    //Manager Inbox
    public String seeVacationApplications(){
        String message="";
        if(managerInbox.getVacationApplications().isEmpty()){
            message = "There are no pending vacation requests.";
        } else {
            for(VacationRequest vacationRequest : managerInbox.getVacationApplications()){
                message += vacationRequest.toString() + Utilities.EOL;
            }
        }

        return message;
    }

    public String handleVacationApplication(){
        String message ="";
        String employeePersonalNo = managerInbox.getVacationApplications().peek().getPersonalNr();
        Employee employee = ((Employee)users.get(employeePersonalNo));
        int vacationDays = managerInbox.getVacationApplications().peek().getDays();
        String id = managerInbox.getVacationApplications().peek().getId();
        if(managerInbox.getVacationApplications().isEmpty()){
            message = "There are no pending vacation requests.";
        } else {
            if (vacationDays > employee.getVacationDays()) {
                message = "The vacation request with ID:" + id + " has been declined.";
            } else {
                managerInbox.getVacationApplications().poll();
                StartProgram.jsonVacationApplication.poll();
                employee.takeDaysOff(vacationDays);
                message = "The vacation request with ID:" + id + " has been approved.";
            }
        }
        return message;
    }

    // ADMINISTRATION CONTROLLER
    //------------------------------------

    // change administration password is already in the menu

    public String createManager (String name, String personalNo, String password, double salary, double bonus) throws
            Exception {
        User manager = new Manager(name, personalNo, password,  salary, bonus);
        manager.setRole(Role.MANAGER);
        bank.addUser(manager);
        StartProgram.jsonManagers.add((Manager) manager);
        return "The manager was created successfully";
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
    //before sending the request we need to specify how many days we want to take
    //then it should be sent to the inbox
    //customer.addOption(option number, h, optionName "message"
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

        String printTransactions="";
        for(String transaction : customer.getBankAccount().getTransactions()){
            printTransactions += transaction + Utilities.EOL;
        }

        String printLoans="";
        if(!bank.getLoans().containsKey("L"+customer.getPersonalNo())){
            printLoans = "No current loans.";
        } else {
            printLoans = bank.getLoans().get("L"+customer.getPersonalNo()).toString();
        }

        String printCardRequests="";
        if(cardRequestIsPending("C"+customer.getPersonalNo())){
            printCardRequests = bank.getCardRequests().get("C"+customer.getPersonalNo()).toString();
        } else {
            printCardRequests = "No pending card requests.";
        }

        String printLoanRequests="";
        if(bank.getLoanRequests().containsKey("LR"+customer.getPersonalNo())){
            printLoanRequests = bank.getLoanRequests().get("LR"+customer.getPersonalNo()).toString();
        } else {
            printLoanRequests = "No pending loan requests.";
        }


        return "--------------------" + Utilities.EOL +
                "Account information for " + customer.getFullName() + Utilities.EOL +
                "Personal number: " + customer.getPersonalNo() + Utilities.EOL +
                "Account number: " + customer.getAccountNo() + Utilities.EOL + Utilities.EOL +
                "Transactions: " + Utilities.EOL +
                printTransactions + Utilities.EOL +
                "Card requests: " + Utilities.EOL +
                printCardRequests + Utilities.EOL + Utilities.EOL +
                "Loan requests: " + Utilities.EOL +
                printLoanRequests + Utilities.EOL + Utilities.EOL +
                "Loans: " + Utilities.EOL +
                printLoans + Utilities.EOL + "--------------------";

    }

    public String calculateDTI(double monthlyDebt, double grossIncome) {
        double dti = monthlyDebt/grossIncome;
        return "The DTI ratio is: " + Utilities.truncateForPrint(dti);
    }


    public String calculateMonthlyMortgage(double loan, double yearlyInterestRate, double loanPeriod){
            /* Monthly mortgage formula:
                 m= p * r (1+r)^n
                   ----------------
                   ((1+r)^n ) - 1

                   this method calculates the monthly mortgage of a loan.
                   The mortgage of a house loan is different see the code below, it depends on if the value of the property
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
        return "The annual mortgage of this loan is: " + Utilities.truncateForPrint(houseMortgage) + Utilities.EOL+
                "This rate is apart from the interest rate.";
    }

    public Employee getEmployee (String inputPersonNumber){
        Employee employee=null;
        if (users.containsKey(inputPersonNumber)) {
            if (users.get(inputPersonNumber) instanceof Employee) {
                employee = ((Employee) users.get(inputPersonNumber));
            }
        } return employee;
    }



    public String createCustomer (String fullName, String personalNo, double income ,String password) throws Exception {
        String bankAccount = accountNoGenerator();
        Customer customer = new Customer(fullName, personalNo, income, password, bankAccount);
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
        if(cardRequestIsPending(cardRequestID)){
            CardRequest cardRequest = bank.getCardRequests().get(cardRequestID);
            Customer customer = (Customer) bank.getUsers().get(cardRequest.getPersonalNr());
            customer.createDebitCard(cardNr, cvc, expirationDate, code);
            bank.removeCardRequest(cardRequest.getPersonalNr(), cardRequest);
            employeeInbox.removeCardRequest(cardRequest);
            StartProgram.jsonCardRequests.remove(cardRequest);
            //Add message to customer that it has been approved
            message = "The card request has been approved.";
        }
        return message;
    }

    public boolean cardRequestIsPending(String cardRequestID) {
        return bank.getCardRequests().containsKey(cardRequestID);
    }
    /*2
     manager.addOptions(0,"Show Bank Balance");
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

       /* for (Map.Entry<String, User> entry : users.entrySet()) {
            if(entry.getValue() instanceof Customer){
                totalLoan += ((Customer) entry.getValue()).getBankAccount().getLoan().getLoanAmount();
            }
        }*/

        for (Map.Entry<String, Loan> loan : bank.getLoans().entrySet()){
            totalLoan += loan.getValue().getLoanAmount();
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
            StartProgram.jsonEmployees.remove(employee);
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
            String lastFour = personalNo.substring(8, 12);

            if (year > 2003 || year < 1900) {
                return false;
            }
            if (month > 12 || month < 1) {
                return false;
            }
            if (day > 31 || day < 1) {
                return false;
            }
            if (!Utilities.isNumber(lastFour)){
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

    public void removeFromEmployeeUnreadMessages(int index) {
        bank.getEmployeeInbox().removeFromUnreadMessages(index);
        StartProgram.jsonEmployeeUnreadMessages.remove(index);
    }
    public void removeFromCustomerUnreadMessages(int index) {
        ((Customer)user).getInbox().removeFromUnreadMessages(index);
    }

    public void removeFromCustomerMessageHistory(int index) {
        ((Customer)user).getInbox().removeFromMessageHistory(index);
    }


    public String viewAllLoanRequests() {
        String message = "";
        String message1 = "Loan requests: ";
        if(bank.getLoanRequests().isEmpty()){
            message = "There are no pending loan requests.";
        } else {
            for(Map.Entry<String, LoanRequest> loanRequestEntry : bank.getLoanRequests().entrySet()){
            message += loanRequestEntry.getValue().toString() + Utilities.EOL;
            }
        }
        return message1 + Utilities.EOL + message;
    }

    public String viewAllCardRequests() {
        String message = "";
        String message1 = "Card requests: ";
        if(bank.getCardRequests().isEmpty()){
            message = "There are no pending card requests.";
        } else {
            for(Map.Entry<String, CardRequest> cardRequestEntry : bank.getCardRequests().entrySet()){
                message += cardRequestEntry.getValue().toString() + Utilities.EOL;
            }
        }
        return message1 + Utilities.EOL + message;
    }

    public String showAllManagers() {
        String message = "Managers: ";
        String printManager="";
            for (Map.Entry<String, User> userEntry : bank.getUsers().entrySet()) {
                if (userEntry.getValue().getRole()==Role.MANAGER) {
                    Manager manager = (Manager) userEntry.getValue();
                    printManager += manager + Utilities.EOL;
                }
            }
        return message + Utilities.EOL + printManager;
    }

    public void addToEmployeeMessageHistory(MessageFormat textMessage) {
        bank.getEmployeeInbox().addMessageToMessageHistory(textMessage);
        StartProgram.jsonEmployeeMessageHistory.add(textMessage);
    }

    public void addToCustomerMessageHistory(MessageFormat textMessage) {
        ((Customer)user).getInbox().addMessageToMessageHistory(textMessage);
    }

    public String viewCustomerSentMessages() {
        String message = "Sent Messages: " + Utilities.EOL;
        String message1 = "";
        if(((Customer)user).getInbox().getSentMessages().isEmpty()){
            message1 = "You have not sent any messages yet.";
        } else {
            for (int i = 0; i < ((Customer) user).getInbox().getSentMessages().size(); i++) {
                message1 += i + ": " + ((Customer) user).getInbox().getSentMessages().get(i) + Utilities.EOL;
            }
        }
        return message + message1;
    }

    public String viewEmployeeSentMessages() {
        String printSentMessages="";
        ArrayList<MessageFormat> sentMessages = new ArrayList<MessageFormat>(employeeInbox.getSentMessages());
        if(sentMessages.isEmpty()){
            printSentMessages = "No messages have been sent yet.";
        } else {
            for (int i = 0; i < sentMessages.size(); i++) {
                printSentMessages += i + ": " + sentMessages.get(i) + Utilities.EOL;
            }
        }
        return printSentMessages;
    }

    public String sendMessageToAllCustomers(String title, String message) {
        String output="";
        MessageFormat textMessage = new MessageFormat(title, message);
        if (textMessage.isEmpty()) {
            output = "The message cannot be empty.";//add these to the others
        } else {
            employeeInbox.addMessageToSentMessages(textMessage);
            StartProgram.jsonEmployeeSentMessages.add(textMessage);
            for(User user : bank.getUsers().values()){
                if (user instanceof Customer){
                    Customer customer = (Customer) user;
                    customer.getInbox().addMessageToUnreadMessages(textMessage);
                }
            }
            output = "The message has been sent to all customers.";
        }
        return output;
    }
}

