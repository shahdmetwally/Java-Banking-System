package src;

public class Employee {
    final String empID;
    private String empName;
    private double grossSalary;
    final double TAX = 0; //change later
    final int vacationDays = 0; //change later

    public Employee(String empID, String empName, int birthYear, double grossSalary){
        this.empID = empID;
        this.empName = empName;
        this.birthYear = birthYear;
        this.grossSalary = grossSalary;

    }

    public void takeDaysOff(int amountOfDays){
        vacationDays = vacationDays - amountOfDays;
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
        return empName + "'s gross salary is " + grossSalary " SEK per month." ;
    }
}
