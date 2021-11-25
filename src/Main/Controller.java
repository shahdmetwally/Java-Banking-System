package Main;

import Classes.Bank;
import Classes.Customer;

import Classes.Employee;

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








































































    //Customer. Should I add all the set and get also for customer?

    public void createBankAccount(String fullName, int personalNumber, String userName, String password, double monthlyGrossSalary, double balance){
        Customer customer = new Customer(fullName, personalNumber, userName,password,monthlyGrossSalary,balance);
    }
    //public void createBankAccount(String name, int birthYear, String customerID, String userName, String userPassword){}

    public double checkBalance() { // 2.0  Check Balance
        return 0;
    }
    //public double checkBalance(){return 0;}

    public double depositMoney(String ID, double amount) throws Exception {
        double balance = 0;
        for(Customer customer: Bank.customers) {
            if (customer.getID().equals(ID)) {
                if (customer.getActive()) {
                    if (amount > 0) {
                        balance+= amount;
                        customer.updateBalance(balance);
                        customer.addTransaction(amount);
                    } else {
                        throw new Exception("You cannot add an amount with a negative value. ");
                    }
                } else {
                    System.out.println("Your account is deactivated.");
                }
            }
        }return balance ;

        }


    public void withdrawMoney(double amount) {
    }

    public void transferMoney(double amount) {
    }

    public void updateName(String newName){
    }

    public void updateSalary(double salary){}

    public void updatePassword(String password){}

    public void updateUserName(String newUserName){}

    public String transactionHistory(){
        return "";
    }

    public void applyForLoan(double amountToLoan){}

    public void textEmployee(String textMessage){}

    public void controlExpenses(){}


    //Need to look thorugh this again, I will work more on this as more methods are created.



 //ysahuduasid


}
