package jordanterry.co.uk.redbluered.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import jordanterry.co.uk.redbluered.game.GameControllerImpl;
import jordanterry.co.uk.redbluered.game.components.DaggerGameComponent;
import jordanterry.co.uk.redbluered.game.components.GameComponent;
import jordanterry.co.uk.redbluered.game.modules.GameModule;

/**
 * Created by jordanterry on 14/10/15.
 */
public class GameActivity extends AppCompatActivity {

    public static final String TAG = GameActivity.class.getSimpleName();

    private GameComponent mGameComponent;

    private GameControllerImpl mGameController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mGameComponent == null) {
            mGameComponent = DaggerGameComponent.builder().gameModule(new GameModule(this))
                    .build();
        }
        mGameController = mGameComponent.provideGameController();
        setContentView(mGameController.getGamePanel());
    }


    @Override
    protected void onPause() {
        super.onPause();
        if(mGameController != null) {
            try {
                mGameController.stop();
            } catch (InterruptedException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }
}
