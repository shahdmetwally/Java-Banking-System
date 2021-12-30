package Inbox;

import Request.VacationRequest;

import java.util.LinkedList;
import java.util.Queue;

public class ManagerInbox extends EmployeeInbox {
    Queue<VacationRequest> vacationApplications;

    public ManagerInbox(){
        super();
        this.vacationApplications= new LinkedList<VacationRequest>();
    }
    public void addVacationApplication(VacationRequest vacationRequest){
        vacationApplications.add(vacationRequest);
    }
    public void seeVacationApplications(){
        System.out.println(vacationApplications);
    }
    public String approveVacationApplication(){
        vacationApplications.poll();
       return "The vacation application has been approved.";
    }



}
