package view;


import javax.swing.*;
import java.awt.*;

public class MethodSelectView extends JPanel {
	
	private JButton selectBtn;
	
	public MethodSelectView() {
		// TODO Auto-generated constructor stub
		
		String[] menuItems = {"BMI", "Body Fat Percentage"};
		
		// Inserting dropdown menu bar
		JComboBox<String> methodBox = new JComboBox<String>(menuItems);
		
		setLayout(new GridLayout());
		
		JLabel titleLabel = new JLabel("Select Fitness Calculation Method", SwingConstants.LEFT);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
		
		titleLabel.setBounds(50, 100, 100, 30);
		methodBox.setBounds(50, 100, 100, 30);
		
		add(titleLabel);
		add(methodBox);
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new MethodSelectView();
	}

}
