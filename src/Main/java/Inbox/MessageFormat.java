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
}
