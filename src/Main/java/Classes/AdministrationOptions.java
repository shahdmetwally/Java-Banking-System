package Classes;

public class AdministrationOptions extends MenuOptions {
    private String password;
   // private String userName;

    public AdministrationOptions(){
        super();
       // this.userName = "Admin";
        this.password = "Admin000";
    }
    public static boolean isStrongPassword(String password){
        boolean hasDigits = password.matches(".*\\d+.*");
        boolean hasUpperCase = password.matches(".*[A-Z]+.*");
        boolean hasLowerCase = password.matches(".*[a-z]+.*");
        boolean islong = password.length() > 8;
        return hasDigits && hasLowerCase && hasUpperCase && islong;
    }
    public boolean isPasswordCorrect(String triedPassword){
        return this.password.equals(triedPassword);
    }

    public void setPassword(String newPassword) throws Exception{
        if(!isStrongPassword(newPassword)){
            throw new Exception("The password is weak. The password must have a minimum of 8 characters in length" +
                    " and contain at least  contain: lowercase letter, uppercase letter, digit.");
        }
        this.password = newPassword;
    }


}
