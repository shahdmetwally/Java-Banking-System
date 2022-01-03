package MainController;


import Bank.Bank;
import Classes.*;
import Inbox.EmployeeInbox;
import Inbox.ManagerInbox;
import Inbox.Inbox;
import Inbox.MessageFormat;
import Loans.Loan;
import Menu.MainMenu;
import Request.CardRequest;
import Request.LoanRequest;
import Request.VacationRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class StartProgram {

    public static HashMap jsonBank = new HashMap<>();
    public static ArrayList<Customer> jsonCustomers = new ArrayList<>();
    public static ArrayList<Employee> jsonEmployees = new ArrayList<>();
    public static ArrayList<Manager> jsonManagers = new ArrayList<>();
    public static ArrayList<User> jsonAdmin = new ArrayList<>();
    public static ArrayList<LoanRequest> jsonLoanRequests = new ArrayList<>();
    public static ArrayList<Loan> jsonLoans = new ArrayList<>();
    public static ArrayList<CardRequest> jsonCardRequests = new ArrayList<>();

    public static HashMap jsonEmployeeInbox = new HashMap<>();
    public static ArrayList<MessageFormat> jsonEmployeeUnreadMessages= new ArrayList<MessageFormat>();


    public static ArrayList<Inbox> jsonCustomerInbox = new ArrayList<>();
    public static HashMap jsonManagerInbox = new HashMap<>();

    public static Queue<VacationRequest> jsonVacationApplication = new LinkedList<>();

    public static void main(String[] args) throws Exception {

        Bank bank = new Bank();

        jsonBank.put("employees", jsonEmployees);
        jsonBank.put("customers", jsonCustomers);
        jsonBank.put("managers", jsonManagers);
        jsonBank.put("admin", jsonAdmin);
        jsonBank.put("Loan requests", jsonLoanRequests);
        jsonBank.put("Loans", jsonLoans);
        jsonBank.put("Card requests", jsonCardRequests);
        jsonBank.put("Employee Inbox", jsonEmployeeInbox);
        jsonBank.put("Manager Inbox", jsonManagerInbox);

        jsonManagerInbox.put("Vacation applications", jsonVacationApplication);
        jsonEmployeeInbox.put("Unread messages", jsonEmployeeUnreadMessages);






        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File("bank.json"));

        JsonNode employeeNode = root.path("employees");
        for (int i = 0; i < employeeNode.size(); i++) {
            Employee employee = mapper.treeToValue(employeeNode.get(i), Employee.class);
            jsonEmployees.add(employee);
            bank.addUser(employee);
        }

        JsonNode customerNode = root.path("customers");
        for (int i = 0; i < customerNode.size(); i++) {
            Customer customer = mapper.treeToValue(customerNode.get(i), Customer.class);
            jsonCustomers.add(customer);
            bank.addUser(customer);
            
        }

        JsonNode managerNode = root.path("managers");
        for (int i = 0; i < managerNode.size(); i++) {
           Manager manager = mapper.treeToValue(managerNode.get(i), Manager.class);
            jsonManagers.add(manager);
            bank.addUser(manager);
        }

        JsonNode adminNode = root.path("admin");
        User admin = mapper.treeToValue(adminNode.get(0), User.class);
        jsonAdmin.add(admin);
        bank.addUser(admin);


        JsonNode loanRequestNode = root.path("Loan requests");
        for (int i = 0; i < loanRequestNode.size(); i++) {
            LoanRequest loanRequest = mapper.treeToValue(loanRequestNode.get(i), LoanRequest.class);
            jsonLoanRequests.add(loanRequest);
            bank.addLoanRequest(loanRequest);
            bank.addLoanApplication(loanRequest.getPersonalNr(), loanRequest);
        }

        JsonNode loansNode = root.path("Loans");
        for (int i = 0; i < loansNode.size(); i++) {
            Loan loan = mapper.treeToValue(loansNode.get(i), Loan.class);
            jsonLoans.add(loan);
            bank.addLoan(loan.getPersonalNr(),loan);
        }

        JsonNode cardRequestsNode = root.path("Card requests");
        for (int i = 0; i < cardRequestsNode.size(); i++) {
            CardRequest cardRequest = mapper.treeToValue(cardRequestsNode.get(i), CardRequest.class);
            jsonCardRequests.add(cardRequest);
            bank.addCardRequest(cardRequest.getPersonalNr(), cardRequest);
            System.out.println(cardRequest.getPersonalNr());
        }

        ManagerInbox managerInbox = bank.getManagerInbox();
        JsonNode managerInboxNode = root.path("Manager Inbox");
        JsonNode vacationApplicationNode = managerInboxNode.path("Vacation applications");
        for (int i = 0; i<vacationApplicationNode.size(); i++) {
            VacationRequest vacationRequest = mapper.treeToValue(vacationApplicationNode.get(i), VacationRequest.class);

            jsonVacationApplication.add(vacationRequest);
            bank.getVacationRequest().put(vacationRequest.getPersonalNr(), vacationRequest);
            managerInbox.addVacationApplication(vacationRequest);


        }

        EmployeeInbox employeeInbox = bank.getEmployeeInbox();
        JsonNode employeeInboxNode = root.path("Employee Inbox");
        JsonNode unreadMessagesFromCustomers = employeeInboxNode.path("Unread messages");
        for(int i = 0; i<unreadMessagesFromCustomers.size(); i++){
            MessageFormat unreadMessage = mapper.treeToValue(unreadMessagesFromCustomers.get(i), MessageFormat.class);
            System.out.println(unreadMessage);
            jsonEmployeeUnreadMessages.add(unreadMessage);
            employeeInbox.addMessageToEmployee(unreadMessage);
        }


        bank.showAllUser();
        System.out.println(bank.getLoans());
        System.out.println(bank.getLoanRequests());
        System.out.println(bank.getEmployeeInbox().toString());
        System.out.println(bank.getLoanRequests());
        System.out.println(bank.getCardRequests());
        System.out.println(bank.getVacationRequest());
        System.out.println(managerInbox.getVacationApplications());


      String option;
        do{

          try {
              System.out.println("Banking System");
              MainMenu bankMenu = new MainMenu(bank);
              bankMenu.handleMainMenu();
              System.out.println(jsonBank);
              mapper.writeValue(Paths.get("bank.json").toFile(), jsonBank);
          }catch (Exception exception){
              System.out.println(exception.getMessage());
          }
          option = "Do you want to continue?";
      }while (option.equalsIgnoreCase("yes"));

    }

}
