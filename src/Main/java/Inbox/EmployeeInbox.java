package Inbox;

import Request.CardRequest;

import java.util.LinkedList;
import java.util.Queue;

public class EmployeeInbox extends Inbox{
    public static Queue<MessageFormat> messagesToCustomers;
    private final Queue<CardRequest> cardRequests;

    public EmployeeInbox(){
        super();
        this.messagesToCustomers = new LinkedList<MessageFormat>();
        this.cardRequests = new LinkedList<CardRequest>();

    }
    public void addMessageToCustomer(MessageFormat message){
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
