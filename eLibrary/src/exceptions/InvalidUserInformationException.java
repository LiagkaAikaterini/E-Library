package exceptions;

public class InvalidUserInformationException extends Exception {
    public InvalidUserInformationException() {}
    public InvalidUserInformationException(String message) { super(message); }
}
