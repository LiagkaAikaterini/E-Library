package exceptions;

public class ReviewException extends Exception {
    public ReviewException() { 
        super("Invalid Review"); 
    }
    public ReviewException(String message) { 
        super(message); 
    }
}
