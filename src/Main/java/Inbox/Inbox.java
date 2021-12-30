package Inbox;

import Request.LoanRequest;
import Utilities.Utilities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// Note: the queue should be an indicator of unread messages;
// So if the message has been read maybe we should take it out of the queue and store it in the messageHistory;

public class Inbox {

    protected ArrayList<MessageFormat> messageHistory;
    protected Queue<MessageFormat> unreadMessageInbox;
    protected Queue<MessageFormat> sentMessages;

    public Inbox() {

        this.messageHistory = new ArrayList<MessageFormat>();
        this.unreadMessageInbox = new LinkedList<MessageFormat>();
        this.sentMessages = new LinkedList<MessageFormat>();
    }

    //Methods For Employees----------------------------------------

    public void addMessageToEmployee(MessageFormat messageFormat) {
        sentMessages.add(messageFormat);
    }

    public void addToMessageHistory(MessageFormat message) {
        messageHistory.add(message);
    }

    public void sendMessageToEmployee(MessageFormat message) {
        sentMessages.add(message);
    }

    public void addMessageInbox(MessageFormat message) {
        unreadMessageInbox.add(message);
    }

    public String removeMessage() {
        messageHistory.add(unreadMessageInbox.poll());
        return "The message has been removed.";
    }

    public void addToSentMessage(MessageFormat messageFormat) {
        sentMessages.add(messageFormat);
    }

    public String getAllMessageInbox() {
        String message = "";
        for (MessageFormat inbox : this.unreadMessageInbox) {
            message += inbox.toString() + Utilities.EOL;
        }
        return message;
    }


    public String sendMessagesToEmployees() {
        String message = "";
        return "";
    }



    // do we want to return the hole array? are we using it? or we just want to print all? in that case change this method.
    public ArrayList<MessageFormat> getMessageHistory() {
        ArrayList<MessageFormat> clone = new ArrayList<>();
        return clone;
    }


}





}
