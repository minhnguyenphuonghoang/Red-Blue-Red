package jordanterry.co.uk.redbluered.ui.presenters;

import android.view.SurfaceView;

/**
 * <p>The Presenter that will present the Game to a View.</p>
 */
public interface GamePresenter extends Presenter {
    SurfaceView getGameSurface();
    void startGame();
    void stopGame();
}
