package jordanterry.co.uk.redbluered.game.modules;

import android.content.Context;

import jordanterry.co.uk.redbluered.models.Square;

/**
 * Module to provide dependencies to the GamePanel.
 */
// @Module
public class GameModule {

    private Context mContext;


    public GameModule(Context context) {
        mContext = context;
    }

   // @Provides
    public Square provideSquare() {
        return new Square();
    }

    // @Provides
    public Context provideContext() {
        return mContext;
    }

}
