package Inbox;

import Bank.LoanRequest;
import Bank.TypesOfLoan;
import Utilities.Utilities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class Inbox {
    Queue<LoanRequest> loanRequests;
    ArrayList<MessageFormat> messageHistory; // maybe ArrayList instead.
    Queue<MessageFormat> messageInbox;
    //Customer -> send message -> employee
    Queue<MessageFormat> messagesToEmployees; // send messages?

    /*
            controller:
            method is send message to employee:

            - add in messagesToEmployee // the customer can see the message that have been send.
            - add it to employeeInbox.  // the employee can see the new message.

     */

    public Inbox(){
        this.loanRequests = new LinkedList<LoanRequest>();
        this.messageHistory = new ArrayList<>();
        this.messageInbox  = new LinkedList<MessageFormat>();
        this.messagesToEmployees = new LinkedList<MessageFormat>();
    }

    //Methods For Employees----------------------------------------
    public void addLoanRequest(LoanRequest loanRequest){
        loanRequests.add(loanRequest);
    }
    public void removeLoanRequest(){

    }

    public void addToMessageHistory(MessageFormat message){
        messageHistory.add(message);
    }

    public void sendMessageToEmployee(MessageFormat message){
        messagesToEmployees.add(message);
    }

    public void addMessageInbox(MessageFormat message){
        messageInbox.add(message);
    }
// remove 4 methods
    public String removeMessage() {
        messageHistory.add(messageInbox.poll());
        return "The message has been removed.";
    }

    // get 4 methods


    public String getMessageInbox() {
        String message = "";
        for( MessageFormat  inbox: this.messageInbox){
            message += inbox + Utilities.EOL;
        }
        return message;
    }

    public String getLoanRequests() {
       String message = "";
       for( LoanRequest  loanRequest: this.loanRequests){
           message += loanRequest.toString() + Utilities.EOL;
       }
       return message;
    }

    public String sendMessagesToEmployees() {
        String message = "";
        return "";
    }

    public ArrayList<String> getMessageHistory(){
       ArrayList<String> clone = new ArrayList<>();
        return clone;
    }

    public static void main(String[] args) {

        Inbox i = new Inbox();

        LoanRequest a = new LoanRequest("199001041848",5000000, TypesOfLoan.CAR_LOAN,1,null,50000);
        LoanRequest b = new LoanRequest("199001041849",5000000, TypesOfLoan.CAR_LOAN,1,null,50000);
        i.addLoanRequest(a);
        i.addLoanRequest(b);

        System.out.print(i.getLoanRequests());
        System.out.println(i.getMessageInbox());
    }

}
