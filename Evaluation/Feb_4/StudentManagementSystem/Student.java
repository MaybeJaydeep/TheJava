package Evaluation.Feb_4.StudentManagementSystem;

import java.util.Scanner;

public class Student {

    private int studentID;
    private String name;
    private String grade;

    static Scanner input = new Scanner(System.in);

    public Student() {}

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public String getGrade() {
        return grade;
    }

    public void insert() {
        System.out.print("Enter Student ID: ");
        studentID = input.nextInt();

        System.out.print("Enter Student Name: ");
        name = input.next();

        System.out.print("Enter Student Grade: ");
        grade = input.next();
    }

    public void updateDetails() {
        System.out.print("Enter new name: ");
        name = input.next();

        System.out.print("Enter new grade: ");
        grade = input.next();
    }
}
