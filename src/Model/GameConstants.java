package Model;

/**
 * Lớp GameConstants chứa các hằng số cố định cho game.
 */

public class GameConstants {
	public static final int GAME_WIDTH = 650;
	public static final int GAME_HEIGHT = 355;
	public static final int BALL_SIZE = 23;
	public static final int BALL_SPEED = 3;
	public static final int PADDLE_WIDTH = 90;
	public static final int PADDLE_HEIGHT = 20;
	public static final int PADDLE_SPEED = 13;
	public static final int PADDLE_START_X = (GAME_WIDTH - PADDLE_WIDTH) / 2;
	public static final int PADDLE_START_Y = GAME_HEIGHT - 40;
}