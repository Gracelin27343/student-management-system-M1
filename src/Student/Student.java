package Student;

//inheritance
public class Student extends Person{
    //attributes
    private String Department;
    private String Course;
    private String AdmissionDate ;
    private boolean status;

    //contructor
    public Student(int id, String name, String gender, String DOB, String department, String course, String admissionDate,boolean status) {
        super(id, name, gender, DOB);
        this.Department = department;
        this.Course = course;
        this.AdmissionDate = admissionDate;
        this.status = status;
    }
    //encapsulation
    //getter and setter
    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String course) {
        Course = course;
    }

    public String getAdmissionDate() {
        return AdmissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        AdmissionDate = admissionDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void displayDetails(){
        super.displayDetails();

        System.out.println("Department: " + Department);
        System.out.println("Course: " + Course);
        System.out.println("Admission Date: " + AdmissionDate);
        System.out.println("Status: " + (status ? "Active" : "Inactive"));

    }

}
