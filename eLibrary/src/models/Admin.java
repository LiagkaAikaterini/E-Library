package models;

import java.time.LocalDate;
import java.util.List;


public class Admin extends UserBase {

    public Admin(String username, String password) {
        super(username, password, true);
    }


    public void createBook(String title, String author, String publisher, String summary, String ISBN, LocalDate datePublished, int copiesAvailable, String categoryName) {
        Book newBook = new Book(title, author, publisher, ISBN, datePublished, copiesAvailable);   
        Library.addBook(newBook);

        // all books must be in some category
        addBookToCategory(newBook, categoryName);
    }


    public void addBookToCategory(Book book, String categoryName) {
        try {
            Category targetCategory = Library.findCategory(categoryName);

            targetCategory.addToCategoryBooks(book.getISBN());
        }        
        catch(NullPointerException n){
            // if it does not exist create it first and call the method again
            createCategory(categoryName);
            addBookToCategory(book, categoryName);
        }
    }

    public void createCategory(String categoryName) {
            // if category does not already exists
            if ( Library.findCategory(categoryName) == null ) {
                Category newCat = new Category(categoryName);
                Library.addCategory(newCat);
            }
    }

    public void deleteCategory(Category category) {
        try{
            for (String isbn : category.getBooksISBN()) {
                Book book = Library.findBook(isbn);
                deleteBook(book);
            }

            Library.removeCategory(category);
        }
        catch (NullPointerException e) {
            // category does not exist
        }
    }

    // I assume the category given actually exists !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void changeCategoryName(String categoryName, String newCategoryName) {
        try{
            Category category = Library.findCategory(categoryName);
            
            category.setName(newCategoryName);        
        }
        catch(NullPointerException e) {
            createCategory(newCategoryName);
        }
    }

    /*
     *  what do i get BookISBN or Book ??????????????????????????????????????????????????????
     *  I assume the frontend gives the object to be removed
     */
    public Book findBook(String Isbn){
        List<Book> books = Library.getAllBooks();
        
        for (Book book : books) {
            if (book.getISBN().equals(Isbn)) {
                return book;
            }
        }

        return null;
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

        //remove all reviews of User 
        List<Book> books = Library.getAllBooks();

        for (Book book : books) {
            book.deleteReviewsOfUser(userToDelete.getUsername());
        }

        // delete user
        Library.removeUser(userToDelete);       
    }

    public void terminateBorrow(Borrowed borrow) {
        Book book = Library.findBook( borrow.getBookISBN() );

        //remove from app's active borrows
        Library.removeActiveBorrow(borrow);

        //fix copies of book
        int currCopies = book.getCopiesAvailable();
        book.setCopiesAvailable(currCopies + 1);
    }

    public List<Borrowed> watchActiveBorrows() {
        return Library.getAllActiveBorrows();
    }

    /* ??????????????????????????????????????????????????????????
    *  the histories and borrows will change automatically -------- CHECK
    *  modify book information
    */

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

    public void changeBookISBN(Book book, String newISBN) throws Exception {
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
            user.removeBorrowHistory(oldISBN);
            user.addBorrowHistory(newISBN);
        }

    }

    public void changeBookDatePublished(Book book, LocalDate date) {
        book.setDatePublished(date);
    }

    public void changeBookCopies(Book book, int copies) {
        book.setCopiesAvailable(copies);
    }


    // modify User information
    // NOT BORROW HISTORY LISTS
    public void changeUserUsername(User user, String newUsername) {
        try {
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
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeUserPassword(User user, String password) {
        user.setPassword(password);
    }

    public void changeUserFirstname(User user, String firstname) {
        user.setFirstName(firstname);
    }

    public void changeUserLastname(User user, String lastname) {
        user.setLastName(lastname);
    }

    public void changeUserIdNum(User user, String id) {
        user.setIdNum(id);
    }

    public void changeUserEmail(User user, String email) {
        user.setEmail(email);
    }

    public void changeUserAddress(User user, String address) {
        user.setAddress(address);
    }

    public void changeUserBirthday(User user, LocalDate date) {
        user.setBirthDate(date);
    }

}
