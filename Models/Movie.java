
import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String title;
    private int year;
    private double rating;
    private List<Review> reviews;

    public Movie(String title, int year) {
        this.title = title;
        this.year = year;
        this.rating = 0.0; // Initial rating
        this.reviews = new ArrayList<>();
    }

    // Getters and setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    // Other methods such as addReview(), calculateRating(), etc.
}