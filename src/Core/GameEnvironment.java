// 322824806 Omer Ahronson
package Core;
import Geometry.Point;
import Physics.Collidable;
import java.util.ArrayList;
import java.util.List;

import Geometry.Line;
import Physics.CollisionInfo;
/**
 * A class for the game.
 */
public class GameEnvironment  {
    private final ArrayList<Collidable> collidableObjects;
    /**
     * Constructor.
     */
    public GameEnvironment() {
        this.collidableObjects = new ArrayList<>();
    }

    /**
     * Add the given collidable to the list of collidable Objects of the game.
     * @param c the collidable object.
     */
    public void addCollidable(Collidable c) {
        if (c != null) {
            collidableObjects.add(c);
        }
    }

    /**
     * A getter method for the list of collidables.
     * @return the list.
     */
    public ArrayList<Collidable> getColidablesList() {
        return this.collidableObjects;
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     * @param trajectory The trajectory of the ball.
     * @return The closest collision that's about to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestCollisionPoint = null;
        Collidable closestCollisionsObject = null;
        List<Collidable> collidables = new ArrayList<>(this.collidableObjects);

        if (this.collidableObjects.isEmpty()) {
            System.out.println("There are no collidable objects in the game.");
            return null;
        }
        for (Collidable collidable : collidables) {
            Point collisionPoint = collidable.getCollisionRectangle().getIntersection(trajectory);
            if (collisionPoint != null) {
                closestCollisionPoint = collisionPoint;
                closestCollisionsObject = collidable;
            }
        }
        if (closestCollisionPoint == null) {
            return null;
        }
        return new CollisionInfo(closestCollisionPoint, closestCollisionsObject);
    }

    /**
     * Removes the given collidable to the list of collidable Objects of the game.
     * @param c The collidable.
     * @throws Exception if a null pointer is passed or if the sprite does not exist in the list.
     */
    public void removeCollidable(Collidable c) throws Exception {
        if (c == null) {
            throw new Exception("Cannot pass a null as a parameter");
        }
        collidableObjects.remove(c);
    }


}