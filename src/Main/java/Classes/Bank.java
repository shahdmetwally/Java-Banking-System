package Classes;

import java.util.ArrayList;

public class Bank {

    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<String> loan = new ArrayList<>();
    public static ArrayList<BankAccount> bankAccounts= new ArrayList<>();


    //-------- not know about the onces below get-------------------------
    public static ArrayList<String> newCardRequests = new ArrayList<>();
    //public static ArrayList<String> loan = new ArrayList<>();
    public static ArrayList<String> mortgageRequests = new ArrayList<>();
    public static ArrayList<String> accountRequests = new ArrayList<>();
    public static ArrayList<String> inbox = new ArrayList<>();


    public ArrayList<User> getUsers(){
        ArrayList<User> usersClone = users;
        return usersClone;
    }
    public void addUser(User user){
        users.add(user);
    }

    public void removeUser(Employee employee){
        users.remove(employee);
    }


}
