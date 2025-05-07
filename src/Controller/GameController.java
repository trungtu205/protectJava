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
    private int lives = 3;

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
        if (!gameState.isGameOver() && !gameState.isPaused()) {
            // Di chuyển bóng và kiểm tra biên
            ball.move(GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT);

            // Kiểm tra va chạm với paddle
            if (ball.getBounds().intersects(paddle.getBounds())) {
                ball.bounceVertical();
                if (ball.getDx() > 0)
                    ball.setDx(ball.getDx() + 0.5);
                if (ball.getDx() < 0)
                    ball.setDx(ball.getDx() - 0.5);
                if (ball.getDy() > 0)
                    ball.setDy(ball.getDy() + 0.5);
                if (ball.getDy() < 0)
                    ball.setDy(ball.getDy() - 0.5);
                gameState.setScore(gameState.getScore() + 1);
                System.out.println("x: " + ball.getDx());
                System.out.println("y: " + ball.getDy());
                
                SoundPlayer.playSound("/hit.wav");
            }

            // Kiểm tra game over
            if (ball.getY() + ball.getSize() >= GameConstants.GAME_HEIGHT) {
                lives--;
                ball.reset();
                if (lives == 0) {
                    gameState.setGameOver(true);
                    gameState.checkHighScore();
                    SoundPlayer.playSound("/game_over.wav");
                    SoundPlayer.stopBackgroundMusic(); // Dừng nhạc nền khi game over
                }
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
        lives = 3;
        // Không gọi playBackgroundMusic vì nhạc nền đã phát từ GameWindow
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
        SoundPlayer.stopBackgroundMusic(); // Dừng nhạc nền khi quay lại menu
    }

    /**
     * Thoát game sau khi xác nhận.
     */
    @Override
    public void exitGame() {
        int response = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn thoát game?", "Xác nhận thoát",
                JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            SoundPlayer.stopBackgroundMusic(); // Dừng nhạc nền khi thoát
            System.exit(0);
        }
    }

    @Override
    public void pauseGame() {
        if (!gameState.isPaused()) {
            gameState.setPaused(true);
            gamePanel.refresh();
            SoundPlayer.stopBackgroundMusic(); // Dừng nhạc nền khi tạm dừng
        }
    }

    @Override
    public void resumeGame() {
        if (gameState.isPaused()) {
            gameState.setPaused(false);
            gamePanel.refresh();
            SoundPlayer.playBackgroundMusic("/opensound.wav"); // Tiếp tục nhạc nền nếu âm thanh bật
        }
    }

    public int getLives() {
        return lives;
    }
}