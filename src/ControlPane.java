import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * Alayah Benjamin
 * Date: November 10th 2025
 * This is the ControlPane Class
 *This will be the Control Panel where the buttons to start, restart and exit are
 */


// This us used to create the buttons to start the game, restart the game and exit the application

public class ControlPane extends HBox {
    private Button startBtn;
    private Button restartBtn;
    private Button exitBtn;

    //constructor that sets up the layout and the button functionality
    public ControlPane(GamePane gamePane){

        // adding padding aroudn the hbox
        this.setPadding(new Insets(10));
        this.setSpacing(20);

        //creating the buttons and their names aligning them to the center of the application at the button
        this.setAlignment(Pos.CENTER);
        startBtn = new Button("Start");
        restartBtn = new Button("Restart");
        exitBtn = new Button("Exit");

        //This is used so that the user can only click on a button using the mouse nothing else
        startBtn.setOnMouseClicked(e -> gamePane.startGame());
        restartBtn.setOnMouseClicked(e -> gamePane.restartGame());
        exitBtn.setOnMouseClicked(e -> System.exit(0));

        //Putting all the buttons together on a panel
        this.getChildren().addAll(startBtn, restartBtn, exitBtn);



    }
}
