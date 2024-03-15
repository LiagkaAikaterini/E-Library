package models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Book implements Serializable{
    private String title;
    private String author;
    private String publisher;
    private String ISBN;
    private LocalDate datePublished;
    private int copiesAvailable;
    private List<Review> reviews;
    private double avgRating;


    // class for a book review
    public static class Review {
        private String username;
        private int rating;
        private String comment;
    
        public Review(String username, int rating, String comment) throws Exception {
            if ( !(rating >= 1 && rating <= 5) ) {
                throw new Exception("The rating should be between 1 and 5");
            }

            this.username = username;
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
            try {
                if ( !(rating >= 1 && rating <= 5) ) {
                    throw new Exception("The rating should be between 1 and 5");
                }
    
                this.rating = rating;
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    
        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }
    }


    public Book(String title, String author, String publisher, String ISBN, LocalDate datePublished, int copiesAvailable) throws Exception {
        // check if isbn available - unique
        if (Library.findBook(ISBN) != null) {
            throw new Exception("Not unique isbn - book already exists with this isbn");
        }

        // date published cannot be in the future 
        if (datePublished.isAfter(java.time.LocalDate.now())) {
            throw new Exception("Future Date");
        }

        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.ISBN = ISBN;
        this.datePublished = datePublished;
        this.copiesAvailable = copiesAvailable;
        this.reviews = new ArrayList<Review>();
        this.avgRating = 0;
    }

    public void updateAvgRating() {
        // if there are no reviews for the book yet
        // if all the reviews have only comments and no ratings
        // int count will remain zero throw an ArithmetcException so i handle that 
        
        try {
            /*
            if (this.reviews.isEmpty()) {
                this.avgRating = 0;
                return;
            }
            */
            int count = 0;
            int sum = 0;
            for (Review rev : this.reviews) {
                //if (rev.rating != 0) {
                    sum += rev.rating;
                    count++;
                //}
            }

            /* check if all the reviews have only comments and no ratings 
            if (sum == 0) {
                this.avgRating = 0;
                return;
            }
            */

            this.avgRating = (sum/count);
        }
        catch(ArithmeticException e) {
            // IF ONLY DIVISION BY ZERO - DO NOT KNOW 
            if ( (e.getMessage()).contains("/ by zero") ) {
                this.avgRating = 0;
            }
            else {
                //throw e;
                e.printStackTrace();
            }
        }
          
    }

    public boolean addReview(String username, int rating, String comment) {
        try {

            // if this user has already reviewed that book change the existing review
            for (Review rev: this.reviews) {
                if ( (rev.username).equals(username) ) {
                    rev.setComment(comment);
                    rev.setRating(rating);
                    updateAvgRating();
                    return true;
                }
            }

            // else create new review - add it to the review list
            
            Review newReview = new Review(username, rating, comment);
            this.reviews.add(newReview);
            updateAvgRating();
            return true;
        } 
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean addReview(String username, int rating) {
        try {

            // if this user has already reviewed that book change the existing review
            for (Review rev: this.reviews) {
                if ( (rev.username).equals(username) ) {
                    rev.setRating(rating);
                    updateAvgRating();
                    return true;
                }
            }
            // else create new review and add it to the review list
            Review newReview = new Review(username, rating, "");
            this.reviews.add(newReview);
            updateAvgRating();
            return true;
            
        }
        catch (Exception e) {
            return false;
        }
                    
    }

    /*
    public boolean addReview(String username, String comment) {
        // if this user has already reviewed that book change the existing review
        try {    
            for (Review rev: this.reviews) {
                if ( (rev.username).equals(username) ) {
                    rev.setComment(comment);
                    return true;
                }
            }
            // else create new review and add it to the review list
            Review newReview = new Review(username, 0, comment);
            this.reviews.add(newReview);
            return true;
        }
        catch(Exception e){
            return false;
        }
            
    }
 */

    public void deleteReviewsOfUser(String username) {
        for (Review rev : this.reviews){
            if ( (rev.getUsername()).equals(username) ) {
                this.reviews.remove(rev);
            }
        }
    }

    public void changeReviewsUsername(String oldUsername, String newUsername) {
        for (Review rev : this.reviews){
            if ( (rev.getUsername()).equals(oldUsername) ) {
                rev.setUsername(newUsername);
            }
        }
    }

    public Integer getPublicationYear() {
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

    public String getISBN() {
        return ISBN;
    }
    public void setISBN(String iSBN) throws Exception{
        if (iSBN.equals(this.ISBN)) {
            return;
        }

        if ( Library.findBook(iSBN) != null ) {
            throw new Exception("This isbn is not available. Please enter unique isbn.");
        }

        this.ISBN = iSBN;
    }

    public LocalDate getDatePublished() {
        return datePublished;
    }
    public void setDatePublished(LocalDate datePublished) throws Exception {
        if (datePublished.isAfter(java.time.LocalDate.now())) {
            throw new Exception("Future Date");
        }
        this.datePublished = datePublished;
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
