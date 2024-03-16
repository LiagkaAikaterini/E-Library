package exceptions;

public class BorrowLimitException extends Exception {
    public BorrowLimitException() { 
        super("You have already reached the borrowing limit of 2 books. Please return a book in order to borrow a new one."); 
    }
}
