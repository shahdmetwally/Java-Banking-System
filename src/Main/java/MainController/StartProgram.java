package MainController;


import Bank.Bank;
import Classes.*;
import Menu.MainMenu;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class StartProgram {

    public static HashMap<String, ArrayList> jsonBank = new HashMap<>();
    public static ArrayList<Customer> jsonCustomers = new ArrayList<>();
    public static ArrayList<Employee> jsonEmployees = new ArrayList<>();
    public static ArrayList<Manager> jsonManagers = new ArrayList<>();


    public static void main(String[] args) throws Exception {

        Bank bank = new Bank();



        jsonBank.put("employees", jsonEmployees);
        jsonBank.put("customers", jsonCustomers);
        jsonBank.put("managers", jsonManagers);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File("users.json"));
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

        bank.showAllUser();

      String option;
        do{

          try {
              System.out.println("Banking System");
              MainMenu bankMenu = new MainMenu(bank);
              bankMenu.handleMainMenu();
              mapper.writeValue(Paths.get("users.json").toFile(), jsonBank);
          }catch (Exception exception){
              System.out.println(exception.getMessage());
          }
          option = "Do you want to continue?";
      }while (option.equalsIgnoreCase("yes"));

    }

}
