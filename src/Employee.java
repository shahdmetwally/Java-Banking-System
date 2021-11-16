package src;

import java.util.ArrayList;

public class Employee {
    private String empID;
    private String empName;
    private int birthYear;
    private double grossSalary;
    final double TAX = 0; //change later
    private int vacationDays;
    private ArrayList<String> inbox;


    public Employee(String empID, String empName, int birthYear, double grossSalary, int vacationDays){
        this.empID = empID;
        this.empName = empName;
        this.birthYear = birthYear;
        this.grossSalary = grossSalary;
        inbox = new ArrayList<>();
    }

    public void approveMortageRequest(){

    }

    public void takeDaysOff(int amountOfDays){
        vacationDays = vacationDays - amountOfDays;
    }

    public void sendMessageToCustomer(String message, Customer customer){
        //customer.inbox.add(message);
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
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

    @Override
    public String toString() {
        return "";
    }
}
