package Loans;

import Classes.Customer;
import Utilities.Utilities;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Loan {

    private String loanID;
    private String name;
    private String personalNr;
    private double loanAmount;
    private TypesOfLoan typesOfLoan;
    private double loanPeriod;
    private HashMap<String,Double> equities;
    private double cashContribution;
    private String coSigner_name;
    private String coSigner_personalNr;
    private double coSigner_salary;
    private double interestRate;
    private TypeOfInterest interestType;
    private String date;
    private double houseWorth;


    public Loan(){}


    public Loan(Customer customer, double loanAmount, TypesOfLoan typesOfLoan, double houseWorth, double interestRate, TypeOfInterest interestType,
                double loanPeriod, HashMap<String,Double> hashMap, double cashContribution,
                String coSigner_name, String coSigner_personalNr, double coSigner_salary) throws Exception {

        this.loanID = "L"+ customer.getPersonalNo();
        this.personalNr = customer.getPersonalNo();
        this.name = customer.getFullName().trim();
        this.typesOfLoan = typesOfLoan;
        if(typesOfLoan == TypesOfLoan.HOUSE_LOAN){
            this.houseWorth = houseWorth;
        }else{
            this.houseWorth = 0;
        }
        this.date = Utilities.date();
        this.interestRate = interestRate;
        this.interestType = interestType;
        this.loanAmount = loanAmount;
        this.loanPeriod = loanPeriod;
        this.equities = hashMap;
        this.cashContribution = cashContribution;
        if(coSigner_name.isBlank()){
            throw new Exception("The name cannot be blank");
        }
        this.coSigner_name = coSigner_name.trim();
        if(coSigner_personalNr.length()!=12) {
            throw new Exception("Personal number must be in this format: YYYYMMDDXXXX");
        }
        this.coSigner_personalNr = coSigner_personalNr;
        if(coSigner_salary < 0){
            throw new Exception("The salary cannot be less than zero");
        }
        this.coSigner_salary = coSigner_salary;

    }
    public Loan(Customer customer, double loanAmount, TypesOfLoan typesOfLoan, double houseWorth, double interestRate, TypeOfInterest interestType,
                double loanPeriod, HashMap<String,Double> hashMap, double cashContribution){

        this.loanID = "L"+ customer.getPersonalNo();
        this.personalNr = customer.getPersonalNo();
        this.name = customer.getFullName();
        this.typesOfLoan = typesOfLoan;
        if(typesOfLoan == TypesOfLoan.HOUSE_LOAN){
            this.houseWorth = houseWorth;
        }else{
            this.houseWorth = 0;
        }
        this.date = Utilities.date();
        this.interestRate = interestRate;
        this.interestType = interestType;
        this.loanAmount = loanAmount;
        this.loanPeriod = loanPeriod;
        this.equities = hashMap;
        this.cashContribution = cashContribution;
        this.coSigner_name = "";
        this.coSigner_personalNr = "";
        this.coSigner_salary = 0;
    }


    public String toString() {
        return "ID: "+  this.loanID + " Date:" + this.date;
    }

    public String printRequest() {
        String tittle =  "Application details: " + Utilities.EOL +
                "-----------------------"+ Utilities.EOL +
                "Date: " + date + Utilities.EOL +
                " Customer name: " + name + Utilities.EOL+
                "Loan application ID: " + loanID+ Utilities.EOL +
                "Type of loan: " + typesOfLoan+ Utilities.EOL ;

        String message2  =  "Loan amount: " + loanAmount + Utilities.EOL +
                "Loan time: " + loanPeriod + Utilities.EOL +
                "OtherEquity: "+Utilities.EOL + printEquities() +
                "Cash contribution: " + cashContribution + Utilities.EOL +
                "Co-Signer name: " + coSigner_name + Utilities.EOL +
                "Co-Signer personal number: " + coSigner_personalNr + Utilities.EOL +
                "Co-Signer salary: " + coSigner_salary;

        if(typesOfLoan == TypesOfLoan.HOUSE_LOAN) {
            String houseValue = "House value:  " + this.houseWorth + Utilities.EOL;
            return tittle + houseValue + message2;
        }else{
            return tittle + message2;
        }
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public String getPersonalNr() {
        return personalNr;
    }

    public String printEquities(){
        StringBuilder print = new StringBuilder();
        for (Map.Entry<String, Double> entry : equities.entrySet()) {
            print.append(entry.getKey()).append(" : ").append(entry.getValue()).append(Utilities.EOL);
        }
        return print.toString();
    }

    @JsonIgnore
    public String getId() {
        return loanID;
    }

    public void setLoanID(String loanID) {
        this.loanID = loanID;
    }

    public String getLoanID() {
        return loanID;
    }

    @JsonIgnore
    public double getAmount() {
        return loanAmount;
    }

    public TypesOfLoan getTypesOfLoan() {
        return typesOfLoan;
    }

    public double getLoanPeriod() {
        return loanPeriod;
    }

    @JsonIgnore
    public TypeOfInterest getTypeOfInterest(){
        return interestType;
    }
    public double getInterestRate() {
           return interestRate;
    }



    public double getCashContribution() {
        return cashContribution;
    }

    public String getCoSigner_name() {
        return coSigner_name;
    }

    public void setCoSigner_name(String coSigner_name) throws Exception {

        this.coSigner_name = coSigner_name;
    }


    public String getCoSigner_personalNr() {
        return coSigner_personalNr;
    }
    public void setCoSigner_personalNr(String personalNr)  {
        coSigner_personalNr = personalNr;
    }

    public void setInterestRate(double interestRate) {
        if(interestType == TypeOfInterest.VARIABLE_RATE){
                this.interestRate = interestRate;
        }
    }

    public void setInterestType(TypeOfInterest interestType) {
        this.interestType = interestType;
    }

    public double getCoSigner_salary() {
        return coSigner_salary;
    }

    public void setCoSigner_salary(double coSigner_salary) {
        this.coSigner_salary = coSigner_salary;
    }

    @JsonIgnore
    public double getMortgagePercentage(){
        double loanSizePercentage = loanAmount / houseWorth;
        if(loanSizePercentage > 0.7){
            return 0.2;
        }else if(0.5 < loanSizePercentage|| loanSizePercentage < 0.7){
            return 0.1;
        }else{
            return 0;
        }
    }





}
