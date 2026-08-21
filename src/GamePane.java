import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;



/**
 * Alayah Benjamin
 * Date: November 10th 2025
 * This is the GamePane Class
 *This will be the controls the user inputs and updates the game animation
 */

public class GamePane extends BorderPane {
    private ActionPane actionPane;
    private CmdCenter cmdCenter;
    private SpaceShip spaceShip;
    private AnimationTimer gameTimer;
    private ControlPane controlPane;
    private StatusPane statusPane;
    private TheHord hord;


    private boolean gameRunning = false;

    // Constructor that intializes all of the components in order to make the game and run the game
    public GamePane(Scene scene){
        actionPane = new ActionPane();
        this.setCenter(actionPane);

        hord = new TheHord(actionPane);

        statusPane = new StatusPane();
        this.setTop(statusPane);

        cmdCenter = new CmdCenter(actionPane);
        actionPane.getChildren().add(cmdCenter);

        spaceShip = new SpaceShip(actionPane);
        actionPane.getChildren().add(spaceShip);

        controlPane = new ControlPane(this);
        this.setBottom(controlPane);

        setupKeyControl(scene);

        setupGameTimer();

    }
    // This method is used to start the game from the state all of the components start from
    // Activites game loop, launches the spaceship, and the positions the command center
    public void startGame(){
        cmdCenter.setX((actionPane.getWidth() - cmdCenter.getFitWidth()) / 2);
        cmdCenter.setY(actionPane.getHeight() - cmdCenter.getFitHeight() - 20);

        cmdCenter.setVisible(true);


        spaceShip.launch();  // This will activate the spaceship movement

        gameRunning = true;

    }

    // This method is used to restart the game and then will make eeverything return to its natural psoition to then play again
    public void restartGame(){
        statusPane.reset();
        spaceShip.deactivate();
        spaceShip.launch();

        cmdCenter.setX((actionPane.getWidth() - cmdCenter.getFitWidth()) / 2);
        cmdCenter.setY((actionPane.getHeight() - cmdCenter.getFitHeight()) - 10);
        hord.restartHord();

        gameRunning = true;

    }

    // This method is used to set up the keyboard controls to move the cmd center and shoot the projectiles
    private void setupKeyControl(Scene scene) {
        this.setOnMouseClicked(e -> this.requestFocus());
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) { //move left
                moveCmdCenter(-cmdCenter.getSpeed());
            } else if (e.getCode() == KeyCode.RIGHT) { //move right
                moveCmdCenter(cmdCenter.getSpeed());
            } else if (e.getCode() == KeyCode.SPACE) { // press the space in order to shoot the projectile
                cmdCenter.fireProjectile(actionPane);
            }
        });
    }

// This is used to move the command center while keeping it all inside of the actionpane (background)
    private void moveCmdCenter(double deltaX){
        double newX = cmdCenter.getX() + deltaX;
        if(newX < 0) newX = 0;
        if (newX > actionPane.getWidth() - cmdCenter.getFitWidth())
            newX = actionPane.getPrefWidth() - cmdCenter.getFitWidth();
        cmdCenter.setX(newX);
    }

    // This method creates the animation loop to run every frame
    // Tracks the alien movement
    private void setupGameTimer(){
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!gameRunning || hord == null) return;
                hord.move();

                for (int col = 0; col < 11; col++){
                    Alien lowest = hord.getLowestAlienInCol(col);
                    if (lowest != null && lowest.getImageView().getY() + 50 >= cmdCenter.getY()){
                        gameRunning = false;
                        System.out.println("GAME OVER!");
                    }
                }
// This is used to check the collison of the projectile hitting the spaceship and the alien
                Projectile projectile = cmdCenter.getProjectile();
                if (projectile != null && projectile.isActive()){
                    projectile.move();

                    for (int col = 0; col < 11; col++){
                        Alien lowest = hord.getLowestAlienInCol(col);
                        if (lowest != null && projectile.getBoundsInParent().intersects(lowest.getImageView().getBoundsInParent())) {
                            lowest.deactivate();
                            actionPane.getChildren().remove(lowest.getImageView());

                            statusPane.addPoints(lowest.getPoints());

                            projectile.deactivate();

                            hord.recordHit();

                            break;
                        }
                    }

                    if (hord.allAliensDestroyed()){
                        hord.resetTheHord();
                    }
                }

                if (spaceShip.isActive() && projectile != null && projectile.isActive() &&
                projectile.getBoundsInParent().intersects(spaceShip.getBoundsInParent())){

                    statusPane.addPoints(spaceShip.getPointValue());
                    spaceShip.deactivate();
                    projectile.deactivate();
                }


            }
        };
        // Starts the timer for the game
        gameTimer.start();
    }
}
