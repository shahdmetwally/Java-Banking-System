package Menu;

import Bank.Bank;
import Classes.Customer;
import Classes.Role;
import MainController.Controller;
import MainController.StartProgram;
import Utilities.UserInput;
import Utilities.Utilities;
import Bank.TypesOfLoan;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Paths;
import java.util.InputMismatchException;


public class MainMenu {

    private final MenuOptions mainMenu;
    private final MenuOptions customer;
    private final MenuOptions otherService;
    private final MenuOptions administration;
    private final MenuOptions employee;
    private final MenuOptions manager;
    private Bank bank;
    private final MenuOptions inbox;



    public MainMenu(Bank bank) {
        this.mainMenu = new MenuOptions();
        this.administration = new MenuOptions();
        this.customer = new MenuOptions();
        this.otherService = new MenuOptions();
        this.manager = new MenuOptions();
        this.employee = new MenuOptions();
        this.bank = bank;
        this.inbox = new MenuOptions();
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

        otherService.setMenuName("Other services Menu " + Utilities.EOL +
                "--------------------" + Utilities.EOL +
                " Choose one of the options below.");
        otherService.addOptions(0,"Update name.");
        otherService.addOptions(1,"Update salary");
        otherService.addOptions(2,"Apply for new card.");
        otherService.addOptions(3,"Block payment card.");
        otherService.addOptions(4,"Loan request with Co-signer");
        otherService.addOptions(5, "Loan request without Co-signer");
        otherService.addOptions(6,"Loan status"); //? should we?
        otherService.addOptions(7,"Return to Customer menu.");

        employee.setMenuName("Employee Menu " + Utilities.EOL +
                "--------------------" + Utilities.EOL +
                " Choose one of the options below.");
        employee.addOptions(0, "Create Customer.");
        employee.addOptions(1, "Create a bank account for customer.");
        employee.addOptions(2, "Show customer accounts.");
        employee.addOptions(3, "Approve loans and mortgages.");
        // we can maybe give access to the employee to enter the customer menu, getting
        // the user personal number and a personal code (4 digit).
        employee.addOptions(4, "Update customer password.");
        employee.addOptions(5, "View salary.");
        employee.addOptions(6, "Apply for vacation.");
        employee.addOptions(7, "Request inbox.");
        employee.addOptions(8, "Manager options.");
        employee.addOptions(9, "Log out.");

        manager.setMenuName("Manager Menu " + Utilities.EOL +
                "--------------------" + Utilities.EOL +
                " Choose one of the options below.");
        manager.addOptions(0, "Show Bank Balance.");
        manager.addOptions(1, "Show total loaned amount.");
        manager.addOptions(2, "Create employee.");
        manager.addOptions(3, "Remove employee.");
        manager.addOptions(4, "Update employee salary.");
        manager.addOptions(5, "Update employee password.");
        manager.addOptions(6,"Return to employee menu.");
    }

    public String enterPersonalNr(){
        String personNr = UserInput.readLine(" Enter customer personal number: ");
        return personNr;
    }

    public Controller login() throws Exception{

            String userName = UserInput.readLine("Enter username: ");
            String password = UserInput.readLine("Enter password: ");
            Controller controller = new Controller(userName, password,bank);
            return controller;
    }

