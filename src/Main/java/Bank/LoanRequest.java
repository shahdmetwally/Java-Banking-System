package Bank;

import Utilities.Utilities;
import java.util.HashMap;
import java.util.Map;

public class LoanRequest {

    private final String id;
    private final double amount;
    private final TypesOfLoan typesOfLoan;
    private final double loanPeriod;
    private HashMap<String,Double> equities;
    private  double cashContribution;
    private String coSigner_name;
    private String coSigner_personalNr;
    private double coSigner_salary;
    private String dateAndTime;

    public LoanRequest(String personalNr, double amount, TypesOfLoan typesOfLoan, double loanPeriod, HashMap<String,Double> hashMap , double cashContribution , String coSigner_name, String coSigner_personalNr, double coSigner_salary) {
        this.id = "LR"+ personalNr;
        this.amount = amount;
        this.typesOfLoan = typesOfLoan;
        this.loanPeriod = loanPeriod;
        this.equities = hashMap;
        this.cashContribution = cashContribution;
        this.coSigner_name = coSigner_name;
        this.coSigner_personalNr = coSigner_personalNr;
        this.coSigner_salary = coSigner_salary;
        this.dateAndTime = Utilities.dateAndTime();
    }

    public LoanRequest(String personalNr, double amount, TypesOfLoan typesOfLoan, double loanPeriod, HashMap<String,Double> hashMap, double cashContribution){

    this.id = "LR"+ personalNr;
    this.amount = amount;
    this.typesOfLoan = typesOfLoan;
    this.loanPeriod = loanPeriod;
    this.equities = hashMap;
    this.cashContribution = cashContribution;
    this.coSigner_name = "";
    this.coSigner_personalNr = "";
    this.coSigner_salary = 0;
    this.dateAndTime = Utilities.dateAndTime();
    }

    public String toString() {
      return "ID: "+  id + " Date:" + dateAndTime;
    }

    public String printRequest(){
        return "Application details: " + Utilities.EOL +
                "-----------------------"+ Utilities.EOL +
                "Loan application ID: " + id + Utilities.EOL +
                "Send: " + dateAndTime + Utilities.EOL +
                "Type of loan: " + typesOfLoan+ Utilities.EOL +
                "Loan amount: " + amount + Utilities.EOL +
                "Loan time: " + loanPeriod + Utilities.EOL +
                "OtherEquity: "+Utilities.EOL + printEquities() +
                "Cash contribution: " + cashContribution + Utilities.EOL +
                "Co-Signer name: " + coSigner_name + Utilities.EOL +
                "Co-Signer personal number: " + coSigner_personalNr + Utilities.EOL +
                "Co-Signer salary: " + coSigner_salary;
    }

    public String printEquities(){
        StringBuilder print = new StringBuilder();
        for (Map.Entry<String, Double> entry : equities.entrySet()) {
               print.append(entry.getKey()).append(" : ").append(entry.getValue()).append(Utilities.EOL);
        }
        return print.toString();
    }


    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public TypesOfLoan getTypesOfLoan() {
        return typesOfLoan;
    }

    public double getLoanPeriod() {
        return loanPeriod;
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
