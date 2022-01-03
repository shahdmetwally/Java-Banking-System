package Inbox;

import Utilities.Utilities;

public class MessageFormat {

    private final String message;
    private final String date;
    private final String title;

    public MessageFormat(String title, String message){
        this.title = title;
        this.message = message;
        this.date = Utilities.dateAndTime();
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String toString() {
        return "Title: " + title + " Date: " + date;
    }

    public boolean isEmpty() {
        boolean a = true;
        boolean b = false;
        if (title.isEmpty() || message.isEmpty()){
            return a;
        }
        else return b;
    }
}
