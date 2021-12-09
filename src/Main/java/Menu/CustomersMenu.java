package Menu;

import Classes.Customer;
import Classes.MenuOptions;
import MainController.Controller;
import Utilities.Utilities;
import Utilities.UserInput;

public class CustomersMenu {
    private final MenuOptions customer = new MenuOptions();
    private final MenuOptions otherService = new MenuOptions();
    private final Controller controller = new Controller();

    public CustomersMenu(){
     /*   customer = new MenuOptions();
        otherService = new MenuOptions();
        controller = new Controller();

      */
    }
    public void setUpCustomerMenu(){
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

    }
    public void setUpOtherServiceMenu(){
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
    public void printOptionCustomer(){
        this.customer.printOptions();
    }
    public void printOptionOtherService(){
        this.otherService.printOptions();
    }

    public void handleCustomerMenu(Customer customer){
      printOptionCustomer();
            int userChoice = UserInput.readInt("Type in the option: ");
            switch (userChoice) {
                case 0:controller.viewAccountBalance(customer);
                    break;
                case 1:
                    double value = UserInput.readDouble("Please enter the amount you want to deposit: ");
                    try {
                        controller.depositMoney(customer, value);
                    } catch (Exception exception) {           // bit confused how to put the exception for every try catch
                        System.out.println(exception.getMessage());
                    }
                    break;
                case 2:
                    try {
                        controller.withdrawMoney(customer,  UserInput.readDouble("Please enter the amount you want to withdraw: "));
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case 3:
                    try {
                        controller.transferMoney(customer, UserInput.readDouble("Please enter the amount you want to transfer: "), UserInput.readLine("Please enter the account No of the recievient:"));
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case 4:
                    controller.FiveLatestTransaction(customer);
                    break;
                case 5:
                    controller.transactionHistory(customer);
                    break;
                case 6:
                    controller.updateBudget(customer,UserInput.readDouble("Please enter the budget: "));
                    break;
                case 7:
                    controller.updateBudget(customer,UserInput.readDouble("Please enter the budget you want to set: "));
                    break;
                case 8:
                    handleOtherService(customer);
                    break;
                case 9:
                    break;
                default:
                    System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                    handleCustomerMenu(customer);
            }
    }


    public void handleOtherService(Customer customer){
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
            case 5:handleCustomerMenu(customer);
                break;

            default:
                System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                handleOtherService(customer);
        }

    }
}
