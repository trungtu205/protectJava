package View;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;
import Controller.GameController;
import Model.*;

/**
 * Lớp GameWindow quản lý cửa sổ chính của game, chuyển đổi giữa menu và game.
 */
public class GameWindow extends JFrame {
	private MenuPanel menuPanel;
	private GamePanel gamePanel;
	private GameController controller;
	private Timer timer;

	/**
	 * Khởi tạo cửa sổ game.
	 */
	public GameWindow() {
		setTitle("Bounce Ball Game");
		setSize(GameConstants.GAME_WIDTH + 16, GameConstants.GAME_HEIGHT + 39);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		GameController gameController = new GameController(null, null, null, gamePanel);
		showMenu();
	}

	/**
	 * Hiển thị menu chính.
	 */
	public void showMenu() {
		extracted();
		menuPanel.addStartListener(e -> startGame());
		menuPanel.addExitListener(e -> System.exit(0));
		menuPanel.addInstructionsListener(e -> showInstructions());
		menuPanel.addInfoListener(e -> showInfo());
		setContentPane(menuPanel);
		revalidate();
	}

	private void extracted() {
		if (timer != null) {
			timer.stop();
		}
		menuPanel = new MenuPanel();

	}

	/**
	 * Bắt đầu game mới.
	 */
	private void startGame() {
		Ball ball = new Ball(GameConstants.GAME_WIDTH / 2, GameConstants.GAME_HEIGHT / 2);
		Paddle paddle = new Paddle(GameConstants.PADDLE_START_X, GameConstants.PADDLE_START_Y);
		GameState gameState = new GameState();
		controller = new GameController(ball, paddle, gameState, null);
		gamePanel = new GamePanel(ball, paddle, gameState,controller, this);
		controller.setGamePanel(gamePanel);

		controller.startGame();

		setContentPane(gamePanel);
		revalidate();
		gamePanel.requestFocusInWindow();

		// Tạo Timer để cập nhật game
		timer = new Timer(16, e -> {
			if (!gameState.isGameOver() && !gameState.isPaused()) {
				controller.updateGame();
				gamePanel.refresh();
			}
		});
		timer.start();
	}
	private void showInstructions() {
		JDialog dialog = new JDialog(this, "Instructions", true);
		int realWidth = getWidth();
		int realHeight = getHeight();
		double scaleX = realWidth / (double) GameConstants.GAME_WIDTH;
		double scaleY = realHeight / (double) GameConstants.GAME_HEIGHT;
		dialog.setSize((int) (450 * scaleX), (int) (155 * scaleY));
		dialog.setLocationRelativeTo(this);
		dialog.setResizable(false);

		// Tạo JPanel để chứa nội dung hướng dẫn
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(new Color(44, 62, 80)); // Màu nền đậm

		// Tạo JLabel với hướng dẫn
		JLabel instructionsLabel = new JLabel("<html><p style='color:white; font-size:18px;'>Use arrow keys to move the paddle.<br>Avoid the obstacles and reach the goal!</p></html>");
		instructionsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		instructionsLabel.setVerticalAlignment(SwingConstants.CENTER);
		instructionsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));

		// Thêm JLabel vào JPanel
		panel.add(instructionsLabel, BorderLayout.CENTER);


		dialog.setContentPane(panel);
		dialog.setVisible(true);
	}
	
	private void showInfo() {
		JDialog dialog = new JDialog(this, "About", true);
		int realWidth = getWidth();
		int realHeight = getHeight();
		double scaleX = realWidth / (double) GameConstants.GAME_WIDTH;
		double scaleY = realHeight / (double) GameConstants.GAME_HEIGHT;
		dialog.setSize((int) (450 * scaleX), (int) (155 * scaleY));
		dialog.setLocationRelativeTo(this);
		dialog.setResizable(false);

		// Tạo JPanel để chứa nội dung
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(new Color(44, 62, 80)); // Màu nền đậm

		// Tạo JLabel với thông tin về game
		JLabel infoLabel = new JLabel("<html><p style='color:white; font-size:18px;'>Bounce Ball Adventure<br>Version 1.0<br>Developed by YourTeam!</p></html>");
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setVerticalAlignment(SwingConstants.CENTER);
		infoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));

		// Thêm JLabel vào JPanel
		panel.add(infoLabel, BorderLayout.CENTER);



		dialog.setContentPane(panel);
		dialog.setVisible(true);
	}

}