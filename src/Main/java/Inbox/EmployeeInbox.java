package Inbox;

import Classes.Customer;
import Request.CardRequest;
import Request.LoanRequest;
import Utilities.Utilities;

import java.util.ArrayList;


public class EmployeeInbox extends Inbox {

    private ArrayList<LoanRequest> loanRequests; // we store the send loanRequest? Every Customer should only be alow to have one request
    private ArrayList<CardRequest> cardRequests;

    public EmployeeInbox() {
        super();
        this.cardRequests = new ArrayList<>();
        this.loanRequests = new ArrayList<>();
    }

    //GETTERS
    @Override
    public ArrayList<MessageFormat> getUnreadMessageInbox() {
        return super.getUnreadMessageInbox();
    }

    @Override
    public ArrayList<MessageFormat> getMessageHistory() {
        return super.getMessageHistory();
    }

    @Override
    public ArrayList<MessageFormat> getSentMessages() {
        return super.getSentMessages();
    }

    public ArrayList<LoanRequest> getLoanRequests() {
        return loanRequests;
    }

    public ArrayList<CardRequest> getCardRequests() {
        return cardRequests;
    }

    //ADD METHODS

    @Override
    public void addMessageToUnreadMessages(MessageFormat message) {
        super.addMessageToUnreadMessages(message);
    }
    @Override
    public void addMessageToMessageHistory(MessageFormat message) {
        super.addMessageToMessageHistory(message);
    }
    @Override
    public void addMessageToSentMessages(MessageFormat message) {
        super.addMessageToSentMessages(message);
    }

    //REMOVE METHODS

    @Override
    public String removeFromUnreadMessages(int index) {
        return super.removeFromUnreadMessages(index);
    }

    @Override
    public String removeFromMessageHistory(int index) {
        return super.removeFromMessageHistory(index);
    }

    public void addCardRequest(CardRequest request) {
        cardRequests.add(request);
    }

    public void removeCardRequest(CardRequest cardRequest) {
        getCardRequests().remove(cardRequest);
    }

    public void addLoanRequest(LoanRequest loanRequest) {
        loanRequests.add(loanRequest);
    }

    public void removeLoanRequest(LoanRequest loanRequest) {
        getLoanRequests().remove(loanRequest);
    }

    public String getAllLoanRequests() {
        String message = "";
        if (!this.loanRequests.isEmpty()) {
            for (LoanRequest loanRequest : this.loanRequests) {
                message += loanRequest.toString() + Utilities.EOL;
            }
        } else {
            message = "No loan requests have been made.";
        }
        return message;
    }

    public String getAllCardRequests() {
        String message = "";
        if (!this.cardRequests.isEmpty()) {
            for (CardRequest cardRequest : this.cardRequests) {
                message += cardRequest.toString() + Utilities.EOL;
            }
        } else {
            message = "No card requests have been made.";
        }

        return message;
    }

    public void sendMessageToCustomers(MessageFormat messageFormat) {
        super.sentMessages.add(messageFormat);
    }

    public void sendMessageToSpecificCustomer(Customer customer, MessageFormat textMessage) {
        customer.getInbox().addMessageToUnreadMessages(textMessage);
    }

    public void addSentMessage(MessageFormat textMessage) {
        addToSentMessage(textMessage);
    }

    @Override
    public String toString() {
        return
                "Loan Requests: " + Utilities.EOL + getAllLoanRequests() +
                        Utilities.EOL + Utilities.EOL +
                        "Card Requests: " + Utilities.EOL + getAllCardRequests();

    }
}

