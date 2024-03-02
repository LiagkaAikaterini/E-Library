import java.util.ArrayList;
import java.util.List;

public class User {
    String username;
    String password;
    String firstName;
    String lastName;
    String idNum;
    String email;
    String address;
    String birthDate;
    List<Borrowed> borrowedBooks;

    public User(String username, String password, String firstName, String lastName, String idNum, String email, String address, String birthDate) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNum = idNum;
        this.email = email;
        this.address = address;
        this.birthDate = birthDate;
        borrowedBooks = new ArrayList<Borrowed>();
    }


    public boolean canBorrow() {
        // check if you can borrow anymore books
        int borrows_allowed = 2;
        for (Borrowed b : this.borrowedBooks) {
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
            this.borrowedBooks.add(newBorrow);
            return "The book was borrowed successfully.";
        }
        else {
            return "The book has no available copies.";
        }
    }

    
    public void reviewBook(Book book, int rating, String comment) {
        book.addReview(this, rating, comment);
    }

    public void reviewBook(Book book, int rating) {
        book.addReview(this, rating);
    }

    public void reviewBook(Book book, String comment) {
        book.addReview(this, comment);
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

    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public List<Borrowed> getBorrowedBooks() {
        return borrowedBooks;
    }
    public void setBorrowedBooks(List<Borrowed> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
