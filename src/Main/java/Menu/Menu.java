package Menu;

import Classes.Customer;
import Main.AdministrationOptions;
import Main.Controller;
import Utilities.UserInput;
import Utilities.Utilities;

import static Classes.Bank.users;

public class Menu {
    private final MenuOptions mainMenu = new MenuOptions();
    private final MenuOptions employee= new MenuOptions();
    private final MenuOptions manager = new MenuOptions();
    private  final AdministrationOptions administration = new AdministrationOptions(); // or system settings
    private final Controller controller = new Controller();
    private final CustomersMenu customersMenu = new CustomersMenu();


    public void Menu() { // this is the constructor of the
        String menuName = "Main Menu " + Utilities.EOL +
                "--------------------" + Utilities.EOL +
                " Choose one of the options below.";
        mainMenu.setMenuName(menuName);
        mainMenu.addOptions(0, "Log in as Customer");
        mainMenu.addOptions(1, "Log in as Employee");
        mainMenu.addOptions(2, "System Administration");
        mainMenu.addOptions(3, "Close System");

        employee.setMenuName("Employee Menu " + Utilities.EOL +
                "--------------------" + Utilities.EOL +
                " Choose one of the options below.");
        employee.addOptions(0, "Create Customer");
        employee.addOptions(1,"Create a bank account for customer");
        employee.addOptions(2, "Show customer accounts");
        employee.addOptions(3,"Approve loans and mortgages");
        // we can maybe give access to the employee to enter the customer menu, getting
        // the user personal number and a personal code.
        employee.addOptions(4, "Update customer password");
        employee.addOptions(5, "View salary");
        employee.addOptions(6,"Apply for vacation");

        employee.addOptions(7,"Manager options");
        employee.addOptions(8, "Go back to Main Menu");

        manager.setMenuName("Manager Menu " + Utilities.EOL +
                "--------------------" + Utilities.EOL +
                " Choose one of the options below.");
        manager.addOptions(0,"Show Bank Balace");
        manager.addOptions(1, "Show total loaned amount");
        manager.addOptions(2,"Create employee");
        manager.addOptions(3, "Remove employee");
        manager.addOptions(4,"update employee salary");
        manager.addOptions(5,"Update employee password");


        administration.setMenuName("Administration Menu " + Utilities.EOL +
                "--------------------" + Utilities.EOL +
                " Choose one of the options below.");
        administration.addOptions(0, "Change administration password.");
        administration.addOptions(1,"Create manager.");
        administration.addOptions(2, "Remove manager.");
        administration.addOptions(3,"Update manager salary.");
        administration.addOptions(4,"Update manager password.");
        administration.addOptions(5,"Go back to main menu.");
    }

    public void handleMainMenu(){
        this.mainMenu.printOptions();

            int userChoice = UserInput.readInt("Type in the option: ");
            switch (userChoice) {

                case 0:handleCustomerMenu();
                    break;
                case 1:startCustomersMenu();
                    break;
                case 2:handleAdministration();
                    break;
                case 3:
                    UserInput.input.close();
                    System.exit(0);
        }
    }

    public void startCustomersMenu(){
        customersMenu.startCustomersMenu();
    }

    public void handleCustomerMenu() {
        this.employee.printOptions();
        String personNumber = UserInput.readLine("Enter personal number: ");
        String password = UserInput.readLine("Enter password: ");
        Customer customer = controller.logInCustomer(personNumber,password);

        if (customer != null) {
            customersMenu.handleCustomerMenu(customer);
        } else {
            System.out.println(" Invalid personal number or password");
            int option = UserInput.readInt(" Enter 0 to try again." + Utilities.EOL + " Enter 1 to go back to main menu. ");
            switch (option) {
                case 1:
                    handleCustomerMenu();
                    break;
                case 2:
                    handleMainMenu();
            }
        }
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
                default:
                    System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);


            }
    }

    public void handleManager(){
        this.manager.printOptions();
        String personNumber = UserInput.readLine("Enter personal number: ");
        String password = UserInput.readLine("Enter password: ");
        Customer customer = controller.logInCustomer(personNumber,password);

        if (customer != null) {
            customersMenu.handleCustomerMenu(customer);
        } else {
            System.out.println(" Invalid personal number or password");
            int option = UserInput.readInt(" Enter 0 to try again." + Utilities.EOL + " Enter 1 to go back to main menu. ");
            switch (option) {
                case 1:
                    handleCustomerMenu();
                    break;
                case 2:
                    handleMainMenu();
            }
        }

    }

    public void handleAdministration () {
      this.administration.printOptions();
      String inputPassword = UserInput.readLine(" Enter password: ");

      if (administration.isPasswordCorrect(inputPassword)) {
          int userChoice = UserInput.readInt("Type in the option:");
          switch (userChoice) {
                case 0:
                  break;
                case 1:
                    System.out.println(" Registering a new Employee: ");
                    String option;
                    do {
                        try {
                            System.out.println("Registering a Employee user: ");
                            String fullName = UserInput.readLine(" Type you full name:");
                            String personalNo = UserInput.readLine(" Enter your personal number (YYYYMMDDXXXX): ");
                            String password;
                            String repeatedPassword;
                            do {
                                password = UserInput.readLine("Create a password: " + Utilities.EOL +
                                        "The password must have a minimum of 8 characters in length" + Utilities.EOL +
                                        "and contain at least  contain: lowercase letter, uppercase letter, digit.");
                                repeatedPassword = UserInput.readLine("Confirm password");

                            } while (!password.equals(repeatedPassword));

                            if (controller.alreadyExistUser(personalNo)) {
                                throw new Exception("This personal number " + personalNo + " has already been registered.");
                            }
                            double salary = UserInput.readDouble("Enter employees gross-salary: ");

                            controller.createEmployee(fullName, personalNo, password, salary);
                        } catch (IllegalAccessException scannerError) {
                            System.out.println("Invalid input");
                        } catch (Exception exception) {
                            System.out.println(exception.getMessage());
                        }
                        option = UserInput.readLine("Do you want to continue?");

                    } while (option.equalsIgnoreCase("yes"));
                        break;
                case 2: // Remove employee

                        break;
                case 3:// update employee name
                        break;
                case 4:// update employee salary
                        break;
                case 5: // update employees password
                        break;
                case 6: // create manager
                        break;
                case 7: // remove manager
                        break;
                case 8: // update manager name
                        break;
                case 9: // update manager salary
                        break;
                case 10: // update manager password
                        break;
                case 11:
                        handleMainMenu();
                        break;
                }
      } else {
          System.out.println("Invalid password");
          int option = UserInput.readInt(" Enter 0 to try again." + Utilities.EOL + " Enter 1 to go back to main menu.");
          switch (option) {
                    case 0:
                        handleEmployeeMenu();
                        break;
                    case 1:
                        handleMainMenu();
          }
      }
    }
}
