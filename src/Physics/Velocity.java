// 322824806 Omer Ahronson
package Physics;
import Geometry.Point;
/**
 *  Physics.Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx, dy;
    /**
     * Constructor.
     * @param dx change in x.
     * @param dy change in y.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * A method to calculate The Physics.Velocity based on desired angle and speed.
     * @param angle angle.
     * @param speed speed.
     * @return The new Physics.Velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(Math.toRadians(angle));
        double dy = speed * Math.sin(Math.toRadians(angle));
        return new Velocity(dx, -dy);
}
    /**
     * A method to calculate The Physics.Velocity based on desired NEGATIVE angle and speed.
     * @param angle angle.
     * @param speed speed.
     * @return The new Physics.Velocity.
     */
public static Velocity fromNegativeAngleAndSpeed(double angle, double speed) {
    double dx = speed * Math.cos(Math.toRadians(angle));
    double dy = speed * Math.sin(Math.toRadians(angle));
    return new Velocity(-dx, dy);
}


    /**
     * Set method for X.
     * @param dx the new dx.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }
    /**
     * Set method for Y.
     * @param dy the new dy.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Get method for Dx.
     * @return Dx.
     */
    public double getDx() {
        return this.dx;
    }
    /**
     * Get method for Dyx.
     * @return Dy.
     */
    public double getDy() {
        return this.dy;
    }
    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p The original point.
     * @return The new modified point.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
}