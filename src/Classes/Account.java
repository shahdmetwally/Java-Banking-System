package Classes;

public class Account {
    private String fullName;
    private String ID;
    private String password;
    private double salary;

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Account(String fullName, String ID, String password, double salary ){
        this.fullName = fullName;
        this.ID = ID;
        this.password = password;
        this.salary = salary;
    }
}
