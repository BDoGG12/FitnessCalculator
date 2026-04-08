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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
