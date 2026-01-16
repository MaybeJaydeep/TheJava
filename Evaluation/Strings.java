package Evaluation;

public class Strings {
    String str1 = "Hello, World!";
    String str2 = new String("Hello, World!");
    String str3 = new String("I am different.");

    public void compareStrings() {
        System.out.println("Using '==': " + (str1 == str2));
        System.out.println("Using 'equals()': " + str1.equals(str2)); 
    }

    public void Immutability() {
        System.out.println("Before concat: " + str3);
        str3.concat(" How are you?");
        System.out.println("After concat: " + str3); 
    }

    public static void main(String[] args) {
        Strings demo = new Strings();
        demo.compareStrings();
        demo.Immutability();
    }
}
