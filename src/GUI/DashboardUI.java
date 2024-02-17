package GUI;

import Models.User;
import Models.Movie;
import Services.MovieManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class DashboardUI extends JFrame {
    private final User user;
    private final MovieManager movieManager;
    private final JPanel contentPanel;

    public DashboardUI(User user, MovieManager movieManager) {
        this.user = user;
        this.movieManager = movieManager;

        setTitle("Dashboard");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel welcomeLabel = new JLabel("Welcome to the Dashboard, " + user.getUsername() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(welcomeLabel);

        contentPanel = new JPanel(new BorderLayout());

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                try {
                    List<Movie> searchResults = movieManager.searchMovies(searchText);
                    displaySearchResults(searchResults);
                } catch (IOException ex) {
                    ex.printStackTrace(); // Handle exception appropriately
                }
            }
        });
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        JPanel recentlyViewedPanel = new JPanel(new BorderLayout());
        JLabel recentlyViewedLabel = new JLabel("Recently Viewed Movies:");
        recentlyViewedLabel.setFont(new Font("Arial", Font.BOLD, 16));
        recentlyViewedPanel.add(recentlyViewedLabel, BorderLayout.NORTH);
        JPanel recentlyViewedMoviesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for (Movie movie : user.getRecentlyViewed()) {
            JButton movieButton = new JButton(movie.getTitle());
            movieButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(DashboardUI.this, "You clicked on " + movie.getTitle());
                }
            });
            recentlyViewedMoviesPanel.add(movieButton);
        }
        recentlyViewedPanel.add(recentlyViewedMoviesPanel, BorderLayout.CENTER);

        JPanel friendsPanel = new JPanel(new BorderLayout());
        JLabel friendsLabel = new JLabel("Friends:");
        friendsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        friendsPanel.add(friendsLabel, BorderLayout.NORTH);
        JPanel friendsListPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for (User friend : user.getFriendsList()) {
            JButton friendButton = new JButton(friend.getUsername());
            friendButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(DashboardUI.this, "You clicked on " + friend.getUsername());
                }
            });
            friendsListPanel.add(friendButton);
        }
        friendsPanel.add(friendsListPanel, BorderLayout.CENTER);

        contentPanel.add(searchPanel, BorderLayout.NORTH);
        contentPanel.add(recentlyViewedPanel, BorderLayout.CENTER);
        contentPanel.add(friendsPanel, BorderLayout.SOUTH);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel);
        pack();
        setVisible(true);
    }

    private void displaySearchResults(List<Movie> searchResults) {
        JPanel searchResultsPanel = new JPanel(new GridLayout(searchResults.size(), 1));
        for (Movie movie : searchResults) {
            JPanel moviePanel = new JPanel(new BorderLayout());
            JLabel titleLabel = new JLabel("Title: " + movie.getTitle());
            JLabel yearLabel = new JLabel("Release Year: " + movie.getReleaseYear());
            JLabel descriptionLabel = new JLabel("Description: " + movie.getDescription());
            JLabel ratingLabel = new JLabel("Rating: " + movie.getRating());
            JLabel coverImageUrlLabel = new JLabel("Cover Image URL: " + movie.getCoverImageUrl());

            moviePanel.add(titleLabel, BorderLayout.NORTH);
            moviePanel.add(yearLabel, BorderLayout.CENTER);
            moviePanel.add(descriptionLabel, BorderLayout.CENTER);
            moviePanel.add(ratingLabel, BorderLayout.CENTER);
            moviePanel.add(coverImageUrlLabel, BorderLayout.CENTER);

            searchResultsPanel.add(moviePanel);
        }

        // Remove the old content panel and add the new search results panel
        contentPanel.removeAll(); // Clear existing content
        contentPanel.add(searchResultsPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}
