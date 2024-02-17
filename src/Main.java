import GUI.LoginUI;

import java.util.List;

import Models.*;


public class Main {
    public static void main(String[] args) {
        User user = new User("John", "123");
        Review review = new Review(user, new Movie("Animal", 2023, null, null), "This movie is not good", 2);
        Comment parentComment = new Comment(user, review, "I agree");
        Comment childComment1 = new Comment(user, parentComment, "Me too!");
        Comment childComment2 = new Comment(user, parentComment, "I disagree");
       Comment newReply = new Comment(user, parentComment, "I also agree");
        parentComment.addReply(newReply);
        List<Comment> replies = parentComment.getReplies();
        System.out.println(replies);
    }
}