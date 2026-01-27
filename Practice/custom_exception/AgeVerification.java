package custom_exception;
import java.util.Scanner;

public class AgeVerification {
    public static void main(String[] args) throws InvalidAgeException {
        Scanner sc = new Scanner(System.in);
        int age;
        System.out.println("Please enter your age: ");
        age = sc.nextInt();
        if (age < 0 || age > 100) {
            throw new InvalidAgeException("Please enter valid age and try again...");
        }
    }
}