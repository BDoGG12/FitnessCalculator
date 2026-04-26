# Javadoc Comments — FitnessCalculator

Drop these Javadoc comment blocks directly above their respective class/method declarations in each source file.

---

## `FitnessCalculator.java`

```java
/**
 * Entry point for the FitnessCalculator application.
 *
 * <p>This class contains the {@code main} method, which bootstraps the
 * Java Swing GUI on the Event Dispatch Thread (EDT) using
 * {@link javax.swing.SwingUtilities#invokeLater}. It creates the primary
 * {@link main.MainFrame} window and wires it to a
 * {@link controller.FitnessController} before making the frame visible.</p>
 *
 * @author BDoGG12
 * @version 1.0
 */
public class FitnessCalculator {

    /**
     * Application entry point. Launches the Swing GUI on the EDT.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) { ... }
}
```

---

## `main/MainFrame.java`

```java
package main;

/**
 * The primary application window for FitnessCalculator.
 *
 * <p>{@code MainFrame} extends {@link javax.swing.JFrame} and serves as the
 * root container for all views. It uses a {@link java.awt.CardLayout} to
 * manage three screens — Home, Input, and Results — allowing seamless
 * panel-based navigation without opening multiple windows.</p>
 *
 * <p>The frame is fixed at 1000×1000 pixels and is not resizable.</p>
 *
 * @author BDoGG12
 * @version 1.0
 * @see view.HomeView
 * @see view.InputView
 * @see view.ResultsView
 * @see controller.FitnessController
 */
public class MainFrame extends JFrame {

    /**
     * Constructs and initialises the main application window.
     *
     * <p>Sets the preferred, minimum, and maximum window size to 1000×1000
     * pixels, configures a {@code CardLayout} panel, instantiates all view
     * components ({@link view.HomeView}, {@link view.InputView},
     * {@link view.ResultsView}), registers them with the card panel under the
     * keys {@code "HOME"}, {@code "INPUT"}, and {@code "RESULT"}, and sets
     * the default close operation to {@link javax.swing.JFrame#EXIT_ON_CLOSE}.</p>
     */
    public MainFrame() { ... }

    /**
     * Switches the visible panel in the {@code CardLayout} to the specified view.
     *
     * @param viewName the card name of the view to display;
     *                 valid values are {@code "HOME"}, {@code "INPUT"}, or {@code "RESULT"}
     */
    public void showView(String viewName) { ... }

    /**
     * Returns the {@link view.MethodSelectView} instance (currently unused in navigation).
     *
     * @return the method-selection view, or {@code null} if not initialised
     */
    public MethodSelectView getMethodSelectView() { ... }

    /**
     * Returns the {@link view.HomeView} instance.
     *
     * @return the home/landing screen view
     */
    public HomeView getHomeView() { ... }

    /**
     * Returns the {@link view.InputView} instance.
     *
     * @return the user data input form view
     */
    public InputView getInputView() { ... }

    /**
     * Returns the {@link view.ResultsView} instance.
     *
     * @return the calculation results display view
     */
    public ResultsView getResultsView() { ... }
}
```

---

## `controller/FitnessController.java`

```java
package controller;

/**
 * MVC Controller for the FitnessCalculator application.
 *
 * <p>{@code FitnessController} mediates between the {@link main.MainFrame}
 * (views) and the {@link model.FitnessModel} (business logic). It registers
 * all action listeners on the view components and orchestrates screen
 * transitions, calculation triggers, and CSV export.</p>
 *
 * <p>On construction, {@link #initController()} is called automatically to
 * attach all event handlers.</p>
 *
 * @author BDoGG12
 * @version 1.0
 * @see model.FitnessModel
 * @see main.MainFrame
 */
public class FitnessController {

    /**
     * The shared data model holding calculation inputs and computed results.
     * Exposed as {@code public} to allow direct field access by helper methods.
     */
    public FitnessModel model;

    /**
     * Constructs a {@code FitnessController} and immediately attaches all
     * event listeners to the provided {@link main.MainFrame}.
     *
     * @param mainFrame the root application window whose view components
     *                  will be wired with action listeners
     */
    public FitnessController(MainFrame mainFrame) { ... }

    /**
     * Registers all action listeners on the view components inside
     * {@link main.MainFrame}.
     *
     * <p>The following listeners are registered:</p>
     * <ul>
     *   <li><b>HomeView → Start button</b>: navigates to the Input screen.</li>
     *   <li><b>InputView → Back button</b>: navigates to the Home screen.</li>
     *   <li><b>InputView → Calculate button</b>: parses user input, creates a
     *       new {@link model.FitnessModel}, computes BMI and body fat, updates
     *       {@link view.ResultsView}, and navigates to the Results screen.
     *       Displays an error dialog on invalid (non-numeric) input.</li>
     *   <li><b>ResultsView → Back button</b>: navigates back to the Input screen.</li>
     *   <li><b>ResultsView → Home button</b>: navigates to the Home screen.</li>
     *   <li><b>ResultsView → Download button</b>: triggers {@link #handleSave()}.</li>
     * </ul>
     */
    private void initController() { ... }

    /**
     * Handles saving the current calculation results to a CSV file.
     *
     * <p>Opens a {@link FileSaveManager.SaveCsvDialog} to let the user choose a
     * file path. If a path is selected, delegates to
     * {@link FileSaveManager.FileCsvSaver#saveResult(String, double, double)}
     * to write the BMI and body fat values. Does nothing if the dialog is
     * cancelled.</p>
     */
    public void handleSave() { ... }
}
```

