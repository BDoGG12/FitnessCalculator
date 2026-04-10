package View;

import javax.swing.*;
import java.awt.*;

public class MethodSelectView extends JPanel {
	
	private JButton selectBtn;
	
	public MethodSelectView() {
		// TODO Auto-generated constructor stub
		
		setLayout(new BorderLayout());
		
		JLabel titleLabel = new JLabel("Select Fitness Calculation Method", SwingConstants.LEFT);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new MethodSelectView();
	}

}