    public void handleMainMenu(){
        setUpMainMenu();
        this.mainMenu.printOptions();
            int userChoice = UserInput.readInt("Type in the option: ");
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
                        mapper.writeValue(Paths.get("users.json").toFile(), StartProgram.jsonBank);

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
            int userChoice = UserInput.readInt("Type in the option: ");
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

                    String value = UserInput.readLine("Please enter the amount you want to deposit: ");
                    try {
                    if(!Utilities.isNumber(value) || value.isEmpty()) {
                        do {
                            value = UserInput.readLine("Please enter a valid number:");
                        } while (!Utilities.isNumber(value) || value.isEmpty());
                    }
                        double actualValue = Double.parseDouble(value);
                        message = controller.depositMoney(actualValue);
                        System.out.println(message);

                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                handleCustomerMenu(controller);
                break;
            case 3:
                try {
                    String amount = UserInput.readLine("Please enter the amount you want to withdraw: ");
                    if(!Utilities.isNumber(amount) || amount.isEmpty()) {
                        do {
                            amount = UserInput.readLine("Please enter a valid number: ");
                        }while(!Utilities.isNumber(amount) || amount.isEmpty());
                    }
                    double acutalAmount = Double.parseDouble(amount);
                    message = controller.withdrawMoney(acutalAmount);
                    System.out.println(message);
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }


                /*try {
                    String amount = UserInput.readLine("Please enter the amount you want to withdraw: ");
                    double amount1 = 0;
                    if (Utilities.isNumber(amount)) {
                         amount1 = Double.parseDouble(amount);
                    } else {
                        System.out.println("Please enter a valid number.");
                    }
                    message = controller.withdrawMoney(amount1);
                    System.out.println(message);
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }

                 */

                handleCustomerMenu(controller);
                break;
            case 4:
                String amount =UserInput.readLine("Please enter the amount you want to transfer: ");
                try {
                    if(!Utilities.isNumber(amount) || amount.isEmpty()){
                        do{
                            amount = UserInput.readLine("Please enter a valid number: ");
                        }while(!Utilities.isNumber(amount) || amount.isEmpty());
                    }
                    double actualAmount = Double.parseDouble(amount);
                    String account =UserInput.readLine("Please enter the account No of the recievient:");

                        message = controller.transferMoney(actualAmount, account);
                        System.out.println(message);

                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }

                /*
                try {
                    String amount =UserInput.readLine("Please enter the amount you want to transfer: ");
                    double amount2 = 0;
                    String account = "";
                    if (Utilities.isNumber(amount)){
                        amount2 = Double.parseDouble(amount);
                        account =UserInput.readLine("Please enter the account No of the recievient:");
                    } else{
                        System.out.println("Please make sure to enter a valid number.");
                        //Can we call the method here istead of going back to the menu
                    }
                    message =controller.transferMoney(amount2, account);
                    System.out.println(message);
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }

                 */


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
                try {
                    String budget = UserInput.readLine("Please enter the budget: ");
                    if (!Utilities.isNumber(budget) || budget.isEmpty()) {
                        do {
                            budget = UserInput.readLine("Please enter a valid number: ");
                        } while (!Utilities.isNumber(budget) || budget.isEmpty());
                    }
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
                        // how does the budget actually work
                        String updateBudget = UserInput.readLine("Please enter the budget you want to set: ");
                        if(!Utilities.isNumber(updateBudget) || updateBudget.isEmpty()) {
                            do {
                                updateBudget = UserInput.readLine("Please enter a valid number: ");
                            } while (!Utilities.isNumber(updateBudget) || updateBudget.isEmpty());
                        }
                        double actualUpdatedBudget = Double.parseDouble(updateBudget);
                        controller.updateBudget(actualUpdatedBudget);
                        System.out.println("You have updated your budget successfully");
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    handleCustomerMenu(controller);

                    /*try {
                        controller.updateBudget(UserInput.readDouble("Please enter the budget you want to set: "));
                        System.out.println("You have updated your budget successfully");
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    handleCustomerMenu(controller);
                    break;

                     */
                case 9:// inbox
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
        int userChoice = UserInput.readInt("Type in the option: ");
        switch (userChoice) {
            case 0:
                try{
                    String newName= UserInput.readLine("Enter new name: ");
                    controller.updateCustomerName(newName);
                    System.out.println(controller);
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
                handleOtherService(controller);
                break;
            case 1: double salary = UserInput.readDouble("Enter salary: ");
                String message = controller.updateSalary(salary);
                System.out.println(message);
                handleOtherService(controller);
                break;
            case 2: //"Apply for new card."

                break;
            case 3:
                String option = UserInput.readLine("Are you sure that you want to block your debit card? "+ Utilities.EOL + " Please answer: yes or no");
                if(option.equalsIgnoreCase("yes")){
                    String cardBlocked = controller.blockCard();
                    System.out.println(cardBlocked);
                    handleOtherService(controller);
                } else {
                    handleOtherService(controller);
                }
                break;
            case 4:  //otherService.addOptions(4,"Loan request");
               //    PERSONAL_LOAN,HOME_LOAN,CAR_LOAN,UNSECURED_LOAN;
                System.out.println(" Loan options: " + Utilities.EOL +
                        "1. Personal loan " + Utilities.EOL +
                        "2. House loan "+ Utilities.EOL +
                        "3. Car loan"+ Utilities.EOL +
                        "4. Unsecured loan");
                TypesOfLoan typesOfLoan = null;
                int newOption = UserInput.readInt("Enter loan option: ");
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
                    }
                }while (newOption>4 ||newOption < 1);

                int loanAmount = UserInput.readInt("Enter the loan amount: ");
                double time = UserInput.readDouble("Enter loan time (years): ");
                String otherEquity = UserInput.readLine("Enter other equity");
                double otherEquitiesValue = UserInput.readDouble("Enter other equities value: ");
                double cashContribution = UserInput.readDouble("Enter cash contribution: " + Utilities.EOL + "Show be at least 15% of the total loan amount");
                String coSigner_name = UserInput.readLine("Enter Co-signer name: ");
                String coSigner_personalNr = UserInput.readLine("Enter Co-signer personal number: ");
                double coSigner_salary =UserInput.readDouble("Enter Co-signer salary: ");

                message =  controller.loanRequestWithCoSigner(loanAmount,typesOfLoan,time,otherEquity,otherEquitiesValue,cashContribution,coSigner_name,coSigner_personalNr,coSigner_salary);
                System.out.println(message);
                // controller:

                String loanAppID= UserInput.readLine("Enter Loan request ID");
                String testMess = bank.getLoanApplications().get(loanAppID).toString();
                System.out.println(testMess);

                handleCustomerMenu(controller);
                break;
            case 5:  System.out.println(" Loan options: " + Utilities.EOL +
                    "1. Personal loan " + Utilities.EOL +
                    "2. House loan "+ Utilities.EOL +
                    "3. Car loan"+ Utilities.EOL +
                    "4. Unsecured loan");
                 typesOfLoan = null;
                 newOption = UserInput.readInt("Enter loan option: ");
                do {
                    switch (newOption) {
                        case 1:
                            typesOfLoan = TypesOfLoan.PERSONAL_LOAN;

                        case 2:
                            typesOfLoan = TypesOfLoan.HOUSE_LOAN;

                        case 3:
                            typesOfLoan = TypesOfLoan.CAR_LOAN;
                            break;
                        case 4:
                            typesOfLoan = TypesOfLoan.UNSECURED_LOAN;
                            break;
                        default:
                            System.out.println("Invalid choice. Please select between option 1 to 4. ");
                    }
                }while (newOption>4 ||newOption < 1);

                 loanAmount = UserInput.readInt("Enter the loan amount: ");
                 time = UserInput.readDouble("Enter loan time (years): ");
                 otherEquity = UserInput.readLine("Enter other equity");
                 otherEquitiesValue = UserInput.readDouble("Enter other equities value: ");
                 cashContribution = UserInput.readDouble("Enter cash contribution: " + Utilities.EOL + "Show be at least 15% of the total loan amount");
                message =  controller.loanRequestWithOutCoSigner(loanAmount,typesOfLoan,time,otherEquity,otherEquitiesValue,cashContribution);
                System.out.println(message);
            break;
            case 6://  otherService.addOptions(5,"Loan status"); //? should we?
                    break;
            case 7: handleCustomerMenu(controller);
                break;
            default:
                System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                handleOtherService(controller);
        }

    }

    public void handleEmployeeMenu(Controller controller) {

        if(controller.getUser().getRole() == Role.EMPLOYEE) {
            this.employee.printOptions();
            int userChoice = UserInput.readInt("Type in the option");

            switch (userChoice) {
                case 0:
                    System.out.println("Registering a new Customer: ");
                    String option;
                    String personalNo ="";
                    try {
                        String fullName = UserInput.readLine("Enter your full name:");
                        do{
                            personalNo = UserInput.readLine("Enter your personal number (YYYYMMDDXXXX): ");
                        }while(!controller.isPersonNrCorrect(personalNo));
                        String password;
                        String repeatedPassword;
                        do {
                            password = UserInput.readLine("Create a password: " + Utilities.EOL +
                                    "The password must have a minimum of 8 characters in length" + Utilities.EOL +
                                    "and contain at least  contain: lowercase letter, uppercase letter, digit.");
                            repeatedPassword = UserInput.readLine("Confirm password");

                        } while (!password.equals(repeatedPassword));
                        String cardNr = UserInput.readLine("Enter card number: ");
                        int cvc = UserInput.readInt("Enter cvc number: ");
                        String expirationDate = UserInput.readLine("Enter expiration date: ");
                        int code = UserInput.readInt("Enter code: ");
                        double salary = UserInput.readDouble("Enter customer salary: ");
                        controller.createCustomer(fullName, personalNo, salary, password, cardNr, cvc, expirationDate, code);
                        System.out.println("Customer was successfully registered.");
                        handleEmployeeMenu(controller);
                    } catch (IllegalAccessException scannerError) {
                        System.out.println("Invalid input.");
                        handleEmployeeMenu(controller);
                    } catch (InputMismatchException exception){
                        System.out.println("Invalid input. Please enter numbers.");
                        handleEmployeeMenu(controller);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                        handleEmployeeMenu(controller);
                    }
                    break;
                case 1://create a bank account for customer
                    // creating a customer already creates a bank account and need to fix the account no. so it saves.
                    handleEmployeeMenu(controller);
                    break;
                case 2:
                    String message = controller.getCustomerInfo(enterPersonalNr());
                    System.out.println(message);
                    handleEmployeeMenu(controller);
                    break;
                case 3://Approve loans and mortgages
                    handleEmployeeMenu(controller);
                    break;
                case 4:
                    personalNo = UserInput.readLine("Please type the personal number for the customer:");
                    String newPassword = UserInput.readLine("Please type the new password:");
                    System.out.println(controller.updateCustomerPassword(personalNo, newPassword));
                    handleEmployeeMenu(controller);
                    break;
                case 5:
                    System.out.println(controller.viewSalary());
                    handleEmployeeMenu(controller);
                    break;
                case 6: // We dont have the method
                    handleEmployeeMenu(controller);
                    break;
                case 7: // we dont hav the method
                    handleEmployeeMenu(controller);
                    break;
                case 8:
                    handleManagerMenu(controller);
                    break;
                case 9:
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


    public void handleManagerMenu(Controller controller) {
        manager.printOptions();
        int userChoice = UserInput.readInt("Type in the option");

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
                String name="";
                String personalNo="";
                String password="";
                double salary=0;
                try{
                name=UserInput.readLine("Please enter the name of the employee: ");
                personalNo = UserInput.readLine("The Personal number of the employee: ");
                password =UserInput.readLine("Please type the employees password: ");
                salary = UserInput.readDouble("Please type the salary of the employee: ");
                    System.out.println(controller.createEmployee(name, personalNo,password, salary));
                    handleManagerMenu(controller);
                } catch (InputMismatchException exception){
                    System.out.println("Invalid input");
                    handleManagerMenu(controller);
                }catch (Exception exception) {
                    System.out.println(exception.getMessage());
                    handleManagerMenu(controller);
                }

                handleManagerMenu(controller);
                break;
            case 3:// remove employee
                personalNo = UserInput.readLine("Please type the personalNo of the employee you wish to remove: ");
                try {
                    System.out.println(controller.removeEmployee(personalNo));
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }

                handleManagerMenu(controller); //thisjoihasdhisfsdsd
                break;
            case 4://update employee salary
                personalNo = UserInput.readLine("Type the personal number of the employee you wish to change the salary of: ");
                double newSalary = UserInput.readDouble("Write the new salary of the employee: ");
                System.out.println(controller.setEmployeeSalary(personalNo,newSalary));
                handleManagerMenu(controller);
                break;
            case 5:// update employee password
                personalNo=UserInput.readLine("Type the personalNo of the employee you wish to change the password of: ");
                password=UserInput.readLine("Please type the new password of the employee: " );
                System.out.println(controller.setEmployeePassword(password, personalNo));
                handleManagerMenu(controller);
                break;
            case 6:
                handleEmployeeMenu(controller);
                break;
            default:
                System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                handleManagerMenu(controller);

        }
    }

    public void handleAdministration(Controller controller){
        if(controller.getUser().getRole() == Role.ADMIN) {
            administration.printOptions();
            int userChoice = UserInput.readInt("Type in the option:");
            switch (userChoice) {
                case 0:
                    try {
                        String password;
                        String repeatedPassword;
                        do {
                            password = UserInput.readLine("Create a password: " + Utilities.EOL +
                                    "The password must have a minimum of 8 characters in length" + Utilities.EOL +
                                    "and contain at least: lowercase letter, uppercase letter, digit.");
                            repeatedPassword = UserInput.readLine("Confirm password");

                        } while (!password.equals(repeatedPassword));
                        controller.setAdminPassword(password);

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
                            String password;
                            String repeatedPassword;
                            do {
                                password = UserInput.readLine("Create a password: " + Utilities.EOL +
                                        "The password must have a minimum of 8 characters in length" + Utilities.EOL +
                                        "and contain at least  contain: lowercase letter, uppercase letter, digit.");
                                repeatedPassword = UserInput.readLine("Confirm password");

                            } while (!password.equals(repeatedPassword));

                            double salary = UserInput.readDouble("Enter manager gross-salary: ");
                            double bonus = UserInput.readDouble("Enter manager bonus:");

                            controller.createManager(fullName, personalNo, password, salary, bonus);
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
                        controller.removeManager(personNr);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    handleAdministration(controller);
                    break;

                case 3:
                    try {
                        String personNr = UserInput.readLine("Enter manger personal number: ");
                        double newSalary = UserInput.readDouble("Enter Manager salary: ");
                        controller.setManagerSalary(newSalary, personNr);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    handleAdministration(controller);
                    break;
                case 4:
                    try {
                        String personNr = UserInput.readLine("Enter manger personal number: ");
                        String password;
                        String repeatedPassword;
                        do {
                            password = UserInput.readLine("Create a password: " + Utilities.EOL +
                                    "The password must have a minimum of 8 characters in length" + Utilities.EOL +
                                    "and contain at least  contain: lowercase letter, uppercase letter, digit.");
                            repeatedPassword = UserInput.readLine("Confirm password");

                        } while (!password.equals(repeatedPassword));
                        controller.setManagerPassword(personNr, password);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    handleAdministration(controller);
                    break;
                case 5:
                    String personNr = UserInput.readLine("Enter employee's personal number : ");
                    double salary = UserInput.readDouble("Enter new salary: ");
                    double bonus = UserInput.readDouble("Enter salary bonus: ");
                    try {
                        controller.promoteEmployee(personNr, salary, bonus);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    handleAdministration(controller);
                    break;
                case 6:// apply for vacation
                    handleMainMenu();
                    break;
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






