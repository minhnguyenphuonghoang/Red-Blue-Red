package jordanterry.co.uk.redbluered.game;

import java.util.List;

import jordanterry.co.uk.redbluered.game.models.GameObject;

/**
 * Created by jordanterry on 20/10/15.
 */
public interface GameSurface {

    void drawState(List<GameObject> gameObjects);

}
