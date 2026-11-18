package Util;

import java.io.*;
import java.util.*;
import Student.Student;

public class FileHandler {

    static String fileName = "students.txt";

    // Save students
    public static void saveStudents(ArrayList<Student> students) {
 //Filewriter
        try {
            FileWriter fw = new FileWriter(fileName);

            for (Student s : students) {
                fw.write(s.getId() + "," + s.getName() + "," + s.getGender() + "," + s.getDOB() + "," +
                                s.getDepartment() + "," + s.getCourse() + "," + s.getAdmissionDate() + "," + s.isStatus() + "\n");
            }

            fw.close();
            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }

    // Load students
    public static ArrayList<Student> loadStudents() {

        ArrayList<Student> students = new ArrayList<>();
    //exception handling
        try {
            File file = new File(fileName);

            if (!file.exists()) {
                return students;
            }
    //file reader
            FileReader fr = new FileReader(file);

            Scanner sc = new Scanner(fr);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(",", -1);

                if (data.length != 8) {
                    System.out.println("Invalid record skipped!");
                    continue;
                }

                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String gender = data[2];
                String DOB = data[3];
                String department = data[4];
                String course = data[5];
                String admissionDate = data[6];
                boolean status = Boolean.parseBoolean(data[7]);

                Student s = new Student(id, name, gender, DOB, department, course, admissionDate, status);

                students.add(s);
            }

            sc.close();
            fr.close();

        } catch (Exception e) {
            System.out.println("File loading error: " + e.getMessage());
        }

        return students;
    }
}