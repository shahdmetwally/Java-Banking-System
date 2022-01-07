package Menu;

import Classes.Customer;
import Inbox.MessageFormat;
import Loans.TypeOfInterest;
import Loans.TypesOfLoan;
import MainController.Controller;

import Utilities.UserInput;
import Utilities.Utilities;

public class OtherServicesCustomerMenu extends CustomerMenu {

    public OtherServicesCustomerMenu(Controller controller) throws Exception {
        super(controller);
    }

    // update loan
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
        TypesOfLoan typesOfLoan = super.loanType();
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
    public void updateCosignerPersonalNr() {
        try {

            String loanID = checkId();
            String personalNr = UserInput.readLine("Please type in the new personal number: ");
            if (!controller.isPersonNrCorrect(personalNr) || personalNr.isEmpty()) {
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
        try {


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

    // customer inbox

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