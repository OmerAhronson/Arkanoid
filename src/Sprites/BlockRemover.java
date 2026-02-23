// 322824806 Omer Ahronson
package Sprites;
import Core.Game;
import Geometry.Ball;
/**
 * A class for Block remover.
 */
public class BlockRemover implements HitListener {
    private final Game game;
    private Counter remainingBlocks;

    /**
     * Constructor method.
     * @param game The game.
     * @param remainingBlocks The number of blocks which are still in the game.
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;

    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) throws Exception {
            beingHit.removeFromGame(game);
            this.remainingBlocks.decrease(1);
    }
}
