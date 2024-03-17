package exceptions;

public class NoCopiesAvailableException extends Exception {
    public NoCopiesAvailableException() { 
        super("This book has no available copies right now."); 
    }
}
