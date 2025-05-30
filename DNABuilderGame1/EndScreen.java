package DNABuilderGame1;

import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The EndScreen shows the final animal built and gives an option to play again.
 */
public class EndScreen {

    private Scene scene;

    public EndScreen(String animalName) {
        StackPane root = new StackPane();
        Image backgroundImage = new Image(getClass().getResourceAsStream("/5148477.jpg"));
        ImageView background = new ImageView(backgroundImage);

        background.setFitWidth(800);
        background.setFitHeight(600);

        // Layout elements like Label, animal image, restart button
        // (Add those here as needed, similar to earlier samples)

        root.getChildren().add(background); // + other layout nodes
        scene = new Scene(root, 800, 600);
    }

    public Parent getRoot() {
        return scene.getRoot();
    }

}

