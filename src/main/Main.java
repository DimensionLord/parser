package main;


import calculator.Parser;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();


        System.out.println("Введите арифметическую операцию:");
        String line = scanner.nextLine();
        while (!"выход".equalsIgnoreCase(line)) {
            try {
                System.out.println("=" + parser.calculateLine(line));
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Введите арифметическую операцию:");
            line = scanner.nextLine();
        }


    }
}
