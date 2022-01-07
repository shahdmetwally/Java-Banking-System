package Menu;

import Bank.Bank;
import Classes.Role;
import MainController.Controller;
import MainController.StartProgram;
import Utilities.UserInput;
import Utilities.Utilities;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Paths;
import java.util.InputMismatchException;

public class Menu {
    private CustomerMenu customerMenu;
    private MenuOptions mainMenu;
    private Controller controller;
    private EmployeeMenu employeeMenu;
    private AdministrationMenu administrationMenu;

    public Menu(Bank bank) {
       this.mainMenu = new MenuOptions();
    }
    public void setUpMain(){
        String menuName = "Main Menu " + Utilities.EOL +
                "--------------------" + Utilities.EOL +
                " Choose one of the options below.";
        mainMenu.setMenuName(menuName);
        mainMenu.addOptions(0, "Log in as a Customer.");
        mainMenu.addOptions(1, "Log in as an Employee.");
        mainMenu.addOptions(2, "System Administration.");
        mainMenu.addOptions(3, "Close System.");

    }

    public Controller login() throws Exception{
        String userName = "";
        do {
            userName = UserInput.readLine("Enter username: ");
            if(!controller.getBank().getUsers().containsKey(userName)){
                System.out.println("Invalid input or user doesn't exists");
            }
        }while(!controller.getBank().getUsers().containsKey(userName));
        String password = UserInput.readLine("Enter password: ");
        return controller;
    }

