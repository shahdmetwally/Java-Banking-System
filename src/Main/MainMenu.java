package Main;

import Classes.Customer;
import Utilities.UserInput;

public class MainMenu {
    private final MenuOptions mainMenu = new MenuOptions();
    private final MenuOptions customer = new MenuOptions();
    private final MenuOptions othercustomer = new MenuOptions();
    private final MenuOptions employee = new MenuOptions();
    private final Controller controller = new Controller();

    public MainMenu() {
        mainMenu.setMenuName("Main Menu: Please choose among the options below.");
        mainMenu.addOptions(0,"Log in as Customer");
        mainMenu.addOptions(1, "Log in as Employee");
        mainMenu.addOptions(2, "Close System");

        customer.setMenuName("Dear Customer, please choose one of the options below to proceed.");
        customer.addOptions(0, "View account balance");
        customer.addOptions(1, "Deposit money");
        customer.addOptions(2, "Withdraw cash");
        customer.addOptions(3, "Transfer money");
        customer.addOptions(4, "View 5 latest transaction");
        customer.addOptions(5, "View all transactions");
        customer.addOptions(6, "Set a budget");
        customer.addOptions(7, "Update budget");
        customer.addOptions(8, "Other services menu");

        // should be the last option, if you want to add something, add it above!
        customer.addOptions(9,"Back to Main Menu");

        othercustomer.addOptions(1,"Edit personal information");
        othercustomer.addOptions(2,"Apply for card");
        othercustomer.addOptions(3,"Block payment card");
        othercustomer.addOptions(4, "Deactivate account");
        othercustomer.addOptions(5,"Request loan and apply for mortgages");

// FIX employee menu
        employee.setMenuName("Dear Employee, please choose one of the options below.");
        employee.addOptions(0,"Create a bank account for Customer");
        employee.addOptions(1, "Customer accounts");
        employee.addOptions(1, "");
        employee.addOptions(2, "View salary");
        employee.addOptions(3,"Apply for vacation");
        employee.addOptions(5,"Approve loans and mortgages");

        // last option
        employee.addOptions(10, "Go back to Main Menu");
    }

    public void handleMainMenu(){
        this.mainMenu.printOptions();

            int userChoice = UserInput.readInt("Type in the option: ");
            switch (userChoice) {

                case 0: this.handleCustomerMenu();

                case 1:handleEmployeeMenu();
                    break;
                case 2:
                    UserInput.input.close();


            System.exit(0);
        }
    }

    public void handleCustomerMenu(){
           this.customer.printOptions();
        String personNumber = UserInput.readLine("Enter personal number: ");
        String password = UserInput.readLine("Enter password: ");
        Customer customer = controller.logInCustomer(personNumber, password);

            if( customer != null) {
                int userChoice = UserInput.readInt("Type in the option: ");
                switch (userChoice) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;

                }
            } else {
                System.out.println(" Wrong personal number or password");
            }

    }

    public void handleEmployeeMenu(){
        this.employee.printOptions();
        int userChoice = UserInput.readInt("Type in the option");

        switch(userChoice){
            case 0:
                // add text to the user
                controller.createBankAccount();
                break;
            case 1: break;
            case 2: break;
            case 3: break;
            case 4: break;
            case 5: break;
        }
    }



}
