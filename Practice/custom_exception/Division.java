package custom_exception;

import java.util.Scanner;

public class Division {
    public static void main(String[] args) {
        System.out.println("Enter the numerator:");
        Scanner sc = new Scanner(System.in);
        int numerator = sc.nextInt();
        System.out.println("Enter the denominator");
        int denominator = sc.nextInt();

        if(denominator == 0){
            throw new DivideByZeroException("Denominator cannot be 0");
        }
        System.out.println("Dision result : " + (numerator/denominator));
    }
}
