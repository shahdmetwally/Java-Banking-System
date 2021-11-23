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
        return "<date> - " + amount;
    }


}
