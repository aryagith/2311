package GUI;

import Models.Movie;
import Models.Review;
import Models.User;
import GUI.Dashboard.Dashboard;
import GUI.Dashboard.MovieUtils;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.*;


public class ReviewsUI extends JFrame {
    private final JPanel pastReviewsPanel;
    private final ExecutorService executorService;
    private final User user;
    private final Dashboard dashboard;

    public ReviewsUI(User user) {
        this.user = user;
        this.dashboard = Dashboard.getInstance();
        setTitle("Reviews");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 600);

        pastReviewsPanel = new JPanel(new GridLayout(0, 1));
        JScrollPane scrollPane = new JScrollPane(pastReviewsPanel);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
        executorService = Executors.newCachedThreadPool();
    }

    public static JPanel ReviewThread (Review review, User user) {
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BorderLayout());
        cardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        cardPanel.setPreferredSize(new Dimension(500, 100));
        String titleText = String.format(
                review.getReviewText(), review.getRating()
        );
        return cardPanel;
    }
    public void displayReviews(List<Review> reviews) {
        pastReviewsPanel.removeAll();
        for (Review review : reviews) {
            executorService.execute(() -> {
                JPanel reviewPanel = ReviewThread(review, user);
                SwingUtilities.invokeLater(() -> {
                    pastReviewsPanel.add(reviewPanel);
                    pastReviewsPanel.revalidate();
                    pastReviewsPanel.repaint();
                });
            });
        }
    }
}
