package GUI;

import Models.User;
import Models.Movie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardUI extends JFrame {
    private final User user;

    public DashboardUI(User user) {
        this.user = user;

        setTitle("Dashboard");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel welcomeLabel = new JLabel("Welcome to the Dashboard, " + user.getUsername() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(welcomeLabel);

        JPanel contentPanel = new JPanel(new GridLayout(2, 1, 10, 10));

        JPanel recentlyViewedPanel = new JPanel(new BorderLayout());
        JLabel recentlyViewedLabel = new JLabel("Recently Viewed Movies:");
        recentlyViewedLabel.setFont(new Font("Arial", Font.BOLD, 16));
        recentlyViewedPanel.add(recentlyViewedLabel, BorderLayout.NORTH);
        JPanel recentlyViewedMoviesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for (Movie movie : user.getRecentlyViewed()) {
            JLabel movieLabel = new JLabel(movie.getTitle());
            recentlyViewedMoviesPanel.add(movieLabel);
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

        contentPanel.add(recentlyViewedPanel);
        contentPanel.add(friendsPanel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel);
        pack();
        setVisible(true);
    }
}
