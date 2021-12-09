package Classes;
import java.io.IOException;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.ParseException;

import java.util.Scanner;

public class JsonUser {
    public static <JsonObjectBuilder> void main(String[] args) throws IOException, ParseException, Exception {
        Scanner input = new Scanner(System.in);

        ObjectMapper mapper = new ObjectMapper();

        Customer jennifer = new Customer("Jennifer", "200202011234","Qwerty1234");
        Bank.users.add(jennifer);
        mapper.writeValue(Paths.get("users.json").toFile(), Bank.users);

            }
        }
    
