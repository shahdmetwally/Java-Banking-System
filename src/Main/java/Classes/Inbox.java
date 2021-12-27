package Classes;

import java.util.LinkedList;
import java.util.Queue;

public class Inbox {
    Queue<String> loanApplications;
    Queue<String> oldMessages;
    Queue<String> vacationApplications;
    Queue<String> messageInbox;

    public Inbox(){
        this.loanApplications = new LinkedList<String>();
        this.oldMessages = new LinkedList<String>();
        this.vacationApplications= new LinkedList<String>();
        this.messageInbox  = new LinkedList<String>();
    }


    //Methods For Employees----------------------------------------
    public void addLoanApplicationMessage(String message){
        loanApplications.add(message);
    }
    public void addOldMessages(String message){
        oldMessages.add(message);
    }
    public void addVacationApllication(String message){
        vacationApplications.add(message);
    }
    public void setMessageInbox(String message){
        messageInbox.add(message);
    }

    public String removeMessage() {
        oldMessages.add(messageInbox.poll());
        return "The message has been removed.";
    }

    public String approveLoanApplication(){
        loanApplications.poll();
        return "The loan application has been approved."; // and if deny?
    }

    public void seeMessageInbox(){
        System.out.println(messageInbox);
    }

    public void seeLoanApplications(){
        System.out.println(loanApplications);
    }

    public void seeMessageHistory(){
        System.out.println(oldMessages);
    }

    //Methods For Managers-------------------------------

    public void seeVacationApplications(){
        System.out.println(vacationApplications);
    }

    public String approveVacationApplication(){
        vacationApplications.poll();
        return "The vacation application has been approved.";

    }


}
