package Classes;

public class Customer {

    private String name;
    private int birthYear;
    String customerID;
    String userName;
    String userPassword;
    double currentBalance;

    public Customer(String name, int birthYear, String customerID, String userName, String userPassword){
        this.name = name;
        this.birthYear = birthYear;
        this.customerID = customerID;
        this.userName = userName;
        this.userPassword = userPassword;
        this.currentBalance = 0;
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

    public double depositMoney(double depositAmount){
        currentBalance =- depositAmount;
        return currentBalance;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }
}
