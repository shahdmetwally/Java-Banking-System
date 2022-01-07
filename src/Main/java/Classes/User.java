package Classes;



import java.io.IOException;

public class User {
    // if abstract we need to devide the log in to part
    private String fullName;
    private String personalNo; // look up francisco example with personnumber // changed from string to int
    private String password;
    private Role role;

    public User(){

    }
    public User(String fullName,String personalNo, String password, Role role) throws Exception{
        if(fullName.isBlank()) {
            throw new Exception("Name cannot be blank.");
        }
        if(personalNo.length()!=12) {
            throw new Exception("Personal number must be in this format: YYYYMMDDXXXX");
        }
        if(!isStrongPassword(password)) {
            throw new Exception("The password is weak. The password must have a minimum of 8 characters in length" +
                    " and contain: lowercase letter, uppercase letter, digit.");
        }
        this.fullName = fullName.trim();
        this.personalNo = personalNo;
        this.password = password;
        this.role = role;

    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public String getPersonalNo() {
        return this.personalNo;
    }

    public String getPassword() {return password;}

    public void setPassword(String password) {
            this.password = password;
    }

    public String getFullName() {return fullName;}

    public void setName(String newName)  {
        this.fullName = newName.trim();
    }

    public boolean isSamePassword(String inputPassword){
        return this.password.equals(inputPassword);
    }

    public static boolean isStrongPassword(String password){
        boolean hasDigits = password.matches(".*\\d+.*");
        boolean hasUpperCase = password.matches(".*[A-Z]+.*");
        boolean hasLowerCase = password.matches(".*[a-z]+.*");
        boolean isLong = password.length() > 7;
        return hasDigits && hasLowerCase && hasUpperCase && isLong;
    }

    public String toString(){
        return "Username: " + personalNo + " password: " + password ;
    }

}
