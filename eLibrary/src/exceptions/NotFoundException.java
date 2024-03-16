package exceptions;

public class NotFoundException extends Exception {
    public NotFoundException() { 
        super("Not Found Exception."); 
    }
    public NotFoundException(String message) { 
        super(message); 
    }
}
