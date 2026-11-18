package Operations;

import Student.Student;
import Exception.StudentNotFoundException;
// Interface provides abstraction
public interface StudentOperations {
    // Declare student management operation methods
    void addStudent(Student s);
    void deleteStudent(int id);
    void viewStudent();
    void SearchStudent(int id);
    void updateStudent(int id, String name, String gender, String DOB, String department, String course, String admissionDate);
    Student findStudent(int id) throws StudentNotFoundException;
    void changeStatus(int id);

}