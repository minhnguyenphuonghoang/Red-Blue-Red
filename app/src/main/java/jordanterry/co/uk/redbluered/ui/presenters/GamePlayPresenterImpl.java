package jordanterry.co.uk.redbluered.ui.presenters;

import jordanterry.co.uk.redbluered.ui.views.GameView;

/**
 * Created by jordanterry on 30/10/15.
 */
public class GamePlayPresenterImpl implements GamePlayPresenter {

    private GameView mGameView;

    public GamePlayPresenterImpl(GameView gameView) {
        mGameView = gameView;
    }

    @Override
    public void clickButton(int colour) {

    }
}
