package jordanterry.co.uk.redbluered.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import jordanterry.co.uk.redbluered.models.Square;

/**
 * Created by jordanterry on 11/10/15.
 */
public class GamePanel extends SurfaceView implements View.OnTouchListener, SurfaceHolder.Callback {

    public static final String TAG = GamePanel.class.getSimpleName();

    private Square mBlueSquare;
    private Square mRedSquare;
    private GameEnvironment mGameEnvironment;

    public GamePanel(Context context) {
        super(context);
        init();
    }

    public GamePanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GamePanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        getHolder().addCallback(this);
        setClickable(true);
        setOnTouchListener(this);
        mGameEnvironment = new GameEnvironment(this);
    }

    public void start() {
        mGameEnvironment.startGame();
        mGameEnvironment.start();

    }

    public void stop() throws InterruptedException {
        mGameEnvironment.stopGame();
        mGameEnvironment.join();
    }

    public void updateGame() {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        mRedSquare.draw(canvas);
        mBlueSquare.draw(canvas);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        float squareWidth = getWidth() * .2f;
        float squareHalf = squareWidth * .5f;
        float redX = (getWidth() * .25f) - squareHalf;
        float redY = (getHeight() * .5f) - squareHalf;
        float blueX = (getWidth() * .75f) - squareHalf;
        float blueY = (getHeight() * .5f) - squareHalf;
        mRedSquare = new Square(redX, redY, squareWidth, squareWidth, Color.RED);
        mBlueSquare = new Square(blueX, blueY, squareWidth, squareWidth, Color.BLUE);

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
