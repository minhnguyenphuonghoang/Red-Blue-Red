package jordanterry.co.uk.redbluered.ui.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import jordanterry.co.uk.redbluered.game.GameControllerImpl;
import jordanterry.co.uk.redbluered.ui.fragments.GameOverFragment;
import jordanterry.co.uk.redbluered.ui.presenters.GameJourneyPresenter;
import jordanterry.co.uk.redbluered.ui.presenters.GameJourneyPresenterImpl;
import jordanterry.co.uk.redbluered.ui.presenters.GamePresenter;
import jordanterry.co.uk.redbluered.ui.presenters.GamePresenterImpl;
import jordanterry.co.uk.redbluered.ui.views.GameView;

/**
 * Created by jordanterry on 14/10/15.
 */
public class GameActivity extends AppCompatActivity implements GameView {

    public static final String TAG = GameActivity.class.getSimpleName();

    public static int REQUEST_CODE = 1;

    public static int RESULT_MAIN_MENU = 2;

    public static int RESULT_GAME_RESTART = 3;

    public static int RESULT_LEADERBOARDS = 4;


    private GameControllerImpl mGameController;

    private GamePresenter mGamePresenter;

    private GameJourneyPresenter mGameJourneyPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGameJourneyPresenter = new GameJourneyPresenterImpl(this);
        mGameController = new GameControllerImpl(this, mGameJourneyPresenter);
        mGamePresenter = new GamePresenterImpl(this, mGameController);

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


    @Override
    public void onGameOver(int level) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag(GameOverFragment.TAG);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        GameOverFragment newFragment = GameOverFragment.newInstance(level);
        newFragment.show(ft, GameOverFragment.TAG);
    }
}
