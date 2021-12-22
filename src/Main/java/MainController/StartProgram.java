package MainController;


import Classes.*;
import Menu.MainMenu;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class StartProgram {

    public static void main(String[] args) throws Exception {
        HashMap<String, ArrayList> jsonBank = new HashMap<>();
        ArrayList<Customer> jsonCustomers = new ArrayList<>();
        ArrayList<Employee> jsonEmployees = new ArrayList<>();
        ArrayList<Manager> jsonManagers = new ArrayList<>();
        jsonBank.put("employees", jsonEmployees);
        jsonBank.put("customers", jsonCustomers);
        jsonBank.put("managers", jsonManagers);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File("users.json"));
        JsonNode employeeNode = root.path("employees");
        for (int i = 0; i < employeeNode.size(); i++) {
            Employee employee = mapper.treeToValue(employeeNode.get(i), Employee.class);
            jsonEmployees.add(employee);
            Bank.users.add(employee);

        }

        JsonNode customerNode = root.path("customers");
        for (int i = 0; i < customerNode.size(); i++) {
            Customer customer = mapper.treeToValue(customerNode.get(i), Customer.class);
            jsonCustomers.add(customer);
            Bank.users.add(customer);
        }

        JsonNode managerNode = root.path("managers");
        for (int i = 0; i < managerNode.size(); i++) {
            Manager manager = mapper.treeToValue(managerNode.get(i), Manager.class);
            jsonManagers.add(manager);
            Bank.users.add(manager);
        }

      String option;
        do{

          try {
              System.out.println(" Welcomen to --name -- Banking System");
              MainMenu bankMenu = new MainMenu();
              bankMenu.handleMainMenu();
              //mapper.writeValue(Paths.get("users.json").toFile(), jsonBank);
          }catch (Exception exception){
              System.out.println(exception.getMessage());
          }
          option = "Do you want to continue?";
      }while (option.equalsIgnoreCase("yes"));

    }

}
