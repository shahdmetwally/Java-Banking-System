package Menu;


import Classes.Customer;
import Classes.Role;
import Inbox.MessageFormat;
import Loans.TypeOfInterest;
import Loans.TypesOfLoan;
import MainController.Controller;
import MainController.Controller2;
import Utilities.UserInput;
import Utilities.Utilities;

import java.util.HashMap;

public class CustomerMenu {

    protected MenuOptions customerMenu;
    protected MenuOptions otherService;
    protected MenuOptions customerInbox;
    protected MenuOptions updateLoanRequest;
    protected Controller controller;
    protected Controller2 controller2;



    public CustomerMenu(Controller controller) throws Exception {
        if (controller.getUser().getRole() == Role.CUSTOMER) {
            this.customerMenu = new MenuOptions();
            this.otherService = new MenuOptions();
            this.customerInbox = new MenuOptions();
            this.updateLoanRequest = new MenuOptions();
            this.controller = controller;
            this.controller2 = new Controller2(controller);
        }else{
            throw new Exception("Access denied. This menu is only for customers.");
        }
    }

    public void setUpCustomerMenu() {
        customerMenu.setMenuName("Customer Menu " + Utilities.EOL +
                "--------------------" + Utilities.EOL +
                " Choose one of the options below.");
        customerMenu.addOptions(0,"View account number.");
        customerMenu.addOptions(1, "View account balance.");
        customerMenu.addOptions(2, "Deposit money.");
        customerMenu.addOptions(3, "Withdraw cash.");
        customerMenu.addOptions(4, "Transfer money.");
        customerMenu.addOptions(5, "View 5 latest transaction.");
        customerMenu.addOptions(6, "View all transactions.");
        customerMenu.addOptions(7, "Set a budget.");
        customerMenu.addOptions(8, "Update budget.");
        customerMenu.addOptions(9,"Message inbox");
        customerMenu.addOptions(10, "Other services menu.");
        customerMenu.addOptions(11,"Log out.");

        customerInbox.setMenuName("Customer Inbox Menu " + Utilities.EOL +
                "--------------------" + Utilities.EOL +
                " Choose one of the options below.");
        customerInbox.addOptions(0, "View messages from employees.");
        customerInbox.addOptions(1, "Send message to employees.");
        customerInbox.addOptions(2, "View message history.");
        customerInbox.addOptions(3, "View sent messages.");
        customerInbox.addOptions(4, "Go back to Customer menu.");


        otherService.setMenuName("Other services Menu " + Utilities.EOL +
                "--------------------" + Utilities.EOL +
                " Choose one of the options below.");
        otherService.addOptions(0,"Update name.");
        otherService.addOptions(1,"Update income");
        otherService.addOptions(2,"Apply for new card.");
        otherService.addOptions(3,"Block payment card.");
        otherService.addOptions(4,"Loan request with Co-signer");
        otherService.addOptions(5, "Loan request without Co-signer");
        otherService.addOptions(6,"Update Loan request");
        otherService.addOptions(7,"Return to Customer menu.");

        updateLoanRequest.setMenuName("Update loan request"+ Utilities.EOL +
                "--------------------" + Utilities.EOL +
                " Choose one of the options below.");
        //Update Amount is not coded or connected "Update amount"
        updateLoanRequest.addOptions(0,"Update type of loan");
        updateLoanRequest.addOptions(1,"Update other equities");
        updateLoanRequest.addOptions(2,"Update the time period of the loan");
        updateLoanRequest.addOptions(3,"Update cash contribution");
        updateLoanRequest.addOptions(4,"Update Co-Signer name");
        updateLoanRequest.addOptions(5,"Update Co-Signer personal number");
        updateLoanRequest.addOptions(6,"Update Co-Signers income");
        updateLoanRequest.addOptions(7,"Update type of interest rate");
        updateLoanRequest.addOptions(8,"Return to Customer menu");

    }

    public void viewAccountNr() {
        String message = controller.viewAccountNo();
        System.out.println(message);
    }

    public void viewAccountBalance() {
        String message = controller.viewAccountBalance();
        System.out.println(message);
    }

    public void depositMoney()throws Exception{
        String value = "";
            do {
                value = UserInput.readLine("Please enter the amount you want to deposit: ");
                if (!Utilities.isNumeric(value) || value.isEmpty()) {
                    System.out.println("Invalid input");
                }
            } while (!Utilities.isNumeric(value) || value.isEmpty());
            double actualValue = Double.parseDouble(value);
            String message = controller.depositMoney(actualValue);
            System.out.println(message);


    }

