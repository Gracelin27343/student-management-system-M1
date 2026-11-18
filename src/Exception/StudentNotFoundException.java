package Exception;
//custom exception
public class StudentNotFoundException extends Exception{
    public StudentNotFoundException(String message){
        super(String.valueOf(message));
    }
}
