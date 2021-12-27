package Bank;

public class Loan {
    private double loanAmount;
    private TypesOfLoan type;
    private double interestRate;
    private String loanID;

    public Loan(String personalNr, double loanAmount, TypesOfLoan type, double interestRate){
        this.loanAmount = loanAmount;
        this.type = type;
        this.interestRate = interestRate;
        this.loanID = "L"+personalNr;

    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
