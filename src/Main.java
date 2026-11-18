import java.util.Scanner;

import Student.Student;
import Operations.StudentFeatures;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
       //object
        StudentFeatures features = new StudentFeatures();

        // Load existing records
        features.loadData();
        int choice = 0;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");

            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Change Student Status");
            System.out.println("6. Search Student");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");

            //exception handling
            try {
                choice = Integer.parseInt(sc.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Gender: ");
                    String gender = sc.nextLine();

                    System.out.print("Enter DOB: ");
                    String dob = sc.nextLine();

                    System.out.print("Enter Department: ");
                    String department = sc.nextLine();

                    System.out.print("Enter Course: ");
                    String course = sc.nextLine();

                    System.out.print("Enter Admission Date: ");
                    String date = sc.nextLine();

                    Student s = new Student(id, name, gender, dob, department, course, date, true);
                    features.addStudent(s);
                    break;

                case 2:
                    features.viewStudent();
                    break;

                case 3:
                    System.out.print("Enter Student ID: ");
                    id = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter new name: ");
                    name = sc.nextLine();

                    System.out.print("Enter new gender: ");
                    gender = sc.nextLine();

                    System.out.print("Enter new DOB: ");
                    dob = sc.nextLine();

                    System.out.print("Enter new department: ");
                    department = sc.nextLine();

                    System.out.print("Enter new course: ");
                    course = sc.nextLine();

                    System.out.print("Enter new admission date: ");
                    date = sc.nextLine();

                    features.updateStudent(id, name, gender, dob, department, course, date);

                    break;

                case 4:
                    System.out.print("Enter Student ID: ");
                    id = Integer.parseInt(sc.nextLine());
                    features.deleteStudent(id);
                    break;

                case 5:
                    System.out.print("Enter Student ID: ");
                    id = Integer.parseInt(sc.nextLine());
                    features.changeStatus(id);
                    break;

                case 6:
                    System.out.print("Enter Id");
                    id = Integer.parseInt(sc.nextLine());
                    features.SearchStudent(id);
                    break;

                case 7:
                    System.out.println("Thank you");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 7);
        sc.close();

    }
}