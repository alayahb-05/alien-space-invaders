/**
 * Alayah Benjamin
 * COMP 167: MAJOR PROJECT 3
 * Section 1
 * Date: November 10th 2025
 * This is the Invader Class
 *This will be displayed as a base for all the alien invader objects
 */
// Holding points taken by the peojectile when it shoots
public abstract class Invader extends GameObject{
    private int pointValue;

    public Invader(){
        super();
        this.pointValue = 0;
    }

    public int getPointValue() {
        return pointValue;
    }
// Each alien has a point system and will determine how much each thing is worth
    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }
}
