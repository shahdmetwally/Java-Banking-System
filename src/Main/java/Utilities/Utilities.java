package Utilities;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Utilities {

    public static final String EOL = System.lineSeparator();
    private Pattern pattern = Pattern.compile("(\\.\\d+)?");

    public static double truncateDouble( double number){
        int truncInt = (int)(number * 100);
        double newDouble = (double)(truncInt)/ 100.00;
        return newDouble;//0,0
    }

    public static String truncateForPrint(double number){
        int truncInt = (int)(number * 100);
        double newDouble = (double)(truncInt)/ 100.00;
        String truncated = String.format("%.2f",newDouble);
        return truncated;
    }

    public static String dateAndTime(){
      Date thisDateAndTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YY/MM/dd HH:mm");
        return dateFormat.format(thisDateAndTime);
    }

    public static String date(){
        Date thisDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YY/MM/dd");
        return dateFormat.format(thisDate);
    }
    public static String time(){
        Date thisTime = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        return timeFormat.format(thisTime);
    }

    public static void main(String[] args) {
        System.out.println(dateAndTime());

        Scanner sc = new Scanner(System.in);
        double userInput = 0;
        while (true) {
            userInput =Double.parseDouble(UserInput.readLine("Please enter a number"));
            try {
                userInput = Double.parseDouble(sc.next());
                break; // will only get to here if input was a double
            } catch (NumberFormatException ignore) {
                System.out.println("Invalid input");
            }
        }
        System.out.println(userInput);
    }
    //This was added
    public static boolean isNumber(String s) {
        for (int i = 0; i < s.length(); i++)
            if (Character.isDigit(s.charAt(i)) == false)
                return false;

        return true;
    }
    public static boolean isLetter(String s) {
        for (int i = 0; i < s.length(); i++)
            if (!Character.isDigit(s.charAt(i)) == true)
                return true;

        return true;
    }


    public static boolean isNumeric(String str) {
        return str.matches("\\d+(\\.\\d+)?");
    }
}
