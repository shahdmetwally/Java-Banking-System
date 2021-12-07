package Menu;

import Main.Controller;
import Utilities.Utilities;
import Utilities.UserInput;

public class CustomersMenu {
    private final MenuOptions customer;
    private final MenuOptions otherService;
    private final Controller controller;

    public CustomersMenu(){
        customer = new MenuOptions();
        otherService = new MenuOptions();
        controller = new Controller();
    }
    public void startCustomersMenu(){
        customer.setMenuName("Customer Menu " + Utilities.EOL +
                "--------------------" + Utilities.EOL +
                " Choose one of the options below.");
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

        otherService.setMenuName("Other services Menu " + Utilities.EOL +
                "--------------------" + Utilities.EOL +
                " Choose one of the options below.");
        otherService.addOptions(0," Update name");
        otherService.addOptions(1,"Apply for card");
        otherService.addOptions(2,"Block payment card");
        otherService.addOptions(3, "Deactivate account");
        otherService.addOptions(4,"Request loan and apply for mortgages");
        otherService.addOptions(5,"Go back to Customer menu");
    }

    public void handleCustomerMenu(){
        this.customer.printOptions();
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
                case 8:handleOtherService();
                    break;
                default:
                    System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                    handleCustomerMenu();
            }
    }


    public void handleOtherService(){
        this.otherService.printOptions();
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
            default:
                System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                handleOtherService();
        }

    }
}
