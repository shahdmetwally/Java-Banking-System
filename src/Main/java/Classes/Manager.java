package Classes;

public class Manager extends Employee{

    private double bonus;

    public Manager(){}

    public Manager(String name, String personalNo, String password, double salary, double bonus) throws Exception{
        super(name, personalNo, password, salary);
        this.bonus = bonus;
    }

    @Override
    public Role getRole() {
        return super.getRole();
    }
    
    public void setRole() {
        super.setRole(Role.MANAGER);
    }


    public double getBonus(){
       return this.bonus;
    }
    public void setBonus(double newBonus){this.bonus = newBonus;
    }
    public String toString(){
        return "Manager: " + getFullName() + ".Personal number: " + getPersonalNo();
    }

}


