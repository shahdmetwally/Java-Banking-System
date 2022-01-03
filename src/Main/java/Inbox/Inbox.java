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
    protected ArrayList<MessageFormat> unreadMessageInbox;
    protected Queue<MessageFormat> sentMessages;

    public Inbox() {

        this.messageHistory = new ArrayList<MessageFormat>();
        this.unreadMessageInbox = new ArrayList<MessageFormat>();
        this.sentMessages = new LinkedList<MessageFormat>();
    }

    public void addMessageToEmployee(MessageFormat messageFormat) {
        sentMessages.add(messageFormat);
    }

    public Queue<MessageFormat> getSentMessages() {
        return sentMessages;
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

    public String removeMessage(int index) {
        messageHistory.add(getUnreadMessageInbox().get(index));
        unreadMessageInbox.remove(index);
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

    public ArrayList<MessageFormat> getUnreadMessageInbox() {
        ArrayList<MessageFormat> clone = unreadMessageInbox;
        return clone;
    }

    public void addMessageToUnreadMessages(MessageFormat message){
        getUnreadMessageInbox().add(message);
    }

    public void addMessageToMessageHistory(MessageFormat message){
        getMessageHistory().add(message);
    }


    public ArrayList<MessageFormat> getMessageHistory() {
        ArrayList<MessageFormat> clone = messageHistory;
        return clone;
    }




}

