# API Reference — FitnessCalculator

All public methods across every class, with parameter types, return types, and descriptions.

---

## `FitnessCalculator` _(default package)_

### `main(String[] args) → void`
| | |
|---|---|
| **Access** | `public static` |
| **Parameters** | `args` — command-line arguments (unused) |
| **Returns** | `void` |
| **Description** | Application entry point. Schedules creation of `MainFrame` and `FitnessController` on the Swing EDT via `SwingUtilities.invokeLater`. |

---

## `main.MainFrame`

Extends `javax.swing.JFrame`.

### `MainFrame()`
| | |
|---|---|
| **Access** | `public` |
| **Parameters** | _(none)_ |
| **Returns** | — (constructor) |
| **Description** | Initialises the 1000×1000 non-resizable window, sets up `CardLayout`, and adds `HomeView`, `InputView`, and `ResultsView` under the card keys `"HOME"`, `"INPUT"`, and `"RESULT"` respectively. |

---

### `showView(String viewName) → void`
| | |
|---|---|
| **Access** | `public` |
| **Parameters** | `viewName` — card key of the panel to show; valid values: `"HOME"`, `"INPUT"`, `"RESULT"` |
| **Returns** | `void` |
| **Description** | Calls `CardLayout.show()` to switch the currently visible panel. |

---

### `getHomeView() → HomeView`
| | |
|---|---|
| **Access** | `public` |
| **Parameters** | _(none)_ |
| **Returns** | `view.HomeView` — the welcome panel |
| **Description** | Returns the `HomeView` instance held by this frame. |

---

### `getInputView() → InputView`
| | |
|---|---|
| **Access** | `public` |
| **Parameters** | _(none)_ |
| **Returns** | `view.InputView` — the data entry panel |
| **Description** | Returns the `InputView` instance held by this frame. |

---

### `getResultsView() → ResultsView`
| | |
|---|---|
| **Access** | `public` |
| **Parameters** | _(none)_ |
| **Returns** | `view.ResultsView` — the results display panel |
| **Description** | Returns the `ResultsView` instance held by this frame. |

---

### `getMethodSelectView() → MethodSelectView`
| | |
|---|---|
| **Access** | `public` |
| **Parameters** | _(none)_ |
| **Returns** | `view.MethodSelectView` — the method selector panel (may be `null`) |
| **Description** | Returns the `MethodSelectView` instance. Currently `null` as it is not initialised in the constructor. |

---

## `controller.FitnessController`

### `FitnessController(MainFrame mainFrame)`
| | |
|---|---|
| **Access** | `public` |
| **Parameters** | `mainFrame` — the root application window |
| **Returns** | — (constructor) |
| **Description** | Stores a reference to `mainFrame` and immediately calls `initController()` to wire all action listeners. |

---

### `handleSave() → void`
| | |
|---|---|
| **Access** | `public` |
| **Parameters** | _(none)_ |
| **Returns** | `void` |
| **Description** | Opens a save dialog via `SaveCsvDialog.chooseFilePath()`. If the user selects a path, reads `model.bmi` and `model.bodyFat` and delegates to `FileCsvSaver.saveResult()`. No-op if the dialog is cancelled. |

---

### Public Field: `model`
| | |
|---|---|
| **Type** | `model.FitnessModel` |
| **Access** | `public` |
| **Description** | The active data model created after the user clicks Calculate. Holds the current calculation inputs and results. `null` before the first calculation. |

---

## `model.FitnessModel`

### `FitnessModel(double weight, double height, int age, String gender)`
| | |
|---|---|
| **Access** | `public` |
| **Parameters** | `weight` — body weight in kg · `height` — height in meters · `age` — age in years · `gender` — `"Male"` or `"Female"` (case-insensitive) |
| **Returns** | — (constructor) |
| **Description** | Stores the user's physical attributes for use in subsequent calculation methods. |

---

### `calculateBMI() → double`
| | |
|---|---|
| **Access** | `public` |
| **Parameters** | _(none)_ |
| **Returns** | `double` — the computed BMI |
| **Side Effect** | Sets the public field `bmi` |
| **Formula** | `BMI = weight / (height × height)` |
| **Description** | Computes and returns the Body Mass Index. |

---

### `calculateBodyFat() → double`
| | |
|---|---|
| **Access** | `public` |
| **Parameters** | _(none)_ |
| **Returns** | `double` — the estimated body fat percentage |
| **Side Effect** | Sets the public fields `bmi` and `bodyFat` |
| **Formula (Male)** | `(1.20 × BMI) + (0.23 × age) − 16.2` |
| **Formula (Female)** | `(1.20 × BMI) + (0.23 × age) − 5.4` |
| **Description** | Estimates body fat percentage using the Deurenberg formula. Internally calls `calculateBMI()` first. |

---

### `getBMICategory() → String`
| | |
|---|---|
| **Access** | `public` |
| **Parameters** | _(none)_ |
| **Returns** | `String` — one of: `"Underweight"`, `"Normal weight"`, `"Overweight"`, `"Obese"` |
| **Side Effect** | Sets the public field `bmi` |
| **Description** | Classifies the current BMI according to WHO thresholds: <18.5 Underweight, 18.5–24.9 Normal, 25–29.9 Overweight, ≥30 Obese. |

---

### Public Fields
| Field | Type | Description |
|-------|------|-------------|
| `bmi` | `double` | Last computed BMI. Updated by `calculateBMI()`, `calculateBodyFat()`, and `getBMICategory()`. |
| `bodyFat` | `double` | Last computed body fat %. Updated by `calculateBodyFat()`. |

---

## `view.HomeView`

Extends `javax.swing.JPanel`.

### `HomeView()`
| | |
|---|---|
| **Access** | `public` |
| **Parameters** | _(none)_ |
| **Description** | Builds the welcome panel with a bold title label and a "Start" button. |

### `getStartButton() → JButton`
| | |
|---|---|
| **Access** | `public` |
| **Returns** | `javax.swing.JButton` labelled "Start" |
| **Description** | Returns the Start button for attaching an action listener. |

---

## `view.InputView`

Extends `javax.swing.JPanel`.

### `InputView()`
| | |
|---|---|
| **Access** | `public` |
| **Parameters** | _(none)_ |
| **Description** | Builds a 6×2 grid form with weight, height, age fields, a gender dropdown, and Back / Calculate buttons. |

### `getWeightField() → JTextField`
| | |
|---|---|
| **Access** | `public` |
| **Returns** | `javax.swing.JTextField` |
| **Description** | Returns the weight input field (expects kg as a decimal number). |

### `getHeightField() → JTextField`
| | |
|---|---|
| **Access** | `public` |
| **Returns** | `javax.swing.JTextField` |
| **Description** | Returns the height input field (expects meters as a decimal number). |

### `getAgeField() → JTextField`
| | |
|---|---|
| **Access** | `public` |
| **Returns** | `javax.swing.JTextField` |
| **Description** | Returns the age input field (expects a whole number). |

### `getGenderBox() → JComboBox<String>`
| | |
|---|---|
| **Access** | `public` |
| **Returns** | `javax.swing.JComboBox<String>` |
| **Description** | Returns the gender dropdown; selected item will be either `"Male"` or `"Female"`. |

### `getCalculateBtn() → JButton`
| | |
|---|---|
| **Access** | `public` |
| **Returns** | `javax.swing.JButton` labelled "Calculate" |
| **Description** | Returns the Calculate button for attaching the calculation trigger. |

### `getBackBtn() → JButton`
| | |
|---|---|
| **Access** | `public` |
| **Returns** | `javax.swing.JButton` labelled "Back" |
| **Description** | Returns the Back button for attaching the navigation listener. |

---

## `view.BMIInputView`

Extends `javax.swing.JPanel`. _(Imperial units — currently unused in navigation)_

### `BMIInputView()`
| | |
|---|---|
| **Access** | `public` |
| **Description** | Builds an input form with fields labelled for pounds and inches. |

### `getWeightField() → JTextField`
Returns the weight field (lbs).

### `getHeightField() → JTextField`
Returns the height field (inches).

### `getAgeField() → JTextField`
Returns the age field.

### `getGenderBox() → JComboBox<String>`
Returns the gender dropdown.

### `getCalculateButton() → JButton`
Returns the Calculate button.

### `getBackButton() → JButton`
Returns the Back button.

---

## `view.MethodSelectView`

Extends `javax.swing.JPanel`. _(Currently unused in navigation)_

### `MethodSelectView()`
| | |
|---|---|
| **Access** | `public` |
| **Description** | Builds a panel with a title and a dropdown offering `"BMI"` and `"Body Fat Percentage"`. No public getters are currently exposed. |

---

## `view.ResultsView`

Extends `javax.swing.JPanel`.

### `ResultsView()`
| | |
|---|---|
| **Access** | `public` |
| **Description** | Builds the 5×1 results panel with BMI, body fat, and category labels, plus Back, Home, and Download Results buttons. |

### `updateResults(double bmi, double bodyFat, String category) → void`
| | |
|---|---|
| **Access** | `public` |
| **Parameters** | `bmi` — the BMI value · `bodyFat` — the body fat % · `category` — the BMI category string |
| **Returns** | `void` |
| **Description** | Updates the three display labels with formatted values (`%.2f` for numerics). |

### `getBackButton() → JButton`
Returns the Back button (navigates to Input screen).

### `getHomeButton() → JButton`
Returns the Home button (navigates to Home screen).

### `getDownloadButton() → JButton`
Returns the Download Results button (triggers CSV export).

### `setDownloadButton(JButton downloadButton) → void`
| | |
|---|---|
| **Access** | `public` |
| **Parameters** | `downloadButton` — a replacement `JButton` |
| **Returns** | `void` |
| **Description** | Replaces the internal download button reference (does not automatically update the panel layout). |

---

## `FileSaveManager.FileCsvSaver`

All methods are `public static`. Not meant to be instantiated.

### `saveResult(String filePath, double bmi, double bodyFat) → void`
| | |
|---|---|
| **Access** | `public static` |
| **Parameters** | `filePath` — absolute path to the target `.csv` file · `bmi` — BMI value to write · `bodyFat` — body fat % to write |
| **Returns** | `void` |
| **Description** | Opens the file in **append mode**. Writes a CSV header (`Date,BMI,BodyFat`) if the file does not yet exist, then appends a new row with today's ISO date, BMI, and body fat. Prints a stack trace to stderr on `IOException`. |

---

## `FileSaveManager.SaveCsvDialog`

All methods are `public static`. Not meant to be instantiated.

### `chooseFilePath() → String`
| | |
|---|---|
| **Access** | `public static` |
| **Parameters** | _(none)_ |
| **Returns** | `String` — the absolute path of the chosen file with `.csv` extension guaranteed, or `null` if the dialog was cancelled |
| **Description** | Opens a `JFileChooser` save dialog titled "Save CSV File". Automatically appends `.csv` to the selected filename if the user did not include it. Returns `null` on cancellation. |