---

## `model/FitnessModel.java`

```java
package model;

/**
 * Data model and calculation engine for fitness metrics.
 *
 * <p>{@code FitnessModel} encapsulates a user's physical attributes —
 * weight, height, age, and gender — and provides methods to compute
 * BMI, body fat percentage, and BMI category classification.</p>
 *
 * <p><b>Formulas used:</b></p>
 * <ul>
 *   <li><b>BMI</b>: {@code weight (kg) / (height (m))²}</li>
 *   <li><b>Body Fat % (Male)</b>: {@code (1.20 × BMI) + (0.23 × age) − 16.2}</li>
 *   <li><b>Body Fat % (Female)</b>: {@code (1.20 × BMI) + (0.23 × age) − 5.4}</li>
 * </ul>
 *
 * <p>Body fat estimation is based on the Deurenberg formula.</p>
 *
 * @author BDoGG12
 * @version 1.0
 */
public class FitnessModel {

    /** The most recently computed BMI value. Updated by {@link #calculateBMI()}. */
    public double bmi;

    /** The most recently computed body fat percentage. Updated by {@link #calculateBodyFat()}. */
    public double bodyFat;

    /**
     * Constructs a {@code FitnessModel} with the user's physical attributes.
     *
     * @param weight the user's weight in kilograms (kg)
     * @param height the user's height in meters (m)
     * @param age    the user's age in years
     * @param gender the user's gender; expected values are {@code "Male"} or {@code "Female"}
     *               (case-insensitive)
     */
    public FitnessModel(double weight, double height, int age, String gender) { ... }

    /**
     * Calculates and returns the Body Mass Index (BMI).
     *
     * <p>Formula: {@code BMI = weight / (height * height)}</p>
     *
     * <p>Also updates the {@link #bmi} field as a side effect.</p>
     *
     * @return the computed BMI as a {@code double}
     */
    public double calculateBMI() { ... }

    /**
     * Calculates and returns the estimated body fat percentage.
     *
     * <p>Uses the Deurenberg formula with a gender-specific constant:</p>
     * <ul>
     *   <li>Male:   {@code (1.20 × BMI) + (0.23 × age) − 16.2}</li>
     *   <li>Female: {@code (1.20 × BMI) + (0.23 × age) − 5.4}</li>
     * </ul>
     *
     * <p>Internally calls {@link #calculateBMI()} before computing.
     * Also updates the {@link #bodyFat} and {@link #bmi} fields as side effects.</p>
     *
     * @return the estimated body fat percentage as a {@code double}
     */
    public double calculateBodyFat() { ... }

    /**
     * Determines and returns the WHO BMI category for the current weight and height.
     *
     * <p>Classification thresholds:</p>
     * <ul>
     *   <li>{@code BMI < 18.5}  → {@code "Underweight"}</li>
     *   <li>{@code 18.5 ≤ BMI < 25} → {@code "Normal weight"}</li>
     *   <li>{@code 25 ≤ BMI < 30} → {@code "Overweight"}</li>
     *   <li>{@code BMI ≥ 30}    → {@code "Obese"}</li>
     * </ul>
     *
     * <p>Also updates the {@link #bmi} field as a side effect.</p>
     *
     * @return a {@code String} describing the BMI category
     */
    public String getBMICategory() { ... }
}
```

