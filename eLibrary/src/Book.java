import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private String author;
    private String publisher;
    private String summary;
    private String ISBN;
    private LocalDate datePublished;
    private String category;
    private int copiesAvailable;
    private List<Review> reviews;
    private double avgRating;


    // class for a book review
    public class Review {
        private User user;
        private int rating;
        private String comment;
    
        public Review(User user, int rating, String comment){
            this.user = user;
            this.rating = rating;
            this.comment = comment;
        }
    
    
        public String getComment() {
            return comment;
        }
        public void setComment(String comment) {
            this.comment = comment;
        }
    
        public int getRating() {
            return rating;
        }
        public void setRating(int rating) {
            try{
                if (rating > 5 || rating < 1) {
                    throw new Exception("The rating should be between 1 and 5");
                }
    
                this.rating = rating;
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    
        public User getUser() {
            return user;
        }
        public void setUser(User user) {
            this.user = user;
        }
    }


    public Book(String title, String author, String publisher, String summary, String ISBN, LocalDate datePublished, String category, int copiesAvailable) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.summary = summary;
        this.ISBN = ISBN;
        this.datePublished = datePublished;
        this.category = category;
        this.copiesAvailable = copiesAvailable;
        this.reviews = new ArrayList<Review>();
        this.avgRating = 0;
    }

    public void updateAvgRating() {
        // check if there are no reviews for the book yet
        if (this.reviews.isEmpty()){
            this.avgRating = 0;
            return;
        }

        int count = 0;
        int sum = 0;
        for (Review rev : this.reviews){
            if (rev.rating != 0){
                sum += rev.rating;
                count++;
            }
        }

        // check if all the reviews have only comments and no ratings 
        if (sum == 0){
            this.avgRating = 0;
            return;
        }

        this.avgRating = (sum/count);
        return;
          
    }

    public void addReview(User user, int rating, String comment) {
        for (Borrowed b : user.borrowedBooks) {
            if (b.getBorrowedBook().getISBN() == this.ISBN) {
                // if this user has already reviewed that book change the existing review
                for (Review r: this.reviews) {
                    if (r.user.username == user.username){
                        r.setComment(comment);
                        r.setRating(rating);
                        updateAvgRating();
                        return;
                    }
                }
                // else create new review and add it to the review list
                Review newReview = new Review(user, rating, comment);
                this.reviews.add(newReview);
                updateAvgRating();
                return;
            }
        }
    }

    public void addReview(User user, int rating) {
        for (Borrowed b : user.borrowedBooks) {
            if (b.getBorrowedBook().getISBN() == this.ISBN) {
                // if this user has already reviewed that book change the existing review
                for (Review r: this.reviews) {
                    if (r.user.username == user.username){
                        r.setRating(rating);
                        updateAvgRating();
                        return;
                    }
                }
                // else create new review and add it to the review list
                Review newReview = new Review(user, rating, null);
                this.reviews.add(newReview);
                updateAvgRating();
                return;
            }
        }
    }

    public void addReview(User user, String comment) {
        for (Borrowed b : user.borrowedBooks) {
            if (b.getBorrowedBook().getISBN() == this.ISBN) {
                // if this user has already reviewed that book change the existing review
                for (Review r: this.reviews) {
                    if (r.user.username == user.username){
                        r.setComment(comment);
                        return;
                    }
                }
                // else create new review and add it to the review list
                Review newReview = new Review(user, 0, comment);
                this.reviews.add(newReview);
                return;
            }
        }
    }

    public int getPublicationYear() {
        return this.datePublished.getYear();
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getISBN() {
        return ISBN;
    }
    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }

    public LocalDate getDatePublished() {
        return datePublished;
    }
    public void setDatePublished(LocalDate datePublished) {
        this.datePublished = datePublished;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }
    public void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }

    public List<Review> getReviews() {
        return reviews;
    }
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public double getAvgRating() {
        return avgRating;
    }
    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

}
