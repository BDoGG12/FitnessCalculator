package FileSaveManager;

import java.io.File;

import javax.swing.JFileChooser;

public class SaveCsvDialog {

	public static String chooseFilePath() {
		JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save CSV File");

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            // Ensure csv extension
            String path = fileToSave.getAbsolutePath();
            if (!path.endsWith(".csv")) {
                path += ".csv";
            }

            return path;
        }

        return null;
	}
}
