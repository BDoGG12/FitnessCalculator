package Model;

public class User {
	
	int age;
	double height, weight, waist, hip, neck;
	String gender;
	
	
	// Constructor for User selecting BMI
	public User(int age, double height, double weight) {
		// TODO Auto-generated constructor stub
		this.age = age;
		this.height = height;
		this.weight = weight;
	}
	
	// Constructor for User selecting Body Fat Percentage
	public User(int age, double height, double weight, double waist, double hip, double neck, String gender) {
		// TODO Auto-generated constructor stub
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.waist = waist;
		
		if (hip == 0) {
			this.hip = 0;
		} else {
			this.hip = hip;
		}
		
		this.gender = gender;
	}
	
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
	
	// Display message for user's body fat
	public String displayBodyFatMsgMale() {
		double result = calculateBodyFat();
		String message;
		int resultInt = (int) result;
		if (isBetween(resultInt, 2, 5)) {
			message = "Essential Fat";
		} else if (isBetween(resultInt, 6, 13)) {
			message = "Athletes";
		} else if (isBetween(resultInt, 14, 17)) {
			message = "Fitness";
		} else if (isBetween(resultInt, 18, 24)) {
			message = "Average";
		} else if (isBetween(resultInt, 25, Integer.MAX_VALUE)) {
			message = "Obese";
		} else {
			message = "Check your results again.";
		}
		
		return message;
	}
	
	private static boolean isBetween(int result, int low, int high) {
		return low <= result && result <= high;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
