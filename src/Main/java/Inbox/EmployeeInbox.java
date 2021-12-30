package Inbox;

import Request.CardRequest;
import Request.LoanRequest;
import Utilities.Utilities;

import java.util.LinkedList;
import java.util.Queue;

public class EmployeeInbox extends Inbox{

    private Queue<LoanRequest> loanRequests; // we store the send loanRequest? Every Customer should only be alow to have one request
    private Queue<CardRequest> cardRequests;

    public EmployeeInbox(){
        super();
        this.cardRequests = new LinkedList<CardRequest>();
        this.loanRequests = new LinkedList<LoanRequest>();

    }
    public void addMessageToCustomer(MessageFormat message){
        sentMessages.add(message);
    }
    public void removeMessagesToCustomer(MessageFormat message){
        sentMessages.remove(message);
    }
    public void addCardRequest(CardRequest request){
        cardRequests.add(request);
    }
    public void removeCardRequest(CardRequest cardRequest){
        cardRequests.remove(cardRequest);
    }
    public void addLoanRequest(LoanRequest loanRequest){
        loanRequests.add(loanRequest);
    }
    public void removeLoanRequest(LoanRequest loanRequest){
        loanRequests.remove(loanRequest);
    }

    public String getAllLoanRequests() {
        String message = "";
        for( LoanRequest  loanRequest: this.loanRequests){
            message += loanRequest.toString() + Utilities.EOL;
        }
        return message;
    }
    public void sendMessageToCustomers(MessageFormat messageFormat){
        super.sentMessages.add(messageFormat);
    }

    public void addSentMessage (MessageFormat textMessage){
        addToSentMessage(textMessage);
    }


/*
customer

- queue inbox  ( unread message)
- messge history
- sended messages

- 3 methods for add
- 3 methods for remove



employee
queue for resquest loanRequest
queue for request cardsRequest
// already there because of hirarchy
queue  inbox message ( unreaded message)
- message history -
- sended messages

- Manager
- queue for vacation request
- queue  inbox message ( unreaded message)
- message history -
- sended messages



 */





}
