package DNABuilderGame1;

import javafx.application.Application;
import javafx.stage.Stage;
/**
 * the main application class that launches the DNA Builder Game. 
 * Sets up the initial game environment. 
 */

public class DNAApp extends Application {
	/**
	 * Launches the JavaFX application. 
	 * 
	 * @param args comand-line arguments (no used)
	 */
	public static void main(String[] args) {
		launch(args); //JavaFX launches the app 

	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		GameScreenManager.init(primaryStage); 
		GameScreenManager.showMenuScreen(); 
		SoundPlayer.playBackgroundMusic(); 
		}
}
