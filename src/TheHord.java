import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * Alayah Benjamin
 * COMP 167: MAJOR PROJECT 3
 * Section 1
 * Date: November 10th 2025
 * This is the GamePane Class
 *This will be the class that groups all the aliens together to form and move all the same way and speed
 */


public class TheHord {

    private Alien[][] aliens;
    private double direction = 1;
    private double speed = 1.0;
    private int moveCounter = 0;
    private Pane actionPane;
    private Image alienSprites;
    private int hitCounter = 0;

    private final int ROWS = 5;
    private final int COLS = 11;
    private double startY = 50;
    private double cmdHeight = 100;

    private final double originalStartY = 50;
    private final double originalSpeed = 1.0;

    public TheHord(Pane actionPane){
        this.actionPane = actionPane;

        // Load alien sprite sheet
        try {
            alienSprites = new Image("file:images/alienspritesheet.png"); // safer path
        } catch (Exception e){
            System.err.println("Alien sprite sheet not found: " + e.getMessage());
        }

        aliens = new Alien[ROWS][COLS];

        initTheHord();
    }

    //The rows and col of how many aliens are in each row and col (2 types have 2 rows)
    public void initTheHord(){
        double startX = 0;
        double spacingX = 45;
        double spacingY = 30;

        for(int row = 0; row < ROWS; row++){
            for(int col = 0; col < COLS; col++){
                int type, points;
// seperating the aliens based on the "type" they are
                if(row == 0){ type = 0; points = 40; }
                else if(row == 1 || row == 2){ type = 1; points = 20; }
                else { type = 2; points = 10; }

                Alien alien = new Alien(actionPane, alienSprites, type, points);
                alien.getImageView().setX(startX + col * spacingX);
                alien.getImageView().setY(startY + row * spacingY);

                // Add the ImageView to the pane
                actionPane.getChildren().add(alien.getImageView());

                aliens[row][col] = alien;
            }
        }
    }
// This is to have the hord go faster the more and more times the aliens are hit
    public void recordHit(){
        hitCounter++;
        if (hitCounter % 4 == 0){
            speed += 0.2;
        }
    }


    public void move(){
        boolean atEdge = false;

        for(int row=0; row<ROWS; row++){
            for(int col=0; col<COLS; col++){
                Alien alien = aliens[row][col];
                if(alien != null){
                    alien.move(direction * speed, 0);

                    if(alien.getImageView().getX() <= 0 || alien.getImageView().getX() + 50 >= actionPane.getWidth()){
                        atEdge = true;
                    }
                }
            }
        }
        if(atEdge) changeDirection();
    }
// This allows the group of aliens move direction based on where it starts (from left too right then go down and then go the pther direction)
    private void changeDirection(){
        direction *= -1;
        for(int row=0; row<ROWS; row++){
            for(int col=0; col<COLS; col++){
                Alien alien = aliens[row][col];
                if(alien != null) alien.move(0,3);
            }
        }
    }

// Once the last col / row gets near the cmd center it will stop the game (game over)
    public Alien getLowestAlienInCol(int col){
        for(int row=ROWS-1; row>=0; row--){
            Alien alien = aliens[row][col];
            if(alien != null && alien.isActive()) return alien;
        }
        return null;
    }

    //This method is for all the aliens are destroyed then the game will end
    public boolean allAliensDestroyed(){
        for (int row=0; row<ROWS; row++){
            for(int col=0; col<COLS; col++){
                Alien alien = aliens[row][col];
                if(alien != null && alien.isActive()) return false;
            }
        }
        return true;
    }




// When the user rest it will remove them and set them back to the orginal position
    public void resetTheHord(){
        // Remove aliens from pane
        for(int row=0; row<ROWS; row++){
            for(int col=0; col<COLS; col++){
                Alien alien = aliens[row][col];
                if(alien != null) actionPane.getChildren().remove(alien.getImageView());
            }
        }

        startY += 20;
        if(startY + ROWS*50 >= actionPane.getHeight() - cmdHeight) startY = 50;

        speed = 1.0;
        moveCounter = 0;
        initTheHord();
    }

    public void restartHord(){
        for(int row=0; row<ROWS; row++){
            for(int col=0; col<COLS; col++){
                Alien alien = aliens[row][col];
                if(alien != null) actionPane.getChildren().remove(alien.getImageView());
            }
        }

        startY = originalStartY;
        speed = originalSpeed;
        moveCounter = 0;
        direction = 1;
        initTheHord();

    }






}
