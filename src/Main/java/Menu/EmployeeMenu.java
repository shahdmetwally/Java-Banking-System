package Menu;

import Classes.Role;
import Inbox.MessageFormat;
import MainController.Controller;
import Utilities.UserInput;
import Utilities.Utilities;

import java.util.InputMismatchException;

public class EmployeeMenu {
    protected MenuOptions employeeMenu;
    protected MenuOptions managerMenu;

    protected MenuOptions employeeInbox;
    protected MenuOptions managerInbox;
    protected Controller controller;

    public EmployeeMenu(Controller controller) throws Exception {
        if (controller.getUser().getRole() == Role.EMPLOYEE || controller.getUser().getRole() == Role.MANAGER) {
            this.employeeMenu = new MenuOptions();
            this.managerMenu = new MenuOptions();
            this.employeeInbox = new MenuOptions();
            this.managerInbox = new MenuOptions();
            this.controller = controller;
        } else {
            throw new Exception("Access denied. This menu is only for employees.");
        }
    }
    public void setUpEmployeeMenu() {
        employeeMenu.setMenuName("Employee Menu " + Utilities.EOL +
                "--------------------" + Utilities.EOL +
                " Choose one of the options below.");
        employeeMenu.addOptions(0, "Create Customer.");
        employeeMenu.addOptions(1, "View a customer's account.");
        employeeMenu.addOptions(2, "Update customer password.");
        employeeMenu.addOptions(3, "Approve loans.");
        employeeMenu.addOptions(4, "Approve card requests.");
        employeeMenu.addOptions(5, "Calculate Debt-to-income (DTI) ratio.");
        employeeMenu.addOptions(6, "Calculate monthly mortgage.");
        employeeMenu.addOptions(7, "Calculate the annual mortgage of a house loan");
        employeeMenu.addOptions(8, "View salary.");
        employeeMenu.addOptions(9, "Apply for vacation.");
        employeeMenu.addOptions(10, "View employee inbox.");
        employeeMenu.addOptions(11, "Manager options.");
        employeeMenu.addOptions(12, "Log out.");

        employeeInbox.setMenuName("Employee Inbox Menu " + Utilities.EOL +
                "--------------------" + Utilities.EOL +
                " Choose one of the options below.");
        employeeInbox.addOptions(0, "View unread messages from customers.");
        employeeInbox.addOptions(1, "Send message to a customer.");
        employeeInbox.addOptions(2, "Send message to all customers.");
        employeeInbox.addOptions(3, "View all loan requests.");
        employeeInbox.addOptions(4, "View all card requests.");
        employeeInbox.addOptions(5, "View message history.");
        employeeInbox.addOptions(6, "View sent messages.");
        employeeInbox.addOptions(7, "Go back to Employee menu.");


        managerMenu.setMenuName("Manager Menu " + Utilities.EOL +
                "--------------------" + Utilities.EOL +
                " Choose one of the options below.");
        managerMenu.addOptions(0, "Show Bank Balance.");
        managerMenu.addOptions(1, "Show total loaned amount.");
        managerMenu.addOptions(2, "Create employee.");
        managerMenu.addOptions(3, "Remove employee.");
        managerMenu.addOptions(4, "Update employee salary.");
        managerMenu.addOptions(5, "Update employee password.");
        managerMenu.addOptions(6, "Set variable interest rate.");
        managerMenu.addOptions(7, "View manager inbox.");
        managerMenu.addOptions(8, "Return to employee menu.");

        managerInbox.setMenuName("Manager Inbox Menu " + Utilities.EOL +
                "--------------------" + Utilities.EOL +
                " Choose one of the options below.");
        managerInbox.addOptions(0, "View all employees' vacation requests.");
        managerInbox.addOptions(1, "Approve or decline employees' vacation request.");
        managerInbox.addOptions(2, "Return to manager menu.");
    }

