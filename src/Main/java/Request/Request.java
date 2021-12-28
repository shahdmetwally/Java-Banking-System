package Request;

import Classes.User;
import Utilities.Utilities;

public class Request {

    private final String personalNr;
    private final String date;
    private final String name;


    public Request(User user){
        this.personalNr =user.getPersonalNo();
        this.name = user.getFullName();
        this.date = Utilities.dateAndTime();
    }

    public String getPersonalNr() {
        return personalNr;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

}
