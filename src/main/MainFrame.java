package main;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.*;

import view.HomeView;
import view.InputView;
import view.MethodSelectView;
import view.ResultsView;

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
		
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		
		Container cp = getContentPane();
		//JPanel panel = new JPanel();
		//homeView = new HomeView();
		inputView = new InputView();
		//methodSelectView = new MethodSelectView();
		//cardPanel.add(methodSelectView, "SELECT METHOD");

		resultsView = new ResultsView();
		
		//cardPanel.add(homeView, "HOME");
		cardPanel.add(inputView, "INPUT");
		cardPanel.add(resultsView, "RESULT");
		//panel.add(homeView);
		//panel.add(inputView);
		//panel.add(resultsView);
		cp.add(cardPanel);
		
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
