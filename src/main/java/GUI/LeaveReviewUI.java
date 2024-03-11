package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Services.*;
import Models.*;
public class LeaveReviewUI extends JFrame {
    private final JPanel contentPanel;
    private final Movie movie;
    private final User user;
    private JSlider ratingSlider;
    public LeaveReviewUI(User user, Movie movie) {
        this.user = user;
        this.movie = movie;
        setTitle("Leave Review");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));
        setLocationRelativeTo(null);
        contentPanel = new JPanel(new BorderLayout());
        JPanel headerPanel = createHeaderPanel();
        JPanel RatingPanel = createRatingPanel();
        JPanel commentPanel = createReviewPanel();
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        contentPanel.add(RatingPanel, BorderLayout.CENTER);
        contentPanel.add(commentPanel, BorderLayout.SOUTH);
        add(contentPanel);
        pack();
        setVisible(true);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel welcomeLabel = new JLabel("Leave a review for " + movie.getTitle());
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        headerPanel.add(welcomeLabel);
        return headerPanel;
    }
    private JPanel createRatingPanel() {
        JPanel ratingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel ratingLabel = new JLabel("Rating: ");
        ratingSlider = new JSlider(1, 5,1);
        ratingSlider.setMajorTickSpacing(1);
        ratingSlider.setPaintLabels(true);
        ratingPanel.add(ratingLabel);
        ratingPanel.add(ratingSlider);
        return ratingPanel;
    }
    private JPanel createReviewPanel() {
        JPanel reviewPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JTextArea reviewArea = new JTextArea(7, 20);
        reviewArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JButton publishButton = new JButton("Publish");
        publishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String commentText = reviewArea.getText();
                int rating = ratingSlider.getValue();
                Review review = new Review(0, user.getUserId(), movie.getMovieId(), commentText, rating);
                createReview(user.getUserId(), review);
                reviewArea.setText("");
                openReviewsUI(movie);
                }
        });
        reviewPanel.add(reviewArea);
        reviewPanel.add(publishButton);
        return reviewPanel;
    }
    public void openReviewsUI (Movie movie){
        ReviewsUI reviewUI = new ReviewsUI(movie);
        reviewUI.setVisible(true);
    }
    public void createReview(int userId, Review review) {
        ReviewService reviewService = new ReviewService();
        reviewService.addReview(userId, review);
    }
}

