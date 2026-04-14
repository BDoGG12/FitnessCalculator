import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.*;

import View.MethodSelectView;

public class MainFrame extends JFrame {
	private MethodSelectView methodSelectView;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	
	public MainFrame() {
		// TODO Auto-generated constructor stub
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
}
