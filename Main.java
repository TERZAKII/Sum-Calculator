package PA;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int number = scanner.nextInt();

        try {
            int sum = SumCalculator.calculateSum(number);
            System.out.println("The sum of all numbers from 1 to " + number + " is " + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
