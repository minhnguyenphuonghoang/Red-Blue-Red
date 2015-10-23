package jordanterry.co.uk.redbluered.ui.presenters;

import jordanterry.co.uk.redbluered.ui.views.GameOverView;

/**
 * Created by jordanterry on 23/10/15.
 */
public class GameOverPresenterImpl implements GameOverPresenter {

    private GameOverView mGameOverView;

    public GameOverPresenterImpl(GameOverView gameOverView) {
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
}
