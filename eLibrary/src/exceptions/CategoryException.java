package exceptions;

public class CategoryException extends Exception {
    public CategoryException() {
        super("This category already exists, please enter a new unique category name");
    }
    public CategoryException(String message) {
        super(message);
    }
}
