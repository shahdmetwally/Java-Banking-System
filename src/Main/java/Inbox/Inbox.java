package Inbox;

import MainController.StartProgram;
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
    protected ArrayList<MessageFormat> sentMessages;

    public Inbox() {

        this.messageHistory = new ArrayList<MessageFormat>();
        this.unreadMessageInbox = new ArrayList<MessageFormat>();
        this.sentMessages = new ArrayList<MessageFormat>();
    }

    public void addMessageToEmployee(MessageFormat messageFormat) {
        sentMessages.add(messageFormat);
    }
    public void sendMessageToEmployee(MessageFormat message) {
        sentMessages.add(message);
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


    //GETTERS

    public ArrayList<MessageFormat> getUnreadMessageInbox() {
        ArrayList<MessageFormat> clone = unreadMessageInbox;
        return clone;
    }
    public ArrayList<MessageFormat> getMessageHistory() {
        ArrayList<MessageFormat> clone = messageHistory;
        return clone;
    }
    public ArrayList<MessageFormat> getSentMessages() {
        ArrayList<MessageFormat> clone = sentMessages;
        return clone;
    }


    //ADD METHODS

    public void addMessageToUnreadMessages(MessageFormat message){
        getUnreadMessageInbox().add(message);
        StartProgram.jsonEmployeeUnreadMessages.add(message);
    }

    public void addMessageToMessageHistory(MessageFormat message){
        getMessageHistory().add(message);
        StartProgram.jsonEmployeeMessageHistory.add(message);
    }

    public void addMessageToSentMessages(MessageFormat message){
        getSentMessages().add(message);
    }

    //REMOVE METHODS

    public String removeFromUnreadMessages(int index){
        getUnreadMessageInbox().remove(index);
        StartProgram.jsonEmployeeUnreadMessages.remove(index);
        return "The message has been removed.";
    }

    public String removeFromMessageHistory(int index){
        getMessageHistory().remove(index);
        return "The message has been removed.";
    }
}

