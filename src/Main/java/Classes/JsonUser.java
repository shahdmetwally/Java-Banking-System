package Classes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;

public class JsonUser {
    public static <JsonObjectBuilder> void main(String[] args) throws IOException, Exception {
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
            String fullName = employeeNode.get(i).path("fullName").asText();
            String password = employeeNode.get(i).path("password").asText();
            String personalNo = employeeNode.get(i).path("personalNo").asText();
            double salary = employeeNode.get(i).path("salary").asDouble();
            Employee employee = new Employee(fullName, personalNo, password, salary);
            jsonEmployees.add(employee);
            Bank.users.add(employee);
        }

        JsonNode customerNode = root.path("customers");
        for (int i = 0; i < customerNode.size(); i++) {
            String fullName = customerNode.get(i).path("fullName").asText();
            String password = customerNode.get(i).path("password").asText();
            String personalNo = customerNode.get(i).path("personalNo").asText();
            Customer customer = new Customer(fullName, personalNo, password);
            jsonCustomers.add(customer);
            Bank.users.add(customer);
        }

        JsonNode managerNode = root.path("managers");
        for (int i = 0; i < managerNode.size(); i++) {
            String fullName = managerNode.get(i).path("fullName").asText();
            String password = managerNode.get(i).path("password").asText();
            String personalNo = managerNode.get(i).path("personalNo").asText();
            double salary = managerNode.get(i).path("salary").asDouble();
            double bonus = managerNode.get(i).path("bonus").asDouble();
            Manager manager = new Manager(fullName, personalNo, password, salary, bonus);
            jsonManagers.add(manager);
            Bank.users.add(manager);
        }

        /*
        for (JsonNode root : rootArray) {
            JsonNode employeeNode = root.path("employees");
            System.out.println(employeeNode);
            for (int i = 0; i < employeeNode.size(); i++) {

                String empName = employeeNode.get(i).path("empName").asText();
                String empID = employeeNode.get(i).path("empID").asText();
                String password = employeeNode.get(i).path("password").asText();
                int birthYear = employeeNode.get(i).path("birthYear").asInt();
                String personalNo = employeeNode.get(i).path("personalNo").asText();
                double salary = employeeNode.get(i).path("salary").asDouble();
                Employee employee = new Employee(empName, personalNo, password, salary);
                System.out.println(employee);
            }*/


            mapper.writeValue(Paths.get("users.json").toFile(), jsonBank);

        }
    }

