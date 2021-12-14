package Classes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.ParseException;

import java.util.Scanner;

public class JsonUser {
    public static <JsonObjectBuilder> void main(String[] args) throws IOException, ParseException, Exception {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootArray = mapper.readTree(new File("employee.json"));
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
            }


            Customer jennifer = new Customer("Jennifer", "200202011234", "Qwerty1234");
            Bank.users.add(jennifer);
            mapper.writeValue(Paths.get("users.json").toFile(), Bank.users); //hi

        }
    }
}