package main;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.*;

import view.HomeView;
import view.InputView;
import view.ResultsView;

public class MainFrame extends JFrame {
	private HomeView homeView;
	private InputView inputView;
	private CardLayout cardLayout;
	private ResultsView resultsView;
	
	private JPanel cardPanel;
	
	public MainFrame() {
		// TODO Auto-generated constructor stub
		setPreferredSize(new Dimension(500, 400));
		setMaximumSize(new Dimension(500, 400));
		setMinimumSize(new Dimension(500, 400));
		setResizable(false);
		setLocationByPlatform(true);
		
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		
		Container cp = getContentPane();
		//JPanel panel = new JPanel();
		homeView = new HomeView();
		inputView = new InputView();
		

		resultsView = new ResultsView();
		
		cardPanel.add(homeView, "HOME");
		cardPanel.add(inputView, "INPUT");
		cardPanel.add(resultsView, "RESULT");
		cp.add(cardPanel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack(); // add this
		setVisible(true);

	}
	
	public void showView(String viewName) {
		cardLayout.show(cardPanel, viewName);
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
