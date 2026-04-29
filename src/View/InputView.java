package view;

import util.PositiveDecimalFilter;
import util.WholeNumberFilter;
import javax.swing.text.AbstractDocument;
import javax.swing.*;
import java.awt.*;

public class InputView extends JPanel {

    private JTextField weightField;
    private JComboBox<String> heightFeetBox;
    private JComboBox<String> heightInchesBox;
    private JTextField ageField;
    private JComboBox<String> genderBox;
    private JButton calculateBtn;
    private JButton backBtn;
    private JButton downloadBtn;

    private JLabel weightError;
    private JLabel ageError;

    private JPanel resultsPanel;
    private JLabel bmiLabel;
    private JLabel bodyFatLabel;
    private JLabel categoryLabel;

    public InputView() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 12, 4, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        weightField = new JTextField(15);
        ((AbstractDocument) weightField.getDocument()).setDocumentFilter(new PositiveDecimalFilter());

        String[] feetOptions = {"4", "5", "6", "7"};
        heightFeetBox = new JComboBox<>(feetOptions);

        String[] inchesOptions = {"0","1","2","3","4","5","6","7","8","9","10","11"};
        heightInchesBox = new JComboBox<>(inchesOptions);

        ageField = new JTextField(15);
        ((AbstractDocument) ageField.getDocument()).setDocumentFilter(new WholeNumberFilter());

        genderBox = new JComboBox<>(new String[]{"Male", "Female"});
        calculateBtn = new JButton("Calculate");
        backBtn = new JButton("Back");
        downloadBtn = new JButton("Download Results");

        backBtn.setPreferredSize(new Dimension(120, 30));
        calculateBtn.setPreferredSize(new Dimension(120, 30));

        // Row 0 - Weight error
        weightError = new JLabel("Weight is required");
        weightError.setForeground(Color.RED);
        weightError.setFont(new Font("SansSerif", Font.PLAIN, 11));
        weightError.setVisible(false);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1;
        gbc.insets = new Insets(4, 12, 0, 12);
        add(weightError, gbc);

        // Row 1 - Weight
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        gbc.insets = new Insets(0, 12, 4, 12);
        add(new JLabel("Weight (lbs):"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        add(weightField, gbc);

        // Row 2 - Height
        gbc.insets = new Insets(4, 12, 4, 12);
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        add(new JLabel("Height:"), gbc);

        JPanel heightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        heightPanel.setOpaque(false);
        heightPanel.add(heightFeetBox);
        heightPanel.add(new JLabel("ft"));
        heightPanel.add(heightInchesBox);
        heightPanel.add(new JLabel("in"));
        gbc.gridx = 1; gbc.weightx = 1;
        add(heightPanel, gbc);

        // Row 3 - Age error
        ageError = new JLabel("Age is required");
        ageError.setForeground(Color.RED);
        ageError.setFont(new Font("SansSerif", Font.PLAIN, 11));
        ageError.setVisible(false);
        gbc.gridx = 1; gbc.gridy = 3; gbc.weightx = 1;
        gbc.insets = new Insets(4, 12, 0, 12);
        add(ageError, gbc);

        // Row 4 - Age
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0;
        gbc.insets = new Insets(0, 12, 4, 12);
        add(new JLabel("Age:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        add(ageField, gbc);

        // Row 5 - Gender
        gbc.insets = new Insets(4, 12, 4, 12);
        gbc.gridx = 0; gbc.gridy = 5; gbc.weightx = 0;
        add(new JLabel("Gender:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        add(genderBox, gbc);

        // Row 6 - Buttons
        gbc.gridx = 0; gbc.gridy = 6; gbc.weightx = 0.5;
        add(backBtn, gbc);
        gbc.gridx = 1; gbc.weightx = 0.5;
        add(calculateBtn, gbc);

        // Row 7 - Results panel (hidden by default)
        resultsPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        resultsPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(10, 0, 0, 0),
            BorderFactory.createTitledBorder("Results")
        ));
        resultsPanel.setVisible(false);

        bmiLabel     = new JLabel("BMI: ");
        bodyFatLabel = new JLabel("Body Fat %: ");
        categoryLabel = new JLabel("BMI Category: ");

        bmiLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        bodyFatLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        categoryLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));

        resultsPanel.add(bmiLabel);
        resultsPanel.add(bodyFatLabel);
        resultsPanel.add(categoryLabel);
        resultsPanel.add(downloadBtn);

        gbc.gridx = 0; gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.insets = new Insets(10, 12, 8, 12);
        add(resultsPanel, gbc);
    }

    public void showResults(double bmi, double bodyFat, String category) {
        bmiLabel.setText(String.format("BMI: %.2f", bmi));
        bodyFatLabel.setText(String.format("Body Fat %%: %.2f", bodyFat));
        categoryLabel.setText("BMI Category: " + category);
        resultsPanel.setVisible(true);
        revalidate();
        repaint();
    }

    public void hideResults() {
        resultsPanel.setVisible(false);
        revalidate();
        repaint();
    }

    public void resetForm() {
        showWeightError(false);
        showAgeError(false);
        heightFeetBox.setSelectedIndex(0);
        heightInchesBox.setSelectedIndex(0);
    }

    public void showWeightError(boolean show) {
        weightError.setVisible(show);
        if (show) {
            weightField.setBorder(BorderFactory.createLineBorder(Color.RED));
        } else {
            weightField.setBorder(UIManager.getLookAndFeel()
                .getDefaults().getBorder("TextField.border"));
        }
        revalidate();
        repaint();
    }

    public void showAgeError(boolean show) {
        ageError.setVisible(show);
        if (show) {
            ageField.setBorder(BorderFactory.createLineBorder(Color.RED));
        } else {
            ageField.setBorder(UIManager.getLookAndFeel()
                .getDefaults().getBorder("TextField.border"));
        }
        revalidate();
        repaint();
    }

    public JTextField getWeightField()            { return weightField; }
    public JComboBox<String> getHeightFeetBox()   { return heightFeetBox; }
    public JComboBox<String> getHeightInchesBox() { return heightInchesBox; }
    public JTextField getAgeField()               { return ageField; }
    public JComboBox<String> getGenderBox()        { return genderBox; }
    public JButton getCalculateBtn()              { return calculateBtn; }
    public JButton getBackBtn()                   { return backBtn; }
    public JButton getDownloadBtn()               { return downloadBtn; }

    public static void main(String[] args) {
        new InputView();
    }
}