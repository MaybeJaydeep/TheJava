package Evaluation.Jan_21;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while(true){

        System.out.println("\n Choose an operation to perform:");
        System.out.println("1. Array Traversal");
        System.out.println("2. Maximum and Minimum in Array");
        System.out.println("3. Even and Odd Count in Array");
        System.out.println("4. Sum and Average of Array Elements");
        System.out.println("5. Loop through Array with logic");
        System.out.println("6. Exit");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
        case 1:ArrayTraversal at = new ArrayTraversal();
        at.traverseArray();
        break;

        case 2:MaximumMinimum mm = new MaximumMinimum();
        mm.findMaxMin();
        break; 

        case 3:EvenOddCount eoc = new EvenOddCount();
        eoc.countEvenOdd();
        break; 

        case 4:SumAverage sa = new SumAverage();
        sa.calculateSumAverage();
        break;

        case 5:LoopArray la = new LoopArray();
        la.loopThroughArrayWithSkipDivisble();
        break;

        case 6: return;

        default:
        System.out.println("Invalid choice. Please try again.");
        }
    }
    }
}
/*  
    Where-ever validations are required, added those validations.
    For Example: In Array Traversal, size of array should be minimum 5(Constraint given by Vidhi didi).
    Still validations are required which will be added in next commit due to time constraint.
*/