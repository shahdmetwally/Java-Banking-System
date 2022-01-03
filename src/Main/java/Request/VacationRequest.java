package Request;

import Classes.Employee;
import Classes.User;
import Utilities.Utilities;

public class VacationRequest extends Request {

    private int days;
    private String id;

    public VacationRequest(){}

    public VacationRequest(User user,int days){
        super(user);
        this.days = days;
        this.id = "V" + getPersonalNr();
    }

    @Override
    public String getPersonalNr() {
        return super.getPersonalNr();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getDate() {
        return super.getDate();
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
   @Override
    public String toString(){
        return "ID: " + id + Utilities.EOL + "Amount of days: " + days + Utilities.EOL + "Request made: " + getDate();
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
