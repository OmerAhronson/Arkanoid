// 322824806 Omer Ahronson
package Geometry;
import Core.Game;
import Core.GameEnvironment;
import biuoop.DrawSurface;
import Threshold.ThresholdComparison;
import Sprites.Sprite;
import Physics.Velocity;
import Physics.CollisionInfo;
import java.awt.Color;


/**
 * A class that will define a basic ball.
 */
public class Ball extends ThresholdComparison implements Sprite {
    private Point center;
    private final int r;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment game;
    private final Line trajectory;
    /**
     * Constructor.
     * @param center center point.
     * @param r radius.
     * @param color color.
     * @param  g game environment.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment g) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.game = g;
        this.trajectory = new Line(this.center, new Point(this.center.getX() + this.velocity.getDx() + this.r,
                this.center.getY() + this.velocity.getDy() + this.r));
    }
    /**
     * A getter for the radius's x coordinates.
     * @return x coordinates.
     */
    public int getX() {
        return (int) this.center.getX();
    }
    /**
     * A getter for the radius's y coordinates.
     * @return y coordinates.
     */
    public int getY() {
        return (int) this.center.getY();
    }
    /**
     * Returns the ball's size.
     * @return calculated size.
     */
    public int getSize() {
        return this.r;
    }
    /**
     * A method for setting Physics.Velocity by assignment.
     * @param v Physics.Velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }
    /**
     * A method for setting velocity by parameters.
     * @param dx difference in x.
     * @param dy difference in y.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * A set method for the trajectory field.
     */
    public void updateTrajectory() {
        this.trajectory.start().setX(this.center.getX());
        this.trajectory.start().setY(this.center.getY());
        this.trajectory.end().setX(this.center.getX() + this.velocity.getDx());
        this.trajectory.end().setY(this.center.getY() + this.velocity.getDy());
    }

    /**
     * A getter for velocity.
     * @return velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }
    /**
     * Applying the velocity to the ball, by adjusting the center's coordinates, which makes the ball "move".
     */
    public void moveOneStep() throws Exception {
        this.updateTrajectory();
        double adjustmentFactor = this.r + 0.1;
        double adjustX = adjustmentFactor;
        double adjustY = adjustmentFactor;
        //Moving in right direction
        if (this.velocity.getDx() > 0) {
            adjustX *= -1;
        }
        if (ThresholdComparison.isEqual(this.velocity.getDx(), 0)) {
            adjustX = 0;
        }
        //Moving down
        if (this.velocity.getDy() > 0) {
            adjustY *= -1;
        }
        if (ThresholdComparison.isEqual(this.velocity.getDy(), 0)) {
            adjustY = 0;
        }
        CollisionInfo info = game.getClosestCollision(trajectory);
        if (info != null) {
            this.center = new Point(info.collisionPoint().getX() + adjustX, info.collisionPoint().getY() + adjustY);
            this.setVelocity(info.collisionObject().hit(this, info.collisionPoint(), this.velocity));
        }
        this.center = this.getVelocity().applyToPoint(this.center);
        if (this.velocity.getDx() == 0) {
            this.trajectory.forceVertical();
        } else {
            this.trajectory.forceNonVertical();
        }
    }



    /**
     * A method made to keep the balls withing given bounds, made for staying withing the frame or a rectangle.
     * @param widthStart Frame's horizontal starting point.
     * @param widthEnd  Frame's horizontal ending point.
     * @param heightStart Frame's vertical starting point.
     * @param heightEnd Frame's vertical ending point.
     */
    public void stayWithinBounds(int widthStart, int widthEnd, int heightStart, int heightEnd) {
        if (ThresholdComparison.aMoreOrEqualB(this.center.getX() + this.r, widthEnd)
                || ThresholdComparison.aLessOrEqualB(this.center.getX() - this.r, widthStart)) {
            this.velocity.setDx(this.velocity.getDx() * -1);
        }
        if (ThresholdComparison.aMoreOrEqualB(this.center.getY() + this.r, heightEnd)
                || ThresholdComparison.aLessOrEqualB(this.center.getY() - this.r, heightStart)) {
            this.velocity.setDy(this.velocity.getDy() * -1);
        }
    }
    /**
     * The method will fill a circle based on its coordinates and radius.
     * @param surface Draw Surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.r);
    }

    /**
     * A method to ensure that a ball will be spawned inside the frame.
     * @param widthStart starting point's X coordinates.
     * @param widthEnd   Ending point's X coordinates.
     * @param heightStart starting point's Y coordinates.
     * @param heightEnd  Ending point's Y coordinates.
     */
    public void adjustCoordinates(int widthStart, int widthEnd, int heightStart, int heightEnd) {
        if (ThresholdComparison.aMoreOrEqualB(this.center.getX() + this.r, widthEnd)) {
            this.center.setX((double) (widthStart + widthEnd) / 2);
        }
        if (ThresholdComparison.aLessOrEqualB(this.center.getX() - this.r, widthStart)) {
            this.center.setX((double) (widthStart + widthEnd) / 2);
        }
        if (ThresholdComparison.aMoreOrEqualB(this.center.getY() + this.r, heightEnd)) {
            this.center.setY((double) (heightEnd + heightStart) / 2);
        }
        if (ThresholdComparison.aLessOrEqualB(this.center.getY() - this.r, heightStart)) {
            this.center.setY((double) (heightEnd + heightStart) / 2);
        }

    }
    /**
     * A method to check if the ball hits a vertical side.
     * @param widthStart starting point's X coordinates.
     * @param widthEnd   Ending point's X coordinates.
     * @param heightStart starting point's Y coordinates.
     * @param heightEnd  Ending point's Y coordinates.
     * @return true if there is a collision or false if not.
     */
    public boolean ballsCollisionWithVertical(int widthStart, int widthEnd, int heightStart, int heightEnd) {
        double x = this.center.getX();
        double y = this.center.getY();
        return ((ThresholdComparison.aMoreOrEqualB(x + this.r, widthStart)
                && ThresholdComparison.aLessOrEqualB(x + this.r, widthEnd)
                && ThresholdComparison.aMoreOrEqualB(this.velocity.getDx(), 0)
                && ThresholdComparison.aMoreOrEqualB(y, heightStart)
                && ThresholdComparison.aLessOrEqualB(y, heightEnd))
                ||
                (ThresholdComparison.aLessOrEqualB(x - this.r, widthEnd)
                        && ThresholdComparison.aMoreOrEqualB(x - this.r, widthStart)
                        && ThresholdComparison.aLessOrEqualB(this.velocity.getDx(), 0)
                        && ThresholdComparison.aMoreOrEqualB(y, heightStart)
                        && ThresholdComparison.aLessOrEqualB(y, heightEnd)));
    }
    /**
     * A method to check if the ball hits a horizontal side.
     * @param widthStart starting point's X coordinates.
     * @param widthEnd   Ending point's X coordinates.
     * @param heightStart starting point's Y coordinates.
     * @param heightEnd  Ending point's Y coordinates.
     * @return true if there is a collision or false if not.
     */
    public boolean ballsCollisionWithHorizontal(int widthStart, int widthEnd, int heightStart, int heightEnd) {
        double x = this.center.getX();
        double y = this.center.getY();
        return ((ThresholdComparison.aMoreOrEqualB(y + this.r, heightStart)
                && ThresholdComparison.aLessOrEqualB(y + this.r, heightEnd)
                && ThresholdComparison.aMoreOrEqualB(this.velocity.getDy(), 0)
                && ThresholdComparison.aMoreOrEqualB(x, widthStart)
                && ThresholdComparison.aLessOrEqualB(x, widthEnd))
                ||
                (ThresholdComparison.aLessOrEqualB(y - this.r, heightEnd)
                        && ThresholdComparison.aMoreOrEqualB(y - this.r, heightStart)
                        && ThresholdComparison.aLessOrEqualB(this.velocity.getDy(), 0)
                        && ThresholdComparison.aMoreOrEqualB(x, widthStart)
                        && ThresholdComparison.aLessOrEqualB(x, widthEnd)));
    }
    /**
     * A method to keep the ball outside a forbidden area, its vertices included.
     * @param widthStart starting point's X coordinates.
     * @param widthEnd   Ending point's X coordinates.
     * @param heightStart starting point's Y coordinates.
     * @param heightEnd  Ending point's Y coordinates.
     */
    public void stayAwayFrom(int widthStart, int widthEnd, int heightStart, int heightEnd) {
        boolean verticalCollision = ballsCollisionWithVertical(widthStart, widthEnd, heightStart, heightEnd);
        boolean horizontalCollision = ballsCollisionWithHorizontal(widthStart, widthEnd, heightStart, heightEnd);
        if (verticalCollision && horizontalCollision) {
            this.velocity.setDx(this.velocity.getDx() * -1);
            this.velocity.setDy(this.velocity.getDy() * -1);
            return;
        }
        if (verticalCollision) {
            this.velocity.setDx(this.velocity.getDx() * -1);
            return;
        }
        if (horizontalCollision) {
            this.velocity.setDy(this.velocity.getDy() * -1);
            return;
        }
        Point[] vertices = new Point[4];
        vertices[0] = new Point(widthStart, heightStart);
        vertices[1] =  new Point(widthStart, heightEnd);
        vertices[2] = new Point(widthEnd, heightStart);
        vertices[3] =  new Point(widthEnd, heightEnd);
        for (int i = 0; i < 4; i++) {
            if (ThresholdComparison.aLessOrEqualB(this.center.distance(vertices[i]), this.r)) {
                this.velocity.setDx(this.velocity.getDx() * -1);
                this.velocity.setDy(this.velocity.getDy() * -1);
                break;
            }
        }
    }

    /**
     * A set method for game.
     * @param g game.
     */
    public void setGame(GameEnvironment g) {
        this.game = g;
    }

    /**
     *  Notify the sprite that time has passed.
     */
    public void timePassed() throws Exception {
        this.moveOneStep();
    }

    /**
     * Adding the ball to the game.
     * @param game game.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }

    /**
     * A get method for color.
     * @return the color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * A setter method for the ball's color.
     * @param newColor the new color.
     */
    public void setColor(Color newColor) {
        this.color = newColor;
    }

    /**
     * A method for removing the ball from the game.
     * @param game the game.
     */
    public void removeFromGame(Game game) throws Exception {
        game.removeSprite(this);
    }
}