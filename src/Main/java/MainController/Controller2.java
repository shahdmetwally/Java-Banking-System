package MainController;

import Bank.Bank;
import Classes.*;
import Inbox.EmployeeInbox;
import Inbox.MessageFormat;
import Loans.Loan;
import Loans.TypeOfInterest;
import Loans.TypesOfLoan;
import Request.CardRequest;
import Request.LoanRequest;
import Request.VacationRequest;
import Utilities.Utilities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Controller2 extends Controller {

    public Controller2(String userName, String password, Bank bank) throws Exception {
        super(userName, password, bank);
    }
    //Loan Request

    public String updateTypeOfLoan(String loanRequestID, TypesOfLoan typesOfLoan) {
        if (bank.getLoanRequests().containsKey(loanRequestID)) {
            LoanRequest loanRequest = bank.getSpecificLoanRequest(loanRequestID);
            loanRequest.setTypesOfLoan(typesOfLoan); // here is the setter.
            return "Type of loan has been updated.";
        } else {
            return "The loan request was not found.";
        }
    }

    public String updateEquities(String loanRequestID, String otherEquity, double equityNewValue) {
        if (bank.getLoanRequests().containsKey(loanRequestID)) {
            LoanRequest loanRequest = bank.getSpecificLoanRequest(loanRequestID);
            for (Map.Entry<String, Double> equity : loanRequest.getEquities().entrySet())
                if (equity.getKey().equals(otherEquity)) {
                    loanRequest.getEquities().remove(otherEquity);
                    loanRequest.getEquities().put(otherEquity, equityNewValue);
                    return loanRequest.printEquities() + "Equity value successfully changed";
                } else {
                    return "Please enter an existing equity: ";
                }
        }
        return "";
    }

    public String updateTimePeriod(String loanRequestID, int loanPeriod) {
        if (bank.getLoanRequests().containsKey(loanRequestID)) {
            LoanRequest loanRequest = bank.getSpecificLoanRequest(loanRequestID);
            loanRequest.setLoanPeriod(loanPeriod);
            return "The time period has been updated.";
        } else {
            return "This LoanRequest has not been found";
        }
    }

    public String updateCashContribution(String loanRequestID, double cashContribution) {
        if (bank.getLoanRequests().containsKey(loanRequestID)) {
            LoanRequest loanRequest = bank.getSpecificLoanRequest(loanRequestID);
            loanRequest.setCashContribution(cashContribution);
            return "Cash contribution has been updated";
        } else {
            return "This LoanRequest has not been found";
        }
    }

    public String updateCoSigner_name(String loanRequestID, String coSigner_name)throws Exception {
        if (bank.getLoanRequests().containsKey(loanRequestID)) {
            LoanRequest loanRequest = bank.getSpecificLoanRequest(loanRequestID);
            loanRequest.setCoSigner_name(coSigner_name);
            return "Co-Signer name has been updated";
        } else {
            return "This LoanRequest has not been found";
        }
    }

    public String updateCoSigner_personalNr(String loanRequestID, String personalNr)throws  Exception {
        if (bank.getLoanRequests().containsKey(loanRequestID)) {
            LoanRequest loanRequest = bank.getSpecificLoanRequest(loanRequestID);
            loanRequest.setCoSigner_personalNr(personalNr);
            return "Co-Signer personal number has been updated";
        } else {
            return "This LoanRequest has not been found";
        }
    }

    public String updateCoSigner_salary(String loanRequestID, double coSigner_salary) throws Exception {
        if (bank.getLoanRequests().containsKey(loanRequestID)) {
            LoanRequest loanRequest = bank.getSpecificLoanRequest(loanRequestID);
            loanRequest.setCoSigner_salary(coSigner_salary);
            return "Co-Signer salary has been updated";
        } else {
            return "This LoanRequest has not been found";
        }
    }

    public String updateInterestType(String loanRequestID, TypeOfInterest interestType) {
        if (bank.getLoanRequests().containsKey(loanRequestID)) {
            LoanRequest loanRequest = bank.getSpecificLoanRequest(loanRequestID);
            loanRequest.setInterestType(interestType);
            return "The interest type has been updated";
        } else {
            return "This LoanRequest has not be found";
        }
    }

    //INBOX

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
        String totalLoanStr= new BigDecimal(Double.toString(totalLoan)).toPlainString();

        return message + totalLoanStr;
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
    public String setEmployeePassword (String newPassword, String personalNo) throws Exception {
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


    public Manager getManager (String personalNo) {
        if (users.containsKey(personalNo)) {
            return ((Manager) users.get(personalNo));
        } else {
            return null;
        }
    }

    public EmployeeInbox getEmployeeInbox(){
        return employeeInbox;
    }

    // ADMINISTRATION CONTROLLER

    public String setAdminPassword (String password) throws Exception {
        user.setPassword(password);
        return "Admin password has been updated";
    }

    public String createManager (String name, String personalNo, String password, double salary, double bonus) throws Exception {
        User manager = new Manager(name, personalNo, password,  salary, bonus);
        manager.setRole(Role.MANAGER);
        bank.addUser(manager);
        StartProgram.jsonManagers.add((Manager) manager);
        return "The manager was created successfully";
    }
    public String showAllManagers() {
        String message = "Managers: ";
        String printManager="";
        for (Map.Entry<String, User> userEntry : bank.getUsers().entrySet()) {
            if (userEntry.getValue().getRole()== Role.MANAGER) {
                Manager manager = (Manager) userEntry.getValue();
                printManager += manager + Utilities.EOL;
            }
        }
        return message + Utilities.EOL + printManager;
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
    public String setManagerSalary (double newSalary, String personalNo){
        getManager(personalNo).setSalary(newSalary);
        return "Salary was successfully updated";
    }
    public String setManagerPassword (String personalNo, String newPassword) throws Exception {
        getManager(personalNo).setPassword(newPassword);
        return "The password was updated ";
    }
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
    public boolean checkViewMessages(){
        if(viewCustomerUnreadMessages().equals("There are no unread messages.")){
            return true;
        }
        return false;
    }
    public String readMessageCustomerMessageHistory(int index){
        ArrayList<MessageFormat> messageHistory = new ArrayList<MessageFormat>(((Customer)user).getInbox().getMessageHistory());
        String printMessage = messageHistory.get(index).getMessage();
        return printMessage;
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
}




