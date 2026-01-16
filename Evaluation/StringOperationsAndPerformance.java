package Evaluation;

public class StringOperationsAndPerformance {

    public static void performanceComparison() {

        // Using String with +
        System.out.println("Using String (+):");
        String result = "";
        for (int i = 1; i <= 10; i++) {
            result = result + i + " ";
        }
        System.out.println("Result: " + result);

        // Using StringBuilder
        System.out.println("\nUsing StringBuilder (append):");
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 10; i++) {
            sb.append(i).append(" ");
        }
        System.out.println("Result: " + sb.toString());
        System.out.println("\nStringBuilder is more efficient for large concatenations");
    }

    public static void stringOperations() {

        String input = "Hello World Java";
        System.out.println("Input: " + input);

        
        System.out.println("\n1. Reverse:");
        String reversed = new StringBuilder(input).reverse().toString();
        System.out.println("Reversed: " + reversed);

    
        System.out.println("\n2. Substring (index 6 to 11):");
        String sub = input.substring(6, 11);
        System.out.println("Substring: " + sub);

        System.out.println("\n3. Search using contains():");
        System.out.println("Contains 'World': " + input.contains("World"));
        System.out.println("Contains 'Python': " + input.contains("Python"));
    }

    public static void main(String[] args) {
        performanceComparison();
        stringOperations();
    }
}
