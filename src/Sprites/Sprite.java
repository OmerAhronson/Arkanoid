// 322824806 Omer Ahronson
package Sprites;
import biuoop.DrawSurface;
/**
 * A sprite interface.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d draw surface.
     */
    void drawOn(DrawSurface d);
    /**
     * notify the sprite that time has passed.
     */
    void timePassed() throws Exception;
}
