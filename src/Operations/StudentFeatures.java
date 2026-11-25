package Operations;

import Student.Student;
import Exception.StudentNotFoundException;
import Util.FileHandler;
import Util.InputValidator;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentFeatures implements StudentOperations {

    // Stores all students and maintains insertion order.
    private ArrayList<Student> students = new ArrayList<>();

    // Stores students using ID as the key for faster searching.
    private final HashMap<Integer, Student> studentMap = new HashMap<>();

    // Save changes and report file errors.
    private boolean persistChanges() {

        if (FileHandler.saveStudents(students)) {
            return true;
        }

        System.out.println("Changes were not saved. Check file permissions and try again.");
        return false;
    }

    // Synchronize HashMap with ArrayList.
    private void rebuildMap() {

        studentMap.clear();

        for (Student student : students) {
            studentMap.put(student.getId(), student);
        }
    }

    // Find Student using HashMap.
    @Override
    public Student findStudent(int id)
            throws StudentNotFoundException {

        Student student = studentMap.get(id);

        if (student == null) {
            throw new StudentNotFoundException("Student not found!");
        }

        return student;
    }

    // Add Student.
    @Override
    public void addStudent(Student student) {

        // Validate student ID.
        if (student == null || !InputValidator.isValidId(student.getId())) {
            System.out.println("Invalid student ID!");
            return;
        }

        // Prevent duplicate student IDs.
        if (studentMap.containsKey(student.getId())) {
            System.out.println("Student ID already exists!");
            return;
        }

        // Validate student details.
        String error = InputValidator.validationMessage(
                student.getName(),
                student.getGender(),
                student.getDOB(),
                student.getDepartment(),
                student.getCourse(),
                student.getAdmissionDate()
        );

        if (error != null) {
            System.out.println("Invalid details: " + error);
            return;
        }

        // Add the same object to both collections.
        students.add(student);
        studentMap.put(student.getId(), student);

        // Save records to file.
        if (persistChanges()) {
            System.out.println("Student added successfully!");
        } else {

            // Roll back if file saving fails.
            students.remove(student);
            studentMap.remove(student.getId());
        }
    }

    // View all Students using ArrayList.
    @Override
    public void viewStudent() {

        if (students.isEmpty()) {
            System.out.println("No student records found!");
            return;
        }

        // Display all student records.
        for (Student student : students) {
            student.displayDetails();
            System.out.println("----------------------");
        }
    }

    // Search Student using HashMap.
    @Override
    public void SearchStudent(int id) {

        try {
            Student student = findStudent(id);
            student.displayDetails();
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // Update Student details.
    @Override
    public void updateStudent(
            int id,
            String name,
            String gender,
            String dob,
            String department,
            String course,
            String admissionDate) {

        Student student;

        try {
            student = findStudent(id);
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        // Validate updated details.
        String error = InputValidator.validationMessage(
                name, gender, dob,
                department, course, admissionDate
        );

        if (error != null) {
            System.out.println("Invalid details: " + error);
            return;
        }

        // Store old values for rollback.
        String oldName = student.getName();
        String oldGender = student.getGender();
        String oldDob = student.getDOB();
        String oldDepartment = student.getDepartment();
        String oldCourse = student.getCourse();
        String oldAdmissionDate = student.getAdmissionDate();

        // Update student details.
        student.setName(name.trim());
        student.setGender(gender.trim());
        student.setDOB(dob.trim());
        student.setDepartment(department.trim());
        student.setCourse(course.trim());
        student.setAdmissionDate(admissionDate.trim());

        // Save updated records.
        if (persistChanges()) {
            System.out.println("Student updated successfully!");
        } else {

            // Restore old details if saving fails.
            student.setName(oldName);
            student.setGender(oldGender);
            student.setDOB(oldDob);
            student.setDepartment(oldDepartment);
            student.setCourse(oldCourse);
            student.setAdmissionDate(oldAdmissionDate);
        }
    }

    // Delete Student from both collections.
    @Override
    public void deleteStudent(int id) {

        Student student;

        try {
            student = findStudent(id);
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        // Remove student from both collections.
        students.remove(student);
        studentMap.remove(id);

        // Save remaining records.
        if (persistChanges()) {
            System.out.println("Student deleted successfully!");
        } else {
            // Restore student if saving fails.
            students.add(student);
            rebuildMap();
        }
    }

    // Change Student Status.
    @Override
    public void changeStatus(int id) {

        Student student;

        try {
            student = findStudent(id);
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        // Store previous status.
        boolean oldStatus = student.isStatus();

        // Toggle Active and Inactive status.
        student.setStatus(!oldStatus);

        if (persistChanges()) {
            System.out.println("Student status changed to " + (student.isStatus() ? "Active" : "Inactive"));
        } else {
            // Restore old status if saving fails.
            student.setStatus(oldStatus);
        }
    }

    // Load records from file into both collections.
    public void loadData() {

        // Load students into ArrayList.
        students = FileHandler.loadStudents();

        // Populate HashMap using student IDs.
        rebuildMap();

        System.out.println(students.size() + " student record(s) loaded successfully!");
    }
}