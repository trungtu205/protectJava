package Model;

import java.awt.Rectangle;

/**
 * Lớp Ball đại diện cho quả bóng trong game. Quản lý vị trí, vận tốc và va chạm
 * của bóng.
 */
public class Ball {
	private double x, y; // Tọa độ bóng
	private double dx, dy; // Vận tốc theo hướng
	private int size; // Kích thước bóng

	/**
	 * Khởi tạo bóng với tọa độ ban đầu.
	 * 
	 * @param x Tọa độ x ban đầu
	 * @param y Tọa độ y ban đầu
	 */
	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		this.dx = GameConstants.BALL_SPEED;
		this.dy = GameConstants.BALL_SPEED;
		this.size = GameConstants.BALL_SIZE;
	}

	/**
	 * Di chuyển bóng và kiểm tra va chạm với biên màn hình.
	 * 
	 * @param panelWidth  Chiều rộng màn hình
	 * @param panelHeight Chiều cao màn hình
	 */
	public void move(int panelWidth, int panelHeight) {
		x += dx;
		y += dy;
		// Kiểm tra va chạm với biên trái/phải
		if (x <= 0 || x + size >= panelWidth) {
			bounceHorizontal();
			x = Math.max(0, Math.min(x, panelWidth - size)); // Giữ bóng trong biên
		}
		// Kiểm tra va chạm với biên trên
		if (y <= 0) {
			bounceVertical();
			y = 0;
		}
		// Kiểm tra nếu bóng ra ngoài biên dưới (mất bóng)
		if (y + size >= panelHeight) {
			y = panelHeight - size; // Giữ bóng tại biên dưới để GameController xử lý
		}
	}

	/**
	 * Đổi hướng vận tốc theo chiều ngang.
	 */
	public void bounceHorizontal() {
		dx = -dx;
	}

	/**
	 * Đổi hướng vận tốc theo chiều dọc.
	 */
	public void bounceVertical() {
		dy = -dy;
	}

	/**
	 * Lấy hình chữ nhật bao quanh bóng để kiểm tra va chạm.
	 * 
	 * @return Hình chữ nhật đại diện cho vùng chiếm bởi bóng
	 */
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, size, size);
	}

	/**
	 * Đặt lại vị trí và vận tốc của bóng về trạng thái ban đầu.
	 */
	public void reset() {
		this.x = GameConstants.GAME_WIDTH / 2 - size / 2;
		this.y = 0;
		this.size = GameConstants.BALL_SIZE;
		this.dx = GameConstants.BALL_SPEED;
		this.dy = GameConstants.BALL_SPEED;
	}

	// Getter và Setter
	public double getX() {
		return x;
	}

	public void setX(final double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(final double y) {
		this.y = y;
	}

	public double getDx() {
		return dx;
	}

	public void setDx(final double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(final double dy) {
		this.dy = dy;
	}

	public int getSize() {
		return size;
	}

	public void setSize(final int size) {
		this.size = size;
	}
}