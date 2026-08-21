
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


/**
 * Alayah Benjamin
 * Date: November 10th 2025
 * This is the Alien Class
 *This will be the class that helps display each type of alien and crop them to later be formed in the hord
 */


public class Alien {

    private ImageView imageView;
    private int points;
    private Image explosionImage;
    private boolean active = true;

    public Alien(Pane actionPane, Image sprite, int type, int points){
        this.imageView = new ImageView(sprite);

        // Optional: set viewport depending on type to show different alien images
        switch (type){
            case 0: imageView.setViewport(new javafx.geometry.Rectangle2D(1,13,14,14)); break;
            case 1: imageView.setViewport(new javafx.geometry.Rectangle2D(55,13,18,14)); break;
            case 2: imageView.setViewport(new javafx.geometry.Rectangle2D(113,14,20,14)); break;
        }

        this.points = points;

    }


    public ImageView getImageView() {
        return imageView;
    }

    public boolean isActive(){
        return active;
    }

    public int getPoints() {
        return points;
    }

    // Move the alien by dx, dy
    public void move(double dx, double dy){
        imageView.setX(imageView.getX() + dx);
        imageView.setY(imageView.getY() + dy);
    }

    public void deactivate(){
        active = false;
        imageView.setVisible(false);
    }


}
