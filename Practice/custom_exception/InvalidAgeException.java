package custom_exception;

public class InvalidAgeException extends Exception {
    InvalidAgeException(){}
    InvalidAgeException(String message){
        System.out.println(message);
    }
}