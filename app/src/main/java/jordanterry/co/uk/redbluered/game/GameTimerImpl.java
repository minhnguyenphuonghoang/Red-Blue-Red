package jordanterry.co.uk.redbluered.game;

import android.util.Log;

/**
 * Created by jordanterry on 11/10/15.
 */
public class GameTimerImpl extends Thread implements GameTimer {

    public static final String TAG = GameTimerImpl.class.getSimpleName();

    private final static int MAX_FRAME_SKIPS = 5;
    private final static int MAX_FPS = 50;
    private final static int FRAME_PERIOD = 1000 / MAX_FPS;
    private boolean isRunning = false;
    private boolean isGameEnded = false;
    private GameController mGameController;


    public GameTimerImpl(GameController gameController) {
        mGameController = gameController;
    }

    @Override
    public void run() {
        long beginTime, timeDiff;
        int sleepTime, framesSkipped;
        while(isRunning){
            if(!isGameEnded) {
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
