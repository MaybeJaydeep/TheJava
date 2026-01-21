package Evaluation.Jan_21;
import java.util.Scanner;

public class SumAverage {

    public void calculateSumAverage() {
        System.out.println("Enter the size of the array:");
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int arr[] = new int[size];

        System.out.println("Enter the elements of the array:");
        for(int i=0; i<size; i++) {
            arr[i] = sc.nextInt();
        }

        int sum =0;
        for(int i=0; i<size; i++) {
            sum += arr[i];
        }
        double average = (double)sum / size;
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + average);
    }

}
