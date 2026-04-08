package Model;

public class User {
	
	int age;
	double height, weight, waist, hip, neck;
	String gender;
	
	
	// method for calculating BMI
	public double calculateBMI() {
		double bmi = (weight / height) * 703;
		
		return bmi;
	}
	
	
	// method for calculating Body Fat Percentage
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