    public void handleMainMenu(){

        String userChoiceStr ;
        do{
            userChoiceStr  = UserInput.readLine("Type in the option: ");
            if(!Utilities.isNumeric(userChoiceStr)|| userChoiceStr.isEmpty()) {
                System.out.println("Invalid input");
            }
        }while(!Utilities.isNumeric(userChoiceStr)|| userChoiceStr.isEmpty());
        int userChoice = Integer.parseInt(userChoiceStr);

        switch (userChoice) {

            case 0: //Log in as a Customer
              String option;
               do {
                   try {
                       handleCustomerMenu();
                       this.controller = login();
                       this.customerMenu = new CustomerMenu(controller);
                       customerMenu.setUpCustomerMenu();
                       handleCustomerMenu();
                   } catch (Exception exception) {
                       System.out.println(exception.getMessage());
                   }
                   option = UserInput.readLine("Do you want to continue? (Yes or No)" );
               }while(option.equalsIgnoreCase("yes"));
                handleMainMenu();
                break;
            case 1: // Log in an Employee
                do{
                try {
                    this.controller = login();
                    this.employeeMenu = new EmployeeMenu(controller);
                    employeeMenu.setUpEmployeeMenu();
                    handleEmployeeMenu();
                } catch (IllegalAccessException scannerError) {
                    System.out.println("Invalid input.");

                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
                option =UserInput.readLine("Do you want to continue? (Yes or No)" );
                }while (option.equalsIgnoreCase("yes"));
                break;
            case 2: //System Administration
                try {
                    this.controller = login();
                    this.administrationMenu = new AdministrationMenu(controller);
                    handleAdministration();
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
                handleMainMenu();
                break;
            case 3: //Close System
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
    // Customer
    public void handleCustomerMenu() {
        if (controller.getUser().getRole() == Role.CUSTOMER) {
            customerMenu.customerMenu.printOptions();

            String userChoiceStr;
            do {
                userChoiceStr = UserInput.readLine("Type in the option: ");
                if (!Utilities.isNumeric(userChoiceStr) || userChoiceStr.isEmpty()) {
                    System.out.println("Invalid input");
                }
            } while (!Utilities.isNumeric(userChoiceStr) || userChoiceStr.isEmpty());
            int userChoice = Integer.parseInt(userChoiceStr);
            switch (userChoice) {
                case 0: //View account number
                    customerMenu.viewAccountNr();
                    handleCustomerMenu();
                    break;
                case 1: //View account balance
                    customerMenu.viewAccountBalance();
                    handleCustomerMenu();
                    break;
                case 2:
                    String option;
                    do{
                        try {
                            customerMenu.depositMoney();
                            handleCustomerMenu();
                        } catch (IllegalAccessException scannerError) {
                            System.out.println("Invalid input.");
                        }catch (Exception exception){
                            System.out.println(exception.getMessage());
                        }
                        option = UserInput.readLine("Do you want to continue? ( yes or no");
                    }while(option.equalsIgnoreCase("yes"));
                    handleCustomerMenu();
                    break;
                case 3:
                    do{
                        try {
                            customerMenu.withDrawMoney();
                            handleCustomerMenu();

                        }catch (Exception exception){
                            System.out.println(exception.getMessage());
                        }
                        option = UserInput.readLine("Do you want to continue? ( yes or no");
                    }while(option.equalsIgnoreCase("yes"));
                    handleCustomerMenu();
                    break;
                case 4: //Transfer money
                    do{
                        try {
                            customerMenu.transferMoney();
                            handleCustomerMenu();

                        }catch (Exception exception){
                            System.out.println(exception.getMessage());
                        }
                        option = UserInput.readLine("Do you want to continue? ( yes or no");
                    }while(option.equalsIgnoreCase("yes"));
                    handleCustomerMenu();

                    break;
                case 5: //View 5 latest transaction
                    customerMenu.view5LatestTransactions();
                    handleCustomerMenu();
                    break;
                case 6: //View all transactions
                    customerMenu.viewAllTransactions();
                    handleCustomerMenu();
                    break;
                case 7://Set a budget
                    customerMenu.setBudget();
                    handleCustomerMenu();
                    break;
                case 8: //Update budget
                    customerMenu.updateBudget();
                    handleCustomerMenu();
                    break;
                case 9://Message inbox
                    handleCustomerInbox();
                    break;
                case 10: //Other services
                    handleOtherService();
                    break;
                case 11: //Log out
                    handleMainMenu();
                default:
                    System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                    handleCustomerMenu();
            }
        } else {
            System.out.println("Access denied. This menu is only for customers.");
            handleMainMenu();
        }
    }

    public void handleOtherService( ) {
        customerMenu.otherService.printOptions();
        String userChoiceStr;
        do {
            userChoiceStr = UserInput.readLine("Type in the option: ");
            if (!Utilities.isNumeric(userChoiceStr) || userChoiceStr.isEmpty()) {
                System.out.println("Invalid input");
            }
        } while (!Utilities.isNumeric(userChoiceStr) || userChoiceStr.isEmpty());
        int userChoice = Integer.parseInt(userChoiceStr);
        switch (userChoice) {
            case 0: //Update name
                customerMenu.updateCustomerName();
                handleOtherService();
                break;
            case 1: //Update salary
                customerMenu.updateSalary();
                handleOtherService();
                break;
            case 2: //Apply for new card
                customerMenu.applyForCard();
                handleOtherService();
                break;
            case 3://block card
                if(customerMenu.blockCardConfirmation()){
                    customerMenu.blockCard();
                    handleCustomerMenu();
                }else{
                    handleOtherService();}
                break;
            case 4:  //Loan request with Co-signer
                customerMenu.loanRequestWithCoSigner();
                handleCustomerMenu();
                break;
            case 5: //Loan request without Co-signer
                customerMenu.loanRequestWithOutCoSigner();
                handleCustomerMenu();
                break;
            case 6:
                handleUpdateLoanRequest();//Update loan request
                break;
            case 7:
                handleCustomerMenu(); //Return to customer menu
                break;
            default:
                System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                handleOtherService();
        }
    }

    public void handleUpdateLoanRequest( ){
        customerMenu.updateLoanRequest.printOptions();
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
                customerMenu.updateLoanType();
                handleUpdateLoanRequest();
                break;
            case 1:
             customerMenu.updateEquities();
                handleUpdateLoanRequest();
                break;

            case 2:
               customerMenu.updateTimePeriod();
                handleUpdateLoanRequest();
                break;
            case 3:
              customerMenu.updateCashContribution();
                handleUpdateLoanRequest();
                break;
            case 4:
                customerMenu.updateCosignerName();
                handleUpdateLoanRequest();
                break;
            case 5:
                customerMenu.updateCosignerPersonalNr();
                handleUpdateLoanRequest();
                break;
            case 6://  otherService.addOptions(5,"Loan status"); //? should we?
               customerMenu.updateCosignerIncome();
                handleUpdateLoanRequest();
                break;
            case 7:
                customerMenu.updateInterestType();
                handleUpdateLoanRequest();
                break;
            case 8:
                handleCustomerMenu();
            default:
                System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                handleUpdateLoanRequest();
        }
    }
    public void handleCustomerInbox( ){
        customerMenu.customerInbox.printOptions();
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
                System.out.println(controller.viewCustomerUnreadMessages());
                if(controller.checkViewMessages()){
                    handleCustomerMenu();
                }
                customerMenu.viewMessages();
                handleCustomerInbox();
                break;
            case 1: //Send messages to employees
                customerMenu.sendMessage();
                handleCustomerInbox();
            case 2://View message historyy
                customerMenu.viewMessageHistory();
                handleCustomerInbox();
                break;
            case 3:
                System.out.println(controller.viewCustomerSentMessages());
                handleCustomerInbox();
                break;
            case 4: //Back to customer menu
                handleCustomerMenu();
                break;
            default:
                System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                handleCustomerInbox();
        }
    }
    // Employee
    public void handleEmployeeMenu(){
        if(controller.getUser().getRole() == Role.EMPLOYEE || controller.getUser().getRole() == Role.MANAGER)  {
            employeeMenu.employeeMenu.printOptions();
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
                    employeeMenu.createCustomer();
                    handleEmployeeMenu();
                    break;
                case 1:
                   employeeMenu.viewCustomerAccount();
                    handleEmployeeMenu();
                    break;
                case 2:
                    int option;
                    do {
                        option = UserInput.readInt("Choose between: " + Utilities.EOL +
                                "1.Approve 2.Decline 3.Modify 4.Interest rate");
                        switch (option) {
                            case 1:
                                employeeMenu.approveLoan();
                                handleEmployeeMenu();
                                break;
                            case 2:
                                employeeMenu.declineLoan();
                                handleEmployeeMenu();
                                break;
                            case 3:
                                employeeMenu.modify();
                                handleEmployeeMenu();
                                break;
                            case 4:
                                employeeMenu.interestRateOffer();
                                handleEmployeeMenu();
                            default:
                                System.out.println("Invalid choice. Please select between option 1 to 3. ");
                        }

                    }while( option < 1 || option > 4);

                    break;
                case 3:
                    employeeMenu.approveCardRequest();
                    handleEmployeeMenu();
                    break;
                case 4:
                    employeeMenu.calculateDTI();
                    handleEmployeeMenu();
                    break;
                case 5:
                    employeeMenu.monthlyMortgage();
                    handleEmployeeMenu();
                    break;
                case 6:
                    employeeMenu.updateCustomerPassword();
                    handleEmployeeMenu();
                    break;
                case 7:
                    employeeMenu.annualMortgageHouse();
                    handleEmployeeMenu();
                    break;
                case 8:
                    System.out.println(controller.viewSalary());
                    handleEmployeeMenu();
                    break;
                case 9: // connect to inbox, see the controller
                    employeeMenu.applyForVacation();
                    handleEmployeeMenu();
                    break;
                case 10:
                    handleEmployeeInbox();
                    break;
                case 11:
                    handleManagerMenu();
                    break;
                case 12:
                    handleMainMenu();
                default:
                    System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                    handleEmployeeMenu();
            }
        } else {
            System.out.println("Access denied. This menu is only for employees.");
            handleMainMenu();
        }
    }
    public void handleEmployeeInbox(){
     employeeMenu.employeeInbox.printOptions();
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
                employeeMenu.viewUnreadMessage();
                handleEmployeeInbox();
                break;
            case 1://send message to a customer
                employeeMenu.sendMessageToCustomer();
                handleEmployeeInbox();
                //add another option, send message to all customers
                break;
            case 2:
               employeeMenu.sendMessageToAllCustomers();
                handleEmployeeInbox();
                break;
            case 3:
                System.out.println(controller.viewAllLoanRequests());
                handleEmployeeInbox();
                break;
            case 4:
                System.out.println(controller.viewAllCardRequests());
                handleEmployeeInbox();
                break;
            case 5:
                employeeMenu.viewMessageHistory();
                handleEmployeeMenu();
                break;
            case 6:
                employeeMenu.viewSentMessage();
                handleEmployeeInbox();
                break;
            case 7:
                handleEmployeeMenu();
                break;
            default:
                System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                handleCustomerInbox();
        }
    }

