package jordanterry.co.uk.redbluered.game.components;

import dagger.Component;
import jordanterry.co.uk.redbluered.game.GameControllerImpl;
import jordanterry.co.uk.redbluered.game.modules.GameModule;

/**
 * <p>Creates an Injected {@link GameControllerImpl}.</p>
 */
@Component(modules = {GameModule.class})
public interface GameComponent {
    // GameControllerImpl provideGameController();
}
