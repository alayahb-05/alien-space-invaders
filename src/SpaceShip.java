import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

/**
 * Alayah Benjamin
 * Date: November 10th 2025
 * This is the SpaceShip Class
 *This will allow the ship to randomly move across the screen and then will disappear when it gets hit
 */

public class SpaceShip extends GameObject {
    private boolean active = false;
    private Random random = new Random();
    private int pointValue = 100;
    private AnimationTimer moveTimer;
    private ActionPane actionPane;
    private Timeline timeline;
    private static final int[] SCORE_VALUES = {100, 200, 300};
    private int scoreIndex = 0;

// Using action pane to place the red spaceship onto the application
    public SpaceShip(ActionPane actionPane){
        super();
        this.actionPane = actionPane;

        try {
            Image spaceship = new Image(new FileInputStream("images/space_invaders_sprite_sheet.jpg"));
            this.setImage(spaceship);

            Rectangle2D ship = new Rectangle2D(10,0,100,80);

            this.setViewport(ship);
            this.setFitWidth(50);
            this.setFitHeight(50);
        } catch (FileNotFoundException e){
            System.out.println("Image not found.");
        }
// Using a try/catch to load the image from the images folder and cropping it to form the rectangle of the full spaceship

        this.setVisible(false);


        moveTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                move();
            }
        };

        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        scheduleNextLaunch();
        timeline.play();

    }

    // Intervals from 5-25 seconds to randomly launch onto the application
    public void scheduleNextLaunch(){
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(getRandomLaunchTime()), e -> launch())
        );
    }

    private double getRandomLaunchTime(){
        return 5 + random.nextInt(21);
    }

// When the user press start it will lauch and be able to move the space ship from left to right and then right to left back and forth waiting to be hit
    public void launch(){
        if (active) return;
        active = true;
        this.setVisible(true);
        moveTimer.start();

        setSpeed(3);
        setY(1);

        double paneWidth = actionPane.getWidth();

        if (paneWidth == 0){
            paneWidth = actionPane.getPrefWidth();
        }

        boolean fromLeft = random.nextBoolean();

        if (fromLeft) {
            setX(-50);
            setDirection(0);

        } else {
            setX(paneWidth + 50);
            setDirection(180);

        }

        moveTimer.start();

        scheduleNextLaunch();
    }





    @Override
    public void move() {
        if (!active) return;


        double newX = this.getX() + getSpeed() * Math.cos(Math.toRadians(getDirection()));
        this.setX(newX);

        if (newX < -50 || newX > actionPane.getWidth() + 50){
            active = false;
            this.setVisible(false);
        }
    }

    // If it is active on the application it will run and be visiable
    public boolean isActive(){
        return active;
    }

    // If it not active it will not show on the application
    public void deactivate(){
        active = false;
        this.setVisible(false);
        moveTimer.stop();
    }

//holding a point value worth 100 points
    public int getPointValue() { return pointValue;}

}
