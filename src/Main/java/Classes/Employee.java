package Classes;

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
    public void setRole() {
        super.setRole(Role.EMPLOYEE);
    }

    public double getSalary() {return salary;}

    public void setSalary(double salary) {this.salary = salary;}


    //Need to look at this
    // this should depend on the salary of the person.
    // should not be here
  /*  public void approveLoan(String personalNumber){
        for(Customer customer : Bank.customers){
            if(customer.getPersonalNo()==personalNumber){
                System.out.println("Mortgage approved.");
            }
        }
    }

   */
// should not be here / done
   /* public void removeEmployee(String emID){
        for(int i = 0; i < Bank.employees.size(); i++){
            if(Bank.employees.get(i).equals(emID)){
                Bank.employees.remove(i);
            }
        }
    }

    */
// should not be here / done
  /*  public void removeCustomerAccount(int personalNumber){
        for(int i = 0; i < Bank.customers.size(); i++){
         //   if(Bank.customers.get(i).getPersonalNo().personalNumber){
           //     Bank.customers.remove(i);
            }
        }
    }

   */


// should not be here / done
  /*  public String getCustomerInfo(String userName, String personalNumer){
        String infoCustomer = "";
        for(Customer customer : Bank.customers){
            if(customer.getFullName().equals(userName)&& (customer.getPersonalNo()== personalNumer)) {
                infoCustomer =  customer.getBankAccount().getTransaction() + "Loan: "+ customer.getBankAccount().getLoan();

            }
        }
        return infoCustomer;
    }

   */


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


