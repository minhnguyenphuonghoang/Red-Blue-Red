package jordanterry.co.uk.redbluered.game.creators;

import jordanterry.co.uk.redbluered.game.GamePanel;

/**
 * Created by jordanterry on 13/10/15.
 */
// @Component(modules = {GameModule.class})
public interface GameCreator {
    GamePanel provideGame();
}
