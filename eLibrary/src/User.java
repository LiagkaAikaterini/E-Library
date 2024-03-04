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
    private List<Book> borrowHistory;
    private List<Borrowed> borrowsNow;

    public User(String username, String password, String firstName, String lastName, String idNum, String email, String address, LocalDate birthDate) {
        super(username, password, false);
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNum = idNum;
        this.email = email;
        this.address = address;
        this.birthDate = birthDate;
        borrowHistory = new ArrayList<Book>();
        borrowsNow = new ArrayList<Borrowed>();
    }


    public boolean canBorrow() {
        // check if you can borrow anymore books
        return borrowsNow.size() < 2;
    }

    /*
        MAYBE DONT RETURN A STRING AND IMPLEMENT WITH EXCEPTIONS ?????????????????????
    */ 
    public String borrowBook(Book book) {
        if ( !canBorrow() ) {
            return "You have already borrowed 2 books. Return a book first to borrow another one.";
        }
        
        // now that i have assured that this user can borrow a new book do the borrowing
        int copies = book.getCopiesAvailable();
        if (copies > 0) {
            book.setCopiesAvailable(copies - 1);
            Borrowed newBorrow = new Borrowed(book, this);
            this.borrowsNow.add(newBorrow);
            App.addActiveBorrow(newBorrow);
            return "The book was borrowed successfully.";
        }
        else {
            return "The book has no available copies.";
        }
    }

    public boolean hasBookBeenBorrowed(Book book) {
        // check if he borrows it now
        for (Borrowed bor : this.borrowsNow) {
            if ( (bor.getBorrowedBook()).equals(book) ) {
                return true;
            }
        }
        
        // check if he has borrowed it in the past
        for (Book b : borrowHistory) {
            if ( (b).equals(book) ) {
                return true;
            }
        }

        return false;
    }

    
    public String reviewBook(Book book, int rating, String comment) {
        // check if user has actually borrowed the book he is trying to review
        if ( !hasBookBeenBorrowed(book) ) {
            return "You have not borrowed this book yet. Please borrow the book before you try to review it.";
        }

        if (rating == 0) {
            book.addReview(this, comment);
        }
        else if (comment.isEmpty()) {
            book.addReview(this, rating);
        }
        else{
            book.addReview(this, rating, comment);
        }
        
        return "The review has been registered";
    }


    public void addBorrowsNow(Borrowed borrow) {
        this.borrowsNow.add(borrow);
    }
    public void removeBorrowsNow(Borrowed borrow) {
        this.borrowsNow.remove(borrow);
    }

    public void addBorrowHistory(Book book) {
        this.borrowHistory.add(book);
    }
    public void removeBorrowHistory(Book book) {
        this.borrowHistory.remove(book);
    }
    public boolean containsInBorrowHistory(Book book) {
        return this.borrowHistory.contains(book);
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

    public List<Book> getBorrowHistory() {
        return borrowHistory;
    }
    public void setBorrowHistory(List<Book> borrowHistory) {
        this.borrowHistory = borrowHistory;
    }

    public List<Borrowed> getBorrowsNow() {
        return borrowsNow;
    }
    public void setBorrowsNow(List<Borrowed> borrowsNow) {
        this.borrowsNow = borrowsNow;
    }
}
