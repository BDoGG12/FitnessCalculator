package model;


public class FitnessModel {
    private double weight;
    private double height;
    private int age;
    private String gender;
    public double bmi;
    public double bodyFat;

    public FitnessModel(double weight, double height, int age, String gender) {
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
    }

    public double calculateBMI() {
        this.bmi = weight / (height * height);
        return this.bmi;
    }

    public double calculateBodyFat() {
        this.bmi = calculateBMI();
        if (gender.equalsIgnoreCase("Male")) {
            this.bodyFat = (1.20 * bmi) + (0.23 * age) - 16.2;
        } else {
            this.bodyFat = (1.20 * bmi) + (0.23 * age) - 5.4;
        }
        return this.bodyFat;
    }

    public String getBMICategory() {
        this.bmi = calculateBMI();

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
