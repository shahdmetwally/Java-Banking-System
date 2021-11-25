package Classes;

public abstract class Person {
    private String fullName;
    private int personalNo;
    private String ID;
    private String password;
    private double salary;


    public Person(String fullName, int personalNo, String ID, String password, double salary ){
        this.fullName = fullName;
        this.personalNo = personalNo;
        this.ID = ID;
        this.password = password;
        this.salary = salary;
    }
    public double getSalary() {return salary;}

    public int getPersonalNo() {return personalNo;}

    public void setSalary(double salary) {this.salary = salary;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getID() {return ID;}

    public void setID(String ID) {this.ID = ID;}

    public String getFullName() {return fullName;}

    public void setFullName(String fullName) {this.fullName = fullName;}



}
