package jordanterry.co.uk.redbluered.ui.presenters;

import android.content.Context;
import android.util.Log;
import android.view.SurfaceView;

import jordanterry.co.uk.redbluered.game.GameController;
import jordanterry.co.uk.redbluered.game.GameControllerImpl;
import jordanterry.co.uk.redbluered.ui.views.GameView;

/**
 * <p>An implementation of {@link GamePresenter} that shall be used to present the game to either
 * the View that contains the game.</p>
 */
public class GamePresenterImpl implements GamePresenter {

    /**
     *
     */
    public static final String TAG = GamePresenterImpl.class.getSimpleName();

    /**
     * <p>The View that will be displaying the game.</p>
     */
    private GameView mGameView;

    /**
     * <p>The GameController that contains the logic to the game.</p>
     */
    private GameController mGameController;

    /**
     * <p>Constructor providing a {@link Context} and the {@link GameView}.</p>
     * @param context
     * @param gameView
     */
    public GamePresenterImpl(Context context, GameView gameView) {
        mGameController = new GameControllerImpl(context);
        mGameView = gameView;
    }

    @Override
    public SurfaceView getGameSurface() {
        return mGameController.getGamePanel();
    }

    @Override
    public void startGame() {
        mGameController.start();
    }

    @Override
    public void stopGame() {
        try {
            mGameController.stop();
        } catch (InterruptedException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {
        stopGame();
    }
}
