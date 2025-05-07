package util;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class SoundPlayer {
    private static Clip backgroundClip; // Lưu Clip của nhạc nền để điều khiển
    private static boolean isSoundEnabled = true; // Trạng thái âm thanh (mặc định bật)

    // Phát âm thanh không lặp (dùng cho hit.wav, game_over.wav)
    public static void playSound(String path) {
        if (!isSoundEnabled) {
            return; // Không phát nếu âm thanh bị tắt
        }
        try {
            System.out.println("Đang cố gắng tải âm thanh từ: " + path);
            InputStream audioSrc = SoundPlayer.class.getResourceAsStream(path);
            if (audioSrc == null) {
                System.err.println("Không tìm thấy file âm thanh trong classpath: " + path);
                return;
            }
            BufferedInputStream bufferedStream = new BufferedInputStream(audioSrc);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedStream);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Định dạng âm thanh không được hỗ trợ: " + path);
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            System.err.println("Không thể truy cập thiết bị âm thanh: " + path);
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Lỗi khác khi phát âm thanh: " + path);
            e.printStackTrace();
        }
    }

    // Phát nhạc nền lặp lại
    public static void playBackgroundMusic(String path) {
        if (!isSoundEnabled) {
            return; // Không phát nếu âm thanh bị tắt
        }
        try {
            System.out.println("Đang cố gắng tải nhạc nền từ: " + path);
            InputStream audioSrc = SoundPlayer.class.getResourceAsStream(path);
            if (audioSrc == null) {
                System.err.println("Không tìm thấy file âm thanh trong classpath: " + path);
                return;
            }
            BufferedInputStream bufferedStream = new BufferedInputStream(audioSrc);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedStream);
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audioStream);
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY); // Lặp vô hạn
            System.out.println("Phát nhạc nền...");
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Định dạng âm thanh không được hỗ trợ: " + path);
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            System.err.println("Không thể truy cập thiết bị âm thanh: " + path);
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Lỗi khác khi phát nhạc nền: " + path);
            e.printStackTrace();
        }
    }

    // Dừng nhạc nền
    public static void stopBackgroundMusic() {
        if (backgroundClip != null && backgroundClip.isRunning()) {
            backgroundClip.stop();
            backgroundClip.close();
            System.out.println("Đã dừng nhạc nền.");
        }
    }

    // Bật/tắt âm thanh
    public static void toggleSound() {
        isSoundEnabled = !isSoundEnabled;
        if (!isSoundEnabled) {
            stopBackgroundMusic(); // Dừng nhạc nền nếu tắt âm thanh
        } else {
            playBackgroundMusic("/opensound.wav"); // Phát nhạc nền nếu bật âm thanh
        }
        System.out.println("Trạng thái âm thanh: " + (isSoundEnabled ? "ON" : "OFF"));
    }

    // Lấy trạng thái âm thanh
    public static boolean isSoundEnabled() {
        return isSoundEnabled;
    }
}