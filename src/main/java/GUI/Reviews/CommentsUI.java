package GUI.Reviews;
import Models.Comment;
import Models.Movie;
import Models.Review;
import Models.User;
import Services.CommentService;
import Services.ReviewService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CommentsUI extends JFrame {
    private JTextArea parentReviewArea;
    private JTextArea commentTextArea;
    private final User user;


    public CommentsUI(Review review, User user) {
        this.user = user;
        setTitle("Comment Section");
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        parentReviewArea = new JTextArea();
        parentReviewArea.setEditable(false);
        commentTextArea = new JTextArea();
        JButton addCommentButton = new JButton("Add Comment");
        addCommentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String commentText = commentTextArea.getText();
                Comment comment = new Comment(0, user.getUserId(), commentText, 0,0);
                createComment(user.getUserId(), comment);
                commentTextArea.setText("");
            }
        });
        JPanel commentPanel = new JPanel(new BorderLayout());
        commentPanel.add(commentTextArea, BorderLayout.CENTER);
        commentPanel.add(addCommentButton, BorderLayout.LINE_END);
        setLayout(new BorderLayout());
        add(new JScrollPane(parentReviewArea), BorderLayout.NORTH);
        add(new JScrollPane(commentTextArea), BorderLayout.CENTER);
        add(commentPanel, BorderLayout.SOUTH);
        displayParentReview(review.getReviewId());
        displayComments(review.getReviewId());
    }

    private void displayParentReview(int reviewId) {
        ReviewService reviewService = new ReviewService();
        try {
            Review parentReview = reviewService.getReviewByReviewId(reviewId);
            parentReviewArea.append("Review: " + parentReview.getReviewText() + "\n\n");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching parent review: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayComments(int reviewId) {
        commentTextArea.setText("");
        Services.CommentService commentService = new Services.CommentService();
        try {
            List<Comment> comments = commentService.getCommentsByReviewId(reviewId);
            for (Comment comment : comments) {
                commentTextArea.append("User: " + commentService.getUsernameByComment(comment) + "\n");
                commentTextArea.append("Rating: " + comment.getCommentText() + "\n\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching reviews: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void createComment(int userId, Comment comment) {
        CommentService commentService = new CommentService();
        CommentService.addComment(userId, comment);
    }
}