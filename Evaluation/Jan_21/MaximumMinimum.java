package Evaluation.Jan_21;

import java.util.Scanner;

public class MaximumMinimum {

    public void findMaxMin() {
        System.out.println("Enter the size of the array:");
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int arr[] = new int[size];
        System.out.println("Enter the elements of the array:");
        for(int i=0; i<size; i++) {
            arr[i] = sc.nextInt();
        }
        int max = arr[0];
        int min = arr[0];
        for(int i=1; i<size; i++) {
            if(arr[i] > max) {
                max = arr[i];
            }
            if(arr[i] < min) {
                min = arr[i];
            }
        }
        System.out.print("Max element: " + max + "\n");
        System.out.print("Min element: " + min + "\n");
    }

}