    //---------------EMPLOYEE
    // case 1:
    public String registerPersonalNr(String message){
         String personalNo = UserInput.readLine(message);
        if (!controller.isPersonNrCorrect(personalNo)) {
            do {
                String totalMessage = "The personal number must be in this format (YYYYMMDDXXXX) and within valid times. " + message;
                personalNo = UserInput.readLine(totalMessage);
            } while (!controller.isPersonNrCorrect(personalNo));
        }
        return personalNo;
    }
    public String registerPassword(String message){
        String password;
        String repeatedPassword;
        do {
            do {
                password = UserInput.readLine(message + Utilities.EOL +
                        "The password must have a minimum of 8 characters in length" + Utilities.EOL +
                        "and contain: a lowercase letter, an uppercase letter, a digit.");
                if (!controller.isStrongPassword(password)) {
                    System.out.println("The password is weak.");
                }
            } while (!controller.isStrongPassword(password));
            repeatedPassword = UserInput.readLine("Confirm password");
        } while (!password.equals(repeatedPassword));
        return password;
    }
    public double registerIncome(String message){
        String income = "";
        do {
            income = UserInput.readLine(message);
            if (!Utilities.isNumeric(income) || income.isEmpty()) {
                System.out.println("Invalid input. ");
            }
        } while (!Utilities.isNumeric(income) || income.isEmpty());

        double newIncome = Double.parseDouble(income);
        return newIncome;
    }

    public void createCustomer() {
        System.out.println("Registering a new Customer: ");

        try {
            String fullName = UserInput.readLine("Enter the customer's full name: ");
            String personNrMessage= "Enter the customer's personal number: ";
            String personalNo = registerPersonalNr(personNrMessage);
            String passwordMessage = "Create a password for the customer:";
            String password = registerPassword(passwordMessage);
            String incomeMessage = "Enter customer income: ";
            double income = registerIncome(incomeMessage);

            controller.createCustomer(fullName, personalNo, income, password);
            System.out.println("Customer was successfully registered.");

        } catch (IllegalAccessException scannerError) {
            System.out.println("Invalid input.");

        } catch (InputMismatchException exception) {
            System.out.println("Invalid input. Please enter numbers.");

        } catch (Exception exception) {
            System.out.println(exception.getMessage());

        }
    }

    public void viewCustomerAccount() {
        String personalNo = "";
        do {
            personalNo = UserInput.readLine("Type the personal number of the customer: ");
            if (controller.checkPersonalNr(personalNo)) {
                System.out.println("There is no registered customer with this personal number." + Utilities.EOL +
                        "Please enter an existing customer's personal number: ");
            }
        } while (controller.checkPersonalNr(personalNo));
        String message = controller.getCustomerInfo(personalNo);
        System.out.println(message);
    }

    public void updateCustomerPassword() {
       String  personalNo = UserInput.readLine("Please type the personal number for the customer:");
        do {
            if(!controller.getBank().getUsers().containsKey(personalNo)||!controller.isCustomer(personalNo) || !controller.isPersonNrCorrect(personalNo)){
                System.out.println("There is no customer registered with that personal number.");
                personalNo = UserInput.readLine("Please enter the personal number of a registered customer:");
            }
        }while(!controller.getBank().getUsers().containsKey(personalNo)||!controller.isCustomer(personalNo) || !controller.isPersonNrCorrect(personalNo));
        String newPassword;
        do {
            newPassword = UserInput.readLine("Please type the new password:");
            if(!controller.isStrongPassword(newPassword)){
                System.out.println("The password must have a minimum of 8 characters in length" + Utilities.EOL +
                        "and contain at least  contain: lowercase letter, uppercase letter, digit.");
            }
        }while(!controller.isStrongPassword(newPassword));
        System.out.println(controller.updateCustomerPassword(personalNo, newPassword));
    }

