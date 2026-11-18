package Operations;

import Student.Student;
import Exception.StudentNotFoundException;
import Util.FileHandler;

import java.util.ArrayList;

public class StudentFeatures implements StudentOperations {

    //collection -ARRAYLIST
    private ArrayList<Student> students = new ArrayList<>();

    // find student by ID
    @Override //overriding
    public Student findStudent(int id) throws StudentNotFoundException {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        throw new StudentNotFoundException("Student not found!");
    }

    // Add Student
    @Override
    public void addStudent(Student s) {

        try {
            findStudent(s.getId());
            System.out.println("Student already exists!");
            return;
        } catch (StudentNotFoundException e) {
            students.add(s);
            FileHandler.saveStudents(students);
            System.out.println("Student added successfully!");
        }
    }

    // View Students
    @Override
    public void viewStudent() {
        if (students.isEmpty()) {
            System.out.println("No student records found!");
            return;
        }
        for (Student s : students) {
            s.displayDetails();
            System.out.println("----------------------");
        }
    }

    //search student
    @Override
    public void SearchStudent(int id) {
        try {
            Student student = findStudent(id);
            student.displayDetails();

        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


    // Update Student
    @Override
    public void updateStudent(int id, String name, String gender, String DOB, String department, String course, String admissionDate) {
        try {
            Student s = findStudent(id);

            s.setName(name);
            s.setGender(gender);
            s.setDOB(DOB);
            s.setDepartment(department);
            s.setCourse(course);
            s.setAdmissionDate(admissionDate);

            FileHandler.saveStudents(students);
            System.out.println("Student updated successfully!");
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // Delete Student
    @Override
    public void deleteStudent(int id) {
        try {
            Student s = findStudent(id);
            students.remove(s);
            FileHandler.saveStudents(students);
            System.out.println("Student deleted successfully!");

        } catch (StudentNotFoundException e) {

            System.out.println(e.getMessage());
        }
    }

    // Change Student Status
    @Override
    public void changeStatus(int id) {

        try {
            Student s = findStudent(id);
            s.setStatus(!s.isStatus());
            FileHandler.saveStudents(students);
            System.out.println("Student status changed successfully!");

            System.out.println("Current Status: " + (s.isStatus() ? "Active" : "Inactive"));

        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // Load Data
    public void loadData() {
        students = FileHandler.loadStudents();
        System.out.println("Student records loaded!");
    }
}