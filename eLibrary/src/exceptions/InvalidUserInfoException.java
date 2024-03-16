package exceptions;

public class InvalidUserInfoException extends Exception {
    public InvalidUserInfoException() { 
        super("Invalid User Information"); 
    }
    public InvalidUserInfoException(String message) { 
        super(message); 
    }
}
