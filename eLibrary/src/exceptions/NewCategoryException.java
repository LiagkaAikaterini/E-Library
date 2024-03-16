package exceptions;

public class NewCategoryException extends Exception {
    public NewCategoryException() {
        super("This category already exists, please enter a new unique category name");
    }
    public NewCategoryException(String message) {
        super(message);
    }
}