    public void withDrawMoney() throws Exception {
        String amount = "";
            do{
                amount = UserInput.readLine("Please enter the amount you want to withdraw: ");
                if(!Utilities.isNumeric(amount) || amount.isEmpty()){
                    System.out.println("Invalid input");
                }
            }while(!Utilities.isNumeric(amount) || amount.isEmpty());
            double actualAmount = Double.parseDouble(amount);
            String message = controller.withdrawMoney(actualAmount);
            System.out.println(message);

    }

    public void transferMoney() throws Exception {
        String   amount = "";
        try {
           do {

                amount = UserInput.readLine("Please enter the amount you want to transfer: ");
                if (!Utilities.isNumeric(amount) || amount.isEmpty()) {
                    System.out.println("Invalid input");
                }
            } while (!Utilities.isNumeric(amount) || amount.isEmpty());
            double actualAmount = Double.parseDouble(amount);
            String account = "";
            do {
                account = UserInput.readLine("Please enter the account No of the recipient:");
                if (!controller.getBank().getBankAccounts().containsKey(account) || account.isEmpty()) {
                    System.out.println("Please make sure to enter an existing account number. ");
                }
            } while (!controller.getBank().getBankAccounts().containsKey(account) || account.isEmpty());
            String message =controller.transferMoney(actualAmount, account);
            System.out.println(message);
        } catch (Exception exception) {
            transferMoney();
            System.out.println(exception.getMessage());
        }

    }

