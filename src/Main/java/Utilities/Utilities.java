package Utilities;

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
}
