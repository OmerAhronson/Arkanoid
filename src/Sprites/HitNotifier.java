// 322824806 Omer Ahronson
package Sprites;
/**
 * An interface for an object who will notify when hit.
 */
public interface HitNotifier {
    // Add hl as a listener to hit events.

    /**
     * Adding the given hit listener object to the list of listeners for the event.
     * @param hl the listener.
     */
    void addHitListener(HitListener hl);
    // Remove hl from the list of listeners to hit events.

    /**
     * Removing the given hit listener object from the list of listeners for the event.
     * @param hl the listener.
     */
    void removeHitListener(HitListener hl);
}
