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
	public double calculateBodyFat() {
		double bodyFat;
		
		if (gender == "male") {
			bodyFat = 86.010 * Math.log10(waist - neck)
		               - 70.041 * Math.log10(height)
		               + 36.76;
		} else {
			bodyFat = 163.205 * Math.log10(waist + hip - neck)
            - 97.684 * Math.log10(height)
            - 78.387;
		}
		return bodyFat;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
