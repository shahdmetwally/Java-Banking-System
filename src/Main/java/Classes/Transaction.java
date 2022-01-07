package Classes;

import Utilities.Utilities;

public class Transaction{

    private double amount;
    private String loanID;
    private String date;

    public Transaction(){

    }
    public Transaction(double amount){
        this.amount = amount;
        this.date = Utilities.dateAndTime();

    }
    public Transaction(double amount,String loanID){
        this.amount = amount;
        this.loanID = loanID;
    }
    public double getAmount() {
        return amount;
    }

    public String toString(){
        String message="";
        if(amount>0) {
             message = Utilities.dateAndTime() + "  +" +amount;
        } else {
            message = Utilities.dateAndTime()+ "  " +amount;
        }
        return message;
    }



}
