package Evaluation.Jan_21;

import java.util.Scanner;

public class LoopArray {

    public void loopThroughArrayWithSkipDivisble() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter starting number of the array:");
        int start = sc.nextInt();
        System.out.println("Enter the ending number of the array:");
        int end = sc.nextInt();
        
        if(start > end){
            System.out.println("Invalid range: Enter a small starting number than ending number.");
            return;
        }
        int size = end - start + 1;
        int arr[] = new int[size];

        for(int i=0; i<size; i++) {
            arr[i] = start + i;
        }
        System.out.println("Enter the divisor to skip elements:");
        int divisor = sc.nextInt();
        System.out.println("Elements of the array (excluding elements divisible by " + divisor + "):");
        for(int i=0; i<size; i++) {
            if(arr[i] % divisor != 0) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
    }
}