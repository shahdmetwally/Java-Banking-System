package Classes;



import java.io.IOException;

public class User {
    // if abstract we need to devide the log in to part
    private String fullName;
    private String personalNo; // look up francisco example with personnumber // changed from string to int
    private String password;

    public User(){

    }
    public User(String fullName,String personalNo, String password) throws Exception{
        if(fullName.isBlank()) {
            throw new Exception("Name cannot be blank.");
        }
        if(personalNo.length()!=12) {
            throw new Exception("Personal number be in this format: YYYYMMDDXXXX");
        }
        if(!isPersonNrCorrect(personalNo)){
            throw new Exception("The age must be more than 18 and less than 120, please input valid month and day");
        }
        if(!isStrongPassword(password)) {
            throw new Exception("The password is weak. The password must have a minimum of 8 characters in length" +
                    " and contain: lowercase letter, uppercase letter, digit.");
        }
        this.fullName = fullName.trim();
        this.personalNo = personalNo;

        this.password = password;
    }


    public String getPersonalNo() {
        return this.personalNo;
    }

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getFullName() {return fullName;}

    public void setName(String newName) throws Exception {
        if(newName.isBlank()) {
            throw new Exception("Name cannot be blank.");
        }
        this.fullName = newName.trim();
    }

    public boolean isSamePersonNo(String inputPersonalNo){
        if(this.personalNo.equals(inputPersonalNo)){
            return true;
        }else{
            return false;
        }
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

    public static boolean isPersonNrCorrect(String personalNo){
        String yearStr = personalNo.substring(0,5);
        int year= Integer.parseInt(yearStr);
        String monthStr = personalNo.substring(5,7);
        int month = Integer.parseInt(monthStr);
        String dayStr= personalNo.substring(7,9);
        int day = Integer.parseInt(dayStr);

        if(year>1900 && year<2003) {
            return false;
        }
        if(month> 12 || month < 1){
            return false;
        }
        if(day>31 || day< 1){
            return false;
        }
        return true;
    }

    public String toString(){
        return "Username: " + personalNo + " password: " + password ;
    }

}
