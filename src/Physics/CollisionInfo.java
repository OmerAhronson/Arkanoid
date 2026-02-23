// 322824806 Omer Ahronson
package Physics;
import Geometry.Point;
/**
 * A class that holds information about a collision.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collidableObject;

    /**
     * Constructor.
     * @param p the point.
     * @param c the coollidable.
     */
    public CollisionInfo(Point p, Collidable c) {
        this.collisionPoint = p;
        this.collidableObject = c;
    }
    /**
     *  returns the point at which the collision occurs.
     * @return the point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * the collidable object involved in the collision.
     * @return the object.
     */
    public Collidable collisionObject() {
        return this.collidableObject;
    }
}
