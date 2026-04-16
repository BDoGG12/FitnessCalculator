import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.*;

import View.HomeView;
import View.InputView;
import View.MethodSelectView;
import View.ResultsView;

public class MainFrame extends JFrame {
	private MethodSelectView methodSelectView;
	private HomeView homeView;
	private InputView inputView;
	private CardLayout cardLayout;
	private ResultsView resultsView;
	
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
	
	public void showView(String viewName) {
		cardLayout.show(cardPanel, viewName);
	}

	public MethodSelectView getMethodSelectView() {
		return methodSelectView;
	}

	public HomeView getHomeView() {
		return homeView;
	}

	public InputView getInputView() {
		return inputView;
	}

	public ResultsView getResultsView() {
		return resultsView;
	}
	
	
}
