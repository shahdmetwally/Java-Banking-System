package Inbox;

import Utilities.UserInput;

import java.util.LinkedList;
import java.util.Queue;

public class InboxCodeRaw {
    // INBOX

    //Methods For Customers--------
//QUEUE FOR CUSTOMER MESSAGES-------------------------------------
   public static Queue<String> messagesToCustomers = new LinkedList<String>(); // Inbox of messages the customers receive
   public static Queue<String> messagesToEmployees = new LinkedList<String>(); // Inbox of messages the employees receive

    public void sendMessageToEmployees(){
        String message = UserInput.readLine("Please type the message that you would like to send to the Customer Support: ");
        if (message == null){
            System.out.println("Your message cannot be empty.");
        }else{
            messagesToEmployees.add(message);
            System.out.println("Your message has been sent successfully.");
        }
    }
    public void removeMessageFromEmployee() {
        getOldMessagesFromEmployee.add(messagesToCustomers.poll());
        System.out.print("The message has been removed.");
    }
    public String ViewCustomerMessageInbox(){
      String message = "";
        for( String mes :messagesToCustomers){
            message += String.format(message);
        }
        return  message;
    }
    public void ViewCustomerMessageHistory(){
        System.out.println(getOldMessagesFromEmployee);
    }

    //QUEUE FOR CUSTOMER LOAN APPLICATIONS-------------- "if" statements need to be applied based on customer budget
    Queue<String> loanApplications = new LinkedList<String>();
   /*
    public void sendApplicationForLoan(Customer customer){

        int loanAmount = UserInput.readInt("Please type the amount you would like to borrow from the bank: ");
        // THIS IS WHERE THE CODE FOR THE LOAN MUST BE ADDED
        String message = "Customer with name: " + customer.getFullName() + " and account balance: "
                + customer.getBalance() + " Would like to apply for a loan of amount: " + loanAmount + ".";
        loanApplications.add(message);
        System.out.println("Your loan application has been sent successfully.");
    }

    */


    //QUEUE FOR MESSAGE HISTORY(after a message has been processed by the Customer)
    Queue<String> getOldMessagesFromEmployee = new LinkedList<String>();

    //Methods For Employees----------------------------------------

    //QUEUE FOR MESSAGE HISTORY(after a message has been processed by the Customer Support)
    Queue<String> oldMessagesFromCustomer = new LinkedList<String>();

    //QUEUE FOR VACATION APPLICATIONS
    Queue<String> vacationApplications = new LinkedList<String>();
   /*
    public void applyForVacation(Employee employee){
        String message = "Employee with name: " + employee.getFullName() + " Would like to apply for vacation.";
        vacationApplications.add(message);
        System.out.println("Your vacation application has been sent successfully.");
    }

    */

    public void sendMessageToCustomers(){
        String message = UserInput.readLine("Please type the message that you would like to send to the Customers: ");
        if (message == null){
            System.out.println("Your message cannot be empty.");
        }else{
            messagesToEmployees.add(message);
            System.out.println("Your message has been sent successfully.");
        }
    }
    public void removeMessageFromCustomer() {
        oldMessagesFromCustomer.add(messagesToEmployees.poll());
        System.out.print("The message has been removed.");
    }

    public void approveLoanApplication(){
        loanApplications.poll();
        System.out.println("The loan application has been approved.");
    }

    public void ViewEmployeeMessageInbox(){
        System.out.println(messagesToEmployees);
    }

    public void seeLoanApplications(){
        System.out.println(loanApplications);
    }

    public void ViewEmployeeMessageHistory(){
        System.out.println(oldMessagesFromCustomer);
    }

    //Methods For Managers-------------------------------

    public void seeVacationApplications(){
        System.out.println(vacationApplications);
    }

    public void approveVacationApplication(){
        vacationApplications.poll();
        System.out.println("The vacation application has been approved.");

    }

    public static void main(String[] args) {
        InboxCodeRaw in = new InboxCodeRaw();

    }

}
