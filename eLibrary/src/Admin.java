import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Admin extends User{

    public Admin(String username, String password, String firstName, String lastName, String idNum, String email, String address, LocalDate birthDate) {
        super(username, password, firstName, lastName, idNum, email, address, birthDate);
    }


    public void createBook(String title, String author, String publisher, String summary, String ISBN, LocalDate datePublished, int copiesAvailable, String categoryName) {
        Book newBook = new Book(title, author, publisher, summary, ISBN, datePublished, copiesAvailable);   
        App.addBook(newBook);

        // all books must be in some category
        addBookToCategory(newBook, categoryName);
    }

    public void addBookToCategory(Book book, String categoryName) {
        List<Category> categories = App.getAllCategories();

        for (Category cat : categories) {
            if ( (cat.getCategoryName()).equals(categoryName) ) {
                cat.addToCategoryBooks(book);
                return;
            }
        }

        // if it does not exist create it first and call the method again
        createCategory(categoryName);
        addBookToCategory(book, categoryName);
        return;
    }

    public void createCategory(String categoryName) {
        Category newCat = new Category(categoryName);
        App.addCategory(newCat);
    }

    public void deleteCategory(Category category) {
        App.removeCategory(category);
        for (Book book : category.getCategoryBooks()) {
            deleteBook(book);
        }
    }

    // I assume the category given actually exists !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void changeCategoryName(String categoryName, String newCategoryName) {
        List<Category> categories = App.getAllCategories();

        for (Category cat : categories) {
            if ( (cat.getCategoryName()).equals(categoryName) ) {
                cat.setCategoryName(newCategoryName);
                return;
            }
        }
    }

    /*
     *  what do i get BookISBN or Book ??????????????????????????????????????????????????????
     *  I assume the frontend gives the object to be removed
     */
    public void deleteBook(Book bookToDelete) {
        // delete all borrows that has not been returned
        for (Borrowed bor : App.getAllActiveBorrows()) {
            if( (bor.getBorrowedBook()).equals(bookToDelete) ) {
                // remove active borrow from user borrowsNow list
                bor.getBorrower().removeBorrowsNow(bor);
                // remove active borrow from the App's active borrow list
                App.removeActiveBorrow(bor);
            }
        }

        //delete book from all histories - if user has not borrowed book nothing will happen
        // preserve history  ?????????????????????????????????????????????????????????????????????????????
        for (User user : App.getAllUsers()) {
            user.removeBorrowHistory(bookToDelete);
        }

        // delete book
        App.removeBook(bookToDelete);        
    }

    public void deleteUser(User user) {
        // terminate all borrows of user 
        // the active borrow list AND the available copies of books will be fixed
        for (Borrowed bor : user.getBorrowsNow()) {
            terminateBorrow(bor);
        }

        // delete user
        App.removeUser(user);;        
    }

    public void terminateBorrow(Borrowed borrow) {
        User user = borrow.getBorrower();
        Book book = borrow.getBorrowedBook();

        // fix user's borrow and history lists - no duplicates allowed in history list
        user.removeBorrowsNow(borrow);
        if ( !user.containsInBorrowHistory(book) ) {
            user.addBorrowHistory(book);
        }

        //remove from app's active borrows
        App.removeActiveBorrow(borrow);

        //fix copies of book
        int currCopies = book.getCopiesAvailable();
        book.setCopiesAvailable(currCopies + 1);
    }

    public List<Borrowed> watchActiveBorrows() {
        return App.getAllActiveBorrows();
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

    public void changeBookSummary(Book book, String summary) {
        book.setSummary(summary);
    }

    public void changeBookISBN(Book book, String isbn) {
        book.setISBN(isbn);
    }

    public void changeBookDatePublished(Book book, LocalDate date) {
        book.setDatePublished(date);
    }

    public void changeBookCopies(Book book, int copies) {
        book.setCopiesAvailable(copies);
    }


    // modify User information
    // NOT BORROW HISTORY OR BORROW NOW LISTS
    public void changeUserUsername(User user, String username) {
        user.setUsername(username);
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
