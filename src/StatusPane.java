import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


/**
 * Alayah Benjamin
 * Date: November 10th 2025
 * This is the StatusPane Class
 *This will be keep track of how many points you will recieve based on the type of alien
 */


public class StatusPane extends HBox {
// Creating a label to keep track of the score
    private Label scoreLabel;
    private int score = 0;

    public StatusPane(){
        this.setPadding(new Insets(10));
        this.setSpacing(20);
//The start of the score will show that the score is 0
        scoreLabel = new Label("Score: 0");

        this.getChildren().add(scoreLabel);
    }
// When the projectile hits the spaceship or the aliens it will take account of the points and add it to the score
    public void addPoints(int points){
        score += points;
        scoreLabel.setText("Score: " + score);
    }
// If the user presses restart it will restart the score back to 0
    public void reset(){
        score = 0;
        scoreLabel.setText("Score: 0");
    }


}
