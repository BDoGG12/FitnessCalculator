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
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(60, 20, 20, 20)); 

        bmiLabel = new JLabel("BMI: ");
        bodyFatLabel = new JLabel("Body Fat %: ");
        categoryLabel = new JLabel("BMI Category: ");
        backButton = new JButton("Back");
        homeButton = new JButton("Home");
        downloadButton = new JButton("Download Results");

        // Results at the top
        JPanel resultsPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        resultsPanel.add(bmiLabel);
        resultsPanel.add(bodyFatLabel);
        resultsPanel.add(categoryLabel);

        // Buttons at the bottom
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        buttonPanel.add(homeButton);
        buttonPanel.add(backButton);
        buttonPanel.add(downloadButton);

        add(resultsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
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
