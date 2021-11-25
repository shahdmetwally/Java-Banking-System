package Classes;
import java.util.ArrayList;

public class Employee extends Account {

    final double TAX = 0.33; //change later
    private int birthYear;
    private int vacationDays;


    public Employee(String empName, String empID, String password, int birthYear, double grossSalary){
        super(empName, empID, password, grossSalary);
        this.birthYear = birthYear;
        this.vacationDays = 25;

    }

    public void createEmployee(String empID, String password, String empName, int birthYear, double grossSalary, int vacationDays){
        Employee emp1 = new Employee(empID, password, empName, birthYear, grossSalary);
    }

    //Need to look at this
    public void approveLoan(int personalNumber){
        for(Customer customer : Bank.customers){
            if(customer.getPersonalNumber()==personalNumber){
                System.out.println("Mortgage approved.");
            }
        }
    }

    public void removeEmployee(String emID){
        for(int i = 0; i < Bank.employees.size(); i++){
            if(Bank.employees.get(i).equals(emID)){
                Bank.employees.remove(i);
            }
        }
    }

    public void removeCustomerAccount(int personalNumber){
        for(int i = 0; i < Bank.customers.size(); i++){
            if(Bank.customers.get(i).getPersonalNumber()==personalNumber){
                Bank.customers.remove(i);
            }
        }
    }


    //This needs work discuss it
    public void sendMessageToCustomer(String message, Customer customer){
        //customer.inbox.add(message)
    }




    public String getCustomerInfo(String userName, int personalNumer){
        String infoCustomer = "";
        for(Customer customer : Bank.customers){
            if(customer.getFullName().equals(userName)&& (customer.getPersonalNumber() == personalNumer)) {
                infoCustomer =  customer.getTransaction() + "Loan: "+ customer.getLoan();

            }
        }
        return infoCustomer;
    }


    public void setVacationDays(int vacationDays) {
        this.vacationDays = vacationDays;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
    public void takeDaysOff(int amountOfDays){
        this.vacationDays -= amountOfDays;
    }

    public double calculateNetSalary(){
        return super.getSalary() * TAX;
    }

    public int getVacationDays() {
        return vacationDays;
    }

    public void updateVacationDays(int vacationDays) {
        this.vacationDays = vacationDays;
    }

    public String toString(){
        return super.getFullName() + "'s gross salary is " + super.getSalary() + " SEK per month." ;
    }

    public void deactivateAccount(Customer customer){
        customer.setActive(false);
    }



}
