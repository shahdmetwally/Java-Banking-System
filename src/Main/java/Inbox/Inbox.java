package Inbox;

import Request.CardRequest;
import Request.LoanRequest;
import Utilities.Utilities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// Note: the queue should be an indicator of unread messages;
// So if the message has been read maybe we should take it out of the queue and store it in the messageHistory;

public class Inbox {
    Queue<LoanRequest> loanRequests; // we store the send loanRequest? Every Customer should only be alow to have one request
    Queue<CardRequest> cardRequests;
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
        this.cardRequests = new LinkedList<>();
    }

    //Methods For Employees----------------------------------------
    public void addLoanRequest(LoanRequest loanRequest){
        loanRequests.add(loanRequest);
    }
    public void removeLoanRequest(LoanRequest loanRequest){
        loanRequests.remove(loanRequest);
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


    public String getAllMessageInbox() {
        String message = "";
        for( MessageFormat  inbox: this.messageInbox){
            message += inbox + Utilities.EOL;
        }
        return message;
    }

    public String getAllLoanRequests() {
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
// do we want to return the hole array? are we using it? or we just want to print all? in that case change this method.
    public String getMessageHistory(){
       ArrayList<String> clone = new ArrayList<>();
        return clone;
    }



}
