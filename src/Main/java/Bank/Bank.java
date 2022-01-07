package Bank;

import Classes.BankAccount;
import Classes.Customer;
import Classes.Transaction;
import Classes.User;
import Inbox.EmployeeInbox;
import Inbox.ManagerInbox;
import Inbox.MessageFormat;
import Loans.Loan;
import MainController.StartProgram;
import Request.CardRequest;
import Request.LoanRequest;
import Request.VacationRequest;
import Utilities.Utilities;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    private  double variableInterestRate;
    private  HashMap<String, User> users; // key is the person Nr
    private  HashMap<String, User> bankAccounts; // do we really need this if is stored in the user?
    private  HashMap<String, Loan>  loans;
    private HashMap<String,Transaction> transactionsTotheBank;


    private  HashMap<String, LoanRequest> loanRequests;
    private  HashMap<String, CardRequest> cardRequests;
    private HashMap<String, VacationRequest> vacationRequest;

    private EmployeeInbox employeeInbox;
    private ManagerInbox managerInbox;

    private BankAccount bankAccount;

    public Bank(){
        this.users = new HashMap<>();
        this.bankAccounts = new HashMap<>();
        this.loans = new HashMap<>();
        this.loanRequests = new HashMap<>();
        this.cardRequests = new HashMap<>();
        this.employeeInbox = new EmployeeInbox();
        this.managerInbox = new ManagerInbox();
        this.vacationRequest = new HashMap<>();
        this.variableInterestRate = 0;


    }
    // this method allows the bank to crete a bank account, to be able to create that we need to create a fake customer for the bank.
    // this is something that can be could have been improved better, but because we could increment the scope to insert
    // create a Bg account and connected to the payments.
    public void setUpTheBanksAccount(){
       try {
           String bankNr = "5051-505051511";
           BankAccount accountForBank = new BankAccount();
           this.bankAccount =accountForBank;
           Customer bankCust = new Customer("Bank", "190505059999", 0, "Bank12345", bankNr);
           addBankAccount(bankNr,bankCust );
       }catch(Exception exception) {
           System.out.println("banks own account");
       }
    }
    public void showAllUser(){
        users.forEach((personNo, user) -> System.out.println(user.getFullName() + " : " + personNo  + ": " + user.getPassword()));
    }
    public BankAccount getBanksAccount(){
        return this.bankAccount;
    }

    public double getTotalCustomerBalance(){
        double balance = 0;
        for (Map.Entry<String, User> user : users.entrySet()) {
            if(user.getValue() instanceof Customer){
                balance += (((Customer) user.getValue()).getBankAccount().getBalance());
            }

        }
        return balance;
    }


    public void showAllLoanRequests(){
        loanRequests.forEach((personNo, loan)-> System.out.println(loan.toString()));
    }
    // we don't allow the original HashMap in the getter to improve the encapsulation of the list.
    public HashMap<String, Loan > getLoans(){
        HashMap<String,Loan> loanClone = loans;
        return loanClone;
    }
    // if the interest type is "Variable" this set all the loan that have type variable to the new interest rate
    public void setAllVariableInterest(double interestRate){
        for (Map.Entry<String, Loan> entry : loans.entrySet()) {
            entry.getValue().setInterestRate(interestRate);
        }
    }
    // we don't allow the original HashMap in the getter to improve the encapsulation of the list.
    public HashMap<String, LoanRequest> getLoanRequests(){
        HashMap<String, LoanRequest> loanApplicationClone = loanRequests;
        return loanApplicationClone;
    }
    // we don't allow the original HashMap in the getter to improve the encapsulation of the list.
    public HashMap<String, User> getUsers() {
        HashMap<String,User> usersClone = users;
        return usersClone;
    }
