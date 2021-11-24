package Classes;
import java.util.ArrayList;

public class Employee {

    final double TAX = 0 ; //change later
    final String empID;
    private String empName;
    private double grossSalary;
    private int birthYear;
    private int vacationDays;//change later


    public Employee(String empID, String empName, int birthYear, double grossSalary,int vacationDays){
        this.empID = empID;
        this.empName = empName;
        this.birthYear = birthYear;
        this.grossSalary = grossSalary;
        this.vacationDays = vacationDays;

    }

    public void createEmployee(String empID, String empName, int birthYear, double grossSalary, int vacationDays){
        Employee emp1 = new Employee(empID, empName, birthYear, grossSalary,vacationDays);
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

    public void promoteEmployee(String emID){
        for(Employee employee : Bank.employees){
            if(employee.getEmpID().equals(emID)){
                employee.getGrossSalary();// Example on how to find specific attribute, also need to give it more access

            }
        }
    }

    //This needs work
    public void sendMessageToCustomer(String message, Customer customer){
        //customer.inbox.add(message)
    }




    public String getCustomerInfo(String userName, int personalNumer){
        String infoCustomer = "";
        for(Customer customer : Bank.customers){
            if(customer.getUserName().equals(userName) && customer.getPersonalNumber() == personalNumer) {
                infoCustomer =  customer.getTransaction() + "Loan: "+ customer.getLoan();

            }
        }
        return infoCustomer;
    }






    public void takeDaysOff(int amountOfDays){
        this.vacationDays -= amountOfDays;
    }

    public String getEmpID() {
        return empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void updateEmpName(String empName) {
        this.empName = empName;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public void updateGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public double calculateNetSalary(){
        return grossSalary * TAX;
    }

    public int getVacationDays() {
        return vacationDays;
    }

    public void updateVacationDays(int vacationDays) {
        this.vacationDays = vacationDays;
    }

    public String toString(){
        return empName + "'s gross salary is " + grossSalary + " SEK per month." ;
    }

    public void deactivateAccount(Customer customer){
        customer.setActive(false);
    }



}
