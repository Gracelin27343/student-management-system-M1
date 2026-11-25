package Student;
//parent class
public class Person {
   // access modifier
    private int Id;
    private String name;
    private String gender;
    private String DOB;

    // Constructor
    public Person(int id, String name, String gender, String DOB) {
        this.Id = id;
        this.name = name;
        this.gender = gender;
        this.DOB = DOB;
    }

//getter and setters
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }


    public void displayDetails() {
        System.out.println("ID: " + Id);
        System.out.println("Name: " + name);
        System.out.println("Gender: " + gender);
        System.out.println("DOB: " + DOB);
    }
}
