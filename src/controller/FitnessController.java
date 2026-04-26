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
                double weight = Double.parseDouble(mainFrame.getInputView().getWeightField().getText());
                double height = Double.parseDouble(mainFrame.getInputView().getHeightField().getText());
                int age = Integer.parseInt(mainFrame.getInputView().getAgeField().getText());
                String gender = (String) mainFrame.getInputView().getGenderBox().getSelectedItem();

                model = new FitnessModel(weight, height, age, gender);

                double bmi = model.calculateBMI();
                double bodyFat = model.calculateBodyFat();
                String category = model.getBMICategory();

                mainFrame.getResultsView().updateResults(bmi, bodyFat, category);
                mainFrame.showView("RESULT");

            } catch (NumberFormatException ex) {
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
