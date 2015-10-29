package jordanterry.co.uk.redbluered.ui.presenters;

import jordanterry.co.uk.redbluered.ui.views.GameView;

/**
 * Created by jordanterry on 23/10/15.
 */
public class GameJourneyPresenterImpl implements GameJourneyPresenter {

    private GameView mGameView;

    public GameJourneyPresenterImpl(GameView gameView) {
        mGameView = gameView;
    }

    @Override
    public void gameOver(int level) {
        mGameView.onGameOver(level);
    }
}
