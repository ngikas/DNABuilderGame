package DNABuilderGame1;

import javafx.geometry.Pos;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Menu screen with title and Start Game button.
 */
public class MenuScreen {

    private Parent root;

    /**
     * Constructor to build the menu UI.
     */
    public MenuScreen() {
        // Title text
        Text title = new Text("🧬 DNA Builder Game");
        title.setFont(Font.font(Assets.FONT_FAMILY, 48));
        title.setFill(Color.DARKGREEN);

        // Start button
        Button startButton = new Button("Start Game");
        startButton.setFont(Font.font(Assets.FONT_FAMILY, 24));
        startButton.setOnAction(e -> GameScreenManager.showGameScreen());

        // Layout
        VBox layout = new VBox(30, title, startButton);
        layout.setAlignment(Pos.CENTER);

        // Background
        Image backgroundImage = new Image(Assets.BACKGROUND_IMAGE);
        ImageView background = new ImageView(backgroundImage);
        background.setFitWidth(800);
        background.setFitHeight(600);

        // Stack everything
        StackPane stack = new StackPane(background, layout);
        this.root = stack;
    }

    /**
     * Returns the root node of the menu screen.
     * @return the UI layout as a Parent
     */
    public Parent getRoot() {
        return root;
    }
}
