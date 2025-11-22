import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Alayah Benjamin
 * COMP 167: MAJOR PROJECT 3
 * Section 1
 * Date: November 10th 2025
 * This is the Main Class
 *This will be the class that loads and runs the application
 */


public class Main extends Application {

    public void start(Stage stage) {
       BorderPane root = new BorderPane();

       // Setting the size of how wide and tall the application is
       Scene scene = new Scene(root, 550, 600);
       GamePane gamePane = new GamePane(scene);
       root.setCenter(gamePane);


// Naming the application
       stage.setScene(scene);
       stage.setTitle("Space Invaders");
       stage.show();

    }
//Launching the application
    public static void main(String[] args){
        launch(args);

    }
}
