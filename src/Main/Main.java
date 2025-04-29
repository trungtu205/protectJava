package Main;

import javax.swing.*;
import Model.*;
import View.*;

public class Main {
	public static void main(final String[] args) {
		SwingUtilities.invokeLater(() -> {
			final GameWindow window = new GameWindow();
			window.setVisible(true);
		});
	}
}
