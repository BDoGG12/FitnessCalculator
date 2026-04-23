package view;


import javax.swing.*;
import java.awt.*;

public class ResultsView extends JPanel {
	
	private JLabel bmiLabel;
    private JLabel bodyFatLabel;
    private JLabel categoryLabel;
    private JButton backButton;
    private JButton homeButton;
    private JButton downloadButton;

	public ResultsView() {
		// TODO Auto-generated constructor stub
		
		setLayout(new GridLayout(5, 1, 10, 10));
		
		bmiLabel = new JLabel("BMI: ");
		bodyFatLabel = new JLabel("Body Fat %: ");
		categoryLabel = new JLabel("BMI Category: ");
		
		backButton = new JButton("Back");
		homeButton = new JButton("Home");
		downloadButton = new JButton("Download Results");
		
		add(bmiLabel);
		add(bodyFatLabel);
		add(categoryLabel);
		add(backButton);
		add(homeButton);
		add(downloadButton);
	}
	
	public void updateResults(double bmi, double bodyFat, String category) {
		bmiLabel.setText("BMI: " + String.format("%.2f", bmi));
		bodyFatLabel.setText("Body Fat %: " + String.format("%.2f", bodyFat));
		categoryLabel.setText("BMI Category: " + category);
	}

	public JButton getBackButton() {
		return backButton;
	}

	public JButton getHomeButton() {
		return homeButton;
	}
	
	
	
	public JButton getDownloadButton() {
		return downloadButton;
	}

	public void setDownloadButton(JButton downloadButton) {
		this.downloadButton = downloadButton;
	}

	public static void main(String[] args) {
		new ResultsView();
	}
	
	
	
	
}
