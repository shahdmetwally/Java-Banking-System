package Classes;

public class Manager extends Employee{



    public Manager(String emName, String emID, String password, int birthYear, double grossSalary){
        super(emName, emID, password, birthYear, grossSalary);

    }

    public void createManager(String emName, String emID, String password, int birthYear, double grossSalary){
        Manager manager= new Manager(emName,emID,password,birthYear,grossSalary);

    }

    public void promoteEmployee(String emID, double newSalary){
        for(Employee employee : Bank.employees){
            if(employee.getID().equals(emID)){

                String name= employee.getFullName();
                String ID = employee.getID();
                String password = employee.getPassword();
                int birthYear= employee.getBirthYear();
                int vaccationDays = employee.getVacationDays();
                Employee emp1 = new Manager(name,ID,password,birthYear,newSalary);
                Bank.employees.remove(employee);
                Bank.employees.add(emp1);// Example on how to find specific attribute, also need to give it more access

            }
        }
    }




    public String getTotalBalance(){
        double message = 0;
        String message1 = "Banks total balance: ";
        for (Customer customer : Bank.customers){
            message += customer.getBalance();
        }
        return message1 + message ;
    }

    public String getAllTransactions(){
        String message = "";
        for (Customer customer : Bank.customers){
            message = "Customer: " + customer.getFullName() + customer.transactionHistory(customer);
        }
        return message;
    }

    public String getTotalLoan(){
        String message= "Total amount of loan giving out: ";
        double totalLoan = 0;
        for(Customer customer:Bank.customers){
            totalLoan+= customer.getLoan();
        }
        return message +totalLoan;
    }


}


