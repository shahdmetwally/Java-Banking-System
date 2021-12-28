package Classes;

import Loans.TypesOfLoan;

public class Employee extends User {

    final double TAX = 0.33; //change later /
    private int vacationDays;
    private double salary;

    public Employee(){

    }

    public Employee(String empName, String personalNo, String password, double salary) throws Exception {
        super(empName, personalNo, password, Role.EMPLOYEE);
        this.vacationDays = 25;

        if(salary < 0){
            throw new Exception("Salary cannot be less than 0.");
        }
        this.salary = salary;
    }

    public double getSalary() {return salary;}

    public void setSalary(double salary) {this.salary = salary;}

    public void setVacationDays(int vacationDays) {
        this.vacationDays = vacationDays;
    }

    public void takeDaysOff(int amountOfDays){
        this.vacationDays -= amountOfDays;
    }

    public double calculateNetSalary(){
        return salary * TAX;
    }

    public int getVacationDays() {
        return vacationDays;
    }

    public void updateVacationDays(int vacationDays) {
        this.vacationDays = vacationDays;
    }

    public String toString(){
        return super.getFullName() + "'s gross salary is " + salary + " SEK per month." ;
    }




}


