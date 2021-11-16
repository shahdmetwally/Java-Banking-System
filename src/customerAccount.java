import java.util.ArrayList;
import java.util.Scanner;
public class customerAccount {
        Scanner scanner = new Scanner(System.in);
        private String name;
        private int birthYear;
        String customerID;
        String userName;
        String userPassword;
        double currentBalance;
        public ArrayList<customerAccount> accounts = new ArrayList<>();

        customerAccount(String name, int birthYear, String customerID, String userName, String userPassword, double currentBalance){
            this.name = name;
            this.birthYear = birthYear;
            this.customerID = customerID;
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

        public String getCustomerID() {
            return customerID;
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
    public boolean transferMoney(){
        boolean canTransfer = false;
            for(customerAccount i : accounts){
                if(i.getCustomerID().equals(customerID)){
                    System.out.println("Enter the amount you want to transfer: ");
                    double amount = scanner.nextDouble();
        if(amount > 0 && amount < currentBalance){
            this.currentBalance = this.currentBalance - amount;
            canTransfer = true;
    } return canTransfer;
}
            } return canTransfer;
        }
        public String applyForNewCard(){
            inbox.add(customerID)
        public String applyForAnAccount(){

            }
        }

}

//+moneyTransfer
//+applyForNewCard
//+createAccount