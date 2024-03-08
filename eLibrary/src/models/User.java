package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class User extends UserBase{
    private String firstName;
    private String lastName;
    private String idNum;
    private String email;
    private String address;
    private LocalDate birthDate;
    private List<String> borrowHistory;

    public User(String username, String password, String firstName, String lastName, String idNum, String email, String address, LocalDate birthDate) {
        super(username, password, false);
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNum = idNum;
        this.email = email;
        this.address = address;
        this.birthDate = birthDate;
        borrowHistory = new ArrayList<String>();
    }


    public boolean canBorrow() {
        // check if you can borrow anymore books
        List<Borrowed> myActiveBorrows = Query.findUsersActiveBorrows(this.getUsername());
        
        return (myActiveBorrows.size() < 2);
    }

    
    public boolean borrowBook(Book book) {
        try {
            if ( !canBorrow() ) {
                throw new Exception("You have already borrowed 2 books. Return a book first to borrow another one.");
            }
            
            // now that i have assured that this user can borrow a new book do the borrowing
            int copies = book.getCopiesAvailable();
            if (copies > 0) {
                String isbn = book.getISBN();
                Borrowed newBorrow = new Borrowed(isbn, this.getUsername());
                Library.addActiveBorrow(newBorrow);
                // add to borrow history imediatelly after the book is borrowed - even if borrow is active - No duplicated allowed
                if ( !(this.borrowHistory).contains(isbn) ) {
                    this.borrowHistory.add(isbn);
                }
                
                // update copies
                book.setCopiesAvailable(copies - 1);
                return true;
            }
            else {
                throw new Exception("The book has no available copies.");
            }
        }
        catch(Exception e) {
            return false;
        }

    }

    public boolean hasBookBeenBorrowed(Book book) {
        // check if he has borrowed it in the past - it contains active borrowed books' isbns as well
        for (String b : this.borrowHistory) {
            if ( b.equals(book.getISBN()) ) {
                return true;
            }
        }

        return false;
    }

    
    public boolean reviewBook(Book book, int rating, String comment) {
        // check if user has actually borrowed the book he is trying to review
        try {
            if ( !hasBookBeenBorrowed(book) ) {
                throw new Exception("You have not borrowed this book yet. Please borrow the book before you try to review it.");
            }

        boolean res;
            if (rating == 0) {
                res = book.addReview(this.getUsername(), comment);
            }
            else if (comment.isEmpty()) {
                res = book.addReview(this.getUsername(), rating);
            }
            else{
                res = book.addReview(this.getUsername(), rating, comment);
            }
            
            return res;
        }
        catch(Exception e){
            return false;
        }
    }



    public void addBorrowHistory(String bookISBN) {
        this.borrowHistory.add(bookISBN);
    }
    public void removeBorrowHistory(String bookISBN) {
        this.borrowHistory.remove(bookISBN);
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

    public String getIdNum() {
        return idNum;
    }
    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<String> getBorrowHistory() {
        return borrowHistory;
    }
    public void setBorrowHistory(List<String> borrowHistory) {
        this.borrowHistory = borrowHistory;
    }
}
