// 322824806 Omer Ahronson
package Sprites;
import Geometry.Ball;
/**
 * An interface for an object that needs to be notified when a collision has occurred.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit The block that has been hit.
     * @param hitter the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter) throws Exception;
}
