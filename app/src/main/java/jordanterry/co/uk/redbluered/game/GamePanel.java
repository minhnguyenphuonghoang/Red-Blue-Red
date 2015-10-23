package jordanterry.co.uk.redbluered.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.List;

import jordanterry.co.uk.redbluered.game.models.GameEnvironment;
import jordanterry.co.uk.redbluered.game.models.GameObject;

/**
 * <p>The GamePanel controls the visual elements of the game by implementing {@link GameSurface}
 * alongside the {@link android.view.View.OnTouchListener} and the
 * {@link android.view.SurfaceHolder.Callback}.</p>
 *
 * <p>GamePanel extends {@link SurfaceView} which allows it to draw the state of the game to
 * the screen.</p>
 */
public class GamePanel extends SurfaceView implements View.OnTouchListener, SurfaceHolder.Callback,
        GameSurface {

    public static final String TAG = GamePanel.class.getSimpleName();

    /**
     * The {@link jordanterry.co.uk.redbluered.game.GamePanel.OnGameInteraction} used to respond to
     * interactions within the game.
     */
    private OnGameInteraction mOnGameInteraction;

    /**
     * The list of {@link GameObject} currently being drawn to the {@link SurfaceView}.
     */
    private List<GameObject> mGameObjects;

    /**
     * <p>Construvtor used to provide the {@link Context} and the
     * {@link jordanterry.co.uk.redbluered.game.GamePanel.OnGameInteraction} to the {@link SurfaceView}.</p>
     * @param context
     * @param onGameInteraction
     */
    public GamePanel(Context context, OnGameInteraction onGameInteraction) {
        super(context);
        init();
        mOnGameInteraction = onGameInteraction;
    }

    /**
     * <p>Brings code out of the constructor.</p>
     * <p>Was originally used when there were 3 different constructors being used.</p>
     */
    private void init() {
        getHolder().addCallback(this);
        setClickable(true);
        setOnTouchListener(this);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        if(mGameObjects != null) {
            for (GameObject gameObject : mGameObjects) {
                gameObject.draw(canvas);
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mOnGameInteraction.onReady(new GameEnvironment(getWidth(), getHeight()));
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        // Empty
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        // Empty
    }



    @Override
    public void drawState(List<GameObject> gameObjects) {
        mGameObjects = gameObjects;
        Canvas canvas = null;
        canvas = getHolder().lockCanvas(null);
        synchronized (getHolder()){
            if(canvas != null) {
                draw(canvas);
            }
        }
        if (canvas != null) {
            getHolder().unlockCanvasAndPost(canvas);
        }
    }



    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mOnGameInteraction.onClick(motionEvent.getX(), motionEvent.getY());
                break;
        }
        return false;
    }


    /**
     * <p>An implementing class can be used to control the response of the game to different
     * types of reaction within the game.</p>
     */
    public interface OnGameInteraction {
        /**
         * <p>Called when the Game is over.</p>
         */
        void onGameOver();

        /**
         * <p>Called when a user touches the games surface.</p>
         * @param x
         * @param y
         */
        void onClick(float x, float y);

        /**
         * Called when the SurfaceView is ready to be used.
         * @param gameEnvironment
         */
        void onReady(GameEnvironment gameEnvironment);
    }


}
