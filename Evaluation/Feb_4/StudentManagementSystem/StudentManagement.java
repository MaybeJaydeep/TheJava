package Evaluation.Feb_4.StudentManagementSystem;

import java.util.Scanner;

public class StudentManagement {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Service service = new Service();

        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Insert Student");
            System.out.println("2. Display Student by ID");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    Student student = new Student();
                    student.insert();
                    service.addStudent(student);
                    break;

                case 2:
                    System.out.print("Enter ID: ");
                    service.displayByID(sc.nextInt());
                    break;

                case 3:
                    System.out.print("Enter ID: ");
                    service.updateStudentByID(sc.nextInt());
                    break;

                case 4:
                    System.out.print("Enter ID: ");
                    service.deleteStudentByID(sc.nextInt());
                    break;

                case 5:
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
