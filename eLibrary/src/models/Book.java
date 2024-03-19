package models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import exceptions.InvalidBookInfoException;
import exceptions.InvalidDateException;
import exceptions.NotFoundException;
import exceptions.ReviewException;


public class Book implements Serializable{
    private String title;
    private String author;
    private String publisher;
    private String ISBN;
    private LocalDate datePublished;
    private int copiesAvailable;
    private List<Review> reviews;
    private double avgRating;

    /* 
     *  subclass for a book review
     */
    public static class Review implements Serializable {
        private String username;
        private int rating;
        private String comment;
    
        public Review(String username, int rating, String comment) throws ReviewException {
            // check if rating is valid before construction the object
            if ( !(rating >= 1 && rating <= 5) ) {
                throw new ReviewException("The rating should be between 1 and 5");
            }

            this.username = username;
            this.rating = rating;
            this.comment = comment;
        }
    
        /* 
         *  setters - getters - add - remove
         */
        public int getRating() {
            return rating;
        }
        // check if rating is valid
        public void setRating(int rating) throws ReviewException {
            if ( !(rating >= 1 && rating <= 5) ) {
                throw new ReviewException("The rating should be between 1 and 5");
            }

            this.rating = rating;
        }
    
        public String getComment() {
            return comment;
        }
        public void setComment(String comment) {
            this.comment = comment;
        }
    
        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }
    }


    /* 
     *  Book Constructor
     */
    public Book(String title, String author, String publisher, String ISBN, LocalDate datePublished, int copiesAvailable) throws InvalidBookInfoException, InvalidDateException {
        // check if isbn available - must be unique for each book - isbn = id of the book
        try {
            Library.findBook(ISBN);
            throw new InvalidBookInfoException("This ISBN already exists. Please provide a new unique ISBN.");
        }
        catch (NotFoundException e) {}

        // date published cannot be in the future 
        if (datePublished.isAfter(java.time.LocalDate.now())) {
            throw new InvalidDateException("A Book that already exists in the library cannot be published in a future Date.");
        }

        if (copiesAvailable < 0) {
            throw new InvalidBookInfoException("The available copies of a book cannot be a negative number.");
        }

        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.ISBN = ISBN;
        this.datePublished = datePublished;
        this.copiesAvailable = copiesAvailable;
        this.reviews = new ArrayList<Review>();
        this.avgRating = 0.0;
    }

    /* 
     *  update the existing average rating
     *  called when a new review is added
     */
    public void updateAvgRating() {
        // if there are no reviews for the book yet
        // int count will remain zero and throw an ArithmetcException so i handle that
        try {
            int count = 0;
            int sum = 0;
            for (Review rev : this.reviews) {
                sum += rev.rating;
                count++;
            }

            double doubleSum = (double) sum;
            double doubleCount = (double) count;

            double avg = (doubleSum/doubleCount);
            double avgRounded = Math.round(avg * 100.0) / 100.0;
            
            this.avgRating = avgRounded ;
        }
        catch(ArithmeticException e) {
            // IF ONLY DIVISION BY ZERO
            if ( (e.getMessage()).contains("/ by zero") ) {
                this.avgRating = 0.0;
            }
            else {
                e.printStackTrace();
            }
        }
          
    }

    /* 
     *  adds a review
     */
    public void addReview(String username, int rating, String comment) throws ReviewException {
        // if this user has already reviewed that book change the existing review
        for (Review rev: this.reviews) {
            if ( (rev.username).equals(username) ) {
                rev.setComment(comment);
                rev.setRating(rating);
                updateAvgRating();
                return;
            }
        }

        // else create new review - add it to the review list
        Review newReview = new Review(username, rating, comment);
        this.reviews.add(newReview);
        updateAvgRating();    
    }

    /* 
     *  deletes all the reviews of a specific user 
     *  called when we Delete User
     */
    public void deleteReviewsOfUser(String username) {
        List<Review> reviewToDelete = new ArrayList<>();

        for (Review rev : this.reviews) {
            if ( (rev.username).equals(username) ) {
                reviewToDelete.add(rev);
            }
        }

        for (Review rev : reviewToDelete) {
            this.reviews.remove(rev);
        }

    }

    /* 
     *  changes the username of a specific user in all the reviews of this book
     *  called when we change the username of this specific User
     */
    public void changeReviewsUsername(String oldUsername, String newUsername) {
        for (Review rev : this.reviews) {
            if ( (rev.username).equals(oldUsername) ) {
                rev.setUsername(newUsername);
            }
        }
    }

    
    /* 
     *  setters - getters - add - remove
     */
    public Integer getPublicationYear() {
        return this.datePublished.getYear();
    }
    public LocalDate getDatePublished() {
        return datePublished;
    }
    // make sure the new publishing date is not in the future
    public void setDatePublished(LocalDate datePublished) throws InvalidDateException {
        if (datePublished.isAfter(java.time.LocalDate.now())) {
            throw new InvalidDateException("A Book that already exists in the library cannot be published in a future Date.");
        }

        this.datePublished = datePublished;
    }

    public String getISBN() {
        return ISBN;
    }
    // make sure the new ISBN is unique
    public void setISBN(String isbn) throws InvalidBookInfoException {
        if (isbn.equals(this.ISBN)) {
            return;
        }

        try {
            Library.findBook(isbn);
            throw new InvalidBookInfoException("This ISBN already exists. Please provide a new unique ISBN.");
        }
        catch (NotFoundException e) {}

        this.ISBN = isbn;
    }
 
    public int getCopiesAvailable() {
        return copiesAvailable;
    }
    // make sure the new available copies are not negative
    public void setCopiesAvailable(int copiesAvailable) throws InvalidBookInfoException {
        if (copiesAvailable < 0) {
            throw new InvalidBookInfoException("The available copies of a book cannot be a negative number.");
        }

        this.copiesAvailable = copiesAvailable;
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
