package jordanterry.co.uk.redbluered.game.components;

import dagger.Component;
import jordanterry.co.uk.redbluered.game.GameControllerImpl;
import jordanterry.co.uk.redbluered.game.modules.GameModule;

/**
 * Created by jordanterry on 13/10/15.
 */
@Component(modules = {GameModule.class})
public interface GameComponent {
    GameControllerImpl provideGameController();
}
