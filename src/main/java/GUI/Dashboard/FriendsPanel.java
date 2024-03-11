package GUI.Dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import Services.MovieManager;

import Models.*;
import Services.*;;

import org.json.JSONException;
public class FriendsPanel extends JPanel {
    private JPanel createSfPanel() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JTextField searchField = new JTextField(10);
        JButton searchButton = new JButton("Add Friend");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                FriendService.addFriend(searchText);
            }
        });
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        return searchPanel;
    }
}
