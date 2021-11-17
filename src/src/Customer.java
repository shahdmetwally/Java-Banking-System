package src;

import java.util.ArrayList;
import java.util.Scanner;
public class customerAccount {
    Scanner scanner = new Scanner(System.in);
    private String name;
    private int birthYear;
    String userID;
    String userName;
    String userPassword;
    double currentBalance;
    //public ArrayList<customerAccount> accounts = new ArrayList<>(); in bank class

    customerAccount(String name, int birthYear, String userID, String userName, String userPassword, double currentBalance){
        this.name = name;
        this.birthYear = birthYear;
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
        this.currentBalance = currentBalance;
    }

    public String getName() {
        return name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "customerAccount{" +
                "scanner=" + scanner +
                ", name='" + name + '\'' +
                ", birthYear=" + birthYear +
                ", userID='" + userID + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", currentBalance=" + currentBalance +
                '}';
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.currentBalance = this.currentBalance + amount;
        }
    }
    public boolean withdraw(double amount){
        boolean canWithdraw = false;
        if(amount > 0 && amount < currentBalance){
            this.currentBalance = this.currentBalance - amount;
            canWithdraw = true;
        }
        return canWithdraw;
    }
    public boolean transferMoney(customerAccount anotherCustomerAccount){
        boolean canTransfer = false;
        for(customerAccount i : accounts){
            if(i.getUserID().equals(userID)){
                System.out.println("Enter the amount you want to transfer: ");
                double amount = scanner.nextDouble();
                if(amount > 0 && amount < currentBalance){
                    this.currentBalance = this.currentBalance - amount;
                    canTransfer = true;
                } return canTransfer;
            }
        } return canTransfer;
    }

}


//+applyForNewCard
//+apply for mortages
//+apply
