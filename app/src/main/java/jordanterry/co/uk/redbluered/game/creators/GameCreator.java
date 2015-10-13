package jordanterry.co.uk.redbluered.game.creators;

import dagger.Component;
import jordanterry.co.uk.redbluered.game.GamePanel;
import jordanterry.co.uk.redbluered.game.modules.GameObjectModule;

/**
 * Created by jordanterry on 13/10/15.
 */
@Component(modules = {GameObjectModule.class})
public interface GameCreator {
    GamePanel provideGame();
}
