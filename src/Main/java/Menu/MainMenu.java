package Menu;

import Bank.Bank;
import Classes.Role;
import Classes.User;
import Inbox.ManagerInbox;
import Inbox.MessageFormat;
import Loans.TypeOfInterest;
import MainController.Controller;
import MainController.StartProgram;
import Utilities.UserInput;
import Utilities.Utilities;
import Loans.TypesOfLoan;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.InputMismatchException;


public class MainMenu {

    private final MenuOptions mainMenu;
    private final MenuOptions customer;
    private final MenuOptions otherService;
    private final MenuOptions administration;
    private final MenuOptions employee;
    private final MenuOptions manager;
    private Bank bank;
    private final MenuOptions customerInbox;
    private final MenuOptions employeeInbox;
    private final MenuOptions managerInbox;
    private final MenuOptions updateLoanRequest;

    public MainMenu(Bank bank) {
        this.mainMenu = new MenuOptions();
        this.administration = new MenuOptions();
        this.customer = new MenuOptions();
        this.otherService = new MenuOptions();
        this.manager = new MenuOptions();
        this.employee = new MenuOptions();
        this.bank = bank;
        this.customerInbox = new MenuOptions();
        this.employeeInbox = new MenuOptions();
        this.managerInbox = new MenuOptions();
        this.updateLoanRequest = new MenuOptions();

    }

    public void setUpMainMenu() {
        String menuName = "Main Menu " + Utilities.EOL +
                "--------------------" + Utilities.EOL +
                " Choose one of the options below.";
        mainMenu.setMenuName(menuName);
        mainMenu.addOptions(0, "Log in as a Customer.");
        mainMenu.addOptions(1, "Log in as a Employee.");
        mainMenu.addOptions(2, "System Administration.");
        mainMenu.addOptions(3, "Close System.");

        administration.setMenuName("Administration Menu " + Utilities.EOL +
                "--------------------" + Utilities.EOL +
                " Choose one of the options below.");
        administration.addOptions(0, "Change administration password.");
        administration.addOptions(1,"Create manager.");
        administration.addOptions(2, "Remove manager.");
        administration.addOptions(3,"Update manager salary.");
        administration.addOptions(4,"Change manager password.");
        administration.addOptions(5,"Promote employee.");;
        administration.addOptions(6,"Log out.");
        administration.addOptions(7, "show all manager");

        customer.setMenuName("Customer Menu " + Utilities.EOL +
                "--------------------" + Utilities.EOL +
                " Choose one of the options below.");
        customer.addOptions(0,"View account number.");
        customer.addOptions(1, "View account balance.");
        customer.addOptions(2, "Deposit money.");
        customer.addOptions(3, "Withdraw cash.");
        customer.addOptions(4, "Transfer money.");
        customer.addOptions(5, "View 5 latest transaction.");
        customer.addOptions(6, "View all transactions.");
        customer.addOptions(7, "Set a budget.");
        customer.addOptions(8, "Update budget.");
        customer.addOptions(9,"Message inbox");
        customer.addOptions(10, "Other services menu.");

        // should be the last option, if you want to add something, add it above!
        customer.addOptions(11,"Log out.");

        customerInbox.setMenuName("Customer Inbox Menu " + Utilities.EOL +
                "--------------------" + Utilities.EOL +
                " Choose one of the options below.");
        customerInbox.addOptions(0, "View messages from employees.");
        customerInbox.addOptions(1, "Send message to employees.");
        customerInbox.addOptions(2, "View old messages.");
        customerInbox.addOptions(3, "Remove oldest message.");
        customerInbox.addOptions(4, "Go back to Customer menu.");


        otherService.setMenuName("Other services Menu " + Utilities.EOL +
                "--------------------" + Utilities.EOL +
                " Choose one of the options below.");
        otherService.addOptions(0,"Update name.");
        otherService.addOptions(1,"Update salary");
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
        updateLoanRequest.addOptions(6,"Update Co-Signers salary");
        updateLoanRequest.addOptions(7,"Update type of interest rate");
        updateLoanRequest.addOptions(8,"Return to Customer menu");

        employee.setMenuName("Employee Menu " + Utilities.EOL +
                "--------------------" + Utilities.EOL +
                " Choose one of the options below.");
        employee.addOptions(0, "Create Customer.");
        employee.addOptions(1, "View a customer's account.");
        employee.addOptions(2, "Approve loans.");
        employee.addOptions(3,"Approve card requests.");
        employee.addOptions(4,"Calculate Debt-to-income (DTI) ratio.");
        employee.addOptions(5,"Calculate monthly mortgage.");
        employee.addOptions(6,"Update customer password.");
        employee.addOptions(7, "Calculate the annual mortgage of a house loan");
        employee.addOptions(8, "View salary.");
        employee.addOptions(9, "Apply for vacation.");
        employee.addOptions(10, "Request inbox.");
        employee.addOptions(11, "Manager options.");
        employee.addOptions(12, "Log out.");

        employeeInbox.setMenuName("Employee Inbox Menu " + Utilities.EOL +
                "--------------------" + Utilities.EOL +
                " Choose one of the options below.");
        employeeInbox.addOptions(0, "View messages from customer.");
        employeeInbox.addOptions(1, "Send message to customer.");
        employeeInbox.addOptions(2, "View message history."); // maybe history
        employeeInbox.addOptions(3, "Remove oldest message.");
        employeeInbox.addOptions(4, "Go back to Employee menu.");


        manager.setMenuName("Manager Menu " + Utilities.EOL +
                "--------------------" + Utilities.EOL +
                " Choose one of the options below.");
        manager.addOptions(0, "Show Bank Balance.");
        manager.addOptions(1, "Show total loaned amount.");
        manager.addOptions(2, "Create employee.");
        manager.addOptions(3, "Remove employee.");
        manager.addOptions(4, "Update employee salary.");
        manager.addOptions(5, "Update employee password.");
        manager.addOptions(6,"Set variable interest rate.");
        manager.addOptions(7,"View manager inbox.");
        manager.addOptions(8,"Return to employee menu.");

        managerInbox.setMenuName("Manager Inbox Menu " + Utilities.EOL +
                "--------------------" + Utilities.EOL +
                " Choose one of the options below.");
        managerInbox.addOptions(0, "View all employees' vacation applications.");
        managerInbox.addOptions(1, "Approve an employee's vacation application.");
    }

    public String enterPersonalNr(){
        String personNr = UserInput.readLine("Enter the customer's personal number: ");
        return personNr;
    }

    public Controller login() throws Exception{
        /*
            If we separe the menu we can add the controller as an attribute and
            create it with this method.
         */
        String userName = "";
        do {
            userName = UserInput.readLine("Enter username: ");
            if(!bank.getUsers().containsKey(userName)){
                System.out.println("Invalid input or user doesn't exists");
            }
        }while(!bank.getUsers().containsKey(userName));
            String password = UserInput.readLine("Enter password: ");
            Controller controller = new Controller(userName, password,bank);
            return controller;
    }


