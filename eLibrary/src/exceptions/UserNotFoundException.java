package exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() { 
        super("Something went wrong. Please try to login again."); 
    }
    public UserNotFoundException(String message) {
        super(message);
    }
}
