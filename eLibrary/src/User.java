import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String idNum;
    private String email;
    private String address;
    private LocalDate birthDate;
    private List<Borrowed> borrowHistory;

    public User(String username, String password, String firstName, String lastName, String idNum, String email, String address, LocalDate birthDate) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNum = idNum;
        this.email = email;
        this.address = address;
        this.birthDate = birthDate;
        borrowHistory = new ArrayList<Borrowed>();
    }


    public boolean canBorrow() {
        // check if you can borrow anymore books
        int borrows_allowed = 2;
        for (Borrowed b : this.borrowHistory) {
            if (b.getIsReturned() == false) {
                borrows_allowed--;
            }
        }

        return (borrows_allowed > 0) ;
    }

    /*
        MAYBE DONT RETURN A STRING AND IMPLEMENT WITH EXCEPTIONS ?????????????????????
    */ 
    public String borrowBook(Book book) {
        boolean canUserBorrow = canBorrow();

        if (!canUserBorrow) {
            return "You have already borrowed 2 books. Return a book first to borrow another one.";
        }
        
        // now that i have assured that this user can borrow a new book do the borrowing
        int copies = book.getCopiesAvailable();
        if (copies > 0) {
            book.setCopiesAvailable(copies - 1);
            Borrowed newBorrow = new Borrowed(book, this);
            this.borrowHistory.add(newBorrow);
            App.addActiveBorrow(newBorrow);
            return "The book was borrowed successfully.";
        }
        else {
            return "The book has no available copies.";
        }
    }

    public boolean hasBookBeenBorrowed(Book book){
        for (Borrowed b : this.borrowHistory) {
            if (b.getBorrowedBook().getISBN() == book.getISBN()) {
                return true;
            }
        }
        return false;
    }

    
    public String reviewBook(Book book, int rating, String comment) {
        // check if user has actually borrowed the book he is trying to review
        if(hasBookBeenBorrowed(book)){
            return "You have not borrowed this book yet. Please borrow the book before you try to review it.";
        }

        if (rating == 0){
            book.addReview(this, comment);
        }
        else if (comment.isEmpty()){
            book.addReview(this, rating);
        }
        else{
            book.addReview(this, rating, comment);
        }
        
        return "The review has been registered";
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
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

    public List<Borrowed> getborrowHistory() {
        return borrowHistory;
    }
    public void setborrowHistory(List<Borrowed> borrowHistory) {
        this.borrowHistory = borrowHistory;
    }
}
