import java.util.logging.Logger;
import java.util.Scanner;

public class ExceptionDemo {
    private static final Logger logger = Logger.getLogger(
            ExceptionDemo.class.getName());

    public static void main(String[] args) {

        
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter a number:");
            int i = sc.nextInt();
            System.out.println(i);
        } catch (Exception e) {
            System.out.println("Please enter numeric data only....");
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

        } catch (ArrayIndexOutOfBoundsException  | MatchException e) {
            /*  e = new ArrayIndexOutOfBoundsException(); 
                you cant change the object here becz implicitly its final 
                System.out.println("Available elements are 5 only, please enter valid index");
            */
            logger.severe("There is problem in input given, please try again with valid input");
        }
        catch(Exception e){
            e = new ArrayIndexOutOfBoundsException();
        //  Allowed here because there is only one class for exception mentioned
            logger.severe("Exception occured, please try again");
        }
        finally{
            System.out.println("Happily executed program");
        }
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
