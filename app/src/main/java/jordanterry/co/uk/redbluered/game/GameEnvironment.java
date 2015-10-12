package jordanterry.co.uk.redbluered.game;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by jordanterry on 11/10/15.
 */
public class GameEnvironment extends Thread {

    private final static int MAX_FRAME_SKIPS = 5;
    private final static int MAX_FPS = 50;
    private final static int FRAME_PERIOD = 1000 / MAX_FPS;
    private GamePanel mGamePanel;
    private SurfaceHolder mSurfaceHolder;
    private boolean isRunning = false;
    private boolean isGameEnded = false;

    public GameEnvironment(GamePanel gamePanel) {
        mGamePanel = gamePanel;
        mSurfaceHolder = gamePanel.getHolder();
    }


    public void startGame() {
        isRunning = true;
        isGameEnded = false;
    }

    public void stopGame() {
        isRunning = false;
        isGameEnded = true;
    }

    @Override
    public void run(){
        Canvas c;
        long beginTime, timeDiff;
        int sleepTime, framesSkipped;
        while(isRunning){
            if(!isGameEnded) {
                beginTime = System.currentTimeMillis();
                c = null;
                framesSkipped = 0;
                mGamePanel.updateGame();
                c = mSurfaceHolder.lockCanvas(null);
                synchronized (mSurfaceHolder){
                    if(c != null) {
                        mGamePanel.draw(c);
                    }
                }
                timeDiff = System.currentTimeMillis() - beginTime;
                sleepTime = (int)(FRAME_PERIOD - timeDiff);
                if(sleepTime > 0) {
                    try {
                        sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
                    mGamePanel.updateGame();
                    sleepTime += FRAME_PERIOD;
                    framesSkipped++;
                }
                if (c != null) {
                    mSurfaceHolder.unlockCanvasAndPost(c);
                }
            } else {
                return;
            }
        }
    }


}
