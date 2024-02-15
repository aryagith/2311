package GUI;
import javax.swing.*;
import java.awt.*;

public class DashboardUI extends JFrame {
    public DashboardUI() {
        setTitle("Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JLabel welcomeLabel = new JLabel("Welcome to the Dashboard!");
        panel.add(welcomeLabel);

        // Add more components as needed for the dashboard

        add(panel);
        setVisible(true);
    }
}

