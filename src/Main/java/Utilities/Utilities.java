package Utilities;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {

    public static final String EOL = System.lineSeparator();

    public static double truncateDouble( double number){
        int truncInt = (int)(number * 100);
        double newDouble = (double)(truncInt)/ 100.00;
        return newDouble;
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
    }


}
