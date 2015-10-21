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
 * Created by jordanterry on 11/10/15.
 */
public class GamePanel extends SurfaceView implements View.OnTouchListener, SurfaceHolder.Callback, GameSurface {

    public static final String TAG = GamePanel.class.getSimpleName();

    public interface OnGameInteraction {
        void onGameOver();
        void onClick(float x, float y);
        void onReady(GameEnvironment gameEnvironment);
    }

    private OnGameInteraction mOnGameInteraction;



    private List<GameObject> mGameObjects;

    public GamePanel(Context context, OnGameInteraction onGameInteraction) {
        super(context);
        init();
        mOnGameInteraction = onGameInteraction;
    }

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

}
