import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private List<Movie> ratedMovies;
    private List<Review> reviews;
    private List<User> friends;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.ratedMovies = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.friends = new ArrayList<>();
    }

    // Getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public List<User> getFriends() {
        return friends;
    }

    // Other methods such as addFriend(), addReview(), etc.
}