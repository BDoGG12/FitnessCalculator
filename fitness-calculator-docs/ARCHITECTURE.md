# Architecture Overview — FitnessCalculator

## Design Pattern: MVC (Model-View-Controller)

FitnessCalculator follows the classic **Model-View-Controller** architecture, keeping the three concerns cleanly separated:

| Layer | Package | Responsibility |
|-------|---------|----------------|
| **Model** | `model` | Business logic and data (calculations) |
| **View** | `view`, `main` | Swing UI panels and the root window |
| **Controller** | `controller` | Event handling, navigation, wiring |
| **Utility** | `FileSaveManager` | File I/O (CSV export) |

---

## Component Diagram

```
┌─────────────────────────────────────────────────────────────────┐
│                        FitnessCalculator                        │
│                     (Application Entry Point)                   │
│   Creates MainFrame, creates FitnessController(mainFrame)       │
└───────────────────────────┬─────────────────────────────────────┘
                            │ creates & owns
                            ▼
┌─────────────────────────────────────────────────────────────────┐
│                          MainFrame                              │
│                  (JFrame + CardLayout host)                      │
│                                                                 │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────────────┐  │
│  │  HomeView    │  │  InputView   │  │    ResultsView       │  │
│  │  ("HOME")    │  │  ("INPUT")   │  │    ("RESULT")        │  │
│  └──────────────┘  └──────────────┘  └──────────────────────┘  │
└──────────────────────────┬──────────────────────────────────────┘
                           │ references
                           ▼
┌─────────────────────────────────────────────────────────────────┐
│                     FitnessController                           │
│                                                                 │
│  • Listens to button clicks on all views                        │
│  • Calls mainFrame.showView(...) for navigation                 │
│  • Instantiates FitnessModel with user input                    │
│  • Calls model.calculateBMI(), calculateBodyFat(),              │
│    getBMICategory()                                             │
│  • Calls resultsView.updateResults(...)                         │
│  • Delegates CSV export to FileSaveManager classes              │
└──────────────────────────┬──────────────────────────────────────┘
                           │ creates & calls
                           ▼
┌──────────────────────────┐    ┌──────────────────────────────┐
│      FitnessModel        │    │      FileSaveManager         │
│                          │    │                              │
│  - weight, height,       │    │  SaveCsvDialog               │
│    age, gender           │    │  → shows JFileChooser        │
│  + calculateBMI()        │    │  → returns file path         │
│  + calculateBodyFat()    │    │                              │
│  + getBMICategory()      │    │  FileCsvSaver                │
│                          │    │  → appends row to .csv       │
└──────────────────────────┘    └──────────────────────────────┘
```

---

## Navigation Flow

```
  ┌──────────┐
  │  Home    │  ← App starts here
  │  View    │
  └────┬─────┘
       │ Start button
       ▼
  ┌──────────┐
  │  Input   │  ← User enters weight, height, age, gender
  │  View    │
  └────┬─────┘
       │ Calculate button
       │ (FitnessController creates FitnessModel,
       │  runs calculations, calls resultsView.updateResults)
       ▼
  ┌──────────┐
  │ Results  │  ← Shows BMI, Body Fat %, Category
  │  View    │
  └──┬───┬───┘
     │   │
     │   │ Download Results button
     │   └──────────────────────────────────┐
     │                                      ▼
     │                              ┌───────────────┐
     │                              │ SaveCsvDialog │
     │                              │ (JFileChooser)│
     │                              └───────┬───────┘
     │                                      │ path selected
     │                                      ▼
     │                              ┌───────────────┐
     │                              │ FileCsvSaver  │
     │                              │ (writes .csv) │
     │                              └───────────────┘
     │
     │ Back button → Input View
     │ Home button → Home View
```

---

## Class Interactions

### Startup Sequence

1. `FitnessCalculator.main()` runs on the Swing EDT.
2. `new MainFrame()` is created — it constructs `HomeView`, `InputView`, and `ResultsView`, registers them with `CardLayout`, and shows `"HOME"` by default.
3. `new FitnessController(frame)` is created — it registers all button listeners via `initController()`.

### Calculation Sequence

1. User fills in the form on `InputView` and clicks **Calculate**.
2. `FitnessController` reads text from `InputView`'s fields and parses them.
3. A new `FitnessModel` is instantiated with the parsed values.
4. The controller calls `model.calculateBMI()`, `model.calculateBodyFat()`, and `model.getBMICategory()`.
5. The results are pushed to `ResultsView.updateResults(bmi, bodyFat, category)`.
6. `mainFrame.showView("RESULT")` switches the visible panel.

### Save Sequence

1. User clicks **Download Results** on `ResultsView`.
2. `FitnessController.handleSave()` calls `SaveCsvDialog.chooseFilePath()`.
3. If a path is returned, `FileCsvSaver.saveResult(path, bmi, bodyFat)` appends a row to the file.

---

## Class Relationships Summary

| Class | Depends On | Type of Relationship |
|-------|-----------|---------------------|
| `FitnessCalculator` | `MainFrame`, `FitnessController` | Creates |
| `MainFrame` | `HomeView`, `InputView`, `ResultsView` | Owns / composes |
| `FitnessController` | `MainFrame`, `FitnessModel`, `FileCsvSaver`, `SaveCsvDialog` | Uses / creates |
| `FitnessModel` | _(none — pure logic)_ | Standalone |
| `ResultsView` | _(none)_ | Pure Swing component |
| `InputView` | _(none)_ | Pure Swing component |
| `HomeView` | _(none)_ | Pure Swing component |
| `FileCsvSaver` | `java.io.FileWriter`, `java.time.LocalDate` | Uses stdlib |
| `SaveCsvDialog` | `javax.swing.JFileChooser` | Wraps Swing |

---

## Unused / In-Progress Components

The following classes are defined but not yet connected to the live navigation flow:

- **`BMIInputView`** — An alternate input form with imperial unit labels (lbs/inches). Intended to be used alongside or instead of `InputView` for users who prefer imperial measurements.
- **`MethodSelectView`** — A dropdown panel to choose between "BMI" and "Body Fat Percentage" calculation modes. Intended to sit between `HomeView` and the input forms to route the user to the appropriate input screen.
