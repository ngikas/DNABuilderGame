package DNABuilderGame1;

import javafx.animation.KeyFrame;

import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * Main gameplay screen: falling bases, score, lives, and DNA matching.
 */
public class GameScreen {

    private Parent root;
    private Label scoreLabel;
    private Label livesLabel;
    private Label targetLabel;
    private Label userInputLabel;

    private int score = 0;
    private int lives = 3;
    private String targetSequence;
    private StringBuilder playerInput = new StringBuilder();

    private Timeline timeline;

    /**
     * Constructs the Game Screen and sets up gameplay.
     */
    public GameScreen() {
        // Labels
        scoreLabel = new Label("Score: 0");
        livesLabel = new Label("Lives: 3");
        scoreLabel.setFont(Font.font(Assets.FONT_FAMILY, 24));
        livesLabel.setFont(Font.font(Assets.FONT_FAMILY, 24));
        scoreLabel.setTextFill(Color.DARKGREEN);
        livesLabel.setTextFill(Color.DARKRED);

        // DNA target
        targetSequence = DNASequence.getRandomTarget();
        targetLabel = new Label("Target: " + targetSequence);
        targetLabel.setFont(Font.font(Assets.FONT_FAMILY, 28));
        targetLabel.setTextFill(Color.PURPLE);

        // User input
        userInputLabel = new Label("Your Input: ");
        userInputLabel.setFont(Font.font(Assets.FONT_FAMILY, 24));
        userInputLabel.setTextFill(Color.NAVY);

        // Layout
        VBox hud = new VBox(10, scoreLabel, livesLabel, targetLabel, userInputLabel);
        hud.setAlignment(Pos.TOP_CENTER);
        hud.setTranslateY(20);

        // Background
        Image backgroundImage = new Image(getClass().getResourceAsStream("/5148477.jpg"));
        ImageView background = new ImageView(backgroundImage);
        
        background.setFitWidth(800);
        background.setFitHeight(600);

        StackPane stack = new StackPane(background, hud);
        this.root = stack;

        // Handle keyboard input
        stack.setOnKeyPressed(this::handleKeyPress);
        stack.setFocusTraversable(true);

        // Start timer and falling logic
        startTimer();
    }

    /**
     * Handles player key presses.
     */
    private void handleKeyPress(KeyEvent event) {
        String key = event.getText().toUpperCase();
        if (!"ATCG".contains(key) || playerInput.length() >= targetSequence.length()) return;

        playerInput.append(key);
        userInputLabel.setText("Your Input: " + playerInput.toString());

        if (playerInput.length() == targetSequence.length()) {
            checkMatch();
        }
    }

    /**
     * Compares player input with the target DNA sequence.
     */
    private void checkMatch() {
        if (playerInput.toString().equalsIgnoreCase(targetSequence)) {
            score += 100;
            scoreLabel.setText("Score: " + score);
            nextRound();
        } else {
            lives--;
            livesLabel.setText("Lives: " + lives);
            if (lives == 0) {
                timeline.stop();
                GameScreenManager.showEndScreen(DNASequence.getAnimalForSequence(targetSequence));
                return;
            }
            playerInput.setLength(0);
            userInputLabel.setText("Your Input: ");
        }
    }

    /**
     * Starts the countdown timer and falling bases animation (optional).
     */
    private void startTimer() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateFallingBases()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Optional animation logic (can be expanded).
     */
    private void updateFallingBases() {
        // Can be used for bonus animations.
    }

    /**
     * Loads a new DNA target sequence.
     */
    private void nextRound() {
        playerInput.setLength(0);
        targetSequence = DNASequence.getRandomTarget();
        targetLabel.setText("Target: " + targetSequence);
        userInputLabel.setText("Your Input: ");
    }

    /**
     * Returns the root node for GameScreen.
     */
    public Parent getRoot() {
        return root;
    }
}
