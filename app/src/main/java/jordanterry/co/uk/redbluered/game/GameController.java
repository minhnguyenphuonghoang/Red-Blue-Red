package jordanterry.co.uk.redbluered.game;

/**
 * Created by jordanterry on 20/10/15.
 */
public interface GameController {

    void updateState();
    void drawState();

    GamePanel getGamePanel();

    void start();
    void stop() throws InterruptedException;

}
