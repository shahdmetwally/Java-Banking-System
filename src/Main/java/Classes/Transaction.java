package Classes;

public class Transaction{

    private double amount;
    // private String date;( insert data)

    public Transaction(double amount){
        this.amount = amount;

    }
    public double getAmount() {
        return amount;
    }

    public String toString(){
        String message="";
        if(amount>0) {
             message = "<date> " + "+" +amount;
        } else { message = "<date>  " +amount;}
        return message;
    }


}
