package Evaluation.Feb_4.StudentManagementSystem;

import java.util.ArrayList;

public class Service {

    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        // Check for duplicate student ID before adding
        for (Student existingStudent : students) {
            if (existingStudent.getStudentID() == student.getStudentID()) {
                System.out.println("Student ID already exists. Student not added.");
                return;
            }
        }
        students.add(student);
        System.out.println("Student added successfully!");
    }

    public void displayByID(int studentID) {
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                System.out.println("Student ID: " + student.getStudentID());
                System.out.println("Name: " + student.getName());
                System.out.println("Grade: " + student.getGrade());
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void updateStudentByID(int studentID) {
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                student.updateDetails();
                System.out.println("Student updated!");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void deleteStudentByID(int studentID) {
        students.removeIf(s -> s.getStudentID() == studentID);
        System.out.println("Student deleted if existed.");
    }
}
