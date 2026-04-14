import javax.swing.*;

import View.MethodSelectView;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FitnessCalculator extends JFrame {
	
	private MethodSelectView methodSelectView;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	
	public FitnessCalculator() {
		setPreferredSize(new Dimension(1000, 1000));
		setMaximumSize(new Dimension(1000, 1000));
		
		setMinimumSize(new Dimension(1000, 1000));
		setResizable(false);
		setLocationByPlatform(true);
		
		
		
		Container cp = getContentPane();
		JPanel panel = new JPanel();
		methodSelectView = new MethodSelectView();
		panel.add(methodSelectView);
		cp.add(panel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FitnessCalculator();

	}

}
