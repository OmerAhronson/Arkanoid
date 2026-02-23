// 322824806 Omer Ahronson
package Core;
import Geometry.Ball;
import Sprites.Block;
import Sprites.Counter;
import Sprites.HitListener;

/**
 * A class for the score tracker.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    /**
     * Constructor method.
     * @param scoreCounter initializing the initial score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) throws Exception {
        this.currentScore.increase(5);
    }
}