package Models;

import java.util.*;

public class Comment {
    private User user;
    private String content;
    private Review review;//comment will be under this review
    private Comment comment;//OR under this comment
    private int noOfUpvotes;
    private int noOfDownvotes;
    private List<Comment> replies;
    private int TotalVotes=(noOfUpvotes-noOfDownvotes);//like on reddit
   
    public Comment(User user,Review review){
       this.content="";
       this.review=review;
       this.noOfDownvotes=0;
       this.noOfUpvotes=0;
       this.user=user;
       this.replies=new ArrayList<Comment>();
    }

    public Comment(User user,Comment comment){
        this.content="";
        this.comment=comment;
        this.noOfDownvotes=0;
        this.noOfUpvotes=0;
        this.user=user;
        this.replies=new ArrayList<Comment>();
    }
    
    public Comment getComment() {
        return comment;
    }
    public int getNoOfDownvotes() {
        return noOfDownvotes;
    }
    public int getNoOfUpvotes() {
     return noOfUpvotes;
    }
    public String getContent() {
        return content;
    }
    public List<Comment> getReplies() {
        return replies;
    }
    public Review getReview() {
        return review;
    }
    public User getUser() {
        return user;
    }
    public void setComment(Comment comment) {
        this.comment = comment;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setNoOfDownvotes(int noOfDownvotes) {
        this.noOfDownvotes = noOfDownvotes;
    }
    public void setNoOfUpvotes(int noOfUpvotes) {
        this.noOfUpvotes = noOfUpvotes;
    }
    public void setReplies(List<Comment> replies) {
        this.replies = replies;
    }
    public void setReview(Review review) {
        this.review = review;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void UpvoteComment() {
        this.noOfUpvotes++;
    }
    public void DownvoteComment() {
        this.noOfDownvotes++;
    }
    public int getTotalVotes() {
        return TotalVotes;
    }
    public void setTotalVotes(int totalVotes) {
        TotalVotes = totalVotes;
    }
    public void addComment(Comment comment) {
        this.replies.add(comment);
    }
}
