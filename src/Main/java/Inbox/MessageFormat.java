package Inbox;

import Utilities.Utilities;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class MessageFormat {

    private String message;
    private String date;
    private String title;

    public MessageFormat(){}

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

    @JsonIgnore
    public boolean isEmpty() {
        boolean a = true;
        boolean b = false;
        if (title.isEmpty() || message.isEmpty()){
            return a;
        }
        else return b;
    }
}
