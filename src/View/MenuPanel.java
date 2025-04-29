package View;

import Model.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
	private JLabel titleLabel, subtitleLabel;
	private JButton startButton, instructionButton, soundButton, infoButton, exitButton;
	private JButton[] buttons;

	private final Color mainColor = new Color(41, 128, 185);
	private final Color accentColor = new Color(255, 203, 5);

	public MenuPanel() {
		setLayout(new GridBagLayout());
		setOpaque(true);
		initComponents();
		addComponents();
		addResizeListener();
	}

	private void initComponents() {
		titleLabel = createLabel("BOUNCE BALL", new Font("Montserrat", Font.BOLD, 42), accentColor);
		subtitleLabel = createLabel("ADVENTURE", new Font("Montserrat", Font.ITALIC, 24), Color.WHITE);

		startButton = createButton("START GAME", new Color(95, 195, 124));
		instructionButton = createButton("INSTRUCTIONS");
		soundButton = createButton("SOUND: ON");
		infoButton = createButton("ABOUT");
		exitButton = createButton("EXIT", new Color(231, 76, 60));

		buttons = new JButton[]{startButton, instructionButton, soundButton, infoButton, exitButton};
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		//background
		GradientPaint backgroundGradient = new GradientPaint(
				0, 0, new Color(44, 62, 80),
				0, getHeight(), new Color(52, 152, 219));
		g2d.setPaint(backgroundGradient);
		g2d.fillRect(0, 0, getWidth(), getHeight());
	}

	private JLabel createLabel(String text, Font font, Color color) {
		JLabel label = new JLabel(text);
		label.setFont(font);
		label.setForeground(color);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		return label;
	}

	private JButton createButton(String text) {
		return createButton(text, mainColor);
	}

	private JButton createButton(String text, Color bgColor) {
		JButton btn = new JButton(text);
		btn.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btn.setBackground(bgColor);
		btn.setForeground(Color.WHITE);
		btn.setFocusPainted(false);
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn.setMaximumSize(new Dimension(280, 60));
		btn.setPreferredSize(new Dimension(280, 60));
		return btn;
	}

	private void addComponents() {
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setOpaque(false);
		contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		contentPanel.add(titleLabel);
		contentPanel.add(subtitleLabel);
		contentPanel.add(Box.createVerticalStrut(30));

		for (JButton btn : buttons) {
			contentPanel.add(btn);
			contentPanel.add(Box.createVerticalStrut(10));
		}

		add(contentPanel);
	}

	private void addResizeListener() {
		addComponentListener(new java.awt.event.ComponentAdapter() {
			@Override
			public void componentResized(java.awt.event.ComponentEvent evt) {
				resizeComponents();
			}
		});
	}

	private void resizeComponents() {
		double scale = Math.min(getWidth() / (double) GameConstants.GAME_WIDTH, getHeight() / (double) GameConstants.GAME_HEIGHT);
		titleLabel.setFont(titleLabel.getFont().deriveFont((float) (42 * scale)));
		subtitleLabel.setFont(subtitleLabel.getFont().deriveFont((float) (20 * scale)));

		for (JButton btn : buttons) {
			btn.setFont(btn.getFont().deriveFont((float) (19 * scale)));
			btn.setMaximumSize(new Dimension((int) (280 * scale), (int) (60 * scale)));
			btn.setPreferredSize(new Dimension((int) (280 * scale), (int) (60 * scale)));
		}
		startButton.setFont(startButton.getFont().deriveFont((float) (22 * scale)));
		revalidate();
	}

	// Add external listeners
	public void addStartListener(ActionListener listener) {
		startButton.addActionListener(listener);
	}

	public void addInstructionListener(ActionListener listener) {
		instructionButton.addActionListener(listener);
	}

	public void addSoundListener(ActionListener listener) {
		soundButton.addActionListener(listener);
	}

	public void addInfoListener(ActionListener listener) {
		infoButton.addActionListener(listener);
	}

	public void addExitListener(ActionListener listener) {
		exitButton.addActionListener(listener);
	}
	public void addInstructionsListener(ActionListener listener) {
		instructionButton.addActionListener(listener);
	}
}
