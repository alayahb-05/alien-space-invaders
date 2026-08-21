/**
 * Alayah Benjamin
 * Date: November 10th 2025
 * This is the Moveable class to be able to move the objects in specific direction in degrees
 */

//Showing direction that the objects will be able to move in degree
public interface Movable {
    public static final double EAST = 0.0;
    public static final double SOUTH = 90.0;
    public static final double WEST = 180.0;
    public static final double NORTH = 270.0;

    void move();
}
