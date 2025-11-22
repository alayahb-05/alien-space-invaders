import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.io.FileInputStream;

/**
 * Alayah Benjamin
 * COMP 167: MAJOR PROJECT 3
 * Section 1
 * Date: November 10th 2025
 * This is the Projectile Class
 * Represents the "firing" mad by the CmdCenter that can be active one at a time
 */

public class Projectile extends GameObject {
    private boolean active;

    public Projectile() {
        super();
        this.active = false;// if its not being used it will not be shown
    }

    public Projectile(double startX, double startY) {
        super(); //Inheriting from parent class
        this.setX(startX);
        this.setY(startY);
        this.setSpeed(13);
        this.active = true;

        loadImage();
    }

    //Loading th epicture of the projectile to indicate what is hitting the aliens and space ship
    private void loadImage(){
        try {
            Image sheet = new Image(new FileInputStream("images/space_invaders_sprite_sheet.jpg"));
            this.setImage(sheet);
            this.setViewport(new Rectangle2D(272, 1135, 3, 10));
            this.setScaleX(2);
            this.setScaleY(2);
        } catch(Exception e) {
            System.out.println("Projectile Image not found.");
        }
    }


    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active){
        this.active = active;
    }

    public void deactivate(){
        this.active = false;
        this.setVisible(false);
    }
// Will move when the space bar is pressed and be directed to move straight up
    @Override
    public void move() {
        if (!active) return;

        this.setY(getY()- getSpeed());

        if (getY() < 0) {
            deactivate();

        }
    }


}
