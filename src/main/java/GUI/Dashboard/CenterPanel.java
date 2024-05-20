package GUI.Dashboard;

import Models.Movie;
import Models.User;
import Services.RecommendationService;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CenterPanel extends JPanel {
    private static CenterPanel instance;
    private final RecommendationService recommendationService;
    private final User user;
    private final Dashboard dashboard;

    public CenterPanel(RecommendationService recommendationService, User user, Dashboard dashboard) {
        this.recommendationService = recommendationService;
        this.user = user;
        this.dashboard = dashboard;
        setLayout(new GridBagLayout()); // Use GridBagLayout for better control
        setBackground(Color.BLACK); // Set background color for better contrast
        setupCenterPanel();
        instance = this;
    }

    public static CenterPanel getInstance() {
        return instance;
    }

    private void setupCenterPanel() {
        List<Movie> recommendedMovies;
        if (user.getRecentlyViewed().isEmpty()) {
            recommendedMovies = recommendationService.getDefaultRecommendations();
        } else {
            recommendedMovies = recommendationService.getRecommendations(user);
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; // Make components fill their grid cells
        gbc.weightx = 1.0; // Make components expand horizontally
        gbc.weighty = 1.0; // Make components expand vertically

        int row = 0;
        int col = 0;
        for (Movie movie : recommendedMovies) {
            JPanel card = Utilities.MovieUtils.createMovieCard(movie, user);
            gbc.gridx = col;
            gbc.gridy = row;
            add(card, gbc);

            col++;
            if (col == 3) { // Adjust the number of columns as needed
                col = 0;
                row++;
            }
        }
    }

    public void refreshRecommendations() {
        removeAll(); // Remove all components from the panel
        setupCenterPanel(); // Set up the panel with new recommendations
        revalidate(); // Revalidate the panel
        repaint(); // Repaint the panel
    }
}

