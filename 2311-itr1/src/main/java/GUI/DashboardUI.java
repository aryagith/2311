package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;
import Services.MovieManager;

import Models.*;
import org.json.JSONException;

public class DashboardUI extends JFrame {
    private final User user;
    private final MovieManager movieManager;
    private final JPanel contentPanel;

    public DashboardUI(User user, MovieManager movieManager) {
        this.user = user;
        this.movieManager = movieManager;

        setTitle("Dashboard");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        setLocationRelativeTo(null);

        contentPanel = new JPanel(new BorderLayout());
        JPanel headerPanel = createHeaderPanel();
        JPanel searchPanel = createSearchPanel();
        JPanel recentlyViewedPanel = createRecentlyViewedPanel();
        JPanel friendsPanel = createFriendsPanel();

        contentPanel.add(headerPanel, BorderLayout.NORTH);
        contentPanel.add(searchPanel, BorderLayout.CENTER);
        contentPanel.add(recentlyViewedPanel, BorderLayout.WEST);
        contentPanel.add(friendsPanel, BorderLayout.EAST);

        add(contentPanel);
        pack();
        setVisible(true);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel welcomeLabel = new JLabel("Welcome to the Dashboard, " + user.getUsername() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(welcomeLabel);
        return headerPanel;
    }

    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                try {
                    searchMovies(searchText);
                } catch (JSONException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        return searchPanel;
    }

    private void searchMovies(String searchText) throws JSONException {
        try {
            List<Movie> searchResults = movieManager.searchMovies(searchText);
            BrowseMovieUI browseMovieUI = new BrowseMovieUI(this.user);
            browseMovieUI.displaySearchResults(searchResults);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error searching for movies: " + ex.getMessage(),
                    "Search Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel createRecentlyViewedPanel() {
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
        return recentlyViewedPanel;
    }

    private JPanel createFriendsPanel() {
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
        return friendsPanel;
    }
}
