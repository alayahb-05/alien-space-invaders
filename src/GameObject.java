import javafx.scene.image.ImageView;

/**
 * Alayah Benjamin
 * COMP 167: MAJOR PROJECT 3
 * Section 1
 * Date: November 10th 2025
 * This is the GameObject class that provides direction, speed, and the boundary info for the motion
 */

public abstract class GameObject extends ImageView implements Movable {
    private double direction;
    private double speed;
    private double parentWidth;
    private double parentHeight;

    public GameObject(){
        this.setDirection(0.0);
        this.setSpeed(0.0);

    }
// args constructor
    public GameObject(double direction, double speed, double parentWidth, double parentHeight){
        this.setDirection(direction);
        this.setSpeed(speed);
        this.setParentHeight(parentHeight);
        this.setParentWidth(parentWidth);
    }

// GETTERS AN SETTERS //
    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getParentWidth() {
        return parentWidth;
    }

    public void setParentWidth(double parentWidth) {
        this.parentWidth = parentWidth;
    }

    public double getParentHeight() {
        return parentHeight;
    }

    public void setParentHeight(double parentHeight) {
        this.parentHeight = parentHeight;
    }
}