---

## `view/HomeView.java`

```java
package view;

/**
 * The welcome/landing screen of the FitnessCalculator application.
 *
 * <p>{@code HomeView} is a {@link javax.swing.JPanel} containing a bold
 * title label ("Welcome to Fitness Calculator") and a Start button that
 * initiates the user flow. It uses a {@link java.awt.GridLayout}.</p>
 *
 * @author BDoGG12
 * @version 1.0
 * @see controller.FitnessController
 */
public class HomeView extends JPanel {

    /**
     * Constructs the {@code HomeView} panel with a title label and a Start button,
     * arranged in a 3×2 GridLayout.
     */
    public HomeView() { ... }

    /**
     * Returns the Start button so the controller can attach an action listener.
     *
     * @return the {@link javax.swing.JButton} labelled "Start"
     */
    public JButton getStartButton() { ... }
}
```

---

## `view/InputView.java`

```java
package view;

/**
 * The user data input form for the FitnessCalculator application (metric units).
 *
 * <p>{@code InputView} is a {@link javax.swing.JPanel} that collects:
 * weight (kg), height (m), age, and gender. It uses a 6×2
 * {@link java.awt.GridLayout} and exposes all input components via
 * getter methods for the controller to read and listen to.</p>
 *
 * @author BDoGG12
 * @version 1.0
 * @see controller.FitnessController
 */
public class InputView extends JPanel {

    /**
     * Constructs the {@code InputView} panel with labelled text fields,
     * a gender dropdown, a Back button, and a Calculate button.
     */
    public InputView() { ... }

    /**
     * Returns the weight input field.
     * @return {@link javax.swing.JTextField} for weight in kilograms
     */
    public JTextField getWeightField() { ... }

    /**
     * Returns the height input field.
     * @return {@link javax.swing.JTextField} for height in meters
     */
    public JTextField getHeightField() { ... }

    /**
     * Returns the age input field.
     * @return {@link javax.swing.JTextField} for age in years
     */
    public JTextField getAgeField() { ... }

    /**
     * Returns the gender selection dropdown.
     * @return {@link javax.swing.JComboBox} with options "Male" and "Female"
     */
    public JComboBox<String> getGenderBox() { ... }

    /**
     * Returns the Calculate button.
     * @return {@link javax.swing.JButton} labelled "Calculate"
     */
    public JButton getCalculateBtn() { ... }

    /**
     * Returns the Back button.
     * @return {@link javax.swing.JButton} labelled "Back"
     */
    public JButton getBackBtn() { ... }
}
```

---

## `view/BMIInputView.java`

```java
package view;

/**
 * An alternate user data input form using imperial units (lbs / inches).
 *
 * <p>{@code BMIInputView} mirrors the structure of {@link InputView} but
 * labels its fields for pounds (lbs) and inches (in). It is currently
 * defined but not wired into the main navigation flow.</p>
 *
 * @author BDoGG12
 * @version 1.0
 */
public class BMIInputView extends JPanel {

    /**
     * Constructs the {@code BMIInputView} panel with labelled text fields
     * for imperial weight and height, an age field, a gender dropdown,
     * a Back button, and a Calculate button.
     */
    public BMIInputView() { ... }

    /** @return {@link javax.swing.JTextField} for weight in pounds */
    public JTextField getWeightField() { ... }

    /** @return {@link javax.swing.JTextField} for height in inches */
    public JTextField getHeightField() { ... }

    /** @return {@link javax.swing.JTextField} for age in years */
    public JTextField getAgeField() { ... }

    /** @return {@link javax.swing.JComboBox} with options "Male" and "Female" */
    public JComboBox<String> getGenderBox() { ... }

    /** @return {@link javax.swing.JButton} labelled "Calculate" */
    public JButton getCalculateButton() { ... }

    /** @return {@link javax.swing.JButton} labelled "Back" */
    public JButton getBackButton() { ... }
}
```

---

## `view/MethodSelectView.java`

```java
package view;

/**
 * A panel for selecting the fitness calculation method.
 *
 * <p>{@code MethodSelectView} displays a title label and a
 * {@link javax.swing.JComboBox} offering "BMI" and "Body Fat Percentage"
 * as selectable calculation modes. Currently defined but not integrated
 * into the main navigation flow.</p>
 *
 * @author BDoGG12
 * @version 1.0
 */
public class MethodSelectView extends JPanel {

    /**
     * Constructs the {@code MethodSelectView} panel with a title label
     * and a method-selection dropdown.
     */
    public MethodSelectView() { ... }
}
```

