package Classes;

import java.util.List;

public abstract class User {
    private String fullName;
    private String personalNo; // look up francisco example with personnumber (
    private String password;
    private double salary;



    public User(String fullName, String personalNo, double salary ) throws Exception{
        if(fullName.isBlank()){
            throw new Exception("Name cannot be blank.");
        }
        if(personalNo.length() != 13){
            throw new Exception("Personal number be in this format: YYYYMMDD- XXXX");
        }
        if(!isStrongPassword(password)){
            throw new Exception(" The password is weak. The password must have a minimum of 8 characters in length" +
                    " and contain at least  contain: lowercase letter, uppercase letter, digit.");
        }
        // exception for personal number
        // exception password

        this.fullName = fullName;
        this.personalNo = personalNo;
        this.password = password;
        this.salary = salary;
    }
    public double getSalary() {return salary;}

    public String getPersonalNo() {
        return personalNo;
    }

    public void setSalary(double salary) {this.salary = salary;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getFullName() {return fullName;}

    public void setFullName(String fullName) {this.fullName = fullName;}


    public boolean isSamePersonNo(String inputPersonalNo){
        return this.personalNo.equals(inputPersonalNo);
    }

    public boolean isSamePassword(String inputPassword){
        return this.password.equals(inputPassword);
    }

    public static boolean isStrongPassword(String password){
        boolean hasDigits = password.matches(".*\\d+.*");
        boolean hasUpperCase = password.matches(".*[A-Z]+.*");
        boolean hasLowerCase = password.matches(".*[a-z]+.*");
        boolean islong = password.length() > 8;
        return hasDigits && hasLowerCase && hasUpperCase && islong;
    }

}
