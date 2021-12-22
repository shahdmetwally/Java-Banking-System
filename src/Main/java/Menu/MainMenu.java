package Menu;

import Classes.*;
import MainController.Controller;
import Utilities.UserInput;
import Utilities.Utilities;


public class MainMenu {

    private final MenuOptions mainMenu;
    private final MenuOptions customer;
    private final MenuOptions otherService;
    private  final MenuOptions administration;
    private final MenuOptions employee;
    private final MenuOptions manager;


    public MainMenu() {
        this.mainMenu = new MenuOptions();
        this.administration = new MenuOptions();
        this.customer = new MenuOptions();
        this.otherService = new MenuOptions();
        this.manager = new MenuOptions();
        this.employee = new MenuOptions();
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
        otherService.addOptions(0," Update name.");
        otherService.addOptions(1,"Apply for new card.");
        otherService.addOptions(2,"Block payment card.");
        otherService.addOptions(3, "Deactivate account.");
        otherService.addOptions(4,"Request loan and apply for mortgages.");
        otherService.addOptions(5,"Go back to Customer menu.");

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
        manager.addOptions(4, "update employee salary.");
        manager.addOptions(5, "Update employee password.");
        manager.addOptions(6,"Go back to employee menu.");
    }

    public long enterPersonalNr(){
        long personNr = UserInput.readInt(" Enter customer personal number: ");
        return personNr;
    }

    public Controller login(){
        try {
            long userName = UserInput.readInt("Enter username: ");
            String password = UserInput.readLine("Enter password: ");
            Controller controller = new Controller(userName, password);
            return controller;
        }catch (Exception exception){
            System.out.println(exception);
        }
        return login();
    }

    public void handleMainMenu(){
        setUpMainMenu();
        this.mainMenu.printOptions();
        int userChoice = UserInput.readInt("Type in the option: ");
        switch (userChoice) {

            case 0:
                handleCustomerMenu(login());
                break;
            case 1:
                handleEmployeeMenu(login());
                break;
            case 2:   String password = UserInput.readLine(" Enter password: ");
                //adminLogIn(password);
                break;
            case 3:
                UserInput.input.close();
                System.exit(0);
                break;
            default:  System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                handleMainMenu();

        }
    }

