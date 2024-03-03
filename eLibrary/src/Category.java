import java.util.List;
import java.util.ArrayList;

public class Category {
    private String categoryName;
    private List<Book> categoryBooks;

    public Category(String name){
        this.categoryName = name;
        this.categoryBooks = new ArrayList<Book>();
    }

    public void addToCategoryBooks(Book book){
        this.categoryBooks.add(book);
    }

    public void removeFromCategoryBooks(Book book){
        this.categoryBooks.remove(book);
    }


    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Book> getCategoryBooks() {
        return categoryBooks;
    }
    public void setCategoryBooks(List<Book> categoryBooks) {
        this.categoryBooks = categoryBooks;
    }

}
