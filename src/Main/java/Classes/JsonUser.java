package Classes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JsonUser {
    public static <JsonObjectBuilder> void main(String[] args) throws IOException, ParseException, Exception {
        HashMap<String, ArrayList> jsonBank = new HashMap<>();
        ArrayList<Customer> jsonCustomers = new ArrayList<>();
        ArrayList<Employee> jsonEmployees = new ArrayList<>();
        jsonBank.put("employees", jsonEmployees);
        jsonBank.put("customers", jsonCustomers);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File("users.json"));
        JsonNode employeeNode = root.path("employees");
        for (int i = 0; i < employeeNode.size(); i++) {
            String fullName = employeeNode.get(i).path("fullName").asText();
            String empID = employeeNode.get(i).path("empID").asText();
            String password = employeeNode.get(i).path("password").asText();
            int birthYear = employeeNode.get(i).path("birthYear").asInt();
            String personalNo = employeeNode.get(i).path("personalNo").asText();
            double salary = employeeNode.get(i).path("salary").asDouble();
            Employee employee = new Employee(fullName, personalNo, password, salary);
            jsonEmployees.add(employee);
        }

        JsonNode customerNode = root.path("customers");
        for (int i = 0; i < customerNode.size(); i++) {
            String fullName = employeeNode.get(i).path("fullName").asText();
            String empID = employeeNode.get(i).path("empID").asText();
            String password = employeeNode.get(i).path("password").asText();
            int birthYear = employeeNode.get(i).path("birthYear").asInt();
            String personalNo = employeeNode.get(i).path("personalNo").asText();
            double salary = employeeNode.get(i).path("salary").asDouble();
            Customer customer = new Customer(fullName, personalNo, password);
            jsonCustomers.add(customer);
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


            //Customer jennifer = new Customer("Jennifer", "200202011234", "Qwerty1234");
            //Bank.users.add(jennifer);

            //ArrayList<Customer> jsonCustomers = new ArrayList<>();
            //Customer anasha = new Customer("Anasha Sarker", "200202011234", "Qwerty1234");
            //jsonCustomers.add(anasha);
            //ArrayList<Employee> jsonEmployees = new ArrayList<>();
            //Employee sadhana = new Employee("Sadhana Anandan", "200202011234", "Abcde1234", 35000.0);
            //jsonEmployees.add(sadhana);


            mapper.writeValue(Paths.get("users.json").toFile(), jsonBank);

        }
    }

