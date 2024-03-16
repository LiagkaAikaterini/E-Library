package exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() { 
        super("User not found. Please check your information and try to login again."); 
    }
    public UserNotFoundException(String message) {
        super(message);
    }
}
