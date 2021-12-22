package Classes;

import java.util.HashMap;

public class Bank {

    private final HashMap<Integer, User> users = new HashMap<Integer, User>();
    private final HashMap<String, User> bankAccounts = new HashMap<>();

    public HashMap<Integer, User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        this.users.put(user.getPersonalNo(), user);
    }

    public void removeUser(User user) {
        this.users.remove(user.getPersonalNo(), user);
    }

    public HashMap<String, User> getBankAccounts() {
        return bankAccounts;
    }

    public void addBankAccount(String bankAccount, Customer customer) {
        this.bankAccounts.put(customer.getAccountNo(), customer);
    }

    public void removeBankAccount(Customer customer) {
        this.bankAccounts.remove(customer.getAccountNo(), customer);
    }
}

