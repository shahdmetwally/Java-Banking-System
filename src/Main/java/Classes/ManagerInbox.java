package Inbox;

import java.util.LinkedList;
import java.util.Queue;

public class ManagerInbox extends EmployeeInbox {
    Queue<String> vacationApplications;

    public ManagerInbox(){
        super();
        this.vacationApplications= new LinkedList<String>();
    }
    public void addVacationApllication(String message){
        vacationApplications.add(message);
    }
    public void seeVacationApplications(){
        System.out.println(vacationApplications);
    }
    public String approveVacationApplication(){
        vacationApplications.poll();
        return "The vacation application has been approved.";

    }

}
