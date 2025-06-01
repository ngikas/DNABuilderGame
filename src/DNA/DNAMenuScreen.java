package DNA;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;


public class DNAMenuScreen {
    private Scene scene;
    private DNAApp app;

    public DNAMenuScreen(DNAApp app) {
        this.app = app;

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPrefSize(800, 600);

        // Background
        Image bg = new Image("file:assets/5148477.jpg");
        BackgroundImage bgi = new BackgroundImage(bg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, false));
        layout.setBackground(new javafx.scene.layout.Background(bgi));

        // Title
        Text title = new Text("Welcome to DNA Builder!");
        title.setFont(Font.font("Comic Sans MS", 28));
        title.setStyle("-fx-fill: navy; -fx-font-weight: bold;");

        // Instructions
        Text instructions = new Text("Build DNA sequences using A, T, C, G to match the animal’s code!\n" +
                "Click or type the bases. You have 3 lives. Score as much as possible in 1 minute!");
        instructions.setFont(Font.font("Comic Sans MS", 16));
        instructions.setStyle("-fx-fill: navy;");

        // Start button
        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> app.showGameScreen());

        // Mute/Unmute button
        Button muteButton = new Button(app.isMuted() ? "Unmute 🔊" : "Mute 🔇");
        muteButton.setOnAction(e -> {
            app.toggleMusic();
            muteButton.setText(app.isMuted() ? "Unmute 🔊" : "Mute 🔇");
        });

        layout.getChildren().addAll(title, instructions, startButton, muteButton);
        scene = new Scene(layout);
    }

    public Scene getScene() {
        return scene;
    }
}
