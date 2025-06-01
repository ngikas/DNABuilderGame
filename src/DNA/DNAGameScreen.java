package DNA;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.util.Duration;

public class DNAGameScreen {
    private Scene scene;
    private VBox layout;
    private DNAApp app;
    private DNALogic logic;
    private StringBuilder currentInput;
    private Label sequenceLabel, inputLabel, scoreLabel, livesLabel, timerLabel;
    private int score = 0;
    private int lives = 3;
    private int timeLeft = 60;
    private Timeline gameTimer;

    public DNAGameScreen(DNAApp app) {
        this.app = app;
        this.logic = new DNALogic();
        this.currentInput = new StringBuilder();

        layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPrefSize(800, 600);

        // Background
        Image bg = new Image("file:assets/5148477.jpg");
        BackgroundImage bgi = new BackgroundImage(bg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, false));
        layout.setBackground(new Background(bgi));

        sequenceLabel = new Label("Target: " + logic.getCurrentSequence());
        inputLabel = new Label("Your DNA: ");
        scoreLabel = new Label("Score: 0");
        livesLabel = new Label("Lives: 3");
        timerLabel = new Label("Time: 60s");

        for (Label label : new Label[]{sequenceLabel, inputLabel, scoreLabel, livesLabel, timerLabel}) {
            label.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            label.setStyle("-fx-text-fill: navy;");
        }

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);

        for (String base : new String[]{"A", "T", "C", "G"}) {
            Button btn = new Button(base);
            btn.setOnAction(e -> handleInput(base));
            buttonBox.getChildren().add(btn);
        }

        Button backspaceBtn = new Button("⌫");
        backspaceBtn.setOnAction(e -> {
            if (currentInput.length() > 0) {
                currentInput.setLength(currentInput.length() - 1);
                inputLabel.setText("Your DNA: " + currentInput);
            }
        });

        Button muteBtn = new Button(app.isMuted() ? "Unmute 🔊" : "Mute 🔇");
        muteBtn.setOnAction(e -> {
            app.toggleMusic();
            muteBtn.setText(app.isMuted() ? "Unmute 🔊" : "Mute 🔇");
        });

        buttonBox.getChildren().addAll(backspaceBtn, muteBtn);
        layout.getChildren().addAll(sequenceLabel, inputLabel, buttonBox, scoreLabel, livesLabel, timerLabel);

        scene = new Scene(layout);
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.A) handleInput("A");
            if (e.getCode() == KeyCode.T) handleInput("T");
            if (e.getCode() == KeyCode.C) handleInput("C");
            if (e.getCode() == KeyCode.G) handleInput("G");
            if (e.getCode() == KeyCode.BACK_SPACE) {
                if (currentInput.length() > 0) {
                    currentInput.setLength(currentInput.length() - 1);
                    inputLabel.setText("Your DNA: " + currentInput);
                }
            }
        });

        startTimer();
    }

    private void handleInput(String base) {
        currentInput.append(base);
        inputLabel.setText("Your DNA: " + currentInput);

        if (currentInput.length() == logic.getCurrentSequence().length()) {
            boolean correct = logic.checkSequence(currentInput.toString());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (correct) {
                score++;
                scoreLabel.setText("Score: " + score);
                alert.setHeaderText("✅ Correct!");
                alert.setContentText("You built a " + logic.getCurrentAnimal() + "!");
            } else {
                lives--;
                livesLabel.setText("Lives: " + lives);
                alert.setHeaderText("❌ Incorrect!");
                alert.setContentText("That was not the correct sequence.");
            }
            alert.showAndWait();
            currentInput.setLength(0);
            logic.generateNewSequence();
            sequenceLabel.setText("Target: " + logic.getCurrentSequence());
            inputLabel.setText("Your DNA: ");
            if (lives <= 0) endGame();
        }
    }

    private void startTimer() {
        gameTimer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            timeLeft--;
            timerLabel.setText("Time: " + timeLeft + "s");
            if (timeLeft <= 0) endGame();
        }));
        gameTimer.setCycleCount(Timeline.INDEFINITE);
        gameTimer.play();
    }

    private void endGame() {
        gameTimer.stop();
        app.showEndScreen(score, logic.getCurrentAnimal(), logic.getBuiltCounts());
    }

    public Scene getScene() {
        return scene;
    }
}
