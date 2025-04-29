package Model;

/**
 * Lớp GameState quản lý trạng thái của game, bao gồm điểm số và trạng thái game
 * over.
 */
public class GameState {
	private int score;
	private boolean isGameOver;
	private int highScore;
	private boolean isGamePause;

	/**
	 * Khởi tạo trạng thái game với giá trị mặc định.
	 */
	public GameState() {
		this.score = 0;
		this.isGameOver = false;
		this.highScore = 0;
		this.isGamePause = false;
	}

	/**
	 * Kiểm tra và cập nhật điểm số cao nhất nếu điểm hiện tại cao hơn.
	 */
	public void checkHighScore() {
		if (score > highScore) {
			highScore = score;
		}
	}

	/**
	 * Đặt lại trạng thái game về ban đầu.
	 */
	public void reset() {
		score = 0;
		isGameOver = false;
	}

	public boolean isPaused() {
		return isGamePause;
	}

	public void setPaused(boolean paused) {
		this.isGamePause = paused;
	}

	// Getter và Setter
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean gameOver) {
		isGameOver = gameOver;
	}

	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(final int highScore) {
		this.highScore = highScore;
	}
}