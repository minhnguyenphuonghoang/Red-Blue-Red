package jordanterry.co.uk.redbluered.game;

import android.content.Context;
import android.widget.Toast;

import jordanterry.co.uk.redbluered.models.Steps;

/**
 * Created by jordanterry on 14/10/15.
 */
public class GameController implements GamePanel.OnGameInteraction {

    private int mLevel;

    private Context mContext;

    private Steps mGameSteps;
    private Steps mUserSteps;

    private GamePanel mGamePanel;

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
