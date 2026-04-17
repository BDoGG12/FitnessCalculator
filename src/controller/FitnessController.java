package controller;
import java.awt.*;
import controller.*;
import main.MainFrame;

public class FitnessController {
	private MainFrame mainFrame;
	
	public FitnessController(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		this.mainFrame = mainFrame;
		
	}
	
	private void initController() {
		mainFrame.getHomeView().getStartButton().addActionListener(e -> {
			mainFrame.showView("INPUT");
		});
		
		mainFrame.getInputView().getBackBtn().addActionListener(e -> {
			mainFrame.showView("HOME");
		});
	}

}
