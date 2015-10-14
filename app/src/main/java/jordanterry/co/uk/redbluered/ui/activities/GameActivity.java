package jordanterry.co.uk.redbluered.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import jordanterry.co.uk.redbluered.game.GameController;

/**
 * Created by jordanterry on 14/10/15.
 */
public class GameActivity extends AppCompatActivity {

    public static final String TAG = GameActivity.class.getSimpleName();

    private GameController mGameController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGameController = new GameController(this);
        setContentView(mGameController.getGamePanel());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mGameController != null) {
            mGameController.start();
        }
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
