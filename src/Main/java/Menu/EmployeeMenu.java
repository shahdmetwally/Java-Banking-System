package Menu;

import Classes.Customer;
import Main.Controller;
import Utilities.Utilities;
import Utilities.UserInput;

import static Classes.Bank.users;

public class EmployeeMenu {

    private final MenuOptions employee = new MenuOptions();
    private final MenuOptions manager  = new MenuOptions();
    private Controller controller = new Controller();

    public EmployeeMenu(){

    }

    public void setUpEmployeeMenu(){
        employee.setMenuName("Employee Menu " + Utilities.EOL +
                "--------------------" + Utilities.EOL +
                " Choose one of the options below.");
        employee.addOptions(0, "Create Customer");
        employee.addOptions(1,"Create a bank account for customer");
        employee.addOptions(2, "Show customer accounts");
        employee.addOptions(3,"Approve loans and mortgages");
        // we can maybe give access to the employee to enter the customer menu, getting
        // the user personal number and a personal code (4 digit).
        employee.addOptions(4, "Update customer password");
        employee.addOptions(5, "View salary");
        employee.addOptions(6,"Apply for vacation");

        employee.addOptions(7,"Manager options");
        employee.addOptions(8, "Go back to Main Menu");
    }
    public void setUpManagerMenu(){
        manager.setMenuName("Manager Menu " + Utilities.EOL +
                "--------------------" + Utilities.EOL +
                " Choose one of the options below.");
        manager.addOptions(0,"Show Bank Balace");
        manager.addOptions(1, "Show total loaned amount");
        manager.addOptions(2,"Create employee");
        manager.addOptions(3, "Remove employee");
        manager.addOptions(4,"update employee salary");
        manager.addOptions(5,"Update employee password");
    }


    public void printOptionManager(){
        this.manager.printOptions();
    }
    public void printOptionEmployee(){
        this.employee.printOptions();
    }

    public void handleEmployeeMenu() {
        this.employee.printOptions();
        int userChoice = UserInput.readInt("Type in the option");

        switch (userChoice) {
            case 0:
                System.out.println(" Registering a new Customer: ");
                String option;
                do {
                    try {
                        System.out.println(" Registering a Customer user: ");
                        String fullName = UserInput.readLine(" Enter you full name:");
                        String personalNo = UserInput.readLine(" Enter your personal number (YYYYMMDDXXXX): ");
                        String password = UserInput.readLine("Create a password: " + Utilities.EOL +
                                "The password must have a minimum of 8 characters in length" + Utilities.EOL +
                                "and contain at least  contain: lowercase letter, uppercase letter, digit.");

                        if (controller.alreadyExistUser(personalNo)) {
                            throw new Exception("This personal number " + personalNo + " has already been registered.");
                        }
                        Customer customer = new Customer(fullName, personalNo, password) {
                        };
                        users.add(customer);
                    } catch (IllegalAccessException scannerError) {
                        System.out.println("Invalid input");
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    option = UserInput.readLine("Do you want to continue?");

                } while (option.equalsIgnoreCase("yes"));

                break;
            case 1://create a bank account for customer
                break;
            case 2://Show customer accounts
                break;
            case 3://Approve loans and mortgages
                break;
            case 4://update customer password
                break;
            case 5://
                break;
            case 6:
                break;
            default:
                System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);


                /*
                  employee.addOptions(0, "Create Customer");
        employee.addOptions(1,"Create a bank account for customer");
        employee.addOptions(2, "Show customer accounts");
        employee.addOptions(3,"Approve loans and mortgages");
        // we can maybe give access to the employee to enter the customer menu, getting
        // the user personal number and a personal code (4 digit).
        employee.addOptions(4, "Update customer password");
        employee.addOptions(5, "View salary");
        employee.addOptions(6,"Apply for vacation");

        employee.addOptions(7,"Manager options");
        employee.addOptions(8, "Go back to Main Menu");
                 */
        }

    }





}