    public void view5LatestTransactions() {
        try {
            String message=controller.FiveLatestTransaction();
            System.out.println(message);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void viewAllTransactions() {
        String message = controller.transactionHistory();
        System.out.println(message);
    }

    public void setBudget() {
        try{
            String budget = "";
            do{
                budget = UserInput.readLine("Please enter the budget you wish to set for each month: ");
                if(!Utilities.isNumeric(budget) || budget.isEmpty()){
                    System.out.println("Invalid input.");
                }
            }while(!Utilities.isNumeric(budget) || budget.isEmpty());
            double actualBudget = Double.parseDouble(budget);
            controller.updateBudget(actualBudget);
            System.out.println("Your budget was set successfully");
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public void updateBudget() {
        try {
            String budget = "";
            do{
                budget = UserInput.readLine("Please type the updated budget: ");
                if (!Utilities.isNumeric(budget) || budget.isEmpty()){
                    System.out.println("Invalid input. ");
                }
            }while(!Utilities.isNumeric(budget) || budget.isEmpty());
            double actualBudget = Double.parseDouble(budget);
            controller.updateBudget(actualBudget);
            System.out.println("You have updated your budget successfully");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void updateCustomerName() {
        try {
            String newName = UserInput.readLine("Enter new name: ");
            System.out.println(controller.updateCustomerName(newName));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void updateSalary() {
        String salary;
        do {
            salary = UserInput.readLine("Enter salary: ");
            if (!Utilities.isNumeric(salary) || salary.isEmpty()) {
                System.out.println("Invalid input");
            }
        } while (!Utilities.isNumeric(salary) || salary.isEmpty());
        double actualAmount = Double.parseDouble(salary);
        String message = controller.updateSalary(actualAmount);
        System.out.println(message);
    }

    public void applyForCard() {
        String message = controller.applyForCard();
        System.out.println(message);
    }

    public boolean blockCardConfirmation() {
        String option = UserInput.readLine("Confirm blocking of card? " + Utilities.EOL + "Please answer: yes or no");
        if (option.equalsIgnoreCase("yes")) {
            return true;
        }
        return false;
    }

    public void blockCard(){
        String cardBlocked = controller.blockCard();
        System.out.println(cardBlocked);
    }

    public double loanHouseWorth(){
        double houseWorth = 0;
       String houseWorthStr;
        do {
            houseWorthStr = UserInput.readLine("Enter the house value: ");
            if (!Utilities.isNumeric(houseWorthStr) || houseWorthStr.isEmpty() ) {
                System.out.println("Invalid input");
            }
        } while (!Utilities.isNumeric(houseWorthStr) || houseWorthStr.isEmpty());
        houseWorth = Double.parseDouble(houseWorthStr);
        return houseWorth;
    }

    public TypesOfLoan loanType() {

        System.out.println(" Loan options: " + Utilities.EOL +
                "1. Personal loan " + Utilities.EOL +
                "2. House loan " + Utilities.EOL +
                "3. Car loan" + Utilities.EOL +
                "4. Unsecured loan");
        TypesOfLoan typesOfLoan = null;

        String newOptionStr;
        do {
            newOptionStr = UserInput.readLine("Enter loan option: ");
            if (!Utilities.isNumber(newOptionStr) || newOptionStr.isEmpty()) {
                System.out.println("Invalid input");
            }
        } while (!Utilities.isNumber(newOptionStr) || newOptionStr.isEmpty());
        int newOption = Integer.parseInt(newOptionStr);

        do {
            switch (newOption) {
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
                    do {
                        newOptionStr = UserInput.readLine("Enter loan option: ");
                        if (!Utilities.isNumber(newOptionStr) || newOptionStr.isEmpty()) {
                            System.out.println("Invalid input");
                        }
                    } while (!Utilities.isNumber(newOptionStr) || newOptionStr.isEmpty());
                    newOption = Integer.parseInt(newOptionStr);
            }
        } while (newOption > 4 || newOption < 1);

        return typesOfLoan;
    }

    public double loanAmount(){
        String loanAmountStr = "";
        do {
            loanAmountStr = UserInput.readLine("Enter the amount of loan: ");
            if (!Utilities.isNumeric(loanAmountStr) || loanAmountStr.isEmpty()) {
                System.out.println("Invalid input");
            }
        } while (!Utilities.isNumeric(loanAmountStr) || loanAmountStr.isEmpty());
        double loanAmount = Double.parseDouble(loanAmountStr);
        return loanAmount;
    }

    public TypeOfInterest loanInterestType(){
        TypeOfInterest interestType = null;
        String typeOfInterest;
        do {
            typeOfInterest = UserInput.readLine(" Enter type of interest. Fix or Variable.");
            if (!(typeOfInterest.equalsIgnoreCase("fix") || typeOfInterest.equalsIgnoreCase("variable"))) {
                System.out.println("Type in fix or variable");
            }
        } while (!(typeOfInterest.equalsIgnoreCase("fix") || typeOfInterest.equalsIgnoreCase("variable")));

        if (typeOfInterest.equalsIgnoreCase("Fix")) {
            interestType = TypeOfInterest.FIX_RATE;
        }
        if (typeOfInterest.equalsIgnoreCase("variable")) {
            interestType = TypeOfInterest.VARIABLE_RATE;
        }
        return interestType;
    }

    public double loanPeriod(){
        String timeStr = "";
        do {
            timeStr = UserInput.readLine("Enter loan time (years):  ");
            if (!Utilities.isNumeric(timeStr) || timeStr.isEmpty()) {
                System.out.println("Invalid input");
            }
        } while (!Utilities.isNumeric(timeStr) || timeStr.isEmpty());
        double time = Double.parseDouble(timeStr);
        return time;
    }

    public HashMap<String,Double> loanEquities(){
        String addEquities;
        HashMap<String, Double> temp = controller2.temporaryHashMap();
        do {
            String otherEquity = UserInput.readLine("Enter other equity name." + Utilities.EOL + "The name of the equity cannot be repeated");
            do{
                if (otherEquity.isBlank() || otherEquity.isEmpty()){
                    otherEquity = UserInput.readLine("The name cannot be empty. ");
                }
            }while(otherEquity.isBlank() || otherEquity.isEmpty());
            String otherEquitiesValueStr;
            do {
                otherEquitiesValueStr = UserInput.readLine("Enter other equities value: ");
                if (!Utilities.isNumeric(otherEquitiesValueStr) || otherEquitiesValueStr.isEmpty()) {
                    System.out.println("Invalid input");
                }
            } while (!Utilities.isNumeric(otherEquitiesValueStr) || otherEquitiesValueStr.isEmpty());
            double otherEquitiesValue = Double.parseDouble(otherEquitiesValueStr);

            System.out.println(controller2.addEquities(otherEquity, otherEquitiesValue, temp));
            addEquities = UserInput.readLine("Do you want to add another equity?" + Utilities.EOL + " Press yes if you wish to add another equity or press any key to continue. ");
            do {
                if (addEquities.isEmpty()) {
                    addEquities = UserInput.readLine("Press yes if you wish to add another equity or press any key to continue");
                }
            }while ( addEquities.isEmpty());
        } while (addEquities.equalsIgnoreCase("Yes"));
        return temp;
    }

    public double loanContribution(double loanAmount){
        String cashContributionStr;
        double cashContribution = 0;
        double fifteenPercent = loanAmount * 0.15;
        do {
            do {
                cashContributionStr = UserInput.readLine("Enter cash contribution:" + Utilities.EOL + "Should be at least 15% of the total loan amount (" + fifteenPercent + ").");


                if (!Utilities.isNumeric(cashContributionStr) || cashContributionStr.isEmpty()) {
                    System.out.println("Invalid input");
                }
            } while (!Utilities.isNumeric(cashContributionStr) || cashContributionStr.isEmpty());
            cashContribution = Double.parseDouble(cashContributionStr);
        } while (!controller.isCashContributionCorrect(cashContribution, loanAmount));
        return cashContribution;
    }

    public void loanRequestWithCoSigner() {
        if (!(controller.getCustomer(controller.getUser().getPersonalNo()).getBankAccount().getLoan() == null)) {
            System.out.println("You have already applied for a lone");
        } else {
            TypesOfLoan typesOfLoan = loanType();
            double loanAmount = loanAmount();
            double houseWorth = 0;
            if( typesOfLoan == TypesOfLoan.PERSONAL_LOAN){
                 houseWorth = loanHouseWorth();
            }
            TypeOfInterest interestType = loanInterestType();
            double time = loanPeriod();
            HashMap<String, Double> tempEquity = loanEquities();
            double cashContribution = loanContribution(loanAmount);

            String coSigner_name = UserInput.readLine("Enter Co-signer name: ");
            do {
                if (coSigner_name.isBlank() || coSigner_name.isEmpty()){
                    coSigner_name = UserInput.readLine("Enter Co-signer name: ");
                }
            }while (coSigner_name.isBlank() || coSigner_name.isEmpty());

            String coSigner_personalNr;
            do {
                coSigner_personalNr = UserInput.readLine("Enter Co-signer personal number: ");
                if (!controller.isPersonNrCorrect(coSigner_personalNr) || coSigner_personalNr.isEmpty()) {
                    System.out.println("Invalid personalNo");
                }
            } while (!controller.isPersonNrCorrect(coSigner_personalNr) || coSigner_personalNr.isEmpty());

            String coSigner_salaryStr = "";
            do {
                coSigner_salaryStr = UserInput.readLine("Enter Co-signer salary: ");
                if (!Utilities.isNumeric(coSigner_salaryStr) || coSigner_salaryStr.isEmpty()) {
                    System.out.println("Invalid input");
                }
            } while (!Utilities.isNumeric(coSigner_salaryStr) || coSigner_salaryStr.isEmpty());
            double coSigner_salary = Double.parseDouble(coSigner_salaryStr);

           String message = controller.loanRequestWithCoSigner(loanAmount, typesOfLoan, interestType, houseWorth, time, tempEquity, cashContribution, coSigner_name, coSigner_personalNr, coSigner_salary);
            System.out.println(message);
        }
    }

    public void loanRequestWithOutCoSigner() {
        if (!(controller.getCustomer(controller.getUser().getPersonalNo()).getBankAccount().getLoan() == null)) {
            System.out.println("You have already applied for a lone");
        } else {
            TypesOfLoan typesOfLoan = loanType();
            double loanAmount = loanAmount();
            double houseWorth = 0;
            if (typesOfLoan == TypesOfLoan.PERSONAL_LOAN) {
                houseWorth = loanHouseWorth();
            }
            TypeOfInterest interestType = loanInterestType();
            double time = loanPeriod();
            HashMap<String, Double> tempEquity = loanEquities();
            double cashContribution = loanContribution(loanAmount);
            String message = controller.loanRequestWithOutCoSigner(loanAmount, typesOfLoan, interestType, houseWorth, time, tempEquity, cashContribution);
            System.out.println(message);
        }
    }

    public String checkId(){
        String loanID = UserInput.readLine("Enter the loan ID: ");
        do {
            if(!controller.getBank().getLoanRequests().containsKey(loanID)){
                loanID = UserInput.readLine("Enter an existing loan ID. ");
            }
        }while(!controller.getBank().getLoanRequests().containsKey(loanID));
        return loanID;
    }

    public void updateLoanType(){
        String ID = checkId();
        TypesOfLoan typesOfLoan = loanType();
        System.out.println(controller2.updateTypeOfLoan(ID,typesOfLoan));

    }

    public void updateEquities(){
        String id = checkId();

        String otherEquity = UserInput.readLine("Please type in the equity you want to change: ");
        do{
            if(otherEquity.isEmpty() || !controller.getBank().getSpecificLoanRequest(id).getEquities().containsKey(otherEquity)||otherEquity.isBlank()){
                otherEquity = UserInput.readLine("Please type something: ");
            }
        }while(otherEquity.isEmpty() || otherEquity.isBlank() ||  !controller.getBank().getSpecificLoanRequest(id).getEquities().containsKey(otherEquity));
        String newEquityValueStr = UserInput.readLine("Please type the new equity value: ");
        do{
            if(!Utilities.isNumeric(newEquityValueStr)){
                newEquityValueStr = UserInput.readLine("Please only use digits for the value. ");
            }
        }while(!Utilities.isNumeric(newEquityValueStr));
        double newEquityValue = Double.parseDouble(newEquityValueStr);
        System.out.println(controller2.updateEquities(id,otherEquity, newEquityValue));
    }

    public void updateTimePeriod(){
        String ID = checkId();
        String loanPeriodStr = UserInput.readLine("Please type in the loan period: ");
        do {
            if (!Utilities.isNumber(loanPeriodStr) || loanPeriodStr.isEmpty()) {
                loanPeriodStr = UserInput.readLine("Please only use digits for the value. ");
            }
        }while(!Utilities.isNumber(loanPeriodStr) || loanPeriodStr.isEmpty());
        int loanPeriod = Integer.parseInt(loanPeriodStr);
        System.out.println(controller2.updateTimePeriod(ID, loanPeriod));
    }

    public void updateCashContribution(){
        String ID = checkId();
        String cashContributionStr = UserInput.readLine("Please type in the cash contribution");
        do{
            if(!Utilities.isNumeric(cashContributionStr) || cashContributionStr.isEmpty()){
                cashContributionStr = UserInput.readLine("Please only use digits for the value. ");
            }
        }while(!Utilities.isNumeric(cashContributionStr) || cashContributionStr.isEmpty());
        double cashContribution = Double.parseDouble(cashContributionStr);
        System.out.println(controller2.updateCashContribution(ID, cashContribution));
    }

    public void updateCosignerName(){
        try {

            String loanID = checkId();
            String coSigner_name = UserInput.readLine("Please type in the new Co-Signer name: ");
            do {
                if (coSigner_name.isEmpty() || coSigner_name.isBlank()) {
                    coSigner_name = UserInput.readLine("Please type in the new Co-Signer name: ");
                }
            } while (coSigner_name.isEmpty() || coSigner_name.isBlank());
            System.out.println(controller2.updateCoSigner_name(loanID, coSigner_name));
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public void updateCosignerPersonalNr(){
        try {
        String loanID = checkId();
        String personalNr = UserInput.readLine("Please type in the new personal number: ");
        if(!controller.isPersonNrCorrect(personalNr) || personalNr.isEmpty()) {
            do {
                personalNr = UserInput.readLine("The personal number must be in this format (YYYYMMDDXXXX) and within valid times: ");
            } while (!controller.isPersonNrCorrect(personalNr) || personalNr.isEmpty());
        }
        System.out.println(controller2.updateCoSigner_personalNr(loanID, personalNr));
    }catch (Exception exception){
        System.out.println(exception.getMessage());
    }
    }

    public void updateCosignerIncome(){
       try{
           String loanID = checkId();
        String coSigner_salaryStr;
        do {
            coSigner_salaryStr = UserInput.readLine("Please type in the new Co-Signer salary");
            if (!Utilities.isNumeric(coSigner_salaryStr) || coSigner_salaryStr.isEmpty()) {
                System.out.println("Invalid input");
            }
        } while (!Utilities.isNumeric(coSigner_salaryStr) || coSigner_salaryStr.isEmpty());
        double coSigner_salary = Double.parseDouble(coSigner_salaryStr);

        System.out.println(controller2.updateCoSigner_salary(loanID, coSigner_salary));
    }catch (Exception exception){
        System.out.println(exception.getMessage());
    }
    }

    public void updateInterestType(){
        String loanID = checkId();
        String typeOfInterest;
        do {
            typeOfInterest = UserInput.readLine(" Enter type of interest. Fix or Variable.");
            if(!(typeOfInterest.equalsIgnoreCase("fix")|| typeOfInterest.equalsIgnoreCase("variable"))){
                System.out.println("Type in fix or variable");
            }
        }while(!(typeOfInterest.equalsIgnoreCase("fix")|| typeOfInterest.equalsIgnoreCase("variable")));

        TypeOfInterest interestType = null;
        if(typeOfInterest.equalsIgnoreCase("Fix")){
            interestType = TypeOfInterest.FIX_RATE;
        }
        if(typeOfInterest.equalsIgnoreCase("variable")){
            interestType = TypeOfInterest.VARIABLE_RATE;
        }
        System.out.println(controller2.updateInterestType(loanID, interestType));
    }

    public void viewMessages(){
        String indexStr = UserInput.readLine("Enter the number of the message you want to read: ");
        if(indexStr.isEmpty() || !Utilities.isNumber(indexStr) || Integer.parseInt(indexStr)<0 || Integer.parseInt(indexStr)>((Customer)controller.getUser()).getInbox().getUnreadMessageInbox().size()-1){
            do{
                indexStr = UserInput.readLine("Invalid input. Enter the number of the message you want to read: ");
            } while(indexStr.isEmpty() || !Utilities.isNumber(indexStr) || Integer.parseInt(indexStr)<0 || Integer.parseInt(indexStr)>((Customer)controller.getUser()).getInbox().getUnreadMessageInbox().size()-1);
        }
        int index = Integer.parseInt(indexStr);
        MessageFormat textMessage = ((Customer)controller.getUser()).getInbox().getUnreadMessageInbox().get(index);
        String message = textMessage + Utilities.EOL +
                "Message: " + Utilities.EOL + controller.readCustomerUnreadMessage(index);
        System.out.println(message);
        String option = UserInput.readLine("Do you want to move the message to the message history?" +
                Utilities.EOL + "Yes or no?");
        if (option.equalsIgnoreCase("yes")) {
            controller2.removeFromCustomerUnreadMessages(index);
            controller2.addToCustomerMessageHistory(textMessage);
            System.out.println("The message has been moved to message history.");
        }
    }

    public void sendMessage(){

        String title = UserInput.readLine("Enter message title: ");
        if(title.isBlank()){
            do {
                title = UserInput.readLine("Title cannot be blank. Enter message title: ");
            } while (title.isBlank());
        }

        String message = UserInput.readLine("Please type the message that you would like to send to Customer Support: ");
        if(message.isBlank()){
            do{
                message = UserInput.readLine("Message cannot be blank." + Utilities.EOL +
                        "Please type the message that you would like to send to Customer Support: ");
            } while(message.isBlank());
        }
        System.out.println(controller2.sendMessageToEmployees(title, message));
    }

    public void viewMessageHistory(){
        System.out.println(controller2.viewCustomerMessageHistory());
        String indexStr = UserInput.readLine("Enter the number of the message you want to read: ");
        if(indexStr.isEmpty() || !Utilities.isNumber(indexStr) || Integer.parseInt(indexStr)>((Customer)controller.getUser()).getInbox().getMessageHistory().size()-1 || Integer.parseInt(indexStr)<0){
            do{
                indexStr = UserInput.readLine("Invalid input. Enter the number of the message you want to read: ");
            } while(indexStr.isEmpty() || !Utilities.isNumber(indexStr) || Integer.parseInt(indexStr)>((Customer)controller.getUser()).getInbox().getMessageHistory().size()-1|| Integer.parseInt(indexStr)<0);
        }
        int index = Integer.parseInt(indexStr);
        MessageFormat textMessage = ((Customer)controller.getUser()).getInbox().getMessageHistory().get(index);
        String message = textMessage + Utilities.EOL +
                "Message: " + Utilities.EOL + controller2.readMessageCustomerMessageHistory(index);
        System.out.println(message);
        String option = UserInput.readLine("Do you want to remove the message from the message history?" +
                Utilities.EOL + "Yes or no?");
        if (option.equalsIgnoreCase("yes")) {
            controller2.removeFromCustomerMessageHistory(index);
            System.out.println("The message has been removed.");
        }
    }
}
