package Classes;
import java.util.ArrayList;

public class Employee {

    final double TAX = 0 ; //change later
    final String empID;
    private String empName;
    private double grossSalary;
    private int birthYear;
    private int vacationDays; //change later
    ArrayList<Transaction> transactions;

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

    public void approveMortagageRequest(int personalNumber){
        for(Customer customer : Bank.customers){
            if(customer.getPersonalNumber()==personalNumber){
                System.out.println("Mortgage approved.");
            }
        }
    }

    public void removeEmployee(String emID){
        for(Employee employee : Bank.employees){
            if(employee.empID.equals(emID)){
                Bank.employees.remove(employee);
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
                employee.getGrossSalary();// Example on how to find specefic attribut

            }
        }
    }

    //This needs work
    public void sendMessageToCustomer(String message, Customer customer){

    }
        //customer.inbox.add(message)



    //this needs work, but its a structure
    public String getCustomerInfo(String customerID){
        String infoCustomer = "";
        for(Customer customer : Bank.customers){
            if(customer.equals(customerID)){
                infoCustomer =  customer.getFullName() + customer.getBalance();

            }
        }
        return infoCustomer; // add loan and more customer info
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

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public double calculateNetSalary(){
        return grossSalary * TAX;
    }

    public int getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(int vacationDays) {
        this.vacationDays = vacationDays;
    }

    public String toString(){
        return empName + "'s gross salary is " + grossSalary + " SEK per month." ;
    }

}
