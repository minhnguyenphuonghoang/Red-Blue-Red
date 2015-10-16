package jordanterry.co.uk.redbluered.game;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

import jordanterry.co.uk.redbluered.game.models.Steps;


/**
 * The GameController class contains the Game.
 */
public class GameController implements GamePanel.OnGameInteraction {

    /**
     * The current level
     */
    private int mLevel;

    /**
     * The Context of the game
     */
    private Context mContext;

    @Inject Steps mGameSteps;
    @Inject Steps mUserSteps;

    private GamePanel mGamePanel;

    @Inject
    public GameController(Context context) {
        mContext = context;
        mLevel = 0;
        mGameSteps = new Steps();
        mUserSteps = new Steps();
        mGamePanel = new GamePanel(context, this);
    }

    public int getLevel() {
        return mLevel;
    }

    public void incrementLevel() {
        mLevel++;
    }

    public GamePanel getGamePanel() {
        return mGamePanel;
    }

    public void start() {
        mGamePanel.start();
    }

    public void stop() throws InterruptedException {
        mGamePanel.stop();
    }

    @Override
    public void newStep(int colour) {
        mGameSteps.addStep(colour);
    }

    @Override
    public void onTouch(int colour) {
        mUserSteps.addStep(colour);
        if(mGameSteps.compareSteps(mUserSteps)) {
            Toast.makeText(mContext, "Steps match", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(mContext, "Game over", Toast.LENGTH_LONG).show();
        }
    }
}
