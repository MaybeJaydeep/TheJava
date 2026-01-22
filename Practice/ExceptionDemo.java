import java.util.Scanner;

public class ExceptionDemo {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter a number:");
            int i = sc.nextInt();
            System.out.println(i);
        } catch (Exception e) {
            System.out.println("Please enter numeric data only....");
            ;
        }

        try {
            System.out.println("please enter 5 elements for array");
            Scanner sc = new Scanner(System.in);
            int[] arr = new int[5];
            for (int i = 0; i < 5; i++) {
                arr[i] = sc.nextInt();
            }
            System.out.println("Which index element you want to access?:");
            int choice = sc.nextInt();
            System.out.println("Your desired element is: " + arr[choice - 1]);

        } catch (Exception e) {
            System.out.println("Available elements are 5 only, please enter valid index");
        }
        System.out.println("Happily executed program");

    }

    /*
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter a number:");
      int i = sc.nextInt();
      System.out.println(i);// abruptly stops if valid numeric data is not given

      System.out.println("please enter 5 elements for array");
      Scanner sc = new Scanner(System.in);
      int[] arr = new int[5];
      for(int i = 0; i < 5; i++){
      arr[i] = sc.nextInt();
      } 

      System.out.println("Which index element you want to access?:");
      int choice = sc.nextInt();
      System.out.println("Your desired element is: "+ arr[choice-1]);// abruptly stops if valid index is not given
      
      System.out.println("Happily executed program"); //Never gets executed if any one of the 2 fails to be executed
     */
}
