package Classes;

public class Manager extends Employee{



    public Manager(String emName, String emID, String password, int birthYear, double grossSalary, int vacationDays){
        super(emName, emID, password, birthYear, grossSalary, vacationDays);

    }

    public void promoteEmployee(String emID, double newSalary){
        for(Employee employee : Bank.employees){
            if(employee.getID().equals(emID)){

                String name= employee.getFullName();
                String ID = employee.getID();
                String password = employee.getPassword();
                int birthYear= employee.getBirthYear();
                int vaccationDays = employee.getVacationDays();
                Employee emp1 = new Manager(name,ID,password,birthYear,newSalary,vaccationDays);
                Bank.employees.remove(employee);
                Bank.employees.add(emp1);// Example on how to find specific attribute, also need to give it more access

            }
        }
    }

}