// because we are not allowing acces to the original HashMap we have created a method that allows the system
// to change still add users to the hashMap. This is for all methods below.
    public void addUser(User user) {
        this.users.put(user.getPersonalNo(), user);
        if(user instanceof Customer){
            this.bankAccounts.put(((Customer) user).getAccountNo(), user);
        }

    }
    public void addLoan(String personalNr,Loan loan ){
       String key = "L" + personalNr;
        this.loans.put(key,loan);
    }
    public void addLoanApplication(String personalNr, LoanRequest loanApplication){
        String key = "LR" + personalNr;
        this.loanRequests.put(key,loanApplication);
    }
    public void addCardRequest(String personalNr,CardRequest inputCardRequest){
        String key = "C" + personalNr;
        cardRequests.put(key,inputCardRequest);
        employeeInbox.addCardRequest(inputCardRequest);
    }

    // The same reason
    public void removeCardRequest(String personalNr, CardRequest inputCardRequest){
        String key = "C" + personalNr;
        this.cardRequests.remove(key,inputCardRequest);
        employeeInbox.removeCardRequest(inputCardRequest);
    }
    public void removeLoan(Loan loan, String personalNr ){
        String key = "L" + personalNr;
        this.loans.remove(key,loan);
    }
    public void removeLoanRequest(String personalNr, LoanRequest loanApplication){
        String key = "LR" + personalNr;
        this.loanRequests.remove(key,loanApplication);
    }

    public LoanRequest getSpecificLoanRequest(String loanRequestID){
        return loanRequests.get(loanRequestID);

    }
    public Loan getSpecificLoan(String loanID){
        return loans.get(loanID);
    }

    public void removeUser(User user) {
        this.users.remove(user.getPersonalNo(), user);
    }

    public HashMap<String, User> getBankAccounts() {
        return bankAccounts;
    }

    public void addBankAccount(String bankAccount, Customer customer) {
        this.bankAccounts.put(customer.getAccountNo(), customer);
    }
// do we need this? the account is connected to the user but should it be a possibility to delete a bank account ?
    public void removeBankAccount(Customer customer) {
        this.bankAccounts.remove(customer.getAccountNo(), customer);
    }

    public EmployeeInbox getEmployeeInbox() {
        return employeeInbox;
    }

    public ManagerInbox getManagerInbox() {
        return managerInbox;
    }

    public void addSentMessage(MessageFormat message) {
        employeeInbox.addToSentMessage(message);
    }

    public void addLoanRequest(LoanRequest loanRequest){
        employeeInbox.addLoanRequest(loanRequest);

    }
    public void removeLoanRequest(LoanRequest loanRequest){
        employeeInbox.removeLoanRequest(loanRequest);
    }

    public HashMap<String, CardRequest> getCardRequests() {
        HashMap<String, CardRequest> clone = cardRequests;
        return clone;
    }

    public double getVariableInterestRate() {
        return variableInterestRate;
    }

    public void setVariableInterestRate(double variableInterestRate) {
        this.variableInterestRate = variableInterestRate;
    }
    public void addVacationRequest(String personalNr, VacationRequest vacationRequest){
        String key = "V" + personalNr;
        this.vacationRequest.put(personalNr,vacationRequest);
    }
    public void removeVacationRequest(String personalNr, VacationRequest vacationRequest){
        String key = "V" + personalNr;
        this.vacationRequest.remove(personalNr,vacationRequest);
    }

    public HashMap<String, VacationRequest> getVacationRequest() {
        HashMap<String, VacationRequest> clone = vacationRequest;
        return clone;
    }

    public void setVacationRequest(HashMap<String, VacationRequest> vacationRequest) {
        this.vacationRequest = vacationRequest;
    }

    public String viewVacationApps(){
        String message = "Vacation Applications: " + Utilities.EOL;

        return message + managerInbox.getVacationApplications() ;
    }

    public String approveVacationApps(String vacationRequestID){
        String message = "Approved vacation application";
        managerInbox.approveVacationApplication();
        vacationRequest.remove(vacationRequestID);
        return message;
    }


}
