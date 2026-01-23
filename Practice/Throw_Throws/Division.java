package Throw_Throws;

import java.util.Scanner;

public class Division {
    public double division() throws ArithmeticException, NumberFormatException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number 1:");
        int n1 = sc.nextInt();
        System.out.println("Enter number 2:");
        int n2 = sc.nextInt();
        if(n2 == 0){
            throw new ArithmeticException("Division by zero");
        }
        return n1/n2;
    }
}
