package jordanterry.co.uk.redbluered.game.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import jordanterry.co.uk.redbluered.game.models.Steps;

/**
 * Module to provide dependencies to the GamePanel.
 */
@Module
public class GameModule {

    private Context mContext;


    public GameModule(Context context) {
        mContext = context;
    }

    @Provides
    public Steps provideSteps() {
        return new Steps();
    }

    @Provides
    public Context provideContext() {
        return mContext;
    }


}
