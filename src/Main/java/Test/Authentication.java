package Test;

import Classes.Bank;
import Classes.Employee;
import Classes.User;

import java.util.ArrayList;

public class Authentication {

    private ArrayList<User> users;

    public Authentication(){
        users = new ArrayList<>();
    }

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