    public void handleMainMenu(){
        setUpMainMenu();
        this.mainMenu.printOptions();
        String userChoiceStr ;
        do{
            userChoiceStr  = UserInput.readLine("Type in the option: ");
            if(!Utilities.isNumeric(userChoiceStr)|| userChoiceStr.isEmpty()) {
                System.out.println("Invalid input");
            }
        }while(!Utilities.isNumeric(userChoiceStr)|| userChoiceStr.isEmpty());
        int userChoice = Integer.parseInt(userChoiceStr);

            switch (userChoice) {

                case 0:
                    try {
                        handleCustomerMenu(login());
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    handleMainMenu();
                    break;
                case 1:
                    try {
                        handleEmployeeMenu(login());
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                        handleMainMenu();
                    }
                    break;
                case 2:
                    try {
                        handleAdministration(login());
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    handleMainMenu();
                    break;
                case 3:
                    UserInput.input.close();
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        mapper.writeValue(Paths.get("bank.json").toFile(), StartProgram.jsonBank);

                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                    handleMainMenu();
            }
    }

    public void handleCustomerMenu(Controller controller) {

        if(controller.getUser().getRole() == Role.CUSTOMER) {

            this.customer.printOptions();
            String userChoiceStr ;
            do{
                userChoiceStr  = UserInput.readLine("Type in the option: ");
                if(!Utilities.isNumeric(userChoiceStr)|| userChoiceStr.isEmpty()) {
                    System.out.println("Invalid input");
                }
            }while(!Utilities.isNumeric(userChoiceStr)|| userChoiceStr.isEmpty());
            int userChoice = Integer.parseInt(userChoiceStr);
            switch (userChoice) {
                case 0:
                    String message = controller.viewAccountNo();
                    System.out.println(message);
                    handleCustomerMenu(controller);
                    break;
                case 1:
                    message = controller.viewAccountBalance();
                    System.out.println(message);
                    handleCustomerMenu(controller);
                    break;
                case 2:
                    String value = "";
                    try {
                        do {
                            value = UserInput.readLine("Please enter the amount you want to deposit: ");
                            if (!Utilities.isNumeric(value) || value.isEmpty()) {
                                System.out.println("Invalid input");
                            }
                        } while (!Utilities.isNumeric(value) || value.isEmpty());
                        double actualValue = Double.parseDouble(value);
                        message = controller.depositMoney(actualValue);
                        System.out.println(message);
                    }catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }


                handleCustomerMenu(controller);
                break;
            case 3:
                String amount = "";
                try {
                    do{
                        amount = UserInput.readLine("Please enter the amount you want to withdraw: ");
                        if(!Utilities.isNumeric(amount) || amount.isEmpty()){
                            System.out.println("Invalid input");
                        }
                    }while(!Utilities.isNumeric(amount) || amount.isEmpty());
                    double actualAmount = Double.parseDouble(amount);
                    message = controller.withdrawMoney(actualAmount);
                    System.out.println(message);
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }

                handleCustomerMenu(controller);
                break;
            case 4:
                 amount = "";
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
                        account = UserInput.readLine("Please enter the account No of the recievient:");
                        if (!bank.getBankAccounts().containsKey(account) || account.isEmpty()) {
                            System.out.println("Please make sure to enter an existing account number. ");
                        }
                    } while (!bank.getBankAccounts().containsKey(account) || account.isEmpty());
                    message =controller.transferMoney(actualAmount, account);
                    System.out.println(message);
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
                handleCustomerMenu(controller);
                break;
            case 5:
                try {
                    message=controller.FiveLatestTransaction();
                    System.out.println(message);
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
                handleCustomerMenu(controller);
                break;
            case 6:
                message=controller.transactionHistory();
                System.out.println(message);
                handleCustomerMenu(controller);
                break;
            case 7:// inbox
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
                    handleCustomerMenu(controller);
                    break;
                case 8:
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
                    handleCustomerMenu(controller);
                    break;
                case 9:// inbox
                    handleCustomerInbox(controller);
                    break;
                case 10:
                    handleOtherService(controller);
                    break;
                case 11:
                    handleMainMenu();
                default:
                    System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                    handleCustomerMenu(controller);
                    int option = UserInput.readInt(" Enter 0 to try again." + Utilities.EOL + " Enter 1 to go back to main menu. ");
                    do {
                        switch (option) {
                            case 0:
                                handleCustomerMenu(controller);
                                break;
                            case 1:
                                handleMainMenu();
                            default:
                                System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                        }
                    } while (option < 0 || option > 1);
            }
        } else {
            System.out.println("Access denied. This menu is only for customers.");
            handleMainMenu();
        }
    }

    public void handleOtherService(Controller controller){
        this.otherService.printOptions();
        String userChoiceStr ;
        do{
            userChoiceStr  = UserInput.readLine("Type in the option: ");
            if(!Utilities.isNumeric(userChoiceStr)|| userChoiceStr.isEmpty()) {
                System.out.println("Invalid input");
            }
        }while(!Utilities.isNumeric(userChoiceStr)|| userChoiceStr.isEmpty());
        int userChoice = Integer.parseInt(userChoiceStr);
        switch (userChoice) {
            case 0: //Update name
                try {
                    String newName = UserInput.readLine("Enter new name: ");
                    System.out.println(controller.updateCustomerName(newName));
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
                handleOtherService(controller);
                break;
            case 1: //Update salary
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
                handleOtherService(controller);
                break;
            case 2: //Apply for new card
                message = controller.applyForCard();
                System.out.println(message);

                handleOtherService(controller);
                break;
            case 3:

                    String option = UserInput.readLine("Confirm blocking of card? " + Utilities.EOL + "Please answer: yes or no");
                    if (option.equalsIgnoreCase("yes")) {
                        String cardBlocked = controller.blockCard();
                        System.out.println(cardBlocked);
                        handleOtherService(controller);
                    } else {
                        handleOtherService(controller);
                    }


                break;
            case 4:  //Loan request with Co-signer
                if (!(controller.getCustomer(controller.getUser().getPersonalNo()).getBankAccount().getLoan() == null)) {
                    System.out.println("You have already applied for a lone");
                } else {
                    System.out.println(" Loan options: " + Utilities.EOL +
                            "1. Personal loan " + Utilities.EOL +
                            "2. House loan " + Utilities.EOL +
                            "3. Car loan" + Utilities.EOL +
                            "4. Unsecured loan");
                    TypesOfLoan typesOfLoan = null;
                    double houseWorth = 0;
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

                                String houseWorthStr;
                                do {
                                    houseWorthStr = UserInput.readLine("Enter the house value: ");
                                    if (!Utilities.isNumeric(houseWorthStr) || houseWorthStr.isEmpty()) {
                                        System.out.println("Invalid input");
                                    }
                                } while (!Utilities.isNumeric(houseWorthStr) || houseWorthStr.isEmpty());
                                houseWorth = Double.parseDouble(houseWorthStr);
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

                    String loanAmountStr = "";
                    do {
                        loanAmountStr = UserInput.readLine("Enter the amount of loan: ");
                        if (!Utilities.isNumeric(loanAmountStr) || loanAmountStr.isEmpty()) {
                            System.out.println("Invalid input");
                        }
                    } while (!Utilities.isNumeric(loanAmountStr) || loanAmountStr.isEmpty());
                    double loanAmount = Double.parseDouble(loanAmountStr);

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

                    String timeStr = "";
                    do {
                        timeStr = UserInput.readLine("Enter loan time (years):  ");
                        if (!Utilities.isNumeric(timeStr) || timeStr.isEmpty()) {
                            System.out.println("Invalid input");
                        }
                    } while (!Utilities.isNumeric(timeStr) || timeStr.isEmpty());
                    double time = Double.parseDouble(timeStr);

                    String addEquities;
                    HashMap<String, Double> temp = controller.temporaryHashMap();
                    do {
                        String otherEquity = UserInput.readLine("Enter other equity name." + Utilities.EOL + "The name of the equity cannot be repeated");
                        String otherEquitiesValueStr;
                        do {
                            otherEquitiesValueStr = UserInput.readLine("Enter other equities value: ");
                            if (!Utilities.isNumeric(otherEquitiesValueStr) || otherEquitiesValueStr.isEmpty()) {
                                System.out.println("Invalid input");
                            }
                        } while (!Utilities.isNumeric(otherEquitiesValueStr) || otherEquitiesValueStr.isEmpty());
                        double otherEquitiesValue = Double.parseDouble(otherEquitiesValueStr);

                        System.out.println(controller.addEquities(otherEquity, otherEquitiesValue, temp));
                        addEquities = UserInput.readLine("Do you want to add another equity?");
                    } while (addEquities.equalsIgnoreCase("Yes"));

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

                    String coSigner_name = UserInput.readLine("Enter Co-signer name: ");

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

                    message = controller.loanRequestWithCoSigner(loanAmount, typesOfLoan, interestType, houseWorth, time, temp, cashContribution, coSigner_name, coSigner_personalNr, coSigner_salary);
                    System.out.println(message);
                }

                // controller:
                handleCustomerMenu(controller);
                break;
            case 5: //Loan request without Co-signer
                if(!(controller.getCustomer(controller.getUser().getPersonalNo()).getBankAccount().getLoan()==null)){
                    System.out.println("You have already applied for a lone");
                }else {

                    System.out.println(" Loan options: " + Utilities.EOL +
                            "1. Personal loan " + Utilities.EOL +
                            "2. House loan " + Utilities.EOL +
                            "3. Car loan" + Utilities.EOL +
                            "4. Unsecured loan");
                    TypesOfLoan typesOfLoan = null;
                    String newOptionStr;
                    do {
                       newOptionStr = UserInput.readLine("Enter loan option: ");
                        if (!Utilities.isNumeric(newOptionStr) || newOptionStr.isEmpty()) {
                            System.out.println("Invalid input");
                        }
                    } while (!Utilities.isNumeric(newOptionStr) || newOptionStr.isEmpty());
                    int newOption = Integer.parseInt(newOptionStr);
                    double houseWorth=0;

                    do {
                        switch (newOption) {
                            case 1:
                                typesOfLoan = TypesOfLoan.PERSONAL_LOAN;
                                String houseWorthStr;
                                do {
                                    houseWorthStr = UserInput.readLine("Enter the house value: ");
                                    if (!Utilities.isNumeric(houseWorthStr) || houseWorthStr.isEmpty()) {
                                        System.out.println("Invalid input");
                                    }
                                } while (!Utilities.isNumeric(houseWorthStr) || houseWorthStr.isEmpty());
                                houseWorth = Double.parseDouble(houseWorthStr);
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
                                do {
                                    System.out.println("Invalid choice. Please select between option 1 to 4. ");
                                    newOptionStr = UserInput.readLine("Enter loan option: ");
                                    if (!Utilities.isNumeric(newOptionStr) || newOptionStr.isEmpty()) {
                                        System.out.println("Invalid input");
                                    }
                                } while (!Utilities.isNumeric(newOptionStr) || newOptionStr.isEmpty());
                                newOption = Integer.parseInt(newOptionStr);

                        }
                    } while (newOption > 4 || newOption < 1);
                    String loanAmountStr;
                    do {
                        loanAmountStr = UserInput.readLine("Enter the amount of loan: ");
                        if (!Utilities.isNumeric(loanAmountStr) || loanAmountStr.isEmpty()) {
                            System.out.println("Invalid input");
                        }
                    } while (!Utilities.isNumeric(loanAmountStr) || loanAmountStr.isEmpty());
                    double loanAmount = Double.parseDouble(loanAmountStr);

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

                    String timeStr;
                    do {
                        timeStr = UserInput.readLine("Enter loan time (years):  ");
                        if (!Utilities.isNumeric(timeStr) || timeStr.isEmpty()) {
                            System.out.println("Invalid input");
                        }
                    } while (!Utilities.isNumeric(timeStr) || timeStr.isEmpty());
                    double time = Double.parseDouble(timeStr);


                    HashMap<String, Double> tempHash = controller.temporaryHashMap();
                    String addEquities;
                    double fifteenPercent= loanAmount*0.15;
                    do {
                        String otherEquity = UserInput.readLine("Enter other equity name" + Utilities.EOL + "The name of the equity cannot be repeated(" + fifteenPercent + ").");
                        String otherEquitiesValueStr;

                        do {
                            otherEquitiesValueStr = UserInput.readLine("Enter other equities value: ");
                            if (!Utilities.isNumeric(otherEquitiesValueStr) || otherEquitiesValueStr.isEmpty()) {
                                System.out.println("Invalid input");
                            }
                        } while (!Utilities.isNumeric(otherEquitiesValueStr) || otherEquitiesValueStr.isEmpty());
                        double otherEquitiesValue = Double.parseDouble(otherEquitiesValueStr);

                        System.out.println(controller.addEquities(otherEquity, otherEquitiesValue, tempHash));
                        addEquities = UserInput.readLine("Do you want to add another equity?");
                    } while (addEquities.equalsIgnoreCase("Yes"));

                    String cashContributionStr;
                    double cashContribution;

                    do {
                        do {
                            cashContributionStr = UserInput.readLine("Enter cash contribution:" + Utilities.EOL + "Should be at least 15% of the total loan amount");
                            if (!Utilities.isNumeric(cashContributionStr) || cashContributionStr.isEmpty()) {
                                System.out.println("Invalid input");
                            }
                        } while (!Utilities.isNumeric(cashContributionStr) || cashContributionStr.isEmpty());
                         cashContribution = Double.parseDouble(cashContributionStr);
                    } while (!controller.isCashContributionCorrect(cashContribution, loanAmount));

                    message = controller.loanRequestWithOutCoSigner(loanAmount, typesOfLoan, interestType, houseWorth, time, tempHash, cashContribution);
                    System.out.println(message);
                }
                handleCustomerMenu(controller);
            break;
            case 6: handleUpdateLoanRequest(controller);//Update loan request
                    break;
            case 7: handleCustomerMenu(controller); //Return to customer menu
                break;
            default:
                System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                handleOtherService(controller);
        }
    }
    public void handleCustomerInbox(Controller controller){
        this.customerInbox.printOptions();
        String userChoiceStr ;
        do{
            userChoiceStr  = UserInput.readLine("Type in the option: ");
            if(!Utilities.isNumeric(userChoiceStr)|| userChoiceStr.isEmpty()) {
                System.out.println("Invalid input");
            }
        }while(!Utilities.isNumeric(userChoiceStr)|| userChoiceStr.isEmpty());
        int userChoice = Integer.parseInt(userChoiceStr);
        switch (userChoice) {
            case 0: //View messages from employees
                System.out.println(controller.viewCustomerMessageInbox());
                handleCustomerInbox(controller);
                break;
            case 1: //Send messages to employees
                String title = UserInput.readLine("Enter message title: ");
                String message = UserInput.readLine("Please type the message that you would like to send to the Customer Support: ");
                MessageFormat textMessage = new MessageFormat(title, message);
                if (textMessage.isEmpty()) {
                    System.out.println("Message cannot be empty");
                } else {
                    System.out.println("Your message has been sent successfully.");
                }
                controller.sendMessageToEmployees(title, message);
                handleCustomerInbox(controller);
            case 2://View old messages
                controller.viewCustomerMessageHistory();
                handleCustomerInbox(controller);
                break;
            case 3: //Remove oldest messages
                controller.removeMessageFromCustomer();
                handleCustomerInbox(controller);
                break;
            case 4: //Back to customer menu
                handleCustomerMenu(controller);
                break;
            default:
                System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                handleCustomerInbox(controller);
        }
    }

    public void handleUpdateLoanRequest(Controller controller){
        this.updateLoanRequest.printOptions();
        String userChoiceStr ;
        do{
            userChoiceStr  = UserInput.readLine("Type in the option: ");
            if(!Utilities.isNumeric(userChoiceStr)|| userChoiceStr.isEmpty()) {
                System.out.println("Invalid input");
            }
        }while(!Utilities.isNumeric(userChoiceStr)|| userChoiceStr.isEmpty());
        int userChoice = Integer.parseInt(userChoiceStr);
        switch (userChoice) {
            case 0:
                String ID = UserInput.readLine("Please type in the loanID: ");
                do {
                    if (!bank.getLoanRequests().containsKey(ID)) {
                        ID = UserInput.readLine("Please enter an existing loanID: ");
                    }
                }while(!bank.getLoanRequests().containsKey(ID));
                System.out.println(" Loan options: " + Utilities.EOL +
                        "1. Personal loan " + Utilities.EOL +
                        "2. House loan "+ Utilities.EOL +
                        "3. Car loan"+ Utilities.EOL +
                        "4. Unsecured loan");
                TypesOfLoan typesOfLoan = null;
                String newOptionStr;
                do{
                    newOptionStr  = UserInput.readLine("Enter loan option: ");
                    if(!Utilities.isNumber(newOptionStr)|| newOptionStr.isEmpty()) {
                        System.out.println("Invalid input");
                    }
                }while(!Utilities.isNumber(newOptionStr)|| newOptionStr.isEmpty());
                int newOption = Integer.parseInt(newOptionStr);
                double houseWorth = 0;
                do {
                    switch (newOption) {
                        case 1:
                            typesOfLoan = TypesOfLoan.PERSONAL_LOAN;
                            String houseWorthStr;
                            do{
                                houseWorthStr   = UserInput.readLine("Enter the house value: ");
                                if(!Utilities.isNumeric(houseWorthStr)|| houseWorthStr.isEmpty()) {
                                    System.out.println("Invalid input");
                                }
                            }while(!Utilities.isNumeric(houseWorthStr)|| houseWorthStr.isEmpty());
                            houseWorth= Double.parseDouble(houseWorthStr);
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
                            do{
                                newOptionStr  = UserInput.readLine("Enter loan option: ");
                                if(!Utilities.isNumber(newOptionStr)|| newOptionStr.isEmpty()) {
                                    System.out.println("Invalid input");
                                }
                            }while(!Utilities.isNumber(newOptionStr)|| newOptionStr.isEmpty());
                                newOption = Integer.parseInt(newOptionStr);

                    }
                }while (newOption>4 ||newOption < 1);

                System.out.println(controller.updateTypeOfLoan(ID,typesOfLoan));
                handleCustomerMenu(controller);
                break;
            case 1:
                ID = UserInput.readLine("Please type in the loanID: ");
                do {
                    if (!bank.getLoanRequests().containsKey(ID)) {
                        ID = UserInput.readLine("Please enter an existing loanID: ");
                    }
                }while(!bank.getLoanRequests().containsKey(ID));

                String otherEquity = UserInput.readLine("Please type in the equity you want to change: ");
                String newEquityValueStr = UserInput.readLine("Please type the new equity value: ");
                do{
                    if(!Utilities.isNumeric(newEquityValueStr)){
                        newEquityValueStr = UserInput.readLine("Please only use digits for the value. ");
                    }
                }while(!Utilities.isNumeric(newEquityValueStr));
                double newEquityValue = Double.parseDouble(newEquityValueStr);
                System.out.println(controller.updateEquities(ID,otherEquity, newEquityValue));
                handleUpdateLoanRequest(controller);
                break;

            case 2:
                ID = UserInput.readLine("Please type in the loanID: ");
                do {
                    if (!bank.getLoanRequests().containsKey(ID)) {
                        ID = UserInput.readLine("Please enter an existing loanID: ");
                    }
                }while(!bank.getLoanRequests().containsKey(ID));
                String loanPeriodStr = UserInput.readLine("Please type in the loan period: ");
                do {
                    if (!Utilities.isNumber(loanPeriodStr)) {
                        loanPeriodStr = UserInput.readLine("Plese only use digits for the value. ");
                    }
                }while(!Utilities.isNumber(loanPeriodStr));
                int loanPeriod = Integer.parseInt(loanPeriodStr);
                System.out.println(controller.updateTimePeriod(ID, loanPeriod));
                handleUpdateLoanRequest(controller);
                break;
            case 3:
                ID = UserInput.readLine("Please type in the loanID: ");
                do {
                    if (!bank.getLoanRequests().containsKey(ID)) {
                        ID = UserInput.readLine("Please enter an existing loanID: ");
                    }
                }while(!bank.getLoanRequests().containsKey(ID));
                String cashContributionStr = UserInput.readLine("Please type in the cash contribution");
                do{
                    if(!Utilities.isNumeric(cashContributionStr)){
                        cashContributionStr = UserInput.readLine("Please only use digits for the value. ");
                    }
                }while(!Utilities.isNumeric(cashContributionStr));
                double cashContribution = Double.parseDouble(cashContributionStr);
                System.out.println(controller.updateCashContribution(ID, cashContribution));
                handleUpdateLoanRequest(controller);
                break;
            case 4:
                String loanID = UserInput.readLine("Enter the loan ID: ");
                do {
                    if(!bank.getLoanRequests().containsKey(loanID)){
                        loanID = UserInput.readLine("Enter an existing loan ID. ");
                    }
                }while(!bank.getLoanRequests().containsKey(loanID));
                String coSigner_name = UserInput.readLine("Please type in the new Co-Signer name: ");
                System.out.println(controller.updateCoSigner_name(loanID,coSigner_name));
                handleUpdateLoanRequest(controller);
                break;
            case 5:
                loanID = UserInput.readLine("Enter the loan ID: ");
                do {
                    if(!bank.getLoanRequests().containsKey(loanID)){
                        loanID = UserInput.readLine("Enter an existing loan ID. ");
                    }
                }while(!bank.getLoanRequests().containsKey(loanID));
                String personalNr = UserInput.readLine("Please type in the new personal number: ");
                if(!controller.isPersonNrCorrect(personalNr)) {
                    do {
                        personalNr = UserInput.readLine("The personal number must be in this format (YYYYMMDDXXXX) and whithin valid times: ");
                    } while (!controller.isPersonNrCorrect(personalNr));
                }
                System.out.println(controller.updateCoSigner_personalNr(loanID, personalNr));
                handleUpdateLoanRequest(controller);
                break;
            case 6://  otherService.addOptions(5,"Loan status"); //? should we?
                loanID = UserInput.readLine("Enter the loan ID: ");
                do {
                    if(!bank.getLoanRequests().containsKey(loanID)){
                        loanID = UserInput.readLine("Enter an existing loan ID. ");
                    }
                }while(!bank.getLoanRequests().containsKey(loanID));

                String coSigner_salaryStr;
                do {
                    coSigner_salaryStr = UserInput.readLine("Please type in the new Co-Signer salary");
                    if (!Utilities.isNumeric(coSigner_salaryStr) || coSigner_salaryStr.isEmpty()) {
                        System.out.println("Invalid input");
                    }
                } while (!Utilities.isNumeric(coSigner_salaryStr) || coSigner_salaryStr.isEmpty());
                double coSigner_salary = Double.parseDouble(coSigner_salaryStr);

                System.out.println(controller.updateCoSigner_salary(loanID, coSigner_salary));
                handleUpdateLoanRequest(controller);
                break;
            case 7:
                loanID = UserInput.readLine("Enter the loan ID: ");
                do {
                    if(!bank.getLoanRequests().containsKey(loanID)){
                        loanID = UserInput.readLine("Enter an existing loan ID. ");
                    }
                }while(!bank.getLoanRequests().containsKey(loanID));
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
                System.out.println(controller.updateInterestType(loanID, interestType));
                handleUpdateLoanRequest(controller);
                break;
            case 8:
                handleCustomerMenu(controller);
            default:
                System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                handleUpdateLoanRequest(controller);
        }

    }

    public void handleEmployeeMenu(Controller controller) {
        if(controller.getUser().getRole() == Role.EMPLOYEE || controller.getUser().getRole() == Role.MANAGER)  {
            this.employee.printOptions();
            String userChoiceStr ;
            do{
                userChoiceStr  = UserInput.readLine("Type in the option: ");
                if(!Utilities.isNumeric(userChoiceStr)|| userChoiceStr.isEmpty()) {
                    System.out.println("Invalid input");
                }
            }while(!Utilities.isNumeric(userChoiceStr)|| userChoiceStr.isEmpty());
            int userChoice = Integer.parseInt(userChoiceStr);

            switch (userChoice) {
                case 0:
                    System.out.println("Registering a new Customer: ");
                    String personalNo ="";
                    try {
                        String fullName = UserInput.readLine("Enter the customer's full name: ");

                        personalNo = UserInput.readLine("Enter the customer's personal number:  ");
                        if(!controller.isPersonNrCorrect(personalNo)) {
                            do {
                                personalNo = UserInput.readLine("Customer's personal number must be in this format (YYYYMMDDXXXX) and whithin valid times: ");
                            } while (!controller.isPersonNrCorrect(personalNo));
                        }
                        String password;
                        String repeatedPassword;
                        do {
                            do {
                                password = UserInput.readLine("Create a password for the customer: " + Utilities.EOL +
                                        "The password must have a minimum of 8 characters in length" + Utilities.EOL +
                                        "and contain: a lowercase letter, an uppercase letter, a digit.");
                                if (!controller.isStrongPassword(password)) {
                                    System.out.println("The password is weak.");
                                }
                            } while (!controller.isStrongPassword(password));
                            repeatedPassword = UserInput.readLine("Confirm password");
                        }while(!password.equals(repeatedPassword));

                        String salary = "";
                        do{
                            salary = UserInput.readLine("Enter customer salary: ");
                            if (!Utilities.isNumeric(salary) || salary.isEmpty()){
                                System.out.println("Invalid input. ");
                            }
                        }while(!Utilities.isNumeric(salary) || salary.isEmpty());

                        double newSalary = Double.parseDouble(salary);
                        controller.createCustomer(fullName, personalNo, newSalary, password);
                        System.out.println("Customer was successfully registered.");
                        handleEmployeeMenu(controller);
                    } catch (IllegalAccessException scannerError) {
                        System.out.println("Invalid input.");

                    } catch (InputMismatchException exception){
                        System.out.println("Invalid input. Please enter numbers.");

                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());

                    }
                    handleEmployeeMenu(controller);
                    break;
                case 1:
                    do {
                        personalNo = UserInput.readLine("Type the personal number of the customer: ");
                        if (!bank.getUsers().containsKey(personalNo)||!controller.isCustomer(personalNo) || !controller.isPersonNrCorrect(personalNo)){
                            System.out.println("There is no registered customer with this personal number." + Utilities.EOL +
                                    "Please enter an existing customer's personal number: ");
                        }
                    }while (!bank.getUsers().containsKey(personalNo)||!controller.isCustomer(personalNo) || !controller.isPersonNrCorrect(personalNo));
                    String message = controller.getCustomerInfo(personalNo);
                    System.out.println(message);
                    handleEmployeeMenu(controller);
                    break;
                case 2:
                    int option;
                    do {
                        String loanRequestID;

                         option = UserInput.readInt("Choose between: " + Utilities.EOL +
                                 "1. Approve 2. Decline 3. Modify 4. Interest rate");
                        switch (option) {
                            case 1: // CHECK NOTES ON THE METHOD
                                String typeOfInterest1="";
                                try {
                                do {
                                loanRequestID = UserInput.readLine("Enter ID of the loan request: ");
                                }while(!controller.checkLoanRequest(loanRequestID));
                                    String typeOfInterest;
                                    //The do-while doesnt work
                                    do {
                                        typeOfInterest = UserInput.readLine(" Enter type of interest. Fix or Variable.");
                                        if(!(typeOfInterest.equalsIgnoreCase("fix")|| typeOfInterest.equalsIgnoreCase("variable"))){
                                            System.out.println("Type in fix or variable");
                                        }
                                    }while(!(typeOfInterest.equalsIgnoreCase("fix")|| typeOfInterest.equalsIgnoreCase("variable")));
                                    String interestRateSrt = "";
                                    if(typeOfInterest.equalsIgnoreCase("Fix")){
                                        if(!Utilities.isNumeric(interestRateSrt)) {
                                            do {
                                                interestRateSrt = UserInput.readLine("Enter interest rate for the loan: ");
                                            } while (!Utilities.isNumeric(interestRateSrt));
                                        }
                                    }
                                    double interestRate = Double.parseDouble(interestRateSrt);
                                    if(typeOfInterest.equalsIgnoreCase("variable")){
                                        interestRate = bank.getVariableInterestRate();
                                    }
                                   String textMessage = UserInput.readLine("Enter message: ");
                                   message = controller.approveLoan(loanRequestID,textMessage,interestRate,typeOfInterest);
                                    System.out.println(message);
                                } catch (IllegalAccessException scannerError) {
                                    System.out.println("Invalid input.");
                                } catch (InputMismatchException exception){
                                    System.out.println("Invalid input. Please enter numbers.");
                                } catch (Exception exception) {
                                    System.out.println(exception.getMessage());
                                }
                                handleEmployeeMenu(controller);
                                break;
                            case 2: try {
                                //What is this supposed to do
                                do {
                                    loanRequestID = UserInput.readLine("Enter ID of the loan request: ");
                                }while (!controller.checkLoanRequest(loanRequestID));
                                String textMessage = UserInput.readLine("Enter message: ");
                                message = controller.declineLoanRequest(loanRequestID, textMessage);
                                System.out.println(message);
                            } catch (IllegalAccessException scannerError) {
                                System.out.println("Invalid input.");}
                            catch (Exception exception){
                                System.out.println(exception.getMessage());
                            }
                            handleEmployeeMenu(controller);

                                break;
                            case 3: try {

                                do {
                                    loanRequestID = UserInput.readLine("Enter ID of the loan request: ");
                                }while (!controller.checkLoanRequest(loanRequestID));

                                String textMessage = UserInput.readLine("Enter message: ");

                                // ADD SEND MESSEGE TO CUSTOMER HERE!!


                            } catch (IllegalAccessException scannerError) {
                                System.out.println("Invalid input.");}
                            catch (Exception exception){
                                System.out.println(exception.getMessage());
                            }
                                handleEmployeeMenu(controller);

                                break;
                            case 4:
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
                                    message = controller.interestOffer(loanRequestID,interestRate,textMessage);
                                    System.out.println(message);
                                } catch (IllegalAccessException scannerError) {
                                    System.out.println("Invalid input.");
                                } catch (InputMismatchException exception){
                                    System.out.println("Invalid input. Please enter numbers.");
                                } catch (Exception exception) {
                                    System.out.println(exception.getMessage());
                                }
                                handleEmployeeMenu(controller);
                            default:
                                System.out.println("Invalid choice. Please select between option 1 to 3. ");
                        }
                    }while( option < 1 || option > 4);

                    break;
                case 3:


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
                        message = controller.approveCardRequest(cardRequestID, cardNr, cvc, expirationDate, code);
                        System.out.println(message);
                    }
                    catch (IllegalAccessException scannerError) {
                System.out.println("Invalid input.");
                    } catch (InputMismatchException exception){
                System.out.println("Invalid input. Please enter numbers.");
                    } catch (Exception exception) {
                System.out.println(exception.getMessage());
                    }

                    handleEmployeeMenu(controller);
                    break;
                case 4:
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

                    message = controller.calculateDTI(monthlyDebt,grossIncome);
                    System.out.println(message);
                    handleEmployeeMenu(controller);
                    break;
                case 5://employee.addOptions(4,"Calculate mortgage.");
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

                    message = controller.calculateMonthlyMortgage(loan,interestRate,loanPeriod);
                    System.out.println(message);
                    handleEmployeeMenu(controller);
                    break;
                case 6:
                    personalNo = UserInput.readLine("Please type the personal number for the customer:");
                    do {
                        if(!bank.getUsers().containsKey(personalNo)||!controller.isCustomer(personalNo) || !controller.isPersonNrCorrect(personalNo)){
                            System.out.println("There is no customer registered with that personal number.");
                            personalNo = UserInput.readLine("Please enter the personal number of a registered customer:");
                        }
                    }while(!bank.getUsers().containsKey(personalNo)||!controller.isCustomer(personalNo) || !controller.isPersonNrCorrect(personalNo));
                    String newPassword;
                    do {
                        newPassword = UserInput.readLine("Please type the new password:");
                        if(!controller.isStrongPassword(newPassword)){
                            System.out.println("The password must have a minimum of 8 characters in length" + Utilities.EOL +
                                    "and contain at least  contain: lowercase letter, uppercase letter, digit.");
                        }
                    }while(!controller.isStrongPassword(newPassword));
                    System.out.println(controller.updateCustomerPassword(personalNo, newPassword));
                    handleEmployeeMenu(controller);
                    break;
                case 7:
                    String loanID = UserInput.readLine("Enter the loan ID: ");
                    do {
                        if(!bank.getLoans().containsKey(loanID)){
                            loanID = UserInput.readLine("Enter an existing loan ID. ");
                        }
                    }while(!bank.getLoans().containsKey(loanID));
                    message =controller.getMortgagePercentage_HouseLoan(loanID);
                    System.out.println(message);
                    handleEmployeeMenu(controller);
                    break;
                case 8:
                    System.out.println(controller.viewSalary());
                    handleEmployeeMenu(controller);
                    break;
                case 9: // connect to inbox, see the controller
                    try {
                        String daysStr = UserInput.readLine("Enter number of days:");
                        do {
                            if(!Utilities.isNumber(daysStr) || daysStr.isEmpty() || Double.parseDouble(daysStr) > 31 ){
                                daysStr = UserInput.readLine("Please only enter digits. ");
                            }
                        }while(!Utilities.isNumber(daysStr) || daysStr.isEmpty() || Double.parseDouble(daysStr) > 31 );
                        int days = Integer.parseInt(daysStr);
                        message = controller.applyForVacation(days);
                        System.out.println(message);
                    } catch (InputMismatchException exception){
                        System.out.println("Invalid input. Please enter numbers.");

                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    handleEmployeeMenu(controller);
                    break;
                case 10:
                    handleEmployeeInbox(controller);
                    // we dont hav the method
                    //System.out.println(controller.getEmployeeInbox());

                    //handleEmployeeMenu(controller);
                    break;
                case 11:
                    handleManagerMenu(controller);
                    break;
                case 12:
                    handleMainMenu();
                default:
                    System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                    handleEmployeeMenu(controller);
            }
        } else {
            System.out.println("Access denied. This menu is only for employees.");
            handleMainMenu();
        }
    }
    public void handleEmployeeInbox(Controller controller){
        this.employeeInbox.printOptions();
        String userChoiceStr ;
        do{
            userChoiceStr  = UserInput.readLine("Type in the option: ");
            if(!Utilities.isNumeric(userChoiceStr)|| userChoiceStr.isEmpty()) {
                System.out.println("Invalid input");
            }
        }while(!Utilities.isNumeric(userChoiceStr)|| userChoiceStr.isEmpty());
        int userChoice = Integer.parseInt(userChoiceStr);
        switch (userChoice){
            case 0:
                controller.viewEmployeeMessageInbox();
                handleEmployeeInbox(controller);
                break;
            case 1:
                String tittle = UserInput.readLine("Enter the title: ");
                String message = UserInput.readLine("Enter message: ");
                controller.sendMessageToCustomers(tittle,message);
                handleEmployeeInbox(controller);
                break;
            case 2:
                controller.viewEmployeeMessageHistory();
                handleEmployeeInbox(controller);
                break;
            case 3:
                controller.removeMessageFromEmployee();
                handleEmployeeInbox(controller);
                break;
            case 4:
                handleEmployeeMenu(controller);
                break;
            default:
                System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                handleCustomerInbox(controller);
        }
    }