    public void approveLoan() {
        String loanRequestID;
        try {
            do {
                loanRequestID = UserInput.readLine("Enter ID of the loan request: ");
            }while(!controller.checkLoanRequest(loanRequestID));
            String typeOfInterest;
            double interestRate=0;
            do {
                typeOfInterest = UserInput.readLine(" Enter type of interest. Fix or Variable.");
                if(!((typeOfInterest.equalsIgnoreCase("fix")|| typeOfInterest.equalsIgnoreCase("variable")))){
                    System.out.println("Type in fix or variable");
                }
            }while(!((typeOfInterest.equalsIgnoreCase("fix")|| typeOfInterest.equalsIgnoreCase("variable"))));
            String interestRateSrt = "";
            if(typeOfInterest.equalsIgnoreCase("Fix")){
                if(!Utilities.isNumeric(interestRateSrt)) {
                    do {
                        interestRateSrt = UserInput.readLine("Enter interest rate for the loan: ");
                    } while (!Utilities.isNumeric(interestRateSrt));
                }
                interestRate = Double.parseDouble(interestRateSrt);
            }

            if(typeOfInterest.equalsIgnoreCase("variable")){
                interestRate = controller.getBank().getVariableInterestRate();
            }
            String textMessage = UserInput.readLine("Enter message: ");
            String message = controller.approveLoan(loanRequestID,textMessage,interestRate,typeOfInterest);
            System.out.println(message);
        } catch (IllegalAccessException scannerError) {
            System.out.println("Invalid input.");
        } catch (InputMismatchException exception){
            System.out.println("Invalid input. Please enter numbers.");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void declineLoan() {
        String loanRequestID;
        try {
            do {
                loanRequestID = UserInput.readLine("Enter ID of the loan request: ");
            } while (!controller.checkLoanRequest(loanRequestID));
            String textMessage = UserInput.readLine("Enter message: ");
            String message = controller.declineLoanRequest(loanRequestID, textMessage);
            System.out.println(message);
        } catch (IllegalAccessException scannerError) {
            System.out.println("Invalid input.");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void modify(){
        String loanRequestID;
        try {
            do {
                loanRequestID = UserInput.readLine("Enter ID of the loan request: ");
            }while (!controller.checkLoanRequest(loanRequestID));

            String textMessage = UserInput.readLine("Enter message: ");
            String messageTitle = "Modify request";
            String message = controller.sendMessageToACustomer(controller.getBank().getLoanRequests().get(loanRequestID).getPersonalNr(),textMessage, messageTitle);
            System.out.println(message);
        } catch (IllegalAccessException scannerError) {
            System.out.println("Invalid input.");}
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }

    }

    public void interestRateOffer(){
      String loanRequestID;
        try {
            do {
                loanRequestID = UserInput.readLine("Enter ID of the loan request: ");
            }while(!controller.checkLoanRequest(loanRequestID));
            String interestRateStr = UserInput.readLine("Enter interest rate offer for the loan: ");
            do{
                if (!Utilities.isNumeric(interestRateStr)){
                    interestRateStr = UserInput.readLine("Please use only digits. ");
                }
            }while(!Utilities.isNumeric(interestRateStr));
            double interestRate = Double.parseDouble(interestRateStr);
            String  textMessage = UserInput.readLine("Enter message: ");
           String  message = controller.interestOffer(loanRequestID,interestRate,textMessage);
            System.out.println(message);
        } catch (IllegalAccessException scannerError) {
            System.out.println("Invalid input.");
        } catch (InputMismatchException exception){
            System.out.println("Invalid input. Please enter numbers.");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }


    public void approveCardRequest(){
        String cardRequestID = UserInput.readLine("Enter the card request ID: ");
        if(!controller.cardRequestIsPending(cardRequestID)){
            do{
                cardRequestID = UserInput.readLine("There are no pending card requests with this ID." + Utilities.EOL + "Enter the card request ID: ");
            } while (!controller.cardRequestIsPending(cardRequestID));
        }

        String cardNr = UserInput.readLine("Enter card number: ");
        if(cardNr.length() != 16 || !Utilities.isNumber(cardNr)){
            do{
                cardNr= UserInput.readLine("The number must be 16 digits." + Utilities.EOL + "Enter card number: ");
            }while(cardNr.length() != 16 || !Utilities.isNumber(cardNr));
        }
        String cvcStr = UserInput.readLine("Enter cvc number: ");
        if(cvcStr.length() != 3 || !Utilities.isNumber(cvcStr)){
            do{
                cvcStr = UserInput.readLine("The cvc number must be 3 digits." + Utilities.EOL + "Enter cvc number: ");
            }while(cvcStr.length() !=3 || !Utilities.isNumber(cvcStr));
        }
        int cvc = Integer.parseInt(cvcStr);
        //This needs a methode to check, expirationDate
        String expirationDate = UserInput.readLine("Enter expiration date: ");

        String codeStr = UserInput.readLine("Enter code: ");
        if(codeStr.length() != 4 || !Utilities.isNumber(codeStr)){
            do{
                codeStr = UserInput.readLine("The code must be a 4 digit number." + Utilities.EOL + "Enter code: ");
            }while(codeStr.length() != 4 || !Utilities.isNumber(codeStr));
        }
        int code = Integer.parseInt(codeStr);

        try{
           String  message = controller.approveCardRequest(cardRequestID, cardNr, cvc, expirationDate, code);
            System.out.println(message);
        }
        catch (IllegalAccessException scannerError) {
            System.out.println("Invalid input.");
        } catch (InputMismatchException exception){
            System.out.println("Invalid input. Please enter numbers.");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
    public void calculateDTI(){
        String monthlyDebtStr;
        do {
            monthlyDebtStr = UserInput.readLine("Enter the monthly debt: ");
            if (!Utilities.isNumeric(monthlyDebtStr) || monthlyDebtStr.isEmpty()) {
                System.out.println("Invalid input");
            }
        } while (!Utilities.isNumeric(monthlyDebtStr) || monthlyDebtStr.isEmpty());
        double monthlyDebt = Double.parseDouble(monthlyDebtStr);

        String grossIncomeStr;
        do{
            grossIncomeStr = UserInput.readLine("Enter the monthly gross income: ");
            if (!Utilities.isNumeric(grossIncomeStr) || grossIncomeStr.isEmpty()) {
                System.out.println("Invalid input");
            }
        } while (!Utilities.isNumeric(grossIncomeStr) || grossIncomeStr.isEmpty());
        double grossIncome = Double.parseDouble(grossIncomeStr);

        String message = controller.calculateDTI(monthlyDebt,grossIncome);
        System.out.println(message);

    }

    public void monthlyMortgage(){
        String loanStr;
        do {
            loanStr = UserInput.readLine("Enter the loan amount: ");
            if (!Utilities.isNumeric(loanStr) || loanStr.isEmpty()) {
                System.out.println("Invalid input");
            }
        } while (!Utilities.isNumeric(loanStr) || loanStr.isEmpty());
        double loan = Double.parseDouble(loanStr);

        String interestRateStr;
        do {
            interestRateStr = UserInput.readLine("Enter interest rate: ");
            if (!Utilities.isNumeric(interestRateStr) || interestRateStr.isEmpty()) {
                System.out.println("Invalid input");
            }
        } while (!Utilities.isNumeric(interestRateStr) || interestRateStr.isEmpty());
        double interestRate = Double.parseDouble(interestRateStr);

        String loanPeriodStr;
        do {
            loanPeriodStr = UserInput.readLine("Enter loan time (in years): ");
            if (!Utilities.isNumeric(loanPeriodStr) || loanPeriodStr.isEmpty()) {
                System.out.println("Invalid input");
            }
        } while (!Utilities.isNumeric(loanPeriodStr) || loanPeriodStr.isEmpty());
        double loanPeriod = Double.parseDouble(loanPeriodStr);

       String message = controller.calculateMonthlyMortgage(loan,interestRate,loanPeriod);
        System.out.println(message);
    }

    public void annualMortgageHouse(){
        String loanID = UserInput.readLine("Enter the loan ID: ");
        do {
            if(!controller.getBank().getLoans().containsKey(loanID)){
                loanID = UserInput.readLine("Enter an existing loan ID. ");
            }
        }while(!controller.getBank().getLoans().containsKey(loanID));
       String  message =controller.getMortgagePercentage_HouseLoan(loanID);
        System.out.println(message);
    }

    public void applyForVacation(){
        try {
            String daysStr = UserInput.readLine("Enter number of days:");
            do {
                if(!Utilities.isNumber(daysStr) || daysStr.isEmpty() || Double.parseDouble(daysStr) > 31 ){
                    daysStr = UserInput.readLine("Please only enter digits. ");
                }
            }while(!Utilities.isNumber(daysStr) || daysStr.isEmpty() || Double.parseDouble(daysStr) > 31 );
            int days = Integer.parseInt(daysStr);
           String  message = controller.applyForVacation(days);
            System.out.println(message);
        } catch (InputMismatchException exception){
            System.out.println("Invalid input. Please enter numbers.");

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }


    // INBONX

    public boolean checkUnreadMessageExist(){
        if (controller.viewEmployeeUnreadMessages().equals("There are no unread messages.")) {
            return false;
        }
        return true;
    }
    public void viewUnreadMessage() {
        System.out.println(controller.viewEmployeeUnreadMessages());
        if (checkUnreadMessageExist()) {
            String indexStr = "";
            indexStr = UserInput.readLine("Enter the number of the message you want to read: ");
            if (indexStr.isEmpty() || indexStr.isBlank() || !Utilities.isNumber(indexStr) || Integer.parseInt(indexStr) > controller.getBank().getEmployeeInbox().getUnreadMessageInbox().size() - 1 || Integer.parseInt(indexStr) < 0) {
                do {
                    indexStr = UserInput.readLine("Invalid input. Enter the number of the message you want to read: ");
                } while (indexStr.isEmpty() || indexStr.isBlank() || !Utilities.isNumber(indexStr) || Integer.parseInt(indexStr) > controller.getBank().getEmployeeInbox().getUnreadMessageInbox().size() - 1 || Integer.parseInt(indexStr) < 0);
            }
            int index = Integer.parseInt(indexStr);
            MessageFormat textMessage = controller.getBank().getEmployeeInbox().getUnreadMessageInbox().get(index);
            String message = textMessage + Utilities.EOL +
                    "Message: " + Utilities.EOL + controller.readEmployeeUnreadMessage(index);
            System.out.println(message);
            String option = UserInput.readLine("Do you want to move the message to the message history?" +
                    Utilities.EOL + "Yes or no?");
            if (option.equalsIgnoreCase("yes")) {
                controller.removeFromEmployeeUnreadMessages(index);
                controller.addToEmployeeMessageHistory(textMessage);
                System.out.println("The message has been moved to message history.");
            }
        }
    }

    public void sendMessageToCustomer(){
        try{
            String personalNr="";
            do {
                personalNr = UserInput.readLine("Enter the personal number of the customer you want to send a message to: ");
                if (!controller.getBank().getUsers().containsKey(personalNr)||!controller.isCustomer(personalNr) || !controller.isPersonNrCorrect(personalNr)){
                    System.out.println("There is no registered customer with this personal number." + Utilities.EOL +
                            "Please enter an existing customer's personal number: ");
                }
            }while (!controller.getBank().getUsers().containsKey(personalNr)||!controller.isCustomer(personalNr) || !controller.isPersonNrCorrect(personalNr));

            String title = UserInput.readLine("Enter the title: ");
            if(title.isBlank()){
                do{
                    title = UserInput.readLine("Title cannot be blank. Enter the title: ");
                } while (title.isBlank());
            }

            String textMessage = UserInput.readLine("Enter message: ");
            if(textMessage.isBlank()){
                do {
                    textMessage = UserInput.readLine("Message cannot be blank. Enter message: ");
                } while (textMessage.isBlank());
            }

            System.out.println(controller.sendMessageToACustomer(personalNr,title,textMessage));
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public void sendMessageToAllCustomers() {
        try {
            String title = UserInput.readLine("Enter the title: ");
            if (title.isBlank()) {
                do {
                    title = UserInput.readLine("Title cannot be blank. Enter the title: ");
                } while (title.isBlank());
            }

            String textMessage = UserInput.readLine("Enter message: ");
            if (textMessage.isBlank()) {
                do {
                    textMessage = UserInput.readLine("Message cannot be blank. Enter message: ");
                } while (textMessage.isBlank());
            }

            System.out.println(controller.sendMessageToAllCustomers(title, textMessage));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
    public boolean checkMessageHistoryExist(){
        if(controller.viewEmployeeMessageHistory().equals("There are no messages available in message history.")){
            return false;
        }
        return true;
    }
    public void viewMessageHistory(){
        System.out.println(controller.viewEmployeeMessageHistory());
        if(checkMessageHistoryExist()) {
            String indexSrt = UserInput.readLine("Enter the index of the message you want to read: ");
            do {
                if (indexSrt.isEmpty() || indexSrt.isBlank() || !Utilities.isNumber(indexSrt) || Integer.parseInt(indexSrt) > controller.getBank().getEmployeeInbox().getMessageHistory().size() - 1 || Integer.parseInt(indexSrt) < 0) {
                    indexSrt = UserInput.readLine("Please only use digits and choose from an existing one.");
                }
            } while (indexSrt.isEmpty() || indexSrt.isBlank() || !Utilities.isNumber(indexSrt) || Integer.parseInt(indexSrt) > controller.getBank().getEmployeeInbox().getMessageHistory().size() - 1 || Integer.parseInt(indexSrt) < 0);
            int index = Integer.parseInt(indexSrt);
            MessageFormat textMessage2 = controller.getBank().getEmployeeInbox().getMessageHistory().get(index);
            String message = textMessage2 + Utilities.EOL +
                    "Message: " + Utilities.EOL + controller.readMessageEmployeeMessageHistory(index);
            System.out.println(message);
            String option = UserInput.readLine("Do you want to remove the message?" +
                    Utilities.EOL + "Yes or no?");
            if (option.equalsIgnoreCase("yes")) {
                controller.removeFromEmployeeMessageHistory(index);
            }
        }

    }

    public boolean checkSentMessageExist(){
        if(controller.viewEmployeeMessageHistory().equals("There are no messages available in message history.")){
            return false;
        }
        return true;
    }
    public void viewSentMessage() {
        System.out.println(controller.viewEmployeeMessageHistory());
        if (checkSentMessageExist()) {
            String indexSrt = UserInput.readLine("Enter the index of the message you want to read: ");
            do {
                if (indexSrt.isEmpty() || indexSrt.isBlank() || !Utilities.isNumber(indexSrt) || Integer.parseInt(indexSrt) > controller.getBank().getEmployeeInbox().getMessageHistory().size() - 1 || Integer.parseInt(indexSrt) < 0) {
                    indexSrt = UserInput.readLine("Please only use digits and choose from an existing one.");
                }
            } while (indexSrt.isEmpty() || indexSrt.isBlank() || !Utilities.isNumber(indexSrt) || Integer.parseInt(indexSrt) > controller.getBank().getEmployeeInbox().getMessageHistory().size() - 1 || Integer.parseInt(indexSrt) < 0);
            int index = Integer.parseInt(indexSrt);
            MessageFormat textMessage2 = controller.getBank().getEmployeeInbox().getMessageHistory().get(index);
            String message = textMessage2 + Utilities.EOL +
                    "Message: " + Utilities.EOL + controller.readMessageEmployeeMessageHistory(index);
            System.out.println(message);
            String option = UserInput.readLine("Do you want to remove the message?" +
                    Utilities.EOL + "Yes or no?");
            if (option.equalsIgnoreCase("yes")) {
                controller.removeFromEmployeeMessageHistory(index);
            }
        }
    }
    public void bankBalance(){ // 0
        try{
            System.out.println(controller.getTotalBalance());
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
    public void showTotalLoanAmount(){ //1
        try{
            System.out.println(controller.getTotalLoan());
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public void createEmployee(){ //2

        String personalNoMessage = "The Personal number of the employee: ";
        String passwordMessage = "Create a password for the employee: ";
        String incomeMessage = "Please enter the salary of the employee: ";

        try {
            String name = UserInput.readLine("Please enter the name of the employee: ");
            String personalNo = registerPersonalNr(personalNoMessage);
            String password = registerPassword(passwordMessage);
            double salary = registerIncome(incomeMessage);
            System.out.println(controller.createEmployee(name, personalNo, password, salary));

        } catch (InputMismatchException exception) {
            System.out.println("Invalid input");

        } catch (Exception exception) {
            System.out.println(exception.getMessage());

        }
    }
    public void removeEmployee(){//3
        String  personalNo = UserInput.readLine("Please enter the personal number of the employee you want to remove: ");
        if (!controller.isPersonNrCorrect(personalNo) || !controller.isEmployee(personalNo)) {
            do {
                personalNo = UserInput.readLine("Please enter an existing employee's personal number in the format (YYYYMMDDXXXX): ");
            } while (!controller.isPersonNrCorrect(personalNo) || !controller.isEmployee(personalNo));
        }
        try {
            System.out.println(controller.removeEmployee(personalNo));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
    public void updateEmployeeSalary(){//4
        String personalNo = UserInput.readLine("Type the personal number of the employee you wish to change the salary of: ");
        if (!controller.isPersonNrCorrect(personalNo) || !controller.isEmployee(personalNo)) {
            do { //should we have it like this
                personalNo = UserInput.readLine("Please enter an existing employee's personal number in the format (YYYYMMDDXXXX): ");
            } while (!controller.isPersonNrCorrect(personalNo) || !controller.isEmployee(personalNo));
        }
        String salaryMessage = "Write the new salary of the employee: ";
        double salary = registerIncome(salaryMessage);

        System.out.println(controller.setEmployeeSalary(personalNo, salary));
    }
    public void updateEmployeePassword(){//5
        String personalNo = UserInput.readLine("Type the personalNo of the employee you wish to change the password of: ");
        if (!controller.isPersonNrCorrect(personalNo) || !controller.isEmployee(personalNo)) {
            do {
                personalNo = UserInput.readLine("Employee's personal number must be in this format (YYYYMMDDXXXX) and within valid times: ");
            } while (!controller.isPersonNrCorrect(personalNo) || !controller.isEmployee(personalNo));
        }

        String passwordMessage = "Please type the new password of the employee: ";
        String password = registerPassword(passwordMessage);
        System.out.println(controller.setEmployeePassword(password, personalNo));
    }
    public void setInterestRate(){
        try {
            double interestRate = UserInput.readDouble("Enter the variable interest rate: ");
            controller.setVariableInterestRate(interestRate);
        } catch (InputMismatchException exception) {
            System.out.println("Invalid input. Please enter numbers.");

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }








}
