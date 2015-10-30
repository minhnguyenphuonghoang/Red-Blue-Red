package jordanterry.co.uk.redbluered.game;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

/**
 * <p>An implementation of GameTimer with a thread that will control the change and displaying of
 * state within the game.</p>
 */
public class GameTimerImpl extends Thread implements GameTimer {

    public static final String TAG = GameTimerImpl.class.getSimpleName();

    /**
     * <p>The max number of skippable frames.</p>
     */
    private final static int MAX_FRAME_SKIPS = 5;

    /**
     * <p>The max frames per second for the game.</p>
     */
    private final static int MAX_FPS = 32;

    /**
     * <p>The length of a frame.</p>
     */
    private final static int FRAME_PERIOD = 1000 / MAX_FPS;

    /**
     * <p>A boolean indicating if the game is running.</p>
     */
    private boolean isRunning = false;

    /**
     * <p>A boolean indicating if the game has ended.</p>
     */
    private boolean isGameEnded = false;

    /**
     * <p>The {@link GameController} that will be told when to update and when to display the game by the
     * GameTimer.</p>
     */
    private GameController mGameController;

    private Handler mHandler;

    public GameTimerImpl(String name, GameController gameController) {
        super(name);
        mGameController = gameController;
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void run() {

        while(isRunning){
            if(!isGameEnded) {
                long beginTime, timeDiff;
                int sleepTime, framesSkipped;
                beginTime = System.currentTimeMillis();
                framesSkipped = 0;

                mGameController.updateState();

                mGameController.drawState();


                timeDiff = System.currentTimeMillis() - beginTime;
                sleepTime = (int)(FRAME_PERIOD - timeDiff);
                if(sleepTime > 0) {
                    try {
                        sleep(sleepTime);
                    } catch (InterruptedException e) {
                        Log.e(TAG, e.getMessage());
                    }
                }

                while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
                    mGameController.updateState();
                    sleepTime += FRAME_PERIOD;
                    framesSkipped++;
                }


            } else {
                isRunning = false;
            }
        }
    }


    @Override
    public void startTimer() {
        isRunning = true;
        isGameEnded = false;
        start();
    }

    @Override
    public void stopTimer() throws InterruptedException {
        isGameEnded = true;
        join();
    }
}
