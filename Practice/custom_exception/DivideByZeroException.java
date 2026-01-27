package custom_exception;
public class DivideByZeroException extends RuntimeException{
    public DivideByZeroException(){ super();}
    public DivideByZeroException(String message) {
        super(message);
    }
}