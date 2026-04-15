package View;

import javax.swing.*;

import java.awt.*;

public class InputView extends JPanel {
	
	private JTextField weightField;
	private JTextField heightField;
	private JTextField ageField;
	private JComboBox<String> genderBox;
	private JButton calculateBtn;
	private JButton backBtn;
	
	public InputView() {
		// TODO Auto-generated constructor stub
		GridLayout gridLayout = new GridLayout(6, 2, 10, 10);
		setLayout(gridLayout);
		
		weightField = new JTextField();
		heightField = new JTextField();
		ageField = new JTextField();
		genderBox = new JComboBox<>(new String[] {"Male", "Female"});
		calculateBtn = new JButton("Calculate");
		backBtn = new JButton("Back");
		
		add(new JLabel("Weight (kg):"));
        add(weightField);

        add(new JLabel("Height (m):"));
        add(heightField);

        add(new JLabel("Age:"));
        add(ageField);

        add(new JLabel("Gender:"));
        add(genderBox);

        add(backBtn);
        add(calculateBtn);
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



	public JButton getCalculateBtn() {
		return calculateBtn;
	}



	public JButton getBackBtn() {
		return backBtn;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
