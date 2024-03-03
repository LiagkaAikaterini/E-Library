import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.binding.StringBinding;

public class Admin extends User{

    public Admin(String username, String password, String firstName, String lastName, String idNum, String email, String address, LocalDate birthDate) {
        super(username, password, firstName, lastName, idNum, email, address, birthDate);
    }


    public void createBook(String title, String author, String publisher, String summary, String ISBN, LocalDate datePublished, int copiesAvailable, String categoryName){
        Book newBook = new Book(title, author, publisher, summary, ISBN, datePublished, copiesAvailable);   
        App.addBook(newBook);

        // all books must be in some category
        addBookToCategory(newBook, categoryName);
    }

    public void addBookToCategory(Book book, String categoryName){
        List<Category> categories = App.getAllCategories();

        for (Category cat : categories){
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

    public void createCategory(String categoryName){
        Category newCat = new Category(categoryName);
        App.addCategory(newCat);
    }

    public void deleteCategory(Category category){
        App.removeCategory(category);
        for (Book book : category.getCategoryBooks()){
            deleteBook(book);
        }
    }

    // I assume the category given actually exists !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void changeCategoryName(String categoryName, String newCategoryName){
        List<Category> categories = App.getAllCategories();

        for (Category cat : categories){
            if ( (cat.getCategoryName()).equals(categoryName) ) {
                cat.setCategoryName(newCategoryName);
                return;
            }
        }
    }

    /*
     *  what do i get BookISBN or Book ??????????????????????????????????????????????????????
     * I assume the frontend gives the obgect to be removed
     */
    public void deleteBook(Book bookToDelete){
        // delete all borrows that has not been returned
        for (Borrowed bor : App.getAllActiveBorrows()){
            if( (bor.getBorrowedBook()).equals(bookToDelete) ) {
                // remove active borrow from user borrow history
                bor.getBorrower().getBorrowsNow().remove(bor);
                // remove active borrow from active borrow list
                App.removeActiveBorrow(bor);
            }
        }

        // delete book
        App.removeBook(bookToDelete);
        
        // -------------- THIS WILL BE USED IF I TAKE ONLY ISBN STRING FROM FRONTEND ----------------------
        /*
        List<Book> books = App.getAllBooks();
        // delete the book from the list
        for (Book book : books){
            if ( (book.getISBN()).equals(bookToDeleteISBN) ){
                books.remove(bookToDelete);
            }
        }
        */


        
    }

    /*
     *  ----------------------------------- INCOMPLETE -----------------------------------------------
     * 
     *  MAYBE BORROWLIST CAN BE MODIFIED ELSEWHERE IN THE APP AND NOT HERE ???????????????????????????????????????????????
     */
    public void deleteActiveBorrow(Borrowed borrow){
        for(Borrowed bor : App.getAllActiveBorrows()){
            //???????????????????????????????????? should i delete from borrow history of users
            
        }
    }

    public void terminateBorrow(Borrowed borrow){
        
    }

    public List<Borrowed> watchActiveBorrows(){
        return App.getAllActiveBorrows();
    }

    public void changeBookTitle(Book book, String title){
        book.setTitle(title);
    }

}
