package jordanterry.co.uk.redbluered.ui.presenters;

import jordanterry.co.uk.redbluered.game.GameStatusController;
import jordanterry.co.uk.redbluered.ui.views.GameView;

/**
 * Created by jordanterry on 30/10/15.
 */
public class GamePlayPresenterImpl implements GamePlayPresenter {

    private GameView mGameView;


    private GameStatusController mGameStatusController;

    private boolean isStarted = false;

    public GamePlayPresenterImpl(GameView gameView) {
        mGameStatusController = new GameStatusController();
        mGameView = gameView;
    }

    @Override
    public void clickButton(int colour) {

        mGameStatusController.addUserStep(colour);
        if(mGameStatusController.isUserCorrect()) {

            if(mGameStatusController.getGameColours().size() == mGameStatusController.getUserColours().size()) {
                addNewLevel();
            } else {
                keepPlaying();
            }

        } else {
            mGameView.onGameOver(mGameStatusController.getLevel());
        }

    }

    @Override
    public void addNewLevel() {
        mGameStatusController.resetUserClicks();
        mGameStatusController.addNewStep();
        mGameView.displaySteps(mGameStatusController.getGameColours());
    }

    @Override
    public void keepPlaying() {

    }

    @Override
    public void onResume() {
        if(!isStarted) {
            addNewLevel();
            isStarted = true;
        }
    }

    @Override
    public void onPause() {

    }

    @Override
    public void reset() {
        mGameStatusController.resetUserClicks();
        mGameStatusController.resetGameSteps();
        mGameStatusController.resetLevel();
        addNewLevel();
        isStarted = true;
    }

}
