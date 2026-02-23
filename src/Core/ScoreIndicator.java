package Core;
import Geometry.Point;
import Sprites.BorderBlock;
import Sprites.Counter;
import Sprites.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * A class for the score indicator.
 */
public class ScoreIndicator extends BorderBlock implements Sprite {
    private Counter scoreCounter;

    /**
     * Constructor method.
     *
     * @param upperLeft upper left point.
     * @param width     width of the block.
     * @param height    height of the block.
     * @param color     color of the block.
     * @param score current score.
     */
    public ScoreIndicator(Point upperLeft, double width, double height, Color color, Counter score) {
        super(upperLeft, width, height, color);
        this.scoreCounter = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
        d.setColor(Color.BLACK);
        int x = (int) this.getRectangle().getWidth() / 2;
        //int y = (int) this.getRectangle().getHeight() / 2;
        int y = (int) this.getRectangle().getHeight() - 4;
        String string = "Score: " + this.scoreCounter.getValue();
        d.drawText(x, y, string, 16);
    }
}
