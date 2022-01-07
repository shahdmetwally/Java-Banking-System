package Request;

import Classes.Customer;
import Loans.TypeOfInterest;
import Loans.TypesOfLoan;
import Classes.User;
import Utilities.Utilities;
import java.util.HashMap;
import java.util.Map;

public class LoanRequest extends Request{

    private  String id;
    private  double amount;
    private  TypesOfLoan typesOfLoan;
    private  double loanPeriod;
    private HashMap<String,Double> equities; // We are not allowing two equities with the same name.
    private  double cashContribution;
    private String coSigner_name;
    private String coSigner_personalNr;
    private double coSigner_salary;
    private double houseWorth;
    private TypeOfInterest interestType;

    public LoanRequest(){}


   public LoanRequest(Customer customer, double loanAmount, TypesOfLoan typesOfLoan, double houseWorth, TypeOfInterest interestType, double loanPeriod, HashMap<String,Double> hashMap, double cashContribution, String coSigner_name, String coSigner_personalNr, double coSigner_salary){
        super(customer);
        this.id = "LR"+ customer.getPersonalNo();
        this.typesOfLoan = typesOfLoan;
        if(typesOfLoan == TypesOfLoan.HOUSE_LOAN){
            this.houseWorth = houseWorth;
        }else{
            this.houseWorth = 0;
        }
        this.interestType = interestType;
        this.amount = loanAmount;
        this.loanPeriod = loanPeriod;
        this.equities = hashMap;
        this.cashContribution = cashContribution;
        this.coSigner_name = coSigner_name.trim();
        this.coSigner_personalNr = coSigner_personalNr;
        this.coSigner_salary = coSigner_salary;
    }

    public LoanRequest(Customer customer, double loanAmount, TypesOfLoan typesOfLoan, double houseWorth, TypeOfInterest interestType,
                       double loanPeriod, HashMap<String,Double> hashMap, double cashContribution){
        super(customer);
        this.id = "LR"+ customer.getPersonalNo();
        this.amount = loanAmount;
        this.typesOfLoan = typesOfLoan;
        if(typesOfLoan == TypesOfLoan.HOUSE_LOAN){
            this.houseWorth = houseWorth;
        }else{
            this.houseWorth = 0;
        }
        this.interestType = interestType;
        this.loanPeriod = loanPeriod;
        this.equities = hashMap;
        this.cashContribution = cashContribution;
        this.coSigner_name = "";
        this.coSigner_personalNr = "";
        this.coSigner_salary = 0;
    }
@Override
    public String toString() {
      return "ID: "+  id + " Date:" + getDate();
    }


    public String printRequest(){
      String tittle =  "Application details: " + Utilities.EOL +
                "-----------------------"+ Utilities.EOL +
                "Date: " + getDate() + Utilities.EOL +
                " Customer name: " + getName() + Utilities.EOL+
                "Loan application ID: " + id + Utilities.EOL +
                "Type of loan: " + typesOfLoan+ Utilities.EOL ;

               String message2  =  "Loan amount: " + Utilities.truncateForPrint(amount) + Utilities.EOL +
                "Loan time: " + loanPeriod + Utilities.EOL +
                "OtherEquity: "+Utilities.EOL + printEquities() +
                "Cash contribution: " + Utilities.truncateForPrint(cashContribution) + Utilities.EOL +
                "Co-Signer name: " + coSigner_name + Utilities.EOL +
                "Co-Signer personal number: " + coSigner_personalNr + Utilities.EOL +
                "Co-Signer salary: " + Utilities.truncateForPrint(coSigner_salary);

      if(typesOfLoan == TypesOfLoan.HOUSE_LOAN) {
          String houseValue = "House value:  " + this.houseWorth + Utilities.EOL;
          return tittle + houseValue + message2;
      }else{
           return tittle + message2;
          }

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
    public void setTypesOfLoan(TypesOfLoan typesOfLoan){
        this.typesOfLoan = typesOfLoan;
    }

    public double getLoanPeriod() {
        return loanPeriod;
    }
    public void setLoanPeriod(int loanPeriod){this.loanPeriod = loanPeriod;}

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

    public double getHouseWorth() {
        return houseWorth;
    }

    public HashMap<String,Double> getEquities(){
        return equities;
    }

    public TypeOfInterest getInterestType() {
        return interestType;
    }
    public void setInterestType(TypeOfInterest interestType) {
        this.interestType = interestType;
    }
}
