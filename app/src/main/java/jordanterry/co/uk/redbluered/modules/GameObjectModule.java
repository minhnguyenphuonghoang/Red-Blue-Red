package jordanterry.co.uk.redbluered.modules;

import dagger.Module;
import dagger.Provides;
import jordanterry.co.uk.redbluered.game.GamePanel;
import jordanterry.co.uk.redbluered.models.Square;
import jordanterry.co.uk.redbluered.models.Steps;

/**
 * Module to provide dependencies to the GamePanel.
 */
@Module(injects = {
        GamePanel.class
})
public class GameObjectModule {

    @Provides
    public Steps provideSteps() {
        return new Steps();
    }

    @Provides
    public Square provideSquare() {
        return new Square();
    }
}
