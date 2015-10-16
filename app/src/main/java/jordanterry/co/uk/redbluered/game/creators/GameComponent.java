package jordanterry.co.uk.redbluered.game.creators;

import dagger.Component;
import jordanterry.co.uk.redbluered.game.GameController;
import jordanterry.co.uk.redbluered.game.modules.GameModule;

/**
 * Created by jordanterry on 13/10/15.
 */
@Component(modules = {GameModule.class})
public interface GameComponent {
    GameController provideGameController();
}
