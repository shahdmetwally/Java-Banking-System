package Classes;

import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class User {
    // if abstract we need to devide the log in to part
    private String fullName;
    private String personalNo; // look up francisco example with personnumber (
    private String password;

    public User(String fullName,String personalNo, String password) throws Exception{
        if(fullName.isBlank()) {
            throw new Exception("Name cannot be blank.");
        }
        if(personalNo.length() != 12) {
            throw new Exception("Personal number be in this format: YYYYMMDDXXXX");
        }
        if(!isStrongPassword(password)) {
            throw new Exception("The password is weak. The password must have a minimum of 8 characters in length" +
                    " and contain at least  contain: lowercase letter, uppercase letter, digit.");
        }
        this.fullName = fullName;
        this.personalNo = personalNo;
        this.password = password;
    }

    public String getPersonalNo() {
        return this.personalNo;
    }

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getFullName() {return fullName;}

    public void setFullName(String fullName) throws IOException, ParseException {this.fullName = fullName;}


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
        boolean isLong = password.length() > 8;
        return hasDigits && hasLowerCase && hasUpperCase && isLong;
    }

}
