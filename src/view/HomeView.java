package view;

import java.awt.*;
import javax.swing.*;

public class HomeView extends JPanel {
	
	private JButton startButton;
	
	public HomeView() {
		// TODO Auto-generated constructor stub
		
		// set layout to border layout
		setLayout(new BorderLayout());
		
		JLabel titleLabel = new JLabel("Welcome to Fitness Calculator", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
		
		add(titleLabel, BorderLayout.CENTER);
		add(startButton, BorderLayout.SOUTH);
	}
	
	public JButton getStartButton() {
		return startButton;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