---

## `view/ResultsView.java`

```java
package view;

/**
 * The results display screen for the FitnessCalculator application.
 *
 * <p>{@code ResultsView} is a {@link javax.swing.JPanel} that shows the
 * computed BMI, body fat percentage, and BMI category after a calculation.
 * It provides Back, Home, and Download Results navigation buttons.
 * Uses a 5×1 {@link java.awt.GridLayout}.</p>
 *
 * @author BDoGG12
 * @version 1.0
 * @see controller.FitnessController
 * @see model.FitnessModel
 */
public class ResultsView extends JPanel {

    /**
     * Constructs the {@code ResultsView} panel with placeholder labels for
     * BMI, body fat, and category, and three navigation/action buttons.
     */
    public ResultsView() { ... }

    /**
     * Updates the result labels with newly computed values.
     *
     * <p>Formats BMI and body fat to two decimal places.</p>
     *
     * @param bmi      the computed BMI value
     * @param bodyFat  the computed body fat percentage
     * @param category the BMI category string (e.g., "Normal weight")
     */
    public void updateResults(double bmi, double bodyFat, String category) { ... }

    /**
     * Returns the Back button (navigates to the Input screen).
     * @return {@link javax.swing.JButton} labelled "Back"
     */
    public JButton getBackButton() { ... }

    /**
     * Returns the Home button (navigates to the Home screen).
     * @return {@link javax.swing.JButton} labelled "Home"
     */
    public JButton getHomeButton() { ... }

    /**
     * Returns the Download Results button (triggers CSV export).
     * @return {@link javax.swing.JButton} labelled "Download Results"
     */
    public JButton getDownloadButton() { ... }

    /**
     * Replaces the Download Results button with a custom button instance.
     *
     * @param downloadButton the replacement {@link javax.swing.JButton}
     */
    public void setDownloadButton(JButton downloadButton) { ... }
}
```

---

## `FileSaveManager/FileCsvSaver.java`

```java
package FileSaveManager;

/**
 * Utility class for saving fitness calculation results to a CSV file.
 *
 * <p>{@code FileCsvSaver} provides a single static method to append a
 * dated result row to a CSV file. If the file does not exist, a header
 * row is written first. All methods are static; this class is not
 * meant to be instantiated.</p>
 *
 * <p>CSV format:</p>
 * <pre>
 * Date,BMI,BodyFat
 * 2026-04-26,22.857142857142858,18.22
 * </pre>
 *
 * @author BDoGG12
 * @version 1.0
 * @see SaveCsvDialog
 */
public class FileCsvSaver {

    /**
     * Appends a fitness result record to the specified CSV file.
     *
     * <p>If the file does not yet exist, the header
     * {@code "Date,BMI,BodyFat"} is written first. Each subsequent call
     * appends a new row with today's date (ISO-8601), BMI, and body fat
     * values. The file is opened in append mode to preserve prior records.</p>
     *
     * @param filePath the absolute path to the target {@code .csv} file
     * @param bmi      the BMI value to record
     * @param bodyFat  the body fat percentage to record
     */
    public static void saveResult(String filePath, double bmi, double bodyFat) { ... }
}
```

---

## `FileSaveManager/SaveCsvDialog.java`

```java
package FileSaveManager;

/**
 * Utility class that presents a native file-save dialog for CSV output.
 *
 * <p>{@code SaveCsvDialog} wraps {@link javax.swing.JFileChooser} to let the
 * user choose a destination path for saving results. It automatically appends
 * the {@code .csv} extension if the user omits it. All methods are static;
 * this class is not meant to be instantiated.</p>
 *
 * @author BDoGG12
 * @version 1.0
 * @see FileCsvSaver
 */
public class SaveCsvDialog {

    /**
     * Opens a "Save CSV File" dialog and returns the chosen file path.
     *
     * <p>If the selected file name does not end with {@code ".csv"}, the
     * extension is appended automatically. Returns {@code null} if the user
     * cancels the dialog.</p>
     *
     * @return the absolute path of the chosen file (with {@code .csv}
     *         extension guaranteed), or {@code null} if the dialog was cancelled
     */
    public static String chooseFilePath() { ... }
}
```
