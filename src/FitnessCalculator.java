import javax.swing.*;

import controller.FitnessController;
import main.MainFrame;
import view.MethodSelectView;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FitnessCalculator {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		javax.swing.SwingUtilities.invokeLater(() -> {
			MainFrame frame = new MainFrame();
			new FitnessController(frame);
			frame.setVisible(true);
		});

	}

}
