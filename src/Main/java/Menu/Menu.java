package Menu;
import Classes.AdministrationOptions;
import Classes.Customer;
import Classes.Employee;
import Classes.MenuOptions;
import MainController.Controller;
import Utilities.UserInput;
import Utilities.Utilities;

public class Menu {
    private final MenuOptions mainMenu;
    private  final AdministrationOptions administration;
    private final EmployeeMenu employeeMenu;
    private final CustomersMenu customersMenu;
    private final Controller controller = new Controller();

    public Menu() {
        this.mainMenu = new MenuOptions();
        this.customersMenu = new CustomersMenu();
        this.employeeMenu = new EmployeeMenu();
        this.administration = new AdministrationOptions();

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
    }
    public void setUpAdminMenu(){
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
    public void setUpMenuOptions(){
        customersMenu.setUpCustomerMenu();
        customersMenu.setUpOtherServiceMenu();
        employeeMenu.setUpManagerMenu();
        employeeMenu.setUpEmployeeMenu();
        setUpMainMenu();
        setUpAdminMenu();

    }


    public void handleMainMenu(){
        setUpMenuOptions();
        this.mainMenu.printOptions();
        int userChoice = UserInput.readInt("Type in the option: ");
        switch (userChoice) {

            case 0:handleCustomerMenu();
                break;
            case 1:handleEmployeeMenu();
                break;
            case 2:handleAdministration();
                break;
            case 3:
                UserInput.input.close();
                System.exit(0);
                break;
            default:  System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                handleMainMenu();

        }
    }

    public void handleCustomerMenu() {
        String personNumber = UserInput.readLine("Enter personal number: ");
        String password = UserInput.readLine("Enter password: ");
        Customer customer = controller.logInCustomer(personNumber,password);

        if (customer != null) {
            customersMenu.handleCustomerMenu(customer);
        } else {
            System.out.println(" Invalid personal number or password");
            int option = UserInput.readInt(" Enter 0 to try again." + Utilities.EOL + " Enter 1 to go back to main menu. ");
            do{
                switch (option) {
                    case 1:
                        handleCustomerMenu();
                        break;
                    case 2:
                        handleMainMenu();
                    default:
                        System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);

                }
            }while (option < 0 || option > 2);
        }
    }


    public void handleEmployeeMenu() {
        String personNumber = UserInput.readLine("Enter personal number: ");
        String password = UserInput.readLine("Enter password: ");
        Employee employee = controller.logInEmployee(personNumber, password);

        if (employee != null) {
            employeeMenu.handleEmployeeMenu(employee);
        } else {
            System.out.println("Invalid personal number or password");
            int option = UserInput.readInt(" Enter 0 to try again." + Utilities.EOL + " Enter 1 to go back to main menu. ");
            do {
                switch (option) {
                    case 1:
                        handleCustomerMenu();
                        break;
                    case 2:
                        handleMainMenu();
                }
            } while (option < 0 || option > 2);
        }
    }

    public void handleAdministration() {
        String inputPassword = UserInput.readLine(" Enter password: ");

        if (administration.isPasswordCorrect(inputPassword)) {
            this.administration.printOptions();
            int userChoice = UserInput.readInt("Type in the option:");
            switch (userChoice) {
                case 0:
                   try {
                       String password;
                       String repeatedPassword;
                       do {
                           password = UserInput.readLine("Create a password: " + Utilities.EOL +
                                   "The password must have a minimum of 8 characters in length" + Utilities.EOL +
                                   "and contain at least  contain: lowercase letter, uppercase letter, digit.");
                           repeatedPassword = UserInput.readLine("Confirm password");

                       } while (!password.equals(repeatedPassword));
                       administration.setPassword(password);
                   }catch (Exception exception){
                       System.out.println(exception.getMessage());
                   }
                    handleAdministration();
                    break;
                case 1:
                    System.out.println(" Create a Manager: ");
                    String option;
                    do {
                        try {
                            System.out.println(" Registering a manager user: ");
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

                            double salary = UserInput.readDouble("Enter manager gross-salary: ");
                            double bonus = UserInput.readDouble("Enter manager bonus:");

                            controller.createManager(fullName, personalNo, password, salary,bonus);
                        } catch (IllegalAccessException scannerError) {
                            System.out.println("Invalid input");
                        } catch (Exception exception) {
                            System.out.println(exception.getMessage());
                        }
                        option = UserInput.readLine("Do you want to continue?");

                    } while (option.equalsIgnoreCase("yes"));
                    handleAdministration();
                    break;

                case 2:
                    try {
                        String personNr = UserInput.readLine("Enter managers personal number: ");
                        controller.removeManager(personNr);
                    }catch (Exception exception){
                        System.out.println(exception.getMessage());
                    }
                    handleAdministration();
                    break;

                case 3:
                   try {
                       String personNr = UserInput.readLine("Enter manger personal number: ");
                       double newSalary = UserInput.readDouble("Enter Manager salary: ");
                       controller.setManagerSalary(newSalary, personNr);
                   }catch (Exception exception){
                       System.out.println(exception.getMessage());
                   }
                    handleAdministration();
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
                        controller.setManagerPassword(personNr,password);
                    }catch (Exception exception){
                        System.out.println(exception.getMessage());
                    }
                    handleAdministration();
                    break;
                case 5:
                    handleMainMenu();
                    break;
                default: System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                    handleAdministration();
            }
        } else {
            System.out.println("Invalid password");
            int option = UserInput.readInt(" Enter 0 to try again." + Utilities.EOL + " Enter 1 to go back to main menu.");
             do{
                switch (option) {
                    case 1:
                        handleAdministration();
                        break;
                    case 2:
                        handleMainMenu();
                    default:
                        System.out.println("Invalid menu option. Please type another option." + Utilities.EOL);
                }
            }while (option < 0 || option > 2);
        }
        }
    }