    public void handleCustomerMenu(Controller controller) {

        this.customer.printOptions();
        int userChoice = UserInput.readInt("Type in the option: ");
        switch (userChoice) {
            case 0:
                String message = controller.viewAccountNo();
                System.out.println(message);
                handleCustomerMenu(controller);
                break;
            case 1:
                message=controller.viewAccountBalance();
                System.out.println(message);
                handleCustomerMenu(controller);
                break;
            case 2:
                double value = UserInput.readDouble("Please enter the amount you want to deposit: ");
                try {
                    message = controller.depositMoney(value);
                    System.out.println(message);
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }

                handleCustomerMenu(controller);
                break;
            case 3:
                try {
                    double amount = UserInput.readDouble("Please enter the amount you want to withdraw: ");
                    message = controller.withdrawMoney(amount);
                    System.out.println(message);
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }

                handleCustomerMenu(controller);
                break;
            case 4:
                try {
                    double amount =UserInput.readDouble("Please enter the amount you want to transfer: ");
                    String account =UserInput.readLine("Please enter the account No of the recievient:");
                    message =controller.transferMoney(amount, account);
                    System.out.println(message);
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
                handleCustomerMenu(controller);
                break;
            case 5:
                message=controller.FiveLatestTransaction();
                System.out.println(message);
                handleCustomerMenu(controller);
                break;
            case 6:
                message=controller.transactionHistory();
                System.out.println(message);
                handleCustomerMenu(controller);
                break;
            case 7:// inbox
                controller.updateBudget(UserInput.readDouble("Please enter the budget: "));
                System.out.println("Your budget was set successfully");
                handleCustomerMenu(controller);
                break;
            case 8:
                controller.updateBudget(UserInput.readDouble("Please enter the budget you want to set: "));
                System.out.println("You have updated your budget successfully");
                handleCustomerMenu(controller);
                break;
            case 9:// inbox
                break;
            case 10:
                handleOtherService(controller);
                break;
            case 11: handleMainMenu();
            default:
                System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                handleCustomerMenu(controller);
                int option = UserInput.readInt(" Enter 0 to try again." + Utilities.EOL + " Enter 1 to go back to main menu. ");
                do{
                    switch (option) {
                        case 0:
                            handleCustomerMenu(controller);
                            break;
                        case 1:
                            handleMainMenu();
                        default:
                            System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                    }
                }while (option < 0 || option > 1);
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
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
                handleOtherService(controller);
                break;
            case 1:
                handleOtherService(controller);
                break;
            case 2:
                String option = UserInput.readLine("Are you sure that you want to block your debit card? "+ Utilities.EOL + " Please answer: yes or no");
                if(option.equalsIgnoreCase("yes")){
                    String cardBlocked = controller.blockCard();
                    System.out.println(cardBlocked);
                    handleOtherService(controller);
                } else {
                    handleOtherService(controller);
                }
                break;
            case 3:
                handleOtherService(controller);
                break;
            case 4:
                handleOtherService(controller);
                break;
            case 5:
                handleCustomerMenu(controller);
                break;

            default:
                System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                handleOtherService(controller);
        }

    }

    public void handleEmployeeMenu(Controller controller) {


        this.employee.printOptions();
        int userChoice = UserInput.readInt("Type in the option");

        switch (userChoice) {
            case 0:
                System.out.println("Registering a new Customer: ");
                String option;
                try {
                    System.out.println("Registering a Customer user: ");
                    String fullName = UserInput.readLine("Enter your full name:");
                    long personalNo = UserInput.readInt("Enter your personal number (YYYYMMDDXXXX): ");
                    String password = UserInput.readLine("Create a password: " + Utilities.EOL +
                            "The password must have a minimum of 8 characters in length" + Utilities.EOL +
                            "and contain: lowercase letter, uppercase letter, digit.");
                    int cardNr = UserInput.readInt("Enter card number: ");
                    int cvc = UserInput.readInt("Enter cvc number: ");
                    String expirationDate = UserInput.readLine("Enter expiration date: ");
                    int code = UserInput.readInt("Enter code: ");
                    controller.createCustomer(fullName,personalNo,password,cardNr,cvc,expirationDate,code);
                    System.out.println("Customer was successfully registered.");
                } catch (IllegalAccessException scannerError) {
                    System.out.println("Invalid input.");
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
                handleEmployeeMenu(controller);
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
                long personalNo = UserInput.readInt("Please type the personal number for the customer:");
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
                String name=UserInput.readLine("Please enter the name of the employee: ");
                long personalNo = UserInput.readInt("The Personal number of the employee: ");
                String password =UserInput.readLine("Please type the employees password: ");
                double salary = UserInput.readDouble("Please type the salary of the employee: ");
                try {
                    System.out.println(controller.createEmployee(name, personalNo,password,salary));
                    handleManagerMenu(controller);
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
                handleManagerMenu(controller);
                break;
            case 3:// remove employee
                personalNo = UserInput.readInt("Please type the personalNo of the employee you wish to remove: ");
                try {
                    System.out.println(controller.removeEmployee(personalNo));
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }

                handleManagerMenu(controller); //thisjoihasdhisfsdsd
                break;
            case 4://update employee salary
                personalNo = UserInput.readInt("Type the personalNo of the employee you wish to change the salary of: ");
                double newSalary = UserInput.readDouble("Write the new salary of the employee: ");
                System.out.println(controller.setEmployeeSalary(personalNo,newSalary));
                handleManagerMenu(controller);
                break;
            case 5:// update employee password
                personalNo=UserInput.readInt("Type the personalNo of the employee you wish to change the password of: ");
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
            administration.printOptions();
            int userChoice = UserInput.readInt("Type in the option:");
            switch (userChoice) {
                case 0:
                    try{
                        String password;
                        String repeatedPassword;
                        do {
                            password = UserInput.readLine("Create a password: " + Utilities.EOL +
                                    "The password must have a minimum of 8 characters in length" + Utilities.EOL +
                                    "and contain at least  contain: lowercase letter, uppercase letter, digit.");
                            repeatedPassword = UserInput.readLine("Confirm password");

                        } while (!password.equals(repeatedPassword));
                        controller.setAdminPassword(password);

                    }catch (Exception exception){
                        System.out.println(exception.getMessage());
                    }
                    handleAdministration(controller);
                    break;
                case 1:
                    System.out.println(" Create a Manager: ");
                    String option;
                    do {
                        try {
                            System.out.println(" Registering a manager user: ");
                            String fullName = UserInput.readLine(" Type you full name:");
                            int personalNo = UserInput.readInt(" Enter your personal number (YYYYMMDDXXXX): ");
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

                            controller.createManager(fullName,personalNo, password, salary,bonus);
                        } catch (IllegalAccessException scannerError) {
                            System.out.println("Invalid input");
                        } catch (Exception exception) {
                            System.out.println(exception.getMessage());
                        }
                        option = UserInput.readLine("Do you want to create another manager?");

                    } while (option.equalsIgnoreCase("yes"));
                    handleAdministration(controller);
                    break;

                case 2:
                    try {
                        long personNr = UserInput.readInt("Enter managers personal number: ");
                        controller.removeManager(personNr);
                    }catch (Exception exception){
                        System.out.println(exception.getMessage());
                    }
                    handleAdministration(controller);
                    break;

                case 3:
                    try {
                        long personNr = UserInput.readInt("Enter manger personal number: ");
                        double newSalary = UserInput.readDouble("Enter Manager salary: ");
                        controller.setManagerSalary(newSalary, personNr);
                    }catch (Exception exception){
                        System.out.println(exception.getMessage());
                    }
                    handleAdministration(controller);
                    break;
                case 4:
                    try {
                        long personNr = UserInput.readInt("Enter manger personal number: ");
                        String password;
                        String repeatedPassword;
                        do {
                            password = UserInput.readLine("Create a password: " + Utilities.EOL +
                                    "The password must have a minimum of 8 characters in length" + Utilities.EOL +
                                    "and contain at least  contain: lowercase letter, uppercase letter, digit.");
                            repeatedPassword = UserInput.readLine("Confirm password");

                        } while (!password.equals(repeatedPassword));
                        controller.setManagerPassword(personNr,password);
                    }catch (Exception exception){
                        System.out.println(exception.getMessage());
                    }
                    handleAdministration(controller);
                    break;
                case 5:
                    long personNr= UserInput.readInt("Enter employees personal number : ");
                    double salary = UserInput.readDouble("Enter new salary: ");
                    double bonus = UserInput.readDouble("Enter salary bonus: ");
                    try{
                        controller.promoteEmployee(personNr,salary,bonus);
                    }catch (Exception exception){
                        System.out.println(exception.getMessage());
                    }
                    break;
                case 6:// apply for vacation
                    handleMainMenu();
                    break;
                default: System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                    handleAdministration(controller);
            }
    }


}






