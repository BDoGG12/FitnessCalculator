package view;

import java.awt.*;
import javax.swing.*;

public class HomeView extends JPanel {
	
	private JButton startButton;
	
	public HomeView() {
	    setLayout(new GridBagLayout()); // centers content vertically and horizontally
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.insets = new Insets(8, 40, 8, 40);

	    JLabel titleLabel = new JLabel("Welcome!", SwingConstants.CENTER);
	    titleLabel.setFont(new Font("SansSerif", Font.BOLD, 26));

	    JLabel messageLabel = new JLabel(
	        "<html><div style='text-align: center;'>Understanding your BMI and body fat can help you<br>make smarter health decisions. Let's get started.</div></html>",
	        SwingConstants.CENTER);
	    messageLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
	    messageLabel.setForeground(Color.GRAY);

	    startButton = new JButton("Start My Calculation");
	    startButton.setPreferredSize(new Dimension(200, 40));
	    startButton.setFont(new Font("SansSerif", Font.PLAIN, 14));

	    gbc.gridy = 0; add(titleLabel, gbc);
	    gbc.gridy = 1; add(messageLabel, gbc);
	    gbc.gridy = 2; gbc.insets = new Insets(20, 40, 8, 40); // extra top margin on button
	    add(startButton, gbc);
	}
	
	public JButton getStartButton() {
		return startButton;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
