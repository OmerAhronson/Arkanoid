// 322824806 Omer Ahronson
package Sprites;
import Core.Game;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import Physics.Collidable;
import Physics.Velocity;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import Geometry.Ball;
/**
 * A block class.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private final Color color;
    private List<HitListener> hitListeners = new ArrayList<>();

    /**
     * Constructor for Sprites.Block.
     * @param upperLeft upper left point.
     * @param width width.
     * @param height height.
     * @param color color.
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.color = color;
    }

    /**
     *  Return the "collision shape" of the object.
     * @return collision shape.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Implementation of hit method.
     * @param collisionPoint collision point.
     * @param currentVelocity the pre-collision velocity.
     * @param hitter The ball that hit the block.
     * @return Updated velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) throws Exception {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Line[] lines = this.rectangle.getRectanglesLines();
        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }
        hitter.setColor(this.color);
        for (int i = 0; i < 2; i++) {
            //Checks if the collision is with a horizontal side of the rectangle:
            if (lines[i].isIntersectingWithPoint(collisionPoint)) {
                return new Velocity(dx, -dy);
            }
        }
        for (int i = 2; i < 4; i++) {
            //Checks if the collision is with a vertical side of the rectangle:
            if (lines[i].isIntersectingWithPoint(collisionPoint)) {

                return new Velocity(-dx, dy);
            }
        }
        //If there was a mistake and the collision point is not a collision point.
        return currentVelocity;
    }
    /**
     * The method will fill a block based on its coordinates and radius.
     * @param d Draw Surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        double x = this.rectangle.getUpperLeft().getX();
        double y = this.rectangle.getUpperLeft().getY();
        d.fillRectangle((int) x, (int) y, (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        for (int i = 0; i < 4; i++) {
            d.setColor(Color.BLACK);
            int startX = (int) this.rectangle.getRectanglesLines()[i].start().getX();
            int startY = (int) this.rectangle.getRectanglesLines()[i].start().getY();
            int endX = (int) this.rectangle.getRectanglesLines()[i].end().getX();
            int endY = (int) this.rectangle.getRectanglesLines()[i].end().getY();
            d.drawLine(startX, startY, endX, endY);


        }
    }

    /**
     *  Notify the sprite that time has passed.
     */
    public void timePassed() {
    }

    /**
     * Adding the block to the game.
     * @param game game.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * A method that checks if the ball's color matches the block's color.
     * @param ball the ball that hits the block.
     * @return truth or false.
     * @throws Exception if a null ball was passed.
     */
    public Boolean ballColorMatch(Ball ball)  {
        if (ball != null) {
            return (this.color.equals(ball.getColor()));

        }
        return false;
    }

    /**
     * Removes the block from the game.
     * @param game the game.
     * @throws Exception if a null game have been passed.
     */
    public void removeFromGame(Game game) throws Exception {
        if (game == null) {
            return;
        }
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        for (HitListener hl : listeners) {
            removeHitListener(hl);
        }
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        if (hl != null) {
            this.hitListeners.add(hl);
        }
    }

    @Override
    public void removeHitListener(HitListener hl) {
        if (hl != null) {
            this.hitListeners.remove(hl);
        }
    }

    /**
     * Notify all listeners about a hit event:.
     * @param hitter The ball that hit the block.
     */
    private void notifyHit(Ball hitter) throws Exception {
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * A get method for the rectangle.
     * @return the rectangle.
     */
    public Rectangle getRectangle() {
        return this.rectangle;
    }
}