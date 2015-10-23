package jordanterry.co.uk.redbluered.game;

import java.util.List;

import jordanterry.co.uk.redbluered.game.models.GameObject;

/**
 * <p>The GameSurface interface defines an object that can be used to draw the current state of the
 * game.</p>
 */
public interface GameSurface {

    /**
     * <p>When provided with a {@link List} of GameObjects draw them to the screen.</p>
     * @param gameObjects
     */
    void drawState(List<GameObject> gameObjects);

}
