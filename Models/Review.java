import java.util.ArrayList;
import java.util.List;

public class Review {
    private String text;
    private User reviewer;
    private Movie movie;

    public Review(String text, User reviewer, Movie movie) {
        this.text = text;
        this.reviewer = reviewer;
        this.movie = movie;
    }

    // Getters and setters

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}