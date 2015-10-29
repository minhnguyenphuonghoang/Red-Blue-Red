package jordanterry.co.uk.redbluered.ui.presenters;

import android.app.Activity;

import jordanterry.co.uk.redbluered.ui.activities.GameActivity;
import jordanterry.co.uk.redbluered.ui.views.GameOverView;

/**
 * <p>An implementation of the {@link GameOverPresenter} that shall be used to hold the GameOver
 * view.</p>
 */
public class GameOverPresenterImpl implements GameOverPresenter {

    private GameOverView mGameOverView;
    private Activity mActivity;

    public GameOverPresenterImpl(Activity activity, GameOverView gameOverView) {
        mActivity = activity;
        mGameOverView = gameOverView;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void restartGame() {
        mActivity.setResult(GameActivity.RESULT_GAME_RESTART);
        mActivity.finish();
    }

    @Override
    public void openLeaderboards() {
        mActivity.setResult(GameActivity.RESULT_LEADERBOARDS);
        mActivity.finish();
    }

    @Override
    public void mainMenu() {
        mActivity.setResult(GameActivity.RESULT_MAIN_MENU);
        mActivity.finish();
    }
}
