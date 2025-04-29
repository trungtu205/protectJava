package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import Controller.GameController;
import Model.*;

/**
 * Lớp GamePanel hiển thị giao diện game và xử lý đầu vào từ bàn phím.
 */
public class GamePanel extends JPanel {
	private final Ball ball;
	private final Paddle paddle;
	private final GameState gameState;
	private final GameController controller;
	private final GameWindow gamewindow;
	
	/**
	 * Khởi tạo GamePanel với các đối tượng game.
	 *
	 * @param ball       Quả bóng
	 * @param paddle     Thanh trượt
	 * @param gameState  Trạng thái game
	 * @param controller Bộ điều khiển game
	 */
	public GamePanel(Ball ball, Paddle paddle, GameState gameState, GameController controller , GameWindow gamewindow) {
		this.ball = ball;
		this.paddle = paddle;
		this.gameState = gameState;
		this.controller = controller;
		this.gamewindow = gamewindow;

		setPreferredSize(new Dimension(GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT));
		setBackground(Color.DARK_GRAY);
		setFocusable(true);
		requestFocusInWindow();

		// Thêm FocusListener để lấy lại focus khi mất
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				requestFocusInWindow();
			}
		});

		// Thêm sự kiện bàn phím
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_LEFT) {
					controller.movePaddleLeft();
				} else if (key == KeyEvent.VK_RIGHT) {
					controller.movePaddleRight();
				} else if (key == KeyEvent.VK_P) {
					if (gameState.isPaused() == false)
						controller.pauseGame();
					else
						controller.resumeGame();
				} else if (key == KeyEvent.VK_SPACE && gameState.isGameOver()) {
					controller.startGame();
				} else if(key == KeyEvent.VK_ESCAPE && gameState.isGameOver()) {
					
					gamewindow.showMenu();
					
				}
				
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		int realWidth = getWidth();
		int realHeight = getHeight();

		//background
		GradientPaint backgroundGradient = new GradientPaint(
				0, 0, new Color(44, 62, 80),
				0, getHeight(), new Color(52, 152, 219));
		g2d.setPaint(backgroundGradient);
		g2d.fillRect(0, 0, getWidth(), getHeight());

		// Tính tỉ lệ theo kích thước gốc
		double scaleX = realWidth / (double) GameConstants.GAME_WIDTH;
		double scaleY = realHeight / (double) GameConstants.GAME_HEIGHT;

		//scale khi vẽ quả bóng và paddle giúp chúng không bị méo
		double scale = Math.min(scaleX, scaleY);


		// Áp dụng tỉ lệ khi vẽ paddle
		g2d.setColor(Color.BLUE);
		g2d.fillRect((int)(paddle.getX() * scaleX),
				(int)(paddle.getY() * scaleY),
				(int)(paddle.getWidth() * scale),
				(int)(paddle.getHeight() * scale));

		// Vẽ bóng
		g2d.setColor(Color.YELLOW);
		g2d.fillOval((int)(ball.getX() * scaleX),
				(int)(ball.getY() * scaleY),
				(int)(ball.getSize() * scale),
				(int)(ball.getSize() * scale));

		// Vẽ điểm
		g2d.setColor(Color.green);
		g2d.setFont(new Font("Arial", Font.PLAIN, (int)(16 * scale)));
		g2d.drawString("Score: " + gameState.getScore(), (int)(10 * scaleX), (int)(25 * scaleY));
		g2d.drawString("High Score: " + gameState.getHighScore(), (int)(10 * scaleX), (int)(45 * scaleY));

		// Game over
		if (gameState.isGameOver()) {
			g2d.setColor(Color.red);
			g2d.setFont(new Font("Arial", Font.BOLD, (int)(45 * scaleY)));
			g2d.drawString("GAME OVER", (int)(180* scaleX), (int)(180 * scaleY));
			g2d.setColor(Color.WHITE);
			g2d.setFont(new Font("Arial", Font.PLAIN, (int)(20 * scaleY)));
			g2d.drawString("Press SPACE to restart", (int)(210 * scaleX), (int)(260 * scaleY));
			g2d.drawString("Press ESC to return to menu", (int)(200 * scaleX), (int)(290 * scaleY));
		}

		// Tạm dừng
		if (gameState.isPaused() && !gameState.isGameOver()) {
			g2d.setColor(Color.RED);
			g2d.setFont(new Font("Arial", Font.BOLD, (int)(40 * scaleY)));
			g2d.drawString("PAUSED", (int)((GameConstants.GAME_WIDTH/2 - 70) * scaleX), (int)((GameConstants.GAME_HEIGHT/2) * scaleY));
			g2d.setFont(new Font("Arial", Font.PLAIN, (int)(20 * scaleY)));
			g2d.drawString("Press P to resume", (int)((GameConstants.GAME_WIDTH/2 - 70) * scaleX), (int)((GameConstants.GAME_HEIGHT/2 + 20)  * scaleY));
		}
	}


	/**
	 * Cập nhật giao diện game.
	 */
	public void refresh() {
		repaint();
	}
}