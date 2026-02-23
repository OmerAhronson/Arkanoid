// 322824806 Omer Ahronson
package Core;
import Geometry.Ball;
import Geometry.Point;
import Physics.Collidable;
import Sprites.SpriteCollection;
import Sprites.Counter;
import Sprites.Sprite;
import Sprites.Paddle;
import Sprites.BorderBlock;
import Sprites.Block;
import Sprites.BallRemover;
import Sprites.BlockRemover;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;
/**
 * The following class will hold the sprites and the collidables, and will be in charge of the animation.
 */
public class Game {
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private GUI gui;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private Counter score;
    /**
     * A constructor method.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blocksCounter = new Counter(57);
        this.ballsCounter = new Counter(3);
        this.score = new Counter(0);
    }
    /**
     * Adds a new collidable to the game environment.
     * @param c the collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Removes a collidable object from the game.
     * @param c The collidable.
     */
    public void removeCollidable(Collidable c) throws Exception {
        this.environment.removeCollidable(c);
    }

    /**
     * Adds a new sprite to the sprites' collection.
     * @param s The sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Removes a sprite from the game.
     * @param s the sprite,
     * @throws Exception if a null parameter is passed.
     */
    public void removeSprite(Sprite s) throws Exception {
        if (s == null) {
            return;
        }
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Shapes.Geometry.Ball (and Sprites.Paddle) and add them to the game.
     */
    public void initialize() {
        double blockWidth = 50;
        double blockHeight = 20;
        this.gui = new GUI("Arkanoid", 800, 600);
        ScoreIndicator scoreIndicator = new ScoreIndicator(new Point(0, 0),
                800, 20, Color.white, score);
        scoreIndicator.addToGame(this);
        Ball ball = new Ball(new Point(110, 100), 5, Color.WHITE, this.environment);
        Ball ball2 = new Ball(new Point(110, 100), 5, Color.WHITE, this.environment);
        Ball ball3 = new Ball(new Point(110, 100), 5, Color.WHITE, this.environment);
        ball.setVelocity(3, 4);
        ball2.setVelocity(2, 2);
        ball.addToGame(this);
        ball2.addToGame(this);
        ball3.setVelocity(7, 3);
        ball3.addToGame(this);
        Paddle paddle = new Paddle(new Point(350, 550), 100, 20, gui.getKeyboardSensor());
        paddle.addToGame(this);
        BorderBlock left = new BorderBlock(new Point(0, 20), 30, 600, Color.GRAY);
        BorderBlock right = new BorderBlock(new Point(770, 20), 30, 600, Color.GRAY);
        BorderBlock top = new BorderBlock(new Point(0, 20), 800, 30, Color.GRAY);
        Block deathBlock = new Block(new Point(0, 595), 800, 5, Color.BLACK);
        left.addToGame(this);
        right.addToGame(this);
        top.addToGame(this);
        BallRemover ballRemover = new BallRemover(this, ballsCounter);
        deathBlock.addHitListener(ballRemover);
        deathBlock.addToGame(this);
        BlockRemover blockRemover = new BlockRemover(this, this.blocksCounter);
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(this.score);

        for (int i = 0; i < 12; i++) {
            Block block = new Block(new Point(770 - ((i + 1) * blockWidth), 100),
                    blockWidth, blockHeight, Color.GRAY);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTracker);
            block.addToGame(this);
        }
        for (int i = 0; i < 11; i++) {
            Block block = new Block(new Point(770 - ((i + 1) * blockWidth), 100 + blockHeight),
                    blockWidth, blockHeight, Color.RED);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTracker);
            block.addToGame(this);
        }
        for (int i = 0; i < 10; i++) {
            Block block = new Block(new Point(770 - ((i + 1) * blockWidth), 100 + 2 * blockHeight),
                    blockWidth, blockHeight, Color.YELLOW);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTracker);
            block.addToGame(this);
        }
        for (int i = 0; i < 9; i++) {
            Block block = new Block(new Point(770 - ((i + 1) * blockWidth), 100 + 3 * blockHeight),
                    blockWidth, blockHeight, Color.MAGENTA);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTracker);
            block.addToGame(this);
        }
        for (int i = 0; i < 8; i++) {
            Block block = new Block(new Point(770 - ((i + 1) * blockWidth), 100 + 4 * blockHeight),
                    blockWidth, blockHeight, Color.PINK);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTracker);
            block.addToGame(this);
        }
        for (int i = 0; i < 7; i++) {
            Block block = new Block(new Point(770 - ((i + 1) * blockWidth), 100 + 5 * blockHeight),
                    blockWidth, blockHeight, Color.GREEN);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTracker);
            block.addToGame(this);
        }

    }

    /**
     * The method which will actually start and run the game.
     */
    public void run() throws Exception {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.BLUE);
            d.fillRectangle(0, 0, 800, 600);
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            if (this.blocksCounter.getValue() <= 0) {
                score.increase(100);
                gui.close();
            }
            if (ballsCounter.getValue() <= 0) {
                gui.close();
            }
        }
    }
}
