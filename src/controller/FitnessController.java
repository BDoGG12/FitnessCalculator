package controller;
import javax.swing.*;

import FileSaveManager.FileCsvSaver;
import FileSaveManager.SaveCsvDialog;

import java.awt.*;
import controller.*;
import main.MainFrame;
import model.FitnessModel;

public class FitnessController {
	private MainFrame mainFrame;
	
	public FitnessModel model;
	
	public FitnessController(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		this.mainFrame = mainFrame;
		initController();
	}
	
	private void initController() {
		mainFrame.getHomeView().getStartButton().addActionListener(e -> {
			mainFrame.showView("INPUT");
		});
		
		mainFrame.getInputView().getBackBtn().addActionListener(e -> {
			mainFrame.showView("HOME");
		});
		
		mainFrame.getInputView().getCalculateBtn().addActionListener(e -> {
    try {
        // Clear previous errors first
        mainFrame.getInputView().showWeightError(false);
        mainFrame.getInputView().showAgeError(false);

        String weightText = mainFrame.getInputView().getWeightField().getText().trim();
        String ageText    = mainFrame.getInputView().getAgeField().getText().trim();

        boolean hasError = false;

        if (weightText.isEmpty()) {
            mainFrame.getInputView().showWeightError(true);
            hasError = true;
        }

        if (ageText.isEmpty()) {
            mainFrame.getInputView().showAgeError(true);
            hasError = true;
        }

        if (hasError) return;

        double weightLbs  = Double.parseDouble(weightText);
        double heightFeet = Double.parseDouble((String) mainFrame.getInputView().getHeightFeetBox().getSelectedItem());
        double heightInches = Double.parseDouble((String) mainFrame.getInputView().getHeightInchesBox().getSelectedItem());
        int age = Integer.parseInt(ageText);
        String gender = (String) mainFrame.getInputView().getGenderBox().getSelectedItem();

        if (weightLbs <= 0 || weightLbs > 1100) {
            mainFrame.getInputView().showWeightError(true);
            return;
        }
        if (age < 1 || age > 120) {
            mainFrame.getInputView().showAgeError(true);
            return;
        }

        double weightKg = weightLbs * 0.453592;
        double heightM  = ((heightFeet * 12) + heightInches) * 0.0254;

        model = new FitnessModel(weightKg, heightM, age, gender);

        double bmi      = model.calculateBMI();
        double bodyFat  = model.calculateBodyFat();
        String category = model.getBMICategory();

        mainFrame.getResultsView().updateResults(bmi, bodyFat, category);
        mainFrame.showView("RESULT");

    } catch (NumberFormatException ex) {
        // DocumentFilter prevents this from happening in normal use
        // but kept as a safety net
        JOptionPane.showMessageDialog(mainFrame,
            "Please enter valid numeric values.",
            "Input Error",
            JOptionPane.ERROR_MESSAGE);
    }
});
		
		mainFrame.getResultsView().getBackButton().addActionListener(e -> {
			mainFrame.showView("INPUT");
		});
		
		mainFrame.getResultsView().getHomeButton().addActionListener(e -> {
			mainFrame.showView("HOME");
		});
		
		mainFrame.getResultsView().getDownloadButton().addActionListener(e -> {
			this.handleSave();
		});
	}
	
	public void handleSave() {
		String path = SaveCsvDialog.chooseFilePath();
		
		if (path != null) {
			double bmi = model.bmi;
			double bodyFat = model.bodyFat;
			
			FileCsvSaver.saveResult(path, bmi, bodyFat);
		}
	}

}
