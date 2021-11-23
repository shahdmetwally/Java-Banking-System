package Main;

public class Controller {




    //Employee

    public void createEmploye(){}

    public void createManager(){}

    public void takeDaysof(int amountDays) { }

    public void approveMortageRequest(String customerID){}

    public void removeEmployee(String emID){}

    public void promoteEmployee(String emID){}

    public void removeCustomerAccount(String customerID){}

    public void sendMessageToCustomer(String customerID){} //another argument

    public String getCustomerInfo(String customerID){
        return "";
    }

    public void updateEmployeName(String emID, String newName){}

    public void updateEmployeSalary(String emID, double newGrossSalary){}


    //Customer

    public void createBankAccount(String name, int birthYear, String customerID, String userName, String userPassword){}

    public double checkBalance(){
        return 0;
    }

    public void depositMoney(double amount){}

    public void withdrawMoney(double amount){}

    public void transferMoney(double amount){}

    public void updateName(String newName){}

    public void updateSalary(double salary){}

    public void updatePassword(String password){}

    public void updateUserName(String newUserName){}

    public String transactionHistory(){
        return "";
    }

    public void applyForLoan(double amountToLoan){}

    public void textEmployee(String textMessage){}

    public void controlExpenses(){}

    public void applyForMortagages(double amountToLoan) //This needs to be disscused

    public void applyForCard(){}

    public void deleteAccount(){}




}