    public void handleManagerMenu(){
        if (controller.getUser().getRole() == Role.MANAGER) {
            employeeMenu.managerMenu.printOptions();
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
                    employeeMenu.bankBalance();
                    handleManagerMenu();
                    break;
                case 1: // show total loaned amount
                  employeeMenu.showTotalLoanAmount();
                    handleManagerMenu();
                    break;
                case 2:// create employee
                    employeeMenu.createEmployee();
                    handleManagerMenu();
                    break;
                case 3:// remove employee
                    employeeMenu.removeEmployee();
                    handleManagerMenu();
                     break;
                case 4://update employee salary
                    employeeMenu.updateEmployeeSalary();
                    handleManagerMenu();
                    break;
                case 5:// update employee password
                   employeeMenu.updateEmployeePassword();
                    handleManagerMenu();
                    break;
                case 6:
                    employeeMenu.setInterestRate();
                    handleManagerMenu();
                    break;
                case 7:
                    handleManagerInbox();
                    break;
                case 8:
                    handleEmployeeMenu();
                    break;
                default:
                    System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                    handleManagerMenu();

            }
        } else {
            System.out.println("Access denied. This menu is only for managers.");
            handleEmployeeMenu();
        }
    }

    public void handleManagerInbox(){
        employeeMenu.managerMenu.printOptions();
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
                handleManagerInbox();
                break;
            case 1:
                System.out.println(controller.handleVacationApplication());
                handleManagerInbox();
                break;
            case 2:
                handleManagerMenu();
                break;
        }
    }

    public void handleAdministration(){
            administrationMenu.administrationMenu.printOptions();
            String userChoiceStr ;
            do{
                userChoiceStr  = UserInput.readLine("Type in the option: ");
                if(!Utilities.isNumeric(userChoiceStr)|| userChoiceStr.isEmpty()) {
                    System.out.println("Invalid input");
                }
            }while(!Utilities.isNumeric(userChoiceStr)|| userChoiceStr.isEmpty());
            int userChoice = Integer.parseInt(userChoiceStr);
            switch (userChoice) {
                case 0: //Change administration password
                    administrationMenu.changeAdminPassword();
                    handleAdministration();
                    break;
                case 1: //Create a manager
                    administrationMenu.createManager();
                    handleAdministration();
                    break;

                case 2: //Remove manager
                    administrationMenu.removeManager();
                    handleAdministration();
                    break;

                case 3: //Update manager salary
                    administrationMenu.updateManageSalary();
                    handleAdministration();
                    break;
                case 4: //Change manager password
                    administrationMenu.updateManagerPassword();
                    handleAdministration();
                    break;
                case 5: //Promote employee
                    administrationMenu.promoteEmployee();
                    handleAdministration();
                    break;
                case 6:
                    System.out.println(controller.showAllManagers());
                    handleAdministration();
                    break;
                case 7: //Log out
                    handleMainMenu();
                    break;
                default:
                    System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                    handleAdministration();
            }

    }





}


