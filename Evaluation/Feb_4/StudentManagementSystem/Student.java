package Evaluation.Feb_4.StudentManagementSystem;

import java.util.Scanner;

public class Student {

    private int studentID;
    private String name;
    private String grade;

    private final Scanner input;

    public Student() {
        this(new Scanner(System.in));
    }

    public Student(Scanner input) {
        this.input = input;
    }
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
        input.nextLine(); // consume the remaining newline

        System.out.print("Enter Student Name: ");
        name = input.nextLine();

        System.out.print("Enter Student Grade: ");
        grade = input.nextLine();
    }

    public void updateDetails() {
        System.out.print("Enter new name: ");
        name = input.nextLine();

        System.out.print("Enter new grade: ");
        grade = input.nextLine();
    }
}
