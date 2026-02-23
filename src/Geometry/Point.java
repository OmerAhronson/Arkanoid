// 322824806 Omer Ahronson
package Geometry;
import Threshold.ThresholdComparison;

/**
 * A class that defines a point.
 */

public class Point extends ThresholdComparison {
    private double x, y;
    /**
     *Constructor.
     *@param x The coordinates for the point's "x" value.
     *@param y The coordinates for the point's "y" value.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * return the distance of this point to the other point.
     * @param other The other point.
     * @return Returns the distance.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow((other.getX() - this.getX()), 2) + Math.pow((other.getY() - this.getY()), 2));
    }
    /**
     * Returns the point's x coordinates.
     * @return x value.
     */
    public double getX() {
        return this.x;
    }
    /**
     * Returns the point's y coordinates.
     * @return y value.
     */
    public double getY() {
        return this.y;
    }

    /**
     * A setter for x.
     * @param x the new x.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * A setter for y.
     * @param y the new y.
     */
    public void setY(double y) {
        this.y = y;
    }
}

