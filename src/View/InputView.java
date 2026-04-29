package view;


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
	
	public InputView() {
    setLayout(new GridBagLayout());
    setBorder(BorderFactory.createEmptyBorder(60, 20, 20, 20));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(8, 12, 8, 12);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    
    

    weightField = new JTextField(15);
    
    // Feet options (4ft to 7ft covers virtually all adults)
    String[] feetOptions = {"4", "5", "6", "7"};
    heightFeetBox = new JComboBox<>(feetOptions);

    // Inches options (0 to 11)
    String[] inchesOptions = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
    heightInchesBox = new JComboBox<>(inchesOptions);
    ageField = new JTextField(15);
    genderBox = new JComboBox<>(new String[]{"Male", "Female"});
    calculateBtn = new JButton("Calculate");
    backBtn = new JButton("Back");
    
    backBtn.setPreferredSize(new Dimension(120, 30));
    calculateBtn.setPreferredSize(new Dimension(120, 30));

    // Row 0 - Weight
    gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
    add(new JLabel("Weight (lbs):"), gbc);
    gbc.gridx = 1; gbc.weightx = 1;
    add(weightField, gbc);

    // Row 1 - Height feet
    gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
    add(new JLabel("Height (ft):"), gbc);
    gbc.gridx = 1; gbc.weightx = 1;
    add(heightFeetBox, gbc);

    // Row 2 - Height inches
    gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
    add(new JLabel("Height (in):"), gbc);
    gbc.gridx = 1; gbc.weightx = 1;
    add(heightInchesBox, gbc);

    // Row 3 - Age
    gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0;
    add(new JLabel("Age:"), gbc);
    gbc.gridx = 1; gbc.weightx = 1;
    add(ageField, gbc);

    // Row 4 - Gender
    gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0;
    add(new JLabel("Gender:"), gbc);
    gbc.gridx = 1; gbc.weightx = 1;
    add(genderBox, gbc);

    // Row 5 - Buttons
    gbc.gridx = 0; gbc.gridy = 5; gbc.weightx = 0.5;
    add(backBtn, gbc);
    gbc.gridx = 1; gbc.weightx = 0.5;
    add(calculateBtn, gbc);
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
