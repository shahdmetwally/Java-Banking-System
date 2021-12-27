package Inbox;

import java.util.LinkedList;
import java.util.Queue;

public class EmployeeInbox extends Inbox{
    Queue<String> messagesToCustomers;

    public EmployeeInbox(){
        super();
        this.messagesToCustomers = new LinkedList<String>();
    }
    public void addMessageToCustomer(String message){
        messagesToCustomers.add(message);
    }

}
