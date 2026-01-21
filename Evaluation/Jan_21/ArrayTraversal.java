package Evaluation.Jan_21;

import java.util.Scanner;

public class ArrayTraversal {
    
    Scanner sc = new Scanner(System.in);
    void traverseArray() {
        System.out.print("Enter the size of the array: ");
        int size = sc.nextInt();
        if(size < 0 || size < 5){
            System.out.println("Please enter a valid size (minimum 5).");
            return;
        }
        int[] arr = new int[size];
        
        System.out.println("Enter " + size + " elements:");
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }
        
        System.out.println("Array elements are:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
