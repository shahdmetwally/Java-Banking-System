package Request;

import Classes.Employee;
import Classes.User;
import Utilities.Utilities;

public class VacationRequest extends Request {

    private int days;
    private String id;

    public VacationRequest(User user,int days){
        super(user);
        this.days = days;
        this.id = "V" + getPersonalNr();
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
   @Override
    public String toString(){
        return "Id: " + id + "Date: " + getDate();
    }

    public String printRequest(){
        return "Vacation request:" + Utilities.EOL+
                "-----------------------"+ Utilities.EOL +
                "Date: " + getDate()+ Utilities.EOL+
                "Customer name: " + getName() + Utilities.EOL +
                "Personal number: " + getPersonalNr()+ Utilities.EOL+
                "Vacation day wanted: " + days;
    }
}
