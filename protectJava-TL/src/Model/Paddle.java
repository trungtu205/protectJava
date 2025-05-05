package Model;

import java.awt.Rectangle;

/**
 * Lớp Paddle đại diện cho thanh trượt trong game. Quản lý vị trí và di chuyển
 * của thanh trượt.
 */
public class Paddle {
	private int x, y;
	private int width;
	private int height;
	private int speed;

	/**
	 * Khởi tạo thanh trượt với tọa độ ban đầu.
	 * 
	 * @param x Tọa độ x ban đầu
	 * @param y Tọa độ y ban đầu
	 */
	public Paddle(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = GameConstants.PADDLE_WIDTH;
		this.height = GameConstants.PADDLE_HEIGHT;
		this.speed = GameConstants.PADDLE_SPEED;
	}

	/**
	 * Di chuyển thanh trượt sang trái.
	 */
	public void moveLeft() {
		x -= speed;
		if (x < 0) {
			x = 0;
		}
	}

	/**
	 * Di chuyển thanh trượt sang phải.
	 * 
	 * @param panelWidth Chiều rộng màn hình để kiểm tra biên
	 */
	public void moveRight(int panelWidth) {
		if (panelWidth <= 0)
			return;
		x += speed;
		if (x + width > panelWidth) {
			x = panelWidth - width;
		}
	}

	/**
	 * Lấy hình chữ nhật bao quanh thanh trượt để kiểm tra va chạm.
	 * 
	 * @return Hình chữ nhật đại diện cho vùng chiếm bởi thanh trượt
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	/**
	 * Đặt lại vị trí của thanh trượt về trạng thái ban đầu.
	 */
	public void reset() {
		this.x = GameConstants.PADDLE_START_X;
		this.y = GameConstants.PADDLE_START_Y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}