package DNABuilderGame1;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL; 

/**
 * Manages background music playback.
 */
public class SoundPlayer {

    private static MediaPlayer backgroundMusic;

    /**
     * Plays the looping background music.
     */
    public static void playBackgroundMusic() {
        try {
            URL musicURL = SoundPlayer.class.getResource("/game-music-7408.wav");
            if (musicURL != null) {
                Media media = new Media(musicURL.toString());
                backgroundMusic = new MediaPlayer(media);
                backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
                backgroundMusic.play();
            } else {
                System.out.println("\u274C Background music file not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
}



