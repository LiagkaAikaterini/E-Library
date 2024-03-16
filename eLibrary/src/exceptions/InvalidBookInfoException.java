package exceptions;

public class InvalidBookInfoException extends Exception {
    public InvalidBookInfoException() { 
        super("Invalid Book Information"); 
    }
    public InvalidBookInfoException(String message) { 
        super(message); 
    }
}
