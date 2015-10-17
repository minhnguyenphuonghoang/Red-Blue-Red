package jordanterry.co.uk.redbluered.game;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;


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
        Toast.makeText(mContext, "Game over", Toast.LENGTH_SHORT).show();
    }
}
