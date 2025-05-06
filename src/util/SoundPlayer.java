package util;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class SoundPlayer {

    public static void playSound(String fileName) {
        try {
            // Tìm và mở stream từ file âm thanh
            InputStream audioSrc = SoundPlayer.class.getResourceAsStream("/" + fileName);
            if (audioSrc == null) {
                System.err.println("Không tìm thấy file: " + fileName);
                return;
            }

            // Chuyển stream thành dạng AudioInputStream
            InputStream bufferedIn = new java.io.BufferedInputStream(audioSrc);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);

            // Lấy clip và phát
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
