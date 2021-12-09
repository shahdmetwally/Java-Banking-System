package Classes;
import java.io.IOException;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.ParseException;

import java.util.Scanner;

public class JsonUser {
    public static <JsonObjectBuilder> void main(String[] args) throws IOException, ParseException, Exception {
        Scanner input = new Scanner(System.in);
        /*
        JsonNode rootArray = mapper.readTree(new File("employee.json"));
        System.out.println(rootArray);
        for (JsonNode root : rootArray) {
            JsonNode employeeNode = root.path("employees");
            System.out.println(employeeNode);
            for (int i = 0; i < employeeNode.size(); i++) {
                String empName = employeeNode.get(i).path("empName").asText();
                String password = employeeNode.get(i).path("password").asText();
                int birthYear = employeeNode.get(i).path("birthYear").asInt();
                int personalNo = employeeNode.get(i).path("personalNo").asInt();
                double salary = employeeNode.get(i).path("salary").asDouble();
        */
        ObjectMapper mapper = new ObjectMapper();

        //Customer jennifer = new Customer("Jennifer", "200202010500","Qwerty1234");
        //Bank.getUsers().add(jennifer);
        mapper.writeValue(Paths.get("users.json").toFile(), Bank.getUsers());

            }
        }
    
