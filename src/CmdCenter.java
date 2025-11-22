import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Alayah Benjamin
 * COMP 167: MAJOR PROJECT 3
 * Section 1
 * Date: November 10th 2025
 * This is the CmdCenter (Command Center) class
 * Represent the player's main "base" station that will move the ship and fire one projectile at a time
 */

public class CmdCenter extends GameObject {
    private Projectile projectile;


    // Loading the picture of the cmd center and cropping it
    public CmdCenter (ActionPane actionPane){
        super();

        try{
            Image image = new Image(new FileInputStream("images/alienspritesheet.png"));
            this.setImage(image);
        } catch (FileNotFoundException exception){
            System.err.println("CmdCenter image not found: " + exception.getMessage());
            System.exit(-1);
        }

        Rectangle2D rectangle2D = new Rectangle2D(217,16,21,15);
        this.setViewport(rectangle2D);

        this.setScaleX(1.5);
        this.setScaleY(1.5);

        setParentWidth(actionPane.getPrefWidth());
        setParentHeight(actionPane.getPrefHeight());

        // Showing how far from the bottom it is in the application
        this.setX(getParentWidth() / 2 - 20);
        this.setY(getParentHeight() - 30);

        setDirection(Movable.EAST);
        setSpeed(10);
    }

    // This i sused to help the cmdcenter be able to shoot up towards the aliens and spaceship
    public void fireProjectile(ActionPane actionPane) {
        if (projectile == null || !projectile.isActive()){
            projectile = new Projectile(this.getX() + this.getFitWidth() / 2, this.getY());
            actionPane.getChildren().add(projectile);
        }
    }

    public Projectile getProjectile() {
        return projectile;
    }

    @Override
    public void move() {

    }
}
