package view;

import util.PositiveDecimalFilter;
import util.WholeNumberFilter;
import javax.swing.text.AbstractDocument;
import javax.swing.*;

import java.awt.*;

public class InputView extends JPanel {
	
	private JTextField weightField;
	private JComboBox<String> heightFeetBox;  // replaces heightFeetField
	private JComboBox<String> heightInchesBox; // replaces heightInchesField
	private JTextField ageField;
	private JComboBox<String> genderBox;
	private JButton calculateBtn;
	private JButton backBtn;
	private JLabel weightError;
	private JLabel ageError;
	
	public InputView() {
    setLayout(new GridBagLayout());
    setBorder(BorderFactory.createEmptyBorder(60, 20, 20, 20));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(8, 12, 8, 12);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    

    weightField = new JTextField(15);
    ((AbstractDocument) weightField.getDocument()).setDocumentFilter(new PositiveDecimalFilter());

    
    // Feet options (4ft to 7ft covers virtually all adults)
    String[] feetOptions = {"4", "5", "6", "7"};
    heightFeetBox = new JComboBox<>(feetOptions);

    // Inches options (0 to 11)
    String[] inchesOptions = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
    heightInchesBox = new JComboBox<>(inchesOptions);
    ageField = new JTextField(15);
    ((AbstractDocument) ageField.getDocument()).setDocumentFilter(new WholeNumberFilter());
    genderBox = new JComboBox<>(new String[]{"Male", "Female"});
    calculateBtn = new JButton("Calculate");
    backBtn = new JButton("Back");
    
    backBtn.setPreferredSize(new Dimension(120, 30));
    calculateBtn.setPreferredSize(new Dimension(120, 30));

    // Row 0 - Weight
    weightError = new JLabel("Please enter a valid weight (1 - 1100 lbs)");
    weightError.setForeground(Color.RED);
    weightError.setFont(new Font("SansSerif", Font.PLAIN, 11));
    weightError.setVisible(false);
    gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1;
    gbc.insets = new Insets(8, 12, 0, 12); // reduce bottom gap
    add(weightError, gbc);

    gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
    gbc.insets = new Insets(0, 12, 8, 12); // reduce top gap
    add(new JLabel("Weight (lbs):"), gbc);
    gbc.gridx = 1; gbc.weightx = 1;
    add(weightField, gbc);

    // Row 2 - Height
    gbc.insets = new Insets(8, 12, 8, 12); // reset insets
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

    // Row 3 - Age
    ageError = new JLabel("Please enter a valid age (1 - 120)");
    ageError.setForeground(Color.RED);
    ageError.setFont(new Font("SansSerif", Font.PLAIN, 11));
    ageError.setVisible(false);
    gbc.gridx = 1; gbc.gridy = 3; gbc.weightx = 1;
    gbc.insets = new Insets(8, 12, 0, 12);
    add(ageError, gbc);

    gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0;
    gbc.insets = new Insets(0, 12, 8, 12);
    add(new JLabel("Age:"), gbc);
    gbc.gridx = 1; gbc.weightx = 1;
    add(ageField, gbc);

    // Row 5 - Gender
    gbc.insets = new Insets(8, 12, 8, 12);
    gbc.gridx = 0; gbc.gridy = 5; gbc.weightx = 0;
    add(new JLabel("Gender:"), gbc);
    gbc.gridx = 1; gbc.weightx = 1;
    add(genderBox, gbc);

    // Row 6 - Buttons
    gbc.gridx = 0; gbc.gridy = 6; gbc.weightx = 0.5;
    add(backBtn, gbc);
    gbc.gridx = 1; gbc.weightx = 0.5;
    add(calculateBtn, gbc);
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
	
	

	public JTextField getWeightField() {
		return weightField;
	}



	public JComboBox<String> getHeightFeetBox() {
	    return heightFeetBox;
	}

	public JComboBox<String> getHeightInchesBox() {
	    return heightInchesBox;
	}



	public JTextField getAgeField() {
		return ageField;
	}



	public JComboBox<String> getGenderBox() {
		return genderBox;
	}



	public JButton getCalculateBtn() {
		return calculateBtn;
	}



	public JButton getBackBtn() {
		return backBtn;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new InputView();
	}

}
