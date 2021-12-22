package Classes;

public class Manager extends Employee{

    private double bonus;

    public Manager(){}

    public Manager(String name, long personalNo, String password, double salary, double bonus) throws Exception{
        super(name, personalNo, password, salary);
        this.bonus = bonus;
    }

    public double getBonus(){
       return this.bonus;
    }
    public void setBonus(double newBonus){this.bonus = newBonus;
    }
    public String toString(){
        return "Manger: " + getFullName() + "personal number " + getPersonalNo();
    }





// shouldnt be here
    /*

   public void promoteEmployee(String personNo, double newSalary) throws Exception{
        for(User currentEmployee : Bank.users){
            if(currentEmployee.getPersonalNo().equals(personNo)){

                String name= currentEmployee.getFullName();
                String personalNr = currentEmployee.getPersonalNo();
                String password = currentEmployee.getPassword();
                int vaccationDays = currentEmployee.getVacationDays();
                Employee newEmployee = new Manager(name,personalNr,password,newSalary);
                Bank.users.remove(currentEmployee);
                Bank.users.add(newEmployee);// Example on how to find specific attribute, also need to give it more access
            }
        }
    }

     */
//Shouldn be here.
 /*   public String getTotalBalance(){
        double message = 0;
        String message1 = "Banks total balance: ";
        for (Customer customer : Bank.users){
            message += customer.getBankAccount().getBalance();
        }
        return message1 + message ;
    }

  */

  /*  public String getAllTransactions(){
        String message = "";
        for (Customer customer : ){                                 //We comented out in the customer class
           // message = "Customer: " + customer.getFullName() + customer.getBankAccount().transactionHistory(customer);
        }
        return message;
    }

   */

 /*   public String getTotalLoan(){
        String message= "Total amount of loan giving out: ";
        double totalLoan = 0;
        for(Customer customer:Bank.customers){
            totalLoan+= customer.getBankAccount().getLoan();
        }
        return message +totalLoan;
    }

  */


}


