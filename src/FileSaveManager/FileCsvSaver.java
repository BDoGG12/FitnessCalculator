package FileSaveManager;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class FileCsvSaver {
	
	public static void saveResult(String filePath, double bmi, double bodyFat) {
        try {
            File file = new File(filePath);
            boolean fileExists = file.exists();

            FileWriter writer = new FileWriter(file, true);

            // Only write header once
            if (!fileExists) {
                writer.write("Date,BMI,BodyFat\n");
            }

            writer.write(LocalDate.now() + "," + bmi + "," + bodyFat + "\n");

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
