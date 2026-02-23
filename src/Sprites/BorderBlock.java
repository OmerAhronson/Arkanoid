// 322824806 Omer Ahronson
package Sprites;
import Geometry.Line;
import Geometry.Point;
import Geometry.Ball;
import Physics.Velocity;
import java.awt.Color;
/**
 * A class for the borders of the screen.
 */
public class BorderBlock extends Block {

    /**
     * Constructor method.
     * @param upperLeft upper left point.
     * @param width width of the block.
     * @param height height of the block.
     * @param color color of the block.
     */
    public BorderBlock(Point upperLeft, double width, double height, Color color) {
        super(upperLeft, width, height, color);
    }

    /**
     * Overrides the hit method to prevent the ball's color from changing.
     * @param hitter The ball that hit the block.
     * @param collisionPoint The point at which the collision occurred.
     * @param currentVelocity The current velocity of the ball.
     * @return Updated velocity after the collision.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) throws Exception {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Line[] lines = this.getRectangle().getRectanglesLines();
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
}