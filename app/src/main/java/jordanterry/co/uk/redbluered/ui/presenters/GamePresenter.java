package jordanterry.co.uk.redbluered.ui.presenters;

import android.view.SurfaceView;

/**
 * <p>The Presenter that will present the Game to a View.</p>
 */
public interface GamePresenter extends Presenter {
    /**
     * <p>Get the SurfaceView of the game.</p>
     * @return
     */
    SurfaceView getGameSurface();

    /**
     * <p>Stop the game.</p>
     */
    void startGame();

    /**
     * <p>Start the game.</p>
     */
    void stopGame();
}
