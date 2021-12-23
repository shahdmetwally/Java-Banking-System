package Utilities;

import java.util.Scanner;

public class UserInput {

    public static Scanner input = new Scanner(System.in);

    public  static int readInt (String message){
        System.out.println(message);
        int value = input.nextInt();
        input.nextLine();
        return value;
    }
    public static double readDouble(String message) {
        System.out.println(message);
        double value = input.nextDouble();
        input.nextLine();
        return value;
    }

    public static String readLine(String message) {
        System.out.println(message);
        String value = input.nextLine();
        return value;
    }
    public static long readLong(String message){
        System.out.println(message);
        long value = input.nextLong();
        input.nextLine();
        return value;
    }
}
