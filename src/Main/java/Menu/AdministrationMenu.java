package Menu;

import Classes.Role;
import MainController.Controller;
import MainController.Controller2;
import Utilities.UserInput;
import Utilities.Utilities;

import java.util.Collections;

public class AdministrationMenu {
    MenuOptions administrationMenu;
    Controller controller;


    public AdministrationMenu(Controller controller) throws Exception {

        this.administrationMenu = new MenuOptions();
        this.controller = controller;
    }

    public Controller2 getController2(){
        return ((Controller2) controller);
    }

   public void setUpAdministrationMenu(){
       administrationMenu.setMenuName("Administration Menu " + Utilities.EOL +
               "--------------------" + Utilities.EOL +
               " Choose one of the options below.");
       administrationMenu.addOptions(0, "Change administration password.");
       administrationMenu.addOptions(1, "SetUp BankAccount for the Bank");
       administrationMenu.addOptions(2,"Create manager.");
       administrationMenu.addOptions(3, "Remove manager.");
       administrationMenu.addOptions(4,"Update manager salary.");
       administrationMenu.addOptions(5,"Change manager password.");
       administrationMenu.addOptions(6,"Promote employee.");;
       administrationMenu.addOptions(7,"Show all managers.");
       administrationMenu.addOptions(8, "Log out.");
   }


    public String registerPassword(){

        String repeatedPassword;
        String password;
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
        return password;
    }
   public void changeAdminPassword(){
       try {
           String password = registerPassword();
           String message = getController2().setAdminPassword(password);
           System.out.println(message);
       } catch (Exception exception) {
           System.out.println(exception.getMessage());
       }
   }
   public void setUpAccountForBank() {
       try {
           controller.getBank().setUpTheBanksAccount();
           double amount = UserInput.readDouble("Enter the bank balance: ");
           controller.getBank().getBanksAccount().depositMoney(amount);
       }catch (Exception exception){
           System.out.println(exception.getMessage());
       }
   }

   public String registerPersonalNr(){
       String personalNo = UserInput.readLine("Enter your personal number (YYYYMMDDXXXX): ");
       if(!controller.isPersonNrCorrect(personalNo)) {
           do {
               personalNo = UserInput.readLine("Your personal number must be in this format (YYYYMMDDXXXX) and whithin valid times: ");
           } while (!controller.isPersonNrCorrect(personalNo));
       }
       return personalNo;
   }
   public double registerSalary(){
       String salaryStr;
       do{
           salaryStr = UserInput.readLine("Enter salary: ");
           if(!Utilities.isNumeric(salaryStr)|| salaryStr.isEmpty()) {
               System.out.println("Enter a valid number");
           }
       }while(!Utilities.isNumeric(salaryStr)|| salaryStr.isEmpty());
       double salary = Double.parseDouble(salaryStr);
       return salary;
   }
   public double registerBonus(){
       String bonus;
       do{
           bonus = UserInput.readLine("Enter manager bonus: ");
           if (!Utilities.isNumeric(bonus) || bonus.isEmpty()){
               System.out.println("Enter a valid number: ");
           }
       }while(!Utilities.isNumeric(bonus) || bonus.isEmpty());
       double actualBonus = Double.parseDouble(bonus);
       return actualBonus;
   }
   public String  checkPersonNr(){
       String personNr = UserInput.readLine("Enter manger personal number: ");
       if(!controller.getBank().getUsers().containsKey(personNr) || !controller.isManager(personNr)) {
           do {
               personNr = UserInput.readLine("Enter an existing managers personal number: ");
           } while (!controller.getBank().getUsers().containsKey(personNr) || !controller.isManager(personNr));
       }
       return personNr;
   }
   public void createManager(){
       System.out.println("Create a Manager: ");
       String option;
       do {
           try {
               System.out.println(" " + "Registering a manager user: ");
               String fullName = UserInput.readLine("Type your full name:");
               String personalNo = registerPersonalNr();
               String password = registerPassword();
               double salary = registerSalary();
               double actualBonus = registerBonus();

               String message = getController2().createManager(fullName, personalNo, password, salary, actualBonus);
               System.out.println(message);
           } catch (IllegalAccessException scannerError) {
               System.out.println("Invalid input.");
           } catch (Exception exception) {
               System.out.println(exception.getMessage());
           }
           option = UserInput.readLine("Do you want to create another manager?" + Utilities.EOL + " Yes or No");

       } while (option.equalsIgnoreCase("yes"));
   }
   public void removeManager(){
       try {
           String personNr = UserInput.readLine("Enter managers personal number: ");
           if(!controller.getBank().getUsers().containsKey(personNr) || !controller.isManager(personNr)) {
               do {
                   personNr = UserInput.readLine("Enter an existing managers personal number: ");
               } while (!controller.getBank().getUsers().containsKey(personNr) || !controller.isManager(personNr));
           }
           String message = getController2().removeManager(personNr);
           System.out.println(message);
       } catch (Exception exception) {
           System.out.println(exception.getMessage());
       }

   }
   public void updateManageSalary(){
       try {
           String personNr = checkPersonNr();
           double newSalary = registerSalary();
           String message = getController2().setManagerSalary(newSalary, personNr);
           System.out.println(message);
       } catch (Exception exception) {
           System.out.println(exception.getMessage());
       }
   }
   public void updateManagerPassword(){
       try {
           String personNr = checkPersonNr();
           String password = registerPassword();
           String message = getController2().setManagerPassword(personNr, password);
           System.out.println(message);
       } catch (Exception exception) {
           System.out.println(exception.getMessage());
       }
   }
   public void promoteEmployee(){
       String personNr = checkPersonNr();
       double salary = registerSalary();
       double bonus = registerBonus();
       try {
           String message = getController2().promoteEmployee(personNr, salary, bonus);
           System.out.println(message);
       } catch (Exception exception) {
           System.out.println(exception.getMessage());
       }
   }

}
