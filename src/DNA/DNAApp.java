package DNA;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.util.Map;

public class DNAApp extends Application {
    private Stage primaryStage;
    private MediaPlayer mediaPlayer;
    private int highScore = 0;
    private boolean isMuted = false;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        setupMusic();
        showMenu();
    }

    public void setupMusic() {
        try {
            File musicFile = new File("assets/game-music-7408.wav");
            mediaPlayer = new MediaPlayer(new javafx.scene.media.Media(musicFile.toURI().toString()));
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        } catch (Exception e) {
            System.out.println("Music error: " + e.getMessage());
        }
    }

    public void toggleMusic() {
        if (mediaPlayer != null) {
            if (isMuted) {
                mediaPlayer.play();
                isMuted = false;
            } else {
                mediaPlayer.pause();
                isMuted = true;
            }
        }
    }

    public boolean isMuted() {
        return isMuted;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void showMenu() {
        DNAMenuScreen menu = new DNAMenuScreen(this);
        primaryStage.setTitle("DNA Builder Game");
        primaryStage.getIcons().add(new Image("file:assets/5148477.jpg"));
        primaryStage.setScene(menu.getScene());
        primaryStage.show();
    }

    public void showGameScreen() {
        if (mediaPlayer != null && !isMuted) {
            mediaPlayer.stop(); // Ensure music restarts cleanly
            mediaPlayer.play();
        }

        DNAGameScreen gameScreen = new DNAGameScreen(this);
        primaryStage.setScene(gameScreen.getScene());
    }

    public void showEndScreen(int score, String currentAnimal, Map<String, Integer> builtCounts) {
        if (score > highScore) {
            highScore = score;
        }
        DNAEndScreen end = new DNAEndScreen(this, score, highScore, currentAnimal, builtCounts);
        primaryStage.setScene(end.getScene());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
