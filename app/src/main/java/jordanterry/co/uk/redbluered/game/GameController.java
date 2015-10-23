package jordanterry.co.uk.redbluered.game;

/**
 * <p>The GameController defines the methods that will need to be implemented to allow another
 * object to control the game.</p>
 * <p>The GameController is used by the {@link GameTimer} to fire events off at the right time.</p>
 */
public interface GameController {
    /**
     * <p>Update the state of any objects and logic within the game.</p>
     */
    void updateState();

    /**
     * <p>Draw the state of the game.</p>
     */
    void drawState();

    /**
     * <p>Get the {@link GamePanel} from the GameController.</p>
     * TODO: Find a way to remove this, it feels like cheating.
     * @return
     */
    GamePanel getGamePanel();

    /**
     * <p>Start the game</p>
     */
    void start();

    /**
     * <p>Stop the game.</p>
     * @throws InterruptedException
     */
    void stop() throws InterruptedException;
}
