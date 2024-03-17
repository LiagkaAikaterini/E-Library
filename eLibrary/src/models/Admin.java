package models;

import java.time.LocalDate;
import java.util.List;
import exceptions.InvalidBookInfoException;
import exceptions.InvalidDateException;
import exceptions.InvalidUserInfoException;
import exceptions.NotFoundException;


public class Admin extends UserBase {

    public Admin(String username, String password) throws InvalidUserInfoException {
        super(username, password, true);
    }


    public void createBook(String title, String author, String publisher, String ISBN, LocalDate datePublished, int copiesAvailable, String categoryName) throws InvalidBookInfoException, InvalidDateException, NotFoundException {
        // all books must be in some category
        // if category does not exist an exception will be thrown and the book will not be created
        addBookToCategory(ISBN, categoryName);
        
        Book newBook = new Book(title, author, publisher, ISBN, datePublished, copiesAvailable);   
        Library.addBook(newBook);

        
    }


    public void addBookToCategory(String bookISBN, String categoryName) throws NotFoundException {
        try {
            // the book can be in one category only 
            // remove from previous category if the book is not newly created
            Category cat = Library.categoryOfBook(bookISBN);
            cat.removeFromCategoryBooks(bookISBN);
        }
        catch (NotFoundException e) {}

        
        // find the category we want to put the book in - if it does not exist throw NotFoundException
        Category targetCategory = Library.findCategory(categoryName);
        targetCategory.addToCategoryBooks(bookISBN);
        
    }

    public void createCategory(String categoryName) {
        // if category does not already exists create category
        try { 
            Library.findCategory(categoryName);
        }
        catch (NotFoundException e) {
            Category newCat = new Category(categoryName);
            Library.addCategory(newCat);
        }
    }

    public void deleteCategory(Category category) {
        String currISBN = null;
        try{   
            for (String isbn : category.getBooksISBN()) {
                currISBN = isbn;
                Book book = Library.findBook(isbn);
                deleteBook(book);
            }

            Library.removeCategory(category);
        }
        catch (NotFoundException e) {
            // Book not found
            category.removeFromCategoryBooks(currISBN);
            deleteCategory(category);
        }  
    }

    public void changeCategoryName(Category category, String newCategoryName) {
        String oldName = category.getName();

        if ( oldName.equals(newCategoryName) ) {
            return;
        }

        try { 
            Library.findCategory(newCategoryName);
        }
        catch (NotFoundException e) {
            // if a category with this name does not already exist
            category.setName(newCategoryName);
        }
        
    }

    public void deleteBook(Book bookToDelete) {
        // delete all borrows that has not been returned
        for (Borrowed bor : Library.getAllActiveBorrows()) {
            if( (bor.getBookISBN()).equals(bookToDelete.getISBN()) ) {
                // remove active borrow from the App's active borrow list
                Library.removeActiveBorrow(bor);
            }
        }

        //delete book from all histories - if user has not borrowed book nothing will happen
        for (User user : Library.getAllUsers()) {
            user.removeBorrowHistory(bookToDelete.getISBN());
        }

        // delete book
        Library.removeBook(bookToDelete);
    }
    

    public void deleteUser(User userToDelete) {
        // terminate all current borrows of user - book copies fixed
        List<Borrowed> activeBorrows = Library.getAllActiveBorrows();

        for (Borrowed borrow : activeBorrows) {
            if ( (borrow.getUsername()).equals(userToDelete.getUsername()) ) {
                terminateBorrow(borrow);
            }
        }

        //remove all reviews of this User 
        List<Book> books = Library.getAllBooks();

        for (Book book : books) {
            book.deleteReviewsOfUser(userToDelete.getUsername());
        }

        // delete user
        Library.removeUser(userToDelete);       
    }

    public void terminateBorrow(Borrowed borrow) {
        try {
            Book book = Library.findBook( borrow.getBookISBN() );

            //remove from app's active borrows
            Library.removeActiveBorrow(borrow);

            //fix copies of book
            int currCopies = book.getCopiesAvailable();
            book.setCopiesAvailable(currCopies + 1);
        }
        catch(NotFoundException e) {
            // the book of the borrow was not found in the library, so we just remove the borrow for ActiveBorrows without worrying about the copies
            Library.removeActiveBorrow(borrow);
        }
        catch (InvalidBookInfoException e) {
            // this is never thrown as we add book copies so the available copies cannot get negative here
        }
    }

    public List<Borrowed> watchActiveBorrows() {
        return Library.getAllActiveBorrows();
    }

    // NOT REVIEWS AND AVG RATING
    public void changeBookTitle(Book book, String title) {
        book.setTitle(title);
    }

    public void changeBookAuthor(Book book, String author) {
        book.setAuthor(author);
    }

    public void changeBookPublisher(Book book, String publisher) {
        book.setPublisher(publisher);
    }

    public void changeBookISBN(Book book, String newISBN) throws InvalidBookInfoException {
        String oldISBN = book.getISBN();

        // change isbn - if not unique isbn -> exception will be thrown here -> the following will not execute
        book.setISBN(newISBN);

        // isbn is the books identifier - changes must be made in every place the book is referenced 
        // change isbn in active borrows
        for (Borrowed borrow : Library.getAllActiveBorrows()) {
            if ( oldISBN.equals(borrow.getBookISBN()) ) {
                borrow.setBookISBN(newISBN);
            }
        }

        // change isbn in borrow history of all users that have borrowed the book - no duplicated in borrow history
        for (User user : Library.getAllUsers()) {
            if ( user.getBorrowHistory().contains(oldISBN) ) {
                user.removeBorrowHistory(oldISBN);
                user.addBorrowHistory(newISBN);
            }
        }

    }

    public void changeBookDatePublished(Book book, LocalDate date) throws InvalidDateException {
        book.setDatePublished(date);
    }

    public void changeBookCopies(Book book, int copies) throws InvalidBookInfoException {
        book.setCopiesAvailable(copies);
    }


    // modify User information - NOT PASSWORD
    // NOT BORROW HISTORY LISTS
    public void changeUserUsername(User user, String newUsername) throws InvalidUserInfoException {
        
            String oldUsername = user.getUsername();

            // change username - if invalid username -> exception will be thrown here -> the following will not execute
            user.setUsername(newUsername);

            // username is used as User id so we need to fix the references to this user
            // change username in Active borrows 
            for (Borrowed borrow : Library.getAllActiveBorrows()) {
                if ( oldUsername.equals(borrow.getUsername()) ) {
                    borrow.setUsername(newUsername);
                }
            }

            // change username in reviews
            for (Book book : Library.getAllBooks()) {
                book.changeReviewsUsername(oldUsername, newUsername);
            }

    }

    public void changeUserFirstname(User user, String firstname) {
        user.setFirstName(firstname);
    }

    public void changeUserLastname(User user, String lastname) {
        user.setLastName(lastname);
    }

    public void changeUserIdNum(User user, String id) throws InvalidUserInfoException {
        user.setIdNum(id);
    }

    public void changeUserEmail(User user, String email) throws InvalidUserInfoException {
        user.setEmail(email);
    }

    public void changeUserAddress(User user, String address) {
        user.setAddress(address);
    }

    public void changeUserBirthday(User user, LocalDate date) throws InvalidDateException {
        user.setBirthDate(date);
    }

}
