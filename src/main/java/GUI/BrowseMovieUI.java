package GUI;

import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.util.List;
import java.util.concurrent.*;

import Models.Movie;

public class BrowseMovieUI extends JFrame {
    private final JPanel searchResultsPanel;
    private final ExecutorService executorService;

    public BrowseMovieUI() {
        setTitle("Movie Search Results");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 600);



        searchResultsPanel = new JPanel(new GridLayout(0, 1));
        JScrollPane scrollPane = new JScrollPane(searchResultsPanel);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
        executorService = Executors.newCachedThreadPool();
        System.out.println("init done");
    }

    public void displaySearchResults(List<Movie> searchResults) {
        for (Movie movie : searchResults) {
            executorService.execute(() -> addMoviePanel(movie));
            System.out.println("movie done");
        }
    }

    private void addMoviePanel(Movie movie) {
        JPanel moviePanel = new JPanel();
        moviePanel.setLayout(new BoxLayout(moviePanel, BoxLayout.Y_AXIS));

        // Title with year and rating
        String titleText = "<html><b>" + movie.getTitle() + "</b> (" + movie.getReleaseYear() + ") " +
                "<font color='red'><b>" + movie.getRating() + "/10 &#9733;</b></font></html>";
        JLabel titleLabel = new JLabel(titleText);
        moviePanel.add(titleLabel);

        // Cover image
        JLabel coverImageLabel = new JLabel();
        loadImage(movie.getCoverImageUrl(), coverImageLabel);
        moviePanel.add(coverImageLabel);

        // Description
        JLabel descriptionLabel = new JLabel("<html><p style='width:400px'>" + movie.getDescription() + "</p></html>");
        descriptionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        moviePanel.add(descriptionLabel);

        // Add moviePanel directly to the searchResultsPanel on the EDT
        SwingUtilities.invokeLater(() -> {
            searchResultsPanel.add(moviePanel);
            searchResultsPanel.revalidate(); // Revalidate the searchResultsPanel
            searchResultsPanel.repaint();    // Repaint the searchResultsPanel
        });

    }

    private void loadImage(String imageUrl, JLabel imageLabel) {
        try {
            URL url = new URL(imageUrl);
            ImageIcon icon = new ImageIcon(url);
            Image scaledImage = icon.getImage().getScaledInstance(150, -1, Image.SCALE_SMOOTH);
            icon = new ImageIcon(scaledImage);
            imageLabel.setIcon(icon);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dispose() {
        executorService.shutdownNow();
        super.dispose();
    }
}
