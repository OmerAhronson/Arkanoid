package Physics;

import Geometry.Ball;
import Geometry.Point;
import Geometry.Rectangle;

/**
 * "Physics.Collidable" interface.
 */
public interface Collidable {
    /**
     * // Return the "collision shape" of the object.
     * @return the shape.
     */
    Rectangle getCollisionRectangle();
    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param collisionPoint collision point.
     * @param currentVelocity the pre-collision velocity.
     * @param hit the ball that hit the block.
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hit, Point collisionPoint, Velocity currentVelocity) throws Exception;
}
