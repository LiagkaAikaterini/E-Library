import java.time.LocalDate;

public class Borrowed {
    private Book borrowedBook;
    private User borrower;
    private LocalDate borrowingDate;
    private LocalDate returnDate;
    private boolean isReturned;

    Borrowed(Book book, User user){
        this.borrowedBook = book;
        this.borrower = user;
        this.borrowingDate = java.time.LocalDate.now();
        this.returnDate = borrowingDate.plusDays(5);
        this.isReturned = false;
    }

    public Book getBorrowedBook() {
        return borrowedBook;
    }
    public void setBorrowedBook(Book borrowedBook) {
        this.borrowedBook = borrowedBook;
    }

    public User getBorrower() {
        return borrower;
    }
    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }

    public LocalDate getBorrowingDate() {
        return borrowingDate;
    }
    public void setBorrowingDate(LocalDate borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean getIsReturned() {
        return isReturned;
    }
    public void setIsReturned(boolean isReturned) {
        this.isReturned = isReturned;
    }

}
