package Util;

import Student.Student;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FileHandler {

    // File name
    private static final String FILE = "students.txt";

    // Save student records
    public static boolean saveStudents(ArrayList<Student> students) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {
            for (Student s : students) {
                // Write student details into file
                bw.write(s.getId() + "," + s.getName() + "," + s.getGender() + "," + s.getDOB() + "," + s.getDepartment() + "," +s.getCourse() + "," + s.getAdmissionDate() + "," + s.isStatus());
                bw.newLine();
            }
            System.out.println("Records saved successfully!");
            return true;
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
            return false;
        }
    }

    // Load student records
    public static ArrayList<Student> loadStudents() {

        ArrayList<Student> students = new ArrayList<>();

        // Store unique student IDs
        Set<Integer> ids = new HashSet<>();

        File file = new File(FILE);

        // Check whether file exists
        if (!file.exists()) {
            return students;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {

            String line;
            int lineNumber = 0;

            // Read file line by line
            while ((line = br.readLine()) != null) {
                lineNumber++;

                try {
                    // Split record using comma
                    String[] data = line.split(",", -1);

                    // Check number of fields
                    if (data.length != 8) {
                        System.out.println("Invalid record at line " + lineNumber);
                        continue;
                    }

                    // Convert ID into integer
                    int id = Integer.parseInt(data[0].trim());

                    // Check duplicate ID
                    if (id <= 0 || !ids.add(id)) {
                        System.out.println("Invalid or duplicate ID: " + id);
                        continue;
                    }

                    // Read student details
                    String name = data[1].trim();
                    String gender = data[2].trim();
                    String dob = data[3].trim();
                    String department = data[4].trim();
                    String course = data[5].trim();
                    String admissionDate = data[6].trim();

                    // Validate student details
                    String error = InputValidator.validationMessage(name, gender, dob, department, course, admissionDate);

                    if (error != null) {
                        ids.remove(id);
                        System.out.println("Invalid record: " + error);
                        continue;
                    }

                    // Validate status
                    if (!data[7].trim().equalsIgnoreCase("true") && !data[7].trim().equalsIgnoreCase("false")) {
                        ids.remove(id);
                        System.out.println("Invalid student status!");
                        continue;
                    }

                    boolean status = Boolean.parseBoolean(data[7].trim());

                    // Create Student object
                    Student s = new Student(id, name, gender, dob, department, course, admissionDate, status);
                    // Add student to ArrayList
                    students.add(s);
                    } catch (NumberFormatException e) {
                    System.out.println("Invalid ID at line " + lineNumber);
                }
            }

        } catch(IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
        return students;
    }
}