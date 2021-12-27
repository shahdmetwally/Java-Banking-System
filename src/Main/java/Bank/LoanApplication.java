package Bank;


import Utilities.Utilities;

public class LoanApplication {

    private final String loanApplication_ID;
    private final double amount;
    private final TypesOfLoan typesOfLoan;
    private final double time;
    private String otherEquities;
    private double otherEquitiesValues;
    private  double cashContribution;
    private String coSigner_name;
    private String coSigner_personalNr;
    private double coSigner_salary;

    public LoanApplication(String personalNr, double amount, TypesOfLoan typesOfLoan, double time, String otherEquity,double otherEquitiesValues, double cashContribution , String coSigner_name, String coSigner_personalNr, double coSigner_salary) {
        this.loanApplication_ID = "LA"+ personalNr;
        this.amount = amount;
        this.typesOfLoan = typesOfLoan;
        this.time = time;
        this.otherEquities = otherEquity;
        this.otherEquitiesValues = otherEquitiesValues;
        this.cashContribution = cashContribution;
        this.coSigner_name = coSigner_name;
        this.coSigner_personalNr = coSigner_personalNr;
        this.coSigner_salary = coSigner_salary;
    }

    public LoanApplication(String personalNr, double amount, TypesOfLoan typesOfLoan, double time, String otherEquity,double otherEquitiesValues, double cashContribution){

    this.loanApplication_ID = "LA"+ personalNr;
    this.amount = amount;
    this.typesOfLoan = typesOfLoan;
    this.time = time;
    this.otherEquities = otherEquity;
    this.otherEquitiesValues = otherEquitiesValues;
    this.cashContribution = cashContribution;
    this.coSigner_name = "";
    this.coSigner_personalNr = "";
    this.coSigner_salary = 0;
    }

    public String toString(){
        return "Application details: " + Utilities.EOL +
                "-----------------------"+ Utilities.EOL +
                "Loan application ID: " + loanApplication_ID + Utilities.EOL +
                "Type of loan: " + typesOfLoan+ Utilities.EOL +
                "Loan amount: " + amount + Utilities.EOL +
                "Loan time: " + time + Utilities.EOL +
                "OtherEquity: " + otherEquities + Utilities.EOL +
                "Cash contribution: " + cashContribution + Utilities.EOL +
                "Co-Signer name: " + coSigner_name + Utilities.EOL +
                "Co-Signer personal number: " + coSigner_personalNr + Utilities.EOL +
                "Co-Signer salary: " + coSigner_salary;
    }


    public String getLoanApplication_ID() {
        return loanApplication_ID;
    }

    public double getAmount() {
        return amount;
    }

    public TypesOfLoan getTypesOfLoan() {
        return typesOfLoan;
    }

    public double getTime() {
        return time;
    }

    public String getOtherEquities() {
        return otherEquities;
    }

    public void setOtherEquities(String otherEquities) {
        this.otherEquities = otherEquities;
    }

    public double getCashContribution() {
        return cashContribution;
    }

    public void setCashContribution(double cashContribution) {
        this.cashContribution = cashContribution;
    }


    public String getCoSigner_name() {
        return coSigner_name;
    }

    public void setCoSigner_name(String coSigner_name) {
        this.coSigner_name = coSigner_name;
    }

    public String getCoSigner_personalNr() {
        return coSigner_personalNr;
    }
    public void setCoSigner_personalNr(String personalNr){
        coSigner_personalNr = personalNr;
    }

    public double getCoSigner_salary() {
        return coSigner_salary;
    }

    public void setCoSigner_salary(double coSigner_salary) {
        this.coSigner_salary = coSigner_salary;
    }
}
