package view;


import javax.swing.*;
import java.awt.*;

public class BMIInputView extends JPanel {
	private JTextField weightField;
	private JTextField heightField;
	private JTextField ageField;
	private JComboBox<String> genderBox;
	private JButton calculateButton;
	private JButton backButton;
	
	public BMIInputView() {
		// TODO Auto-generated constructor stub
		weightField = new JTextField();
		heightField = new JTextField();
		ageField = new JTextField();
		genderBox = new JComboBox<>(new String[] {"Male", "Female"});
		calculateButton = new JButton("Calculate");
		backButton = new JButton("Back");
		
		add(new JLabel("Weight (Ibs):"));
        add(weightField);

        add(new JLabel("Height (in):"));
        add(heightField);

        add(new JLabel("Age:"));
        add(ageField);

        add(new JLabel("Gender:"));
        add(genderBox);

        add(backButton);
        add(calculateButton);
	}
	
	public JTextField getWeightField() {
		return weightField;
	}

	public JTextField getHeightField() {
		return heightField;
	}

	public JTextField getAgeField() {
		return ageField;
	}

	public JComboBox<String> getGenderBox() {
		return genderBox;
	}

	public JButton getCalculateButton() {
		return calculateButton;
	}

	public JButton getBackButton() {
		return backButton;
	}
	
	public static void main(String[] args) {
		new BMIInputView();
	}

}
