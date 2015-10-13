package jordanterry.co.uk.redbluered.game.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import jordanterry.co.uk.redbluered.models.Square;
import jordanterry.co.uk.redbluered.models.Steps;

/**
 * Module to provide dependencies to the GamePanel.
 */
@Module
public class GameObjectModule {

    private Context mContext;

    public GameObjectModule(Context context) {
        mContext = context;
    }

    @Provides
    public Steps provideSteps() {
        return new Steps();
    }

    @Provides
    public Square provideSquare() {
        return new Square();
    }

    @Provides
    public Context provideContext() {
        return mContext;
    }

}