    public void handleManagerMenu(Controller controller) {
        if (controller.getUser().getRole() == Role.MANAGER) {
            manager.printOptions();
            String userChoiceStr ;
            do{
                userChoiceStr  = UserInput.readLine("Type in the option: ");
                if(!Utilities.isNumeric(userChoiceStr)|| userChoiceStr.isEmpty()) {
                    System.out.println("Invalid input");
                }
            }while(!Utilities.isNumeric(userChoiceStr)|| userChoiceStr.isEmpty());
            int userChoice = Integer.parseInt(userChoiceStr);

            switch (userChoice) {
                case 0: // show bank balance
                    System.out.println(controller.getTotalBalance());
                    handleManagerMenu(controller);
                    break;
                case 1: // show total loaned amount
                    System.out.println(controller.getTotalLoan());
                    handleManagerMenu(controller);
                    break;
                case 2:// create employee
                    String name = "";
                    String personalNo = "";
                    String password = "";

                    try {
                        name = UserInput.readLine("Please enter the name of the employee: ");
                        personalNo = UserInput.readLine("The Personal number of the employee: ");
                        if (!controller.isPersonNrCorrect(personalNo)) {
                            do {
                                personalNo = UserInput.readLine("Employee's personal number must be in this format (YYYYMMDDXXXX) and whithin valid times: ");
                            } while (!controller.isPersonNrCorrect(personalNo));
                        }
                        String repeatedPassword;
                        do {
                            do {
                                password = UserInput.readLine("Create a password for the employee: " + Utilities.EOL +
                                        "The password must have a minimum of 8 characters in length" + Utilities.EOL +
                                        "and contain at least  contain: lowercase letter, uppercase letter, digit.");
                                if (!controller.isStrongPassword(password)) {
                                    System.out.println("The password is weak.");
                                }
                            } while (!controller.isStrongPassword(password));
                            repeatedPassword = UserInput.readLine("Confirm password");
                        } while (!password.equals(repeatedPassword));
                        String salary;
                        do {
                            salary = UserInput.readLine("Please enter the salary of the employee: ");
                            if (!Utilities.isNumeric(salary) || salary.isEmpty()) {
                                System.out.println("Enter a valid number");
                            }
                        } while (!Utilities.isNumeric(salary) || salary.isEmpty());
                        double actualSalary = Double.parseDouble(salary);
                        System.out.println(controller.createEmployee(name, personalNo, password, actualSalary));
                        handleManagerMenu(controller);
                    } catch (InputMismatchException exception) {
                        System.out.println("Invalid input");
                        handleManagerMenu(controller);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                        handleManagerMenu(controller);
                    }


                    handleManagerMenu(controller);
                    break;
                case 3:// remove employee
                    personalNo = UserInput.readLine("Please enter the personal number of the employee you want to remove: ");
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

                    handleManagerMenu(controller); //thisjoihasdhisfsdsd
                    break;
                case 4://update employee salary
                    personalNo = UserInput.readLine("Type the personal number of the employee you wish to change the salary of: ");
                    if (!controller.isPersonNrCorrect(personalNo) || !controller.isEmployee(personalNo)) {
                        do { //should we have it like this
                            personalNo = UserInput.readLine("Please enter an existing employee's personal number in the format (YYYYMMDDXXXX): ");
                        } while (!controller.isPersonNrCorrect(personalNo) || !controller.isEmployee(personalNo));
                    }

                    String salary;
                    do {
                        salary = UserInput.readLine("Write the new salary of the employee: ");
                        if (!Utilities.isNumeric(salary) || salary.isEmpty()) {
                            System.out.println("Enter a valid number");
                        }
                    } while (!Utilities.isNumeric(salary) || salary.isEmpty());
                    double newSalary = Double.parseDouble(salary);
                    System.out.println(controller.setEmployeeSalary(personalNo, newSalary));
                    handleManagerMenu(controller);
                    break;
                case 5:// update employee password
                    personalNo = UserInput.readLine("Type the personalNo of the employee you wish to change the password of: ");
                    if (!controller.isPersonNrCorrect(personalNo) || !controller.isEmployee(personalNo)) {
                        do {
                            personalNo = UserInput.readLine("Employee's personal number must be in this format (YYYYMMDDXXXX) and whithin valid times: ");
                        } while (!controller.isPersonNrCorrect(personalNo) || !controller.isEmployee(personalNo));
                    }

                    String repeatedPassword;
                    do {
                        do {
                            password = UserInput.readLine("Please type the new password of the employee: " + Utilities.EOL +
                                    "The password must have a minimum of 8 characters in length" + Utilities.EOL +
                                    "and contain at least  contain: lowercase letter, uppercase letter, digit.");
                            if (!controller.isStrongPassword(password)) {
                                System.out.println("The password is weak.");
                            }
                        } while (!controller.isStrongPassword(password));
                        repeatedPassword = UserInput.readLine("Confirm password");
                    } while (!password.equals(repeatedPassword));
                    System.out.println(controller.setEmployeePassword(password, personalNo));
                    handleManagerMenu(controller);
                    break;
                case 6:
                    try {
                        double interestRate = UserInput.readDouble("Enter the variable interest rate: ");
                        controller.setVariableInterestRate(interestRate);
                    } catch (InputMismatchException exception) {
                        System.out.println("Invalid input. Please enter numbers.");

                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());

                    }
                    handleManagerMenu(controller);
                    break;
                case 7:
                    handleManagerInbox(controller);
                    break;
                case 8:
                    handleEmployeeMenu(controller);
                    break;
                default:
                    System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                    handleManagerMenu(controller);

            }
        } else {
            System.out.println("Access denied. This menu is only for managers.");
            handleEmployeeMenu(controller);
        }
    }


    public void handleManagerInbox(Controller controller){
        this.managerInbox.printOptions();
        String userChoiceStr ;
        do{
            userChoiceStr  = UserInput.readLine("Type in the option: ");
            if(!Utilities.isNumeric(userChoiceStr)|| userChoiceStr.isEmpty()) {
                System.out.println("Invalid input");
            }
        }while(!Utilities.isNumeric(userChoiceStr)|| userChoiceStr.isEmpty());
        int userChoice = Integer.parseInt(userChoiceStr);
        switch (userChoice){
            case 0:
                System.out.println(controller.seeVacationApplications());
                handleManagerInbox(controller);
                break;
            case 1:
                System.out.println(controller.approveVacationApplication());
                handleManagerInbox(controller);
                break;
        }
    }

    public void handleAdministration(Controller controller){
        if(controller.getUser().getRole() == Role.ADMIN) {
            administration.printOptions();
            String userChoiceStr ;
            do{
                userChoiceStr  = UserInput.readLine("Type in the option: ");
                if(!Utilities.isNumeric(userChoiceStr)|| userChoiceStr.isEmpty()) {
                    System.out.println("Invalid input");
                }
            }while(!Utilities.isNumeric(userChoiceStr)|| userChoiceStr.isEmpty());
            int userChoice = Integer.parseInt(userChoiceStr);
            switch (userChoice) {
                case 0:
                    try {
                        String password;
                        String repeatedPassword;
                        do {
                            do {
                                password = UserInput.readLine("Create a password: " + Utilities.EOL +
                                        "The password must have a minimum of 8 characters in length" + Utilities.EOL +
                                        "and contain at least  contain: lowercase letter, uppercase letter, digit.");
                                if (!controller.isStrongPassword(password)) {
                                    System.out.println("The password is weak.");
                                }
                            } while (!controller.isStrongPassword(password));
                            repeatedPassword = UserInput.readLine("Confirm password");
                        }while(!password.equals(repeatedPassword));

                        String message = controller.setAdminPassword(password);
                        System.out.println(message);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    handleAdministration(controller);
                    break;
                case 1:
                    System.out.println("Create a Manager: ");
                    String option;
                    do {
                        try {
                            System.out.println(" " + "Registering a manager user: ");
                            String fullName = UserInput.readLine("Type your full name:");
                            String personalNo = UserInput.readLine("Enter your personal number (YYYYMMDDXXXX): ");
                            if(!controller.isPersonNrCorrect(personalNo)) {
                                do {
                                    personalNo = UserInput.readLine("Your personal number must be in this format (YYYYMMDDXXXX) and whithin valid times: ");
                                } while (!controller.isPersonNrCorrect(personalNo));
                            }
                            String password;
                            String repeatedPassword;
                            do {
                                do {
                                    password = UserInput.readLine("Create a password: " + Utilities.EOL +
                                            "The password must have a minimum of 8 characters in length" + Utilities.EOL +
                                            "and contain at least  contain: lowercase letter, uppercase letter, digit.");
                                    if (!controller.isStrongPassword(password)) {
                                        System.out.println("The password is weak.");
                                    }
                                } while (!controller.isStrongPassword(password));
                                repeatedPassword = UserInput.readLine("Confirm password");
                            }while(!password.equals(repeatedPassword));

                            String salaryStr;
                            do{
                                salaryStr = UserInput.readLine("Enter salary: ");
                                if(!Utilities.isNumeric(salaryStr)|| salaryStr.isEmpty()) {
                                    System.out.println("Enter a valid number");
                                }
                            }while(!Utilities.isNumeric(salaryStr)|| salaryStr.isEmpty());
                            double salary = Double.parseDouble(salaryStr);

                            String bonus;
                            do{
                                bonus = UserInput.readLine("Enter manager bonus: ");
                                if (!Utilities.isNumeric(bonus) || bonus.isEmpty()){
                                    System.out.println("Enter a valid number: ");
                                }
                            }while(!Utilities.isNumeric(bonus) || bonus.isEmpty());
                            double actualBonus = Double.parseDouble(bonus);
                            String message = controller.createManager(fullName, personalNo, password, salary, actualBonus);
                            System.out.println(message);
                        } catch (IllegalAccessException scannerError) {
                            System.out.println("Invalid input.");
                        } catch (Exception exception) {
                            System.out.println(exception.getMessage());
                        }
                        option = UserInput.readLine("Do you want to create another manager?" + Utilities.EOL + " Yes or No");

                    } while (option.equalsIgnoreCase("yes"));
                    handleAdministration(controller);
                    break;

                case 2:
                    try {
                        String personNr = UserInput.readLine("Enter managers personal number: ");
                        if(!bank.getUsers().containsKey(personNr) || !controller.isManager(personNr)) {
                            do {
                                personNr = UserInput.readLine("Enter an existing managers personal number: ");
                            } while (!bank.getUsers().containsKey(personNr) || !controller.isManager(personNr));
                        }
                        String message = controller.removeManager(personNr);
                        System.out.println(message);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }

                    handleAdministration(controller);
                    break;

                case 3:
                    try {
                        String personNr = UserInput.readLine("Enter manger personal number: ");
                        if(!bank.getUsers().containsKey(personNr) || !controller.isManager(personNr)) {
                            do {
                                personNr = UserInput.readLine("Enter an existing managers personal number: ");
                            } while (!bank.getUsers().containsKey(personNr) || !controller.isManager(personNr));
                        }
                        String newSalary = "";
                        do{
                            newSalary = UserInput.readLine("Enter Manager salary: ");
                            if (!Utilities.isNumeric(newSalary) || newSalary.isEmpty()){
                                System.out.println("Invalid input. ");
                            }
                        }while(!Utilities.isNumeric(newSalary) || newSalary.isEmpty());
                        double actualNewSalary = Double.parseDouble(newSalary);
                        String message = controller.setManagerSalary(actualNewSalary, personNr);
                        System.out.println(message);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    handleAdministration(controller);
                    break;
                case 4:
                    try {
                        String personNr = UserInput.readLine("Enter manger personal number: ");
                        if(!bank.getUsers().containsKey(personNr) || !controller.isManager(personNr)) {
                            do {
                                personNr = UserInput.readLine("Enter an existing managers personal number: ");
                            } while (!bank.getUsers().containsKey(personNr) || !controller.isManager(personNr));
                        }
                        String password = "";
                        String repeatedPassword;
                        do {
                            do {
                                password = UserInput.readLine("Create a password: " + Utilities.EOL +
                                        "The password must have a minimum of 8 characters in length" + Utilities.EOL +
                                        "and contain at least  contain: lowercase letter, uppercase letter, digit.");
                                if (!controller.isStrongPassword(password)) {
                                    System.out.println("The password is weak.");
                                }
                            } while (!controller.isStrongPassword(password));
                            repeatedPassword = UserInput.readLine("Confirm password");
                        }while(!password.equals(repeatedPassword));
                        String message = controller.setManagerPassword(personNr, password);
                        System.out.println(message);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    handleAdministration(controller);
                    break;
                case 5:
                    String personNr = UserInput.readLine("Enter employee's personal number : ");
                    if(!bank.getUsers().containsKey(personNr) || !controller.isEmployee(personNr)) {
                        do {
                            personNr = UserInput.readLine("Enter an existing employees personal number: ");
                        } while (!bank.getUsers().containsKey(personNr) || !controller.isEmployee(personNr));
                    }
                    String salary = "";
                    do{
                        salary = UserInput.readLine("Enter new salary: ");
                        if (!Utilities.isNumeric(salary) || salary.isEmpty()){
                            System.out.println("Invalid input. ");
                        }
                    }while(!Utilities.isNumeric(salary) || salary.isEmpty());
                    double newSalary = Double.parseDouble(salary);
                    String bonus = "";
                    do{
                        bonus = UserInput.readLine("Enter salary bonus: ");
                        if (!Utilities.isNumeric(bonus) || bonus.isEmpty()){
                            System.out.println("Invalid input. ");
                        }
                    }while(!Utilities.isNumeric(bonus) || bonus.isEmpty());
                    double actualBonus = Double.parseDouble(bonus);
                    try {
                        String message = controller.promoteEmployee(personNr, newSalary, actualBonus);
                        System.out.println(message);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    handleAdministration(controller);
                    break;
                case 6:
                    handleMainMenu();
                    break;
                case 7:
                    break; //Show all managers, method is missing
                default:
                    System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                    handleAdministration(controller);
            }
        } else {
            System.out.println("Access denied. This menu is only for administration.");
            handleMainMenu();
        }
    }

}






