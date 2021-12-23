package Classes;

import Utilities.Utilities;

public class Transaction{

    private double amount;
    // private String date;( insert data)

    public Transaction(){

    }
    public Transaction(double amount){
        this.amount = amount;

    }
    public double getAmount() {
        return amount;
    }

    public String toString(){
        String message="";
        if(amount>0) {
             message = Utilities.dateAndTime() + "  +" +amount;
        } else { message = Utilities.dateAndTime()+ "  " +amount;}
        return message;
    }
    //hello


}
