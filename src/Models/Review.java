package Models;

import java.util.*;

public class Review {
    private Rating rating;
    private Movie movie;
    private List<Comment> replies;
    private String title;
    private User user;

    public Review(User user,Movie movie){
        title="";
        rating=new Rating();
        replies=new ArrayList<Comment>();
        this.user=user;
        this.movie=movie;
    }
    public Review(User user,Movie movie,Rating rating,String title){
        this.title=title;
        this.movie=movie;
        this.rating=rating;
        this.user=user;
        this.replies=new ArrayList<Comment>();
    }
    
    public Movie getMovie() {
        return movie;
    }
    public Rating getRating() {
        return rating;
    }
    public String getTitle() {
        return title;
    }
    public List<Comment> getReplies() {
        return replies;
    }
    public User getUser() {
        return user;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    public void setRating(Rating rating) {
        this.rating = rating;
    }
    public void setReplies(List<Comment> replies) {
        this.replies = replies;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void addComment(Comment comment){
       this.replies.add(comment);
    }
}
