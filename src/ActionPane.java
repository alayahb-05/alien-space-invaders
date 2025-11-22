import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.io.FileInputStream;

/**
 * ActionPane displays all game objects including background and aliens
 */
public class ActionPane extends Pane {


// Setting the application to be 550 by 600
    public ActionPane() {
        this.setPrefSize(550, 600);

        // Using a try and catch to load the image that will represent the background of the application
        // Add background
        try {
            Image background = new Image(new FileInputStream("images/space4.jpg"));
            ImageView bgView = new ImageView(background);
            bgView.setFitWidth(this.getPrefWidth());
            bgView.setFitHeight(this.getPrefHeight());
            this.getChildren().add(bgView);
        } catch (Exception e) {
            this.setStyle("-fx-background-color: black;");
            System.err.println("Background image not found: " + e.getMessage());
        }


    }
}
