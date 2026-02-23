package Sprites;
import Core.Game;
import Geometry.Ball;
/**
 * A class for Ball remober.
 */
public class BallRemover implements HitListener {
    private final Game game;
    private Counter availabeBalls;
    /**
     * Constructor method.
     * @param game The game.
     * @param availabeBalls The number of blocks which are still in the game.
     */
    public BallRemover(Game game, Counter availabeBalls) {
        this.game = game;
        this.availabeBalls = availabeBalls;


    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) throws Exception {
        hitter.removeFromGame(game);
        this.availabeBalls.decrease(1);
    }
}

