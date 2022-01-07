package MainController;
import Bank.Bank;
import Inbox.*;
import Loans.*;
import Request.*;
import Classes.*;
import Utilities.Utilities;
import java.util.*;

public class Controller {
    protected  Bank bank;
    protected  HashMap<String, User> users;
    protected  HashMap<String, User> bankAccounts;
    protected  User user;
    protected EmployeeInbox employeeInbox;
    protected ManagerInbox managerInbox;


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

    public Bank getBank(){
        return bank;
    }
    public User getUser(){
        return user;
    }
    public boolean checkPersonalNr(String personalNr){
        if (bank.getUsers().containsKey(personalNr) && isCustomer(personalNr) && isPersonNrCorrect(personalNr)){
            return true;
        }
        return false;
    }
    // CUSTOMER CONTROLLER

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

//OTHERSERVICES

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
    public String loanRequestWithCoSigner (double amount, TypesOfLoan typesOfLoan, TypeOfInterest typeOfInterest, double houseWorth, double time,HashMap<String, Double> hashMap ,double cashContribution, String coSigner_name, String coSigner_personalNr, double coSigner_salary) {

        LoanRequest loanRequest = new LoanRequest(((Customer) user), amount, typesOfLoan,houseWorth, typeOfInterest, time, hashMap, cashContribution,coSigner_name,coSigner_personalNr,coSigner_salary);
        bank.addLoanApplication(user.getPersonalNo(),loanRequest);
        bank.addLoanRequest(loanRequest);
        StartProgram.jsonLoanRequests.add(loanRequest);
        return "Loan request has been sent. The loan application ID is: LR"+ user.getPersonalNo();
    }
    public String loanRequestWithOutCoSigner (double amount, TypesOfLoan typesOfLoan, TypeOfInterest typeOfInterest, double houseWorth, double time,HashMap<String, Double> hashMap ,double cashContribution) {
        // A loan request without Co-Signer is created and added to the bank back up and the inbox in to the customers and employee inbox
        LoanRequest loanRequest = new LoanRequest(((Customer) user), amount, typesOfLoan,houseWorth, typeOfInterest, time, hashMap, cashContribution);
        bank.addLoanApplication(user.getPersonalNo(),loanRequest);
        bank.addLoanRequest(loanRequest);
        StartProgram.jsonLoanRequests.add(loanRequest);
        employeeInbox.addLoanRequest(loanRequest);

        return "Loan request has been sent. The loan application ID is: LR" + user.getPersonalNo();
    }

    // EMPLOYEE CONTROLLER
    //--------------------------------------

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

    public Customer getCustomer (String personalNo) {
        Customer customer=null;
        if (users.containsKey(personalNo)) {
            if (users.get(personalNo) instanceof Customer) {
                customer = ((Customer) users.get(personalNo));
            }
        } return customer;
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
    public boolean cardRequestIsPending(String cardRequestID) {
        return bank.getCardRequests().containsKey(cardRequestID);
    }
    public String updateCustomerPassword (String personalNo, String newPassword) throws Exception {
        Customer customer = getCustomer(personalNo);
        customer.setPassword(newPassword);
        return "The password was updated.";
    }
    public String approveLoan(String loanRequestID, String message, double interestRate, String typeOfInterest)throws Exception {

        if (checkLoanRequest(loanRequestID)) {
            LoanRequest loanRequest = bank.getSpecificLoanRequest(loanRequestID);
            Customer customer = (Customer) users.get(loanRequest.getPersonalNr());

            double amount = loanRequest.getAmount();
            TypesOfLoan typesOfLoan = loanRequest.getTypesOfLoan();
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
            customer.setLoan(loan);
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

    public String viewSalary () {
        return "Your salary is " + ((Employee) user).getSalary();
    }
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

    public void removeFromEmployeeMessageHistory(int index){
        bank.getEmployeeInbox().removeFromMessageHistory(index);
        StartProgram.jsonEmployeeMessageHistory.remove(index);
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



    public boolean checkLoanRequest(String loanRequestID) throws Exception{
        if(bank.getLoanRequests().containsKey(loanRequestID)){
            return true;
        }
        throw new Exception("The loan request was not found");
    }

    public String interestOffer(String loanRequestID, double interestRate, String message ) throws Exception{

        String totalMessage = "";
        if(checkLoanRequest(loanRequestID)) {
            LoanRequest loanRequest = bank.getSpecificLoanRequest(loanRequestID);
            Customer customer = (Customer) users.get(loanRequest.getPersonalNr());
            String title = "Interest offer " +Utilities.EOL+ "Loan request ID: " + loanRequestID;
            String textMessage =  " Interest offer: " + interestRate+  Utilities.EOL +
                    "Type of interest: " + loanRequest.getInterestType() + Utilities.EOL;
            MessageFormat InterestMessage = new MessageFormat(title,message);
            totalMessage= title + textMessage + message;
            customer.addSentMessage(InterestMessage);

        }
        return totalMessage;
    }

    // METHODS TO CHECK IN THE MAIN MENU

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


}



