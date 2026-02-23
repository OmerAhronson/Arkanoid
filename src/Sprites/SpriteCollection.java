// 322824806 Omer Ahronson
package Sprites;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;
/**
 * The following class will hold a collection of sprites.
 */
public class SpriteCollection {
    private final ArrayList<Sprite> sprites;

    /**
     * A constructor method.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }
    /**
     * Adds a new sprite to the array list.
     *
     * @param s The new sprite.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Removes a sprite from the array list.
     * @param s the sprite.
     * @throws Exception if a null pointer is passed or if the sprite does not exist in the list.
     */
    public void removeSprite(Sprite s) throws Exception {
        if (s == null) {
            throw new Exception("Error.");
        }
        sprites.remove(s);
    }
    /**
     * Call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() throws Exception {
        List<Sprite> spritesCopy = new ArrayList<>(this.sprites);

        for (Sprite sprite : spritesCopy) {
            sprite.timePassed();
        }
    }

    /**
     * Call drawOn(d) on all sprites.
     * @param d draw surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.sprites) {
            sprite.drawOn(d);
        }
    }
}
