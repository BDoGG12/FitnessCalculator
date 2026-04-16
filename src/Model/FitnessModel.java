package Model;

public class FitnessModel {
    private double weight;
    private double height;
    private int age;
    private String gender;

    public FitnessModel(double weight, double height, int age, String gender) {
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
    }

    public double calculateBMI() {
        return weight / (height * height);
    }

    public double calculateBodyFat() {
        double bmi = calculateBMI();
        if (gender.equalsIgnoreCase("Male")) {
            return (1.20 * bmi) + (0.23 * age) - 16.2;
        } else {
            return (1.20 * bmi) + (0.23 * age) - 5.4;
        }
    }

    public String getBMICategory() {
        double bmi = calculateBMI();

        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal weight";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
}
