package jordanterry.co.uk.redbluered.game;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import jordanterry.co.uk.redbluered.ui.activities.MenuActivity;


/**
 * The GameController class contains the Game.
 */
public class GameController implements GamePanel.OnGameInteraction {

    public static final String TAG = GameController.class.getSimpleName();

    /**
     * The Context of the game
     */
    @Inject Context mContext;

    private GamePanel mGamePanel;

    @Inject
    public GameController(Context context) {
        mGamePanel = new GamePanel(context, this);
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
    public void onGameOver() {

        Intent intent = new Intent(mContext, MenuActivity.class);
        mContext.startActivity(intent);
    }
}
