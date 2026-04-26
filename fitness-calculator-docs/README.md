# 🏋️ FitnessCalculator

A Java Swing desktop application that calculates **BMI (Body Mass Index)** and **Body Fat Percentage** from user-provided health data, displays results in a clean multi-screen GUI, and supports exporting results to a CSV file.

---

## 📋 Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Setup & Installation](#setup--installation)
- [Running the Application](#running-the-application)
- [Usage Examples](#usage-examples)
- [CSV Export](#csv-export)
- [Known Limitations](#known-limitations)
- [Contributing](#contributing)

---

## Project Overview

FitnessCalculator is a desktop GUI application built with **Java Swing**. It guides the user through a simple multi-screen flow:

1. **Home Screen** — Welcome screen with a Start button.
2. **Input Screen** — User enters weight (kg), height (m), age, and gender.
3. **Results Screen** — Displays computed BMI, body fat percentage, and BMI category.
4. **CSV Export** — Optionally save results to a `.csv` file for record-keeping.

The application uses the **MVC (Model-View-Controller)** architectural pattern, keeping business logic, UI components, and navigation control clearly separated.

---

## Features

- ✅ **BMI Calculation** — Computes Body Mass Index using weight (kg) and height (m).
- ✅ **Body Fat Percentage** — Estimates body fat using the Deurenberg formula, adjusted for gender and age.
- ✅ **BMI Category Classification** — Classifies result as Underweight, Normal weight, Overweight, or Obese.
- ✅ **Multi-Screen Navigation** — Smooth panel switching using `CardLayout`.
- ✅ **Input Validation** — Alerts user if non-numeric values are entered.
- ✅ **CSV Export** — Save results (date, BMI, body fat %) to a `.csv` file via a native file-save dialog.
- ✅ **Cross-Platform** — Runs on any OS with a compatible JDK.

---

## Project Structure

```
FitnessCalculator/
├── src/
│   ├── FitnessCalculator.java          # Application entry point (main method)
│   ├── main/
│   │   └── MainFrame.java              # Root JFrame, manages CardLayout panels
│   ├── controller/
│   │   └── FitnessController.java      # Event handlers and application logic
│   ├── model/
│   │   └── FitnessModel.java           # BMI & body fat calculation logic
│   ├── view/
│   │   ├── HomeView.java               # Welcome / landing screen
│   │   ├── InputView.java              # Data entry form (metric units)
│   │   ├── BMIInputView.java           # Alternate input form (imperial units)
│   │   ├── MethodSelectView.java       # Calculation method selector (dropdown)
│   │   └── ResultsView.java            # Results display screen
│   └── FileSaveManager/
│       ├── FileCsvSaver.java           # Writes results to a CSV file
│       └── SaveCsvDialog.java          # Native file-save dialog wrapper
├── bin/                                # Compiled .class files (Eclipse output)
├── .classpath
├── .project
└── .gitignore
```

---

## Prerequisites

| Requirement | Version |
|-------------|---------|
| Java JDK    | 8 or later |
| IDE (optional) | Eclipse IDE for Java Developers |

---

## Setup & Installation

### Option A — Eclipse IDE (Recommended)

1. **Clone the repository:**
   ```bash
   git clone https://github.com/BDoGG12/FitnessCalculator.git
   ```

2. **Open Eclipse** and go to `File → Import → Existing Projects into Workspace`.

3. **Browse** to the cloned `FitnessCalculator` directory and click **Finish**.

4. Eclipse will auto-detect the `.classpath` and `.project` files and configure the project.

### Option B — Command Line

1. **Clone the repository:**
   ```bash
   git clone https://github.com/BDoGG12/FitnessCalculator.git
   cd FitnessCalculator
   ```

2. **Compile all source files:**
   ```bash
   mkdir -p bin
   javac -d bin -sourcepath src src/FitnessCalculator.java \
     src/main/MainFrame.java \
     src/controller/FitnessController.java \
     src/model/FitnessModel.java \
     src/view/*.java \
     src/FileSaveManager/*.java
   ```

3. **Run the application:**
   ```bash
   java -cp bin FitnessCalculator
   ```

---

## Running the Application

### From Eclipse
Right-click `FitnessCalculator.java` → **Run As → Java Application**.

### From the Command Line
```bash
java -cp bin FitnessCalculator
```

A 1000×1000 pixel window will open showing the Home screen.

---

## Usage Examples

### Step 1 — Home Screen
Click **Start** to navigate to the input form.

### Step 2 — Enter Your Data

| Field      | Example Value | Notes                      |
|------------|---------------|----------------------------|
| Weight     | `70`          | In kilograms (kg)          |
| Height     | `1.75`        | In meters (m)              |
| Age        | `25`          | Whole number               |
| Gender     | `Male`        | Select from dropdown       |

Click **Calculate** to compute your results.

### Step 3 — View Results

The Results screen displays:

```
BMI:            22.86
Body Fat %:     18.22
BMI Category:   Normal weight
```

Use **Back** to edit inputs, **Home** to return to the start, or **Download Results** to save a CSV.

---

## CSV Export

Clicking **Download Results** opens a native save dialog. Choose a location and file name — `.csv` is appended automatically if not present.

The CSV format is:

```csv
Date,BMI,BodyFat
2026-04-26,22.857142857142858,18.22
```

Each subsequent save **appends** a new row to the same file (header is written only once), making it easy to track results over time.

---

## Known Limitations

- `BMIInputView.java` (imperial units: lbs/inches) is defined but not currently wired into the main navigation flow.
- `MethodSelectView.java` (calculation method dropdown) is defined but not actively used in the current UI.
- The window size is fixed at 1000×1000 pixels and is not resizable.
- Body fat estimation uses the Deurenberg formula, which is an approximation.

---

## Contributing

1. Fork the repository.
2. Create a feature branch: `git checkout -b feature/my-feature`
3. Commit your changes: `git commit -m "Add my feature"`
4. Push: `git push origin feature/my-feature`
5. Open a Pull Request.

---

## License

This project is open source. See the repository for license details.
