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
//+createAccount