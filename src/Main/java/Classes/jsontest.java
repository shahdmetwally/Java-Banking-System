package Classes;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;

public class jsontest {

    public static void main(String[] args) throws Exception {
        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(new FileReader("employee.json"));
        ArrayList<Employee> employees = new ArrayList<>();

        for (Object o : array) {
            JSONObject person = (JSONObject) o;
            JSONArray array1 = (JSONArray) person.get("employees");
            JSONArray array2 = (JSONArray) person.get("customers");

            for (Object o1 : array1) {

                JSONObject employee = (JSONObject) o1;

                String empName = (String) employee.get("empName");
                String personalNo = (String) employee.get("personalNo");
                String empID = (String) employee.get("empID");
                String password = (String) employee.get("password");
                double birthYearDouble = (double) employee.get("birthYear");
                int birthYear = (int) birthYearDouble;
                double grossSalary = (double) employee.get("grossSalary");
                Employee e = new Employee(empName, personalNo, password, grossSalary);
                employees.add(e);
                System.out.println(e);


            }
            for (Object o2 : array2) {
                JSONObject employee = (JSONObject) o2;

                String name = (String) employee.get("name");
                String personalNo = (String) employee.get("personalNo");
                String customerID = (String) employee.get("customerID");
                String password = (String) employee.get("password");
                double birthYearDouble = (double) employee.get("birthYear");
                int birthYear = (int) birthYearDouble;
                double grossSalary = (double) employee.get("grossSalary");
                Employee e = new Employee(name, personalNo, password, grossSalary);

                System.out.println(e);
            }
        }

        for (Employee e : employees){
            if(e.getFullName().equals("Jennifer Hälgh")){
                e.setFullName("Jen Hälgh");
                System.out.println(e);
            }
        }
        //hi



    }
}


