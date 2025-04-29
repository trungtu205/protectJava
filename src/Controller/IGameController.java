package Controller;

/**
 * Giao diện IGameController định nghĩa các phương thức điều khiển game.
 */
public interface IGameController {
    void updateGame(); // Cập nhật trạng thái game
    void movePaddleLeft(); // Di chuyển thanh trượt sang trái
    void movePaddleRight(); // Di chuyển thanh trượt sang phải
    void startGame(); // Bắt đầu game mới
    void resetGame(); // Đặt lại game
    void pauseGame();
    void resumeGame();
    void exitGame(); // Thoát game
    void setGamePanel(View.GamePanel gamePanel); // Đặt panel giao diện
}