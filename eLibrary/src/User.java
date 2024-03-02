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

    User(String username, String password, String firstName, String lastName, String idNum, String email, String address, String birthDate) {
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

    
    void reviewBook(Book book, int rating, String comment) {
        book.addReview(this, rating, comment);
    }

    void reviewBook(Book book, int rating) {
        book.addReview(this, rating);
    }

    void reviewBook(Book book, String comment) {
        book.addReview(this, comment);
    }


}
