package Services;
import Models.*;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FriendService {
    static DefaultListModel<String> listModel = new DefaultListModel<>();
    public static boolean addFriend(String s) {
        String sql = "INSERT INTO Friends_Test (User, Friend) VALUES (?,?)";
        String reverseSql = "INSERT INTO Friends_Test (User, Friend) VALUES (?,?)";

        try (Connection conn = DbFunctions.connect();


             PreparedStatement pstmt = conn.prepareStatement(sql);
             PreparedStatement reversePstmt = conn.prepareStatement(reverseSql)) {


            pstmt.setString(1, User.getUsername());
            pstmt.setString(2, s);
            int affectedRows = pstmt.executeUpdate();


            reversePstmt.setString(1, s);
            reversePstmt.setString(2, User.getUsername());
            refreshFriendsList(User.getUsername(), listModel);
            int reverseAffectedRows = reversePstmt.executeUpdate();


            return (affectedRows > 0 && reverseAffectedRows > 0);
        }

        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void refreshFriendsList(String username, DefaultListModel<String> listModel) {
        listModel.clear();


        ArrayList<String> friends = retrieveFriendsFromDatabase(username);


        for (String friend : friends) {
            listModel.addElement(friend);
        }
    }
    public static ArrayList<String> retrieveFriendsFromDatabase(String username) {
        ArrayList<String> friends = new ArrayList<>();
        String sql = "SELECT Friend FROM Friends_Test WHERE User = ?";

        try (Connection conn = DbFunctions.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String friend = resultSet.getString("Friend");
                friends.add(friend);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return friends;
    }
    public static List<User> getFriends(String namePart) {
        List<User> friends = new ArrayList<>();
        String sql = "SELECT Friend FROM Friends_Test ";
        try (Connection conn = DbFunctions.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User u1 = resultSetToFriends(rs);
                friends.add(u1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friends;
    }

    private static User resultSetToFriends(ResultSet rs) throws SQLException {
        User u1 = new User(
                rs.getString("Friend")
        );
        return u1;
    }
    public static JPanel createFriendsPanel() throws SQLException {
      /*  JPanel friendsPanel = new JPanel(new BorderLayout());
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
        return friendsPanel; */

       /* JPanel friendsPanel = new JPanel(new BorderLayout());
        JLabel friendsLabel = new JLabel("Friends:");
        friendsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        friendsPanel.add(friendsLabel, BorderLayout.NORTH);
        JPanel friendsListPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String sql = "SELECT Friend from Friends_Test WHERE USER = (USER) VALUES(?)";
        try (Connection conn = DbFunctions.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql))
            {
                pstmt.setString(1,User.getUsername());
                int affectedRows = pstmt.executeUpdate();

            }

    } */
        JPanel panel = new JPanel(new BorderLayout());

        // Heading Label
        JLabel headingLabel = new JLabel("Friends:");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(headingLabel, BorderLayout.NORTH);


        JList<String> friendsList = new JList<>(listModel);

        JScrollPane scrollPane = new JScrollPane(friendsList);
        panel.add(scrollPane, BorderLayout.CENTER);


        FriendService.refreshFriendsList(User.getUsername(), listModel);

        return panel;
    }
}