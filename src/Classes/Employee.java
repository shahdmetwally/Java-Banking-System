package Classes;

public class Employee {

    final double TAX = 0 ; //change later
    final String empID;
    private String empName;
    private double grossSalary;
    private int birthYear;
    private int vacationDays; //change later

    public Employee(String empID, String empName, int birthYear, double grossSalary,int vacationDays){
        this.empID = empID;
        this.empName = empName;
        this.birthYear = birthYear;
        this.grossSalary = grossSalary;
        this.vacationDays = 0;

    }

    //this needs work, but its a structure
    public String getCustomerInfo(String customerID){
        String infoCustomer = "";
        for(Customer customer : Bank.customers){
            if(customer.equals(customerID)){
                infoCustomer =  customer.getName() + customer.getCurrentBalance();

            }
        }
        return infoCustomer; // add loan and more
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
