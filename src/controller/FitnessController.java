package controller;

import javax.swing.*;
import FileSaveManager.FileCsvSaver;
import FileSaveManager.SaveCsvDialog;
import main.MainFrame;
import model.FitnessModel;

public class FitnessController {
    private MainFrame mainFrame;
    public FitnessModel model;

    public FitnessController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initController();
    }

    private void initController() {

        // Home -> Input
        mainFrame.getHomeView().getStartButton().addActionListener(e -> {
            mainFrame.getInputView().resetForm();
            mainFrame.getInputView().hideResults();
            mainFrame.getInputView().getWeightField().setText("");
            mainFrame.getInputView().getAgeField().setText("");
            mainFrame.showView("INPUT");
        });

        // Input -> Home
        mainFrame.getInputView().getBackBtn().addActionListener(e -> {
            mainFrame.getInputView().resetForm();
            mainFrame.getInputView().hideResults();
            mainFrame.showView("HOME");
        });

        // Calculate
        mainFrame.getInputView().getCalculateBtn().addActionListener(e -> {
            try {
                // Clear previous errors
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

                double weightLbs    = Double.parseDouble(weightText);
                double heightFeet   = Double.parseDouble((String) mainFrame.getInputView().getHeightFeetBox().getSelectedItem());
                double heightInches = Double.parseDouble((String) mainFrame.getInputView().getHeightInchesBox().getSelectedItem());
                int age             = Integer.parseInt(ageText);
                String gender       = (String) mainFrame.getInputView().getGenderBox().getSelectedItem();

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

                mainFrame.getInputView().showResults(bmi, bodyFat, category);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainFrame,
                    "Please enter valid numeric values.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        // Download
        mainFrame.getInputView().getDownloadBtn().addActionListener(e -> {
            this.handleSave();
        });
    }

    public void handleSave() {
        String path = SaveCsvDialog.chooseFilePath();
        if (path != null) {
            double bmi     = model.bmi;
            double bodyFat = model.bodyFat;
            FileCsvSaver.saveResult(path, bmi, bodyFat);
        }
    }
}