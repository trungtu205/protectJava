package Controller;

import javax.swing.*;
import Model.*;
import View.GamePanel;
import util.SoundPlayer;

/**
 * Lớp GameController điều khiển logic chính của game, bao gồm di chuyển, va
 * chạm, và trạng thái game.
 */
public class GameController implements IGameController {
	private Ball ball;
	private Paddle paddle;
	private GameState gameState;
	private GamePanel gamePanel;

	/**
	 * Khởi tạo GameController với các đối tượng game.
	 * 
	 * @param ball      Quả bóng
	 * @param paddle    Thanh trượt
	 * @param gameState Trạng thái game
	 * @param gamePanel Panel giao diện game
	 */
	public GameController(Ball ball, Paddle paddle, GameState gameState, GamePanel gamePanel) {
		this.ball = ball;
		this.paddle = paddle;
		this.gameState = gameState;
		this.gamePanel = gamePanel;
	}

	/**
	 * Đặt panel giao diện game.
	 * 
	 * @param gamePanel Panel giao diện game
	 */
	@Override
	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		if (this.gamePanel == null) {
			throw new IllegalArgumentException("GamePanel không được phép null");
		}
	}


	/**
	 * Cập nhật trạng thái game, bao gồm di chuyển bóng và kiểm tra va chạm.
	 */
	@Override
	public void updateGame() {
		if (!gameState.isGameOver() && gameState.isPaused() == false) {
			// Di chuyển bóng và kiểm tra biên
			ball.move(GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT);

			// Kiểm tra va chạm với paddle
			if (ball.getBounds().intersects(paddle.getBounds())) {
				ball.bounceVertical();
				if (ball.getDx() > 0)
					ball.setDx(ball.getDx()+1);
				if (ball.getDx()  < 0)
					ball.setDx(ball.getDx()-1);
				if (ball.getDy()  > 0)
					ball.setDy(ball.getDy()+1);
				if (ball.getDy()  < 0)
					ball.setDy(ball.getDy()-1);
				gameState.setScore(gameState.getScore() + 1);
				
				// Âm thanh sau khi va chạm với paddle
				SoundPlayer.playSound("hit.wav");
			}

			// Kiểm tra game over
			if (ball.getY() + ball.getSize() >= GameConstants.GAME_HEIGHT) {
				gameState.setGameOver(true);
				gameState.checkHighScore();
				
				// Âm thanh sau khi game over
				SoundPlayer.playSound("game_over.wav");
			}
		}
	}

	/**
	 * Di chuyển thanh trượt sang trái.
	 */
	@Override
	public void movePaddleLeft() {
		if (!gameState.isPaused()) {
			paddle.moveLeft();
		}
	}

	@Override
	public void movePaddleRight() {
		if (!gameState.isPaused()) {
			paddle.moveRight(GameConstants.GAME_WIDTH);
		}
	}

	/**
	 * Bắt đầu game mới.
	 */
	@Override
	public void startGame() {
		gameState.setScore(0);
		gameState.setGameOver(false);
		ball.reset();
		paddle.reset();
	}

	/**
	 * Đặt lại game để quay lại menu.
	 */
	@Override
	public void resetGame() {
		gameState.setGameOver(true);
		gameState.checkHighScore();
		ball.reset();
		paddle.reset();
		gamePanel.refresh();
	}

	/**
	 * Thoát game sau khi xác nhận.
	 */
	@Override
	public void exitGame() {
		
			System.exit(0);
		
	}

	@Override
	public void pauseGame() {
		if (gameState.isPaused() == false) {
			gameState.setPaused(true);
			gamePanel.refresh();
		}

	}

	@Override
	public void resumeGame() {
		if (gameState.isPaused() == true) {
			gameState.setPaused(false);
			gamePanel.refresh();
		}

	}

}
