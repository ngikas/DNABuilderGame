package DNA;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.util.Map;

public class DNAEndScreen {
    private Scene scene;
    private DNAApp app;

    public DNAEndScreen(DNAApp app, int score, int highScore, String lastAnimal, Map<String, Integer> builtCounts) {
        this.app = app;

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPrefSize(800, 600);

        // Background
        Image bg = new Image("file:assets/5148477.jpg");
        BackgroundImage bgi = new BackgroundImage(bg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, false));
        layout.setBackground(new Background(bgi));

        Text title = new Text("Game Over!");
        title.setFont(Font.font("Comic Sans MS", 28));
        title.setStyle("-fx-fill: navy; -fx-font-weight: bold;");

        Text scoreText = new Text("Your Score: " + score + "\nHigh Score: " + highScore);
        scoreText.setFont(Font.font("Comic Sans MS", 20));
        scoreText.setStyle("-fx-fill: navy;");

        // Summary
        StringBuilder summary = new StringBuilder("You created:\n");
        for (String animal : builtCounts.keySet()) {
            summary.append(builtCounts.get(animal)).append(" ").append(animal).append("(s)\n");
        }
        Text summaryText = new Text(summary.toString());
        summaryText.setFont(Font.font("Comic Sans MS", 16));
        summaryText.setStyle("-fx-fill: navy;");

        // Animal Images
        HBox imageBox = new HBox(10);
        imageBox.setAlignment(Pos.CENTER);
        for (String animal : builtCounts.keySet()) {
            try {
                ImageView animalView = new ImageView(new Image("file:assets/" + animal + ".png"));
                animalView.setFitHeight(100);
                animalView.setPreserveRatio(true);
                imageBox.getChildren().add(animalView);
            } catch (Exception e) {
                System.out.println("Image error for " + animal);
            }
        }

        // Restart Button
        Button restartButton = new Button("Restart Game");
        restartButton.setOnAction(e -> app.showGameScreen());

        // Mute Button
        Button muteButton = new Button(app.isMuted() ? "Unmute 🔊" : "Mute 🔇");
        muteButton.setOnAction(e -> {
            app.toggleMusic();
            muteButton.setText(app.isMuted() ? "Unmute 🔊" : "Mute 🔇");
        });

        layout.getChildren().addAll(title, scoreText, summaryText, imageBox, restartButton, muteButton);
        scene = new Scene(layout);
    }

    public Scene getScene() {
        return scene;
    }
}
