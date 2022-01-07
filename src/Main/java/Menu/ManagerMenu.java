package Menu;

import MainController.Controller;
import Utilities.UserInput;
import Utilities.Utilities;

import java.util.InputMismatchException;

public class ManagerMenu extends EmployeeMenu{

    public ManagerMenu(Controller controller){
      super(controller);
    }
    public void bankBalance(){ // 0
        try{
            System.out.println(controller.getTotalBalance());
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
    public void showTotalLoanAmount(){ //1
        try{
            System.out.println(controller.getTotalLoan());
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public void createEmployee(){ //2

        String personalNoMessage = "The Personal number of the employee: ";
        String passwordMessage = "Create a password for the employee: ";
        String incomeMessage = "Please enter the salary of the employee: ";

        try {
            String name = UserInput.readLine("Please enter the name of the employee: ");
            String personalNo = super.registerPersonalNr(personalNoMessage);
            String password = super.registerPassword(passwordMessage);
            double salary = super.registerIncome(incomeMessage);
            System.out.println(controller.createEmployee(name, personalNo, password, salary));

        } catch (InputMismatchException exception) {
            System.out.println("Invalid input");

        } catch (Exception exception) {
            System.out.println(exception.getMessage());

        }
    }
    public void removeEmployee(){//3
       String  personalNo = UserInput.readLine("Please enter the personal number of the employee you want to remove: ");
        if (!controller.isPersonNrCorrect(personalNo) || !controller.isEmployee(personalNo)) {
            do {
                personalNo = UserInput.readLine("Please enter an existing employee's personal number in the format (YYYYMMDDXXXX): ");
            } while (!controller.isPersonNrCorrect(personalNo) || !controller.isEmployee(personalNo));
        }
        try {
            System.out.println(controller.removeEmployee(personalNo));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
    public void updateEmployeeSalary(){//4
        String personalNo = UserInput.readLine("Type the personal number of the employee you wish to change the salary of: ");
        if (!controller.isPersonNrCorrect(personalNo) || !controller.isEmployee(personalNo)) {
            do { //should we have it like this
                personalNo = UserInput.readLine("Please enter an existing employee's personal number in the format (YYYYMMDDXXXX): ");
            } while (!controller.isPersonNrCorrect(personalNo) || !controller.isEmployee(personalNo));
        }
        String salaryMessage = "Write the new salary of the employee: ";
        double salary = super.registerIncome(salaryMessage);

        System.out.println(controller.setEmployeeSalary(personalNo, salary));
    }
    public void updateEmployeePassword(){//5
       String personalNo = UserInput.readLine("Type the personalNo of the employee you wish to change the password of: ");
        if (!controller.isPersonNrCorrect(personalNo) || !controller.isEmployee(personalNo)) {
            do {
                personalNo = UserInput.readLine("Employee's personal number must be in this format (YYYYMMDDXXXX) and within valid times: ");
            } while (!controller.isPersonNrCorrect(personalNo) || !controller.isEmployee(personalNo));
        }

        String passwordMessage = "Please type the new password of the employee: ";
        String password = super.registerPassword(passwordMessage);
        System.out.println(controller.setEmployeePassword(password, personalNo));
    }
    public void setInterestRate(){
        try {
            double interestRate = UserInput.readDouble("Enter the variable interest rate: ");
            controller.setVariableInterestRate(interestRate);
        } catch (InputMismatchException exception) {
            System.out.println("Invalid input. Please enter numbers.");

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

}
