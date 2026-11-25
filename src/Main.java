import Operations.StudentFeatures;
import Student.Student;
import Util.InputValidator;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        StudentFeatures features = new StudentFeatures();

        // Load existing student records.
        features.loadData();
        boolean running = true;

        while (running) {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Change Student Status");
            System.out.println("6. Search Student");
            System.out.println("7. Exit");

            int choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    addStudent(features);
                    break;

                case 2:
                    features.viewStudent();
                    break;

                case 3:
                    updateStudent(features);
                    break;

                case 4:
                    int deleteId = readPositiveInt("Enter Student ID: ");
                    features.deleteStudent(deleteId);
                    break;

                case 5:
                    int statusId = readPositiveInt("Enter Student ID: ");
                    features.changeStatus(statusId);
                    break;

                case 6:
                    int searchId = readPositiveInt("Enter Student ID: ");
                    features.SearchStudent(searchId);
                    break;

                case 7:
                    running = false;
                    System.out.println("Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice! Enter 1 to 7.");
            }
        }

        sc.close();
    }

    // Add Student.
    private static void addStudent(StudentFeatures features) {

        int id = readPositiveInt("Enter ID: ");

        String name = readRequiredText("Enter Name: ");
        String gender = readGender();
        String dob = readDate("Enter DOB (dd/MM/yyyy): ");
        String department = readRequiredText("Enter Department: ");
        String course = readRequiredText("Enter Course: ");
        String admissionDate = readDate("Enter Admission Date (dd/MM/yyyy): ");

        // Create student object.
        Student student = new Student(id, name, gender, dob, department, course, admissionDate, true);

        features.addStudent(student);
    }

    // Update Student.
    private static void updateStudent(StudentFeatures features) {

        int id = readPositiveInt("Enter Student ID to update: ");
        String name = readRequiredText("Enter New Name: ");
        String gender = readGender();
        String dob = readDate("Enter New DOB (dd/MM/yyyy): ");
        String department = readRequiredText("Enter New Department: ");
        String course = readRequiredText("Enter New Course: ");
        String admissionDate = readDate("Enter New Admission Date (dd/MM/yyyy): ");

        features.updateStudent(id, name, gender, dob, department, course, admissionDate);
    }

    // Read integer safely.
    private static int readInt(String prompt) {

        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number! Please try again.");
            }
        }
    }

    // Validate positive ID.
    private static int readPositiveInt(String prompt) {

        while (true) {
            int value = readInt(prompt);
            if (InputValidator.isValidId(value)) {
                return value;
            }
            System.out.println("ID must be greater than zero.");
        }
    }

    // Validate required text.
    private static String readRequiredText(String prompt) {

        while (true) {
            System.out.print(prompt);
            String value = sc.nextLine().trim();
            if (InputValidator.isValidText(value)) {
                return value;
            }
            System.out.println(
                    "This field is required and cannot contain commas."
            );
        }
    }

    // Validate gender.
    private static String readGender() {

        while (true) {

            System.out.print("Enter Gender (Male/Female/Other): ");

            String gender = sc.nextLine().trim();
            if (InputValidator.isValidGender(gender)) {
                return gender;
            }
            System.out.println("Please enter Male, Female or Other.");
        }
    }

    // Validate date.
    private static String readDate(String prompt) {

        while (true) {
            System.out.print(prompt);
            String date = sc.nextLine().trim();

            if (InputValidator.isValidDate(date)) {
                return date;
            }
            System.out.println("Invalid date! Use a valid date in dd/MM/yyyy format.");
        }
    }
}