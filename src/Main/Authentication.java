package Main;

import Classes.User;
import Utilities.UserInput;
import Utilities.Utilities;

import java.util.ArrayList;

public class Authentication {
    private ArrayList<User> users;

    public Authentication(){
        this.users = new ArrayList<>();
    }
    // see if we should devide the user and person or they can be the same.
    public void registerUser(String fullName, String personalNo, String password, double salary){
        String option;
        // this text should be move to the menu and only leave the functionalities
        do{
            try{
                System.out.println(" Registering a new user: ");
                String personalNo = UserInput.readLine(" Type your personal number ( YYYYMMDD-XXXX): ");
                String password = UserInput.readLine("Create a password: " + Utilities.EOL
                "The password must have a minimum of 8 characters in length" + Utilities.EOL+
                "and contain at least  contain: lowercase letter, uppercase letter, digit.");

                if(alreadyExistUser(personalNo)){
                    throw new Exception("This personal number " + personalNo + " has already been registered.");
                }
                User user = new User(fullName,personalNo,password,salary);
            }
        }
    }
    private boolean alreadyExistUser( String inputUsername){
        boolean repeated = false;
        for(User currentUser: users){
            if(currentUser.isSamePersonNo(inputUsername)){
                repeated = true;
            }
        }
        return false;
    }
}
