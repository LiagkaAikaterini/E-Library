package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import exceptions.BorrowLimitException;
import exceptions.InvalidBookInfoException;
import exceptions.InvalidDateException;
import exceptions.InvalidUserInfoException;
import exceptions.NoCopiesAvailableException;
import exceptions.ReviewException;


public class User extends UserBase{
    private String firstName;
    private String lastName;
    private String idNum;
    private String email;
    private String address;
    private LocalDate birthDate;
    private List<String> borrowHistory;

    public User(String username, String password, String firstName, String lastName, String idNum, String email, String address, LocalDate birthDate) throws InvalidDateException, InvalidUserInfoException {
        super(username, password, false);
        
        // check if idNum is unique
        if (Library.isIdNumOccupied(idNum)) {
            throw new InvalidUserInfoException("This ID number is already used by another registered user. Please enter a unique ID Number");
        }

        //check if email has the correct format something@domain.end
        Pattern correctEmailFormat = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        
        if (!correctEmailFormat.matcher(email).matches()) {
            throw new InvalidUserInfoException("Invalid E-mail Format: a valid format is username@domain.com\nusername can contain: a-z  A-Z  0-9  . _ % + -\nusername must be followed by @\ndomain can contain: a-z  A-Z  0-9  . - \ndomain must be followed by dot (.) and 2 letter characters ");
        }
        
        // check if email is unique
        if (Library.isEmailOccupied(email)) {
            throw new InvalidUserInfoException("This e-mail is already used by another registered user. Please enter another email");
        }

        // birthday cannot be future date
        if (birthDate.isAfter(java.time.LocalDate.now())) {
            throw new InvalidDateException("Please Enter a valid birth date. It cannot be set to date in the future.");
        }
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNum = idNum;
        this.email = email;
        this.address = address;
        this.birthDate = birthDate;
        borrowHistory = new ArrayList<String>();
    }

    /* 
     *  checks if you can borrow anymore books
     */
    public boolean canBorrow() {
        List<Borrowed> myActiveBorrows = Library.findUsersActiveBorrows(this.getUsername());
        return (myActiveBorrows.size() < 2);
    }

    /* 
     *  Executes the borrow of a specific book
     */
    public void borrowBook(Book book) throws BorrowLimitException, NoCopiesAvailableException {
        if ( !canBorrow() ) {
            throw new BorrowLimitException();
        }

        int copies = book.getCopiesAvailable();
        if (copies <= 0) {
            throw new NoCopiesAvailableException();
        }

        String isbn = book.getISBN();
        Borrowed newBorrow = new Borrowed(isbn, this.getUsername());
        Library.addActiveBorrow(newBorrow);
        
        // add to borrow history imediatelly after the book is borrowed - even if borrow is active
        // No duplicates allowed
        if ( !((this.borrowHistory).contains(isbn)) ) {
            this.borrowHistory.add(isbn);
        }
        
        try {
            // update copies
            book.setCopiesAvailable(copies - 1); 
        }
        catch (InvalidBookInfoException e) {
            // this is never thrown as we checked already if there are available copies 
            // so the available copies >=1 and cannot get negative here
        }
               
    }

    /* 
     *  checks if he is currently borrowing this book
     */
    public boolean isBorrowActive(Book book) {
        List<Borrowed> myBorrows = Library.findUsersActiveBorrows(this.getUsername());

        for (Borrowed bor : myBorrows) {
            if ( (bor.getBookISBN()).equals(book.getISBN()) ) {
                return true;
            }
        }

        return false;
    }

    /* 
     *  the user creates a review for a specific book 
     */
    public void reviewBook(Book book, int rating, String comment) throws ReviewException {
        // check if user is actually currently borrowing the book he is trying to review 
        if ( !isBorrowActive(book) ) {
            throw new ReviewException("You are not currently borrowing this book. Your borrow must be active in order to review a book.");
        }

        book.addReview(this.getUsername(), rating, comment); 
    }

    /* 
     *  setters - getters - add - remove
     */
    public void addBorrowHistory(String bookISBN) {
        this.borrowHistory.add(bookISBN);
    }
    public void removeBorrowHistory(String bookISBN) {
        this.borrowHistory.remove(bookISBN);
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) throws InvalidUserInfoException {
        // if the new email is the same with the previouw email nothing is done
        if (email.equals(this.email)) {
            return;
        }

        //check if email has the correct format something@domain.end
        Pattern correctEmailFormat = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        
        if (!correctEmailFormat.matcher(email).matches()) {
            throw new InvalidUserInfoException("Invalid E-mail Format: a valid format is username@domain.com\nusername can contain: a-z  A-Z  0-9  . _ % + -\nusername must be followed by @\ndomain can contain: a-z  A-Z  0-9  . - \ndomain must be followed by dot (.) and 2 letter characters ");
        }
        
        // check if email is unique
        if (Library.isEmailOccupied(email)) {
            throw new InvalidUserInfoException("This e-mail is already used by another registered user. Please enter another email");
        }

        this.email = email;
    }

    public String getIdNum() {
        return idNum;
    }
    public void setIdNum(String idNum) throws InvalidUserInfoException {
        // if the new id number is the same with the previouw id number nothing is done
        if (idNum.equals(this.idNum)) {
            return;
        }

        // check if idNum is unique
        if (Library.isIdNumOccupied(idNum)) {
            throw new InvalidUserInfoException("This ID number is already used by another registered user. Please enter a unique ID Number");
        }

        this.idNum = idNum;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) throws InvalidDateException {
        // birth date cannot be in the future
        if (birthDate.isAfter(java.time.LocalDate.now())) {
            throw new InvalidDateException("Please Enter a valid birth date. It cannot be set to date in the future.");
        }
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getBorrowHistory() {
        return borrowHistory;
    }
    public void setBorrowHistory(List<String> borrowHistory) {
        this.borrowHistory = borrowHistory;
    }
}
