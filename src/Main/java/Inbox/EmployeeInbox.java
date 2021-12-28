package Inbox;

import Request.CardRequest;

import java.util.LinkedList;
import java.util.Queue;

public class EmployeeInbox extends Inbox{
    public static Queue<String> messagesToCustomers;
    private final Queue<CardRequest> cardRequests;

    public EmployeeInbox(){
        super();
        this.messagesToCustomers = new LinkedList<String>();
        this.cardRequests = new LinkedList<CardRequest>();

    }
    public void addMessageToCustomer(String message){
        messagesToCustomers.add(message);
    }
    public void removeMessagesToCustomer(MessageFormat message){
        messagesToCustomers.remove(message);
    }
    public void addCardRequest(CardRequest request){
        cardRequests.add(request);
    }
    public void removeCardRequest(CardRequest cardRequest){
        cardRequests.remove(cardRequest);
    }







}
