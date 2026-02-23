// 322824806 Omer Ahronson
package Sprites;
import Core.Game;
import Geometry.Ball;
import Geometry.Point;
import Geometry.Rectangle;
import Physics.Collidable;
import Physics.Velocity;
import biuoop.DrawSurface;
import java.awt.Color;
/**
 * The paddle class.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    /**
     * Consturctor methid.
     * @param upperLeft upper left Points coordinates.
     * @param width width.
     * @param height height.
     * @param keyboard keyboard.
     */
    public Paddle(Point upperLeft, double width, double height, biuoop.KeyboardSensor keyboard) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.keyboard = keyboard;
    }
    /**
     * Moves the paddle to the left.
     */
    public void moveLeft() {
        double curX = this.rectangle.getUpperLeft().getX();
        double curY = this.rectangle.getUpperLeft().getY();
        double width = this.rectangle.getWidth();
        double height = this.rectangle.getHeight();
        double newX;
        if (curX + this.rectangle.getWidth() <= 5) {
            newX = 800;
            this.rectangle = new Rectangle(new Point(newX, curY), width, height);
            return;
        }
        this.rectangle = new Rectangle(new Point(curX - 5, curY), width, height);
    }
    /**
     * Moves the paddle to the right.
     */
    public void moveRight() {
        double curX = this.rectangle.getUpperLeft().getX();
        double curY = this.rectangle.getUpperLeft().getY();
        double width = this.rectangle.getWidth();
        double height = this.rectangle.getHeight();
        double newX;
        if (curX - this.rectangle.getWidth() >= 795) {
            newX = 0;
            this.rectangle = new Rectangle(new Point(newX, curY), width, height);
            return;
        }
        this.rectangle = new Rectangle(new Point(curX + 5, curY), width, height);
    }

    /**
     * Lets the Sprites.Paddle know time has passed,
     * and proceeds to check if the keyboard's relevant buttons have been pressed.
     */
    public void timePassed() {
        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     *  Implementation of hit method, designed to react differently according to where the ball hit the paddle.
     * @param collisionPoint collision point.
     * @param currentVelocity the pre-collision velocity.
     * @param hitter the ball that hit the paddle.
     * @return The new calculated velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double collisionX = collisionPoint.getX() - this.rectangle.getUpperLeft().getX();
        double fifth = (this.rectangle.getWidth()) / 5;
        int region = (int) (((int) collisionX) / fifth + 1);
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double speed = 5;
        switch (region) {
            case 1 : return Velocity.fromNegativeAngleAndSpeed(330, speed);
            case 2: return Velocity.fromNegativeAngleAndSpeed(300, speed);
            case 3: return  new Velocity(dx, -dy);
            case 4: return  Velocity.fromAngleAndSpeed(60, speed);
            case 5: return  Velocity.fromAngleAndSpeed(30, speed);
            default:
                return Velocity.fromAngleAndSpeed(currentVelocity.getDx(), currentVelocity.getDy());
        }
    }

    /**
     * Returns the paddle's rectangle form.
     * @return rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Drawing method.
     * @param d draw surface.
     */
    public void drawOn(DrawSurface d) {
            d.setColor(Color.orange);
            double x = this.rectangle.getUpperLeft().getX();
            double y = this.rectangle.getUpperLeft().getY();
            d.fillRectangle((int) x, (int) y, (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }
    /**
     * Adding the Sprites.Paddle to the game.
     * @param game game.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

}
