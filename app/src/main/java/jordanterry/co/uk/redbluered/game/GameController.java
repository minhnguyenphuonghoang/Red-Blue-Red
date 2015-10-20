package jordanterry.co.uk.redbluered.game;

import jordanterry.co.uk.redbluered.game.models.GameObject;

/**
 * Created by jordanterry on 20/10/15.
 */
public interface GameController {

    void updateState();
    void drawState();

    void start();
    void stop() throws InterruptedException;

}
