package Classes;

import java.util.ArrayList;
import java.util.HashMap;

public class Bank {

    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<String> loan = new ArrayList<>();
    public static ArrayList<BankAccount> bankAccounts= new ArrayList<>();

    private HashMap<Integer, User> user = new HashMap<Integer,User>();

    //-------- not know about the onces below get-------------------------
    public static ArrayList<String> newCardRequests = new ArrayList<>();
    //public static ArrayList<String> loan = new ArrayList<>();
    public static ArrayList<String> mortgageRequests = new ArrayList<>();
    public static ArrayList<String> accountRequests = new ArrayList<>();
    public static ArrayList<String> inbox = new ArrayList<>();


   /* public ArrayList<User> getUsers(){
        ArrayList<User> usersClone = users;
        return usersClone;
    }*/

    public ArrayList<User> getAllUsers() {
        return (ArrayList<User>) user.values();
    }

    // public void addUser(User user){
     //   users.add(user);
   // }

    public void addUser (User user){
        this.user.put(user.getPersonalNo(),user);
    }

    //public void removeUser(Employee employee){
       // users.remove(employee);
  //  }

    public void removeUser(Employee employee){
        this.user.remove(employee.getPersonalNo(), employee);
    }

}
