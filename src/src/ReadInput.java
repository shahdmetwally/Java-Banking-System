package src;

import java.util.Scanner;

public class ReadInput {

        public static Scanner input = new Scanner(System.in);

        // Reading Double
        public static double readDouble(String message){
            System.out.print(message);
            double value= input.nextDouble();
            input.nextLine();
            return value;

        }

        // Reading integer
        public static int readInteger(String message) {
            System.out.print(message);
            int value = input.nextInt();
            input.nextLine();
            return value;
        }


        // Reading strings
        public static String readString(String message) {
            System.out.print(message);
            return input.nextLine();
        }

    }
