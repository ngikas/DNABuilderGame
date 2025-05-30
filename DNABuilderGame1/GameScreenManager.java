package DNABuilderGame1;

import javafx.scene.Scene;
import javafx.stage.Stage; 



/**
 * Manages switching between different screens in the DNA Builder game.
 */
public class GameScreenManager {

    private static Stage primaryStage;
    private static Scene mainScene;

    /**
     * Initializes the game scene manager with the JavaFX stage.
     * @param stage the main stage for the game
     */
    public static void init(Stage stage) {
        primaryStage = stage;
        mainScene = new Scene(new MenuScreen().getRoot(), 800, 600);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("DNA Builder Game");
        primaryStage.show();
    }

    /**
     * Shows the main menu screen.
     */
    public static void showMenuScreen() {
        MenuScreen menu = new MenuScreen();
        mainScene.setRoot(menu.getRoot());
    }

    /**
     * Shows the game screen.
     */
    public static void showGameScreen() {
        GameScreen game = new GameScreen();
        mainScene.setRoot(game.getRoot());
    }

    /**
     * Shows the end screen after building an animal.
     * @param animalName the completed animal
     */
    public static void showEndScreen(String animalName) {
        EndScreen end = new EndScreen(animalName);
        mainScene.setRoot(end.getRoot());
    }

    /**
     * Gets the current primary stage.
     * @return the primary stage
     */
    public static Stage getStage() {
        return primaryStage;
    }
}
