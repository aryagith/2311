package Models;

import java.util.List;

public class User {
    private String username;
    private String password;
    private Watchlist watchlist;
    private List<Review> reviews;
    private List<User> friends_list;
    private List<Comment> comments;

    // Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
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
}
