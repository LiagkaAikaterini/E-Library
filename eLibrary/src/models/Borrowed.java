package models;

import java.io.Serializable;
import java.time.LocalDate;
import exceptions.InvalidDateException;


public class Borrowed implements Serializable {
    private String bookISBN;
    private String username;
    private LocalDate borrowingDate;
    private LocalDate returnDate;

    public Borrowed(String book, String user) {
        this.bookISBN = book;
        this.username = user;
        this.borrowingDate = java.time.LocalDate.now();
        this.returnDate = borrowingDate.plusDays(5);
    }

    public String getBookISBN() {
        return bookISBN;
    }
    public void setBookISBN(String bookISBN){
        this.bookISBN = bookISBN;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    // No setter - you cannot set a new borrowing date, it is initialized automatically when the borrow is created
    public LocalDate getBorrowingDate() {
        return borrowingDate;
    }
    

    public LocalDate getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(LocalDate returnDate) throws InvalidDateException {
        if(returnDate.equals(this.returnDate)) {
            return;
        }
        // ensure that the new return date is after the previous return date
        // you cannot oblige someone to return a book before the borrowing time limit 
        if ( returnDate.isBefore(this.returnDate) ) {
            throw new InvalidDateException("Invalid return date. Choose a return date after the borrowing date");
        }
        
        this.returnDate = returnDate;  
    }

}
