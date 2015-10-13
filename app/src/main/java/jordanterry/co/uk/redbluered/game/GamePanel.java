package jordanterry.co.uk.redbluered.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import jordanterry.co.uk.redbluered.GameColours;
import jordanterry.co.uk.redbluered.models.Square;
import jordanterry.co.uk.redbluered.models.Steps;

/**
 * Created by jordanterry on 11/10/15.
 */
public class GamePanel extends SurfaceView implements View.OnTouchListener, SurfaceHolder.Callback {

    public static final String TAG = GamePanel.class.getSimpleName();

    private Square mBlueSquare;
    private Square mRedSquare;
    private Square mInstructionSquare;
    private GameEnvironment mGameEnvironment;

    private Steps mGameSteps;
    private Steps mUserSteps;

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
        mGameSteps = new Steps();
        mUserSteps = new Steps();
        mGameSteps.addStep(GameColours.RED);
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
        mInstructionSquare.draw(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        float squareWidth = getWidth() * .25f;
        float squareHalf = squareWidth * .5f;
        float redX = (getWidth() * .25f) - squareHalf;
        float redY = (getHeight() * .5f) - squareHalf;
        float blueX = (getWidth() * .75f) - squareHalf;
        float blueY = (getHeight() * .5f) - squareHalf;
        float middleX = (getWidth() * .5f) - squareHalf;
        float middleY = (getHeight() * .5f) - squareHalf;
        mRedSquare = new Square(redX, redY, squareWidth, squareWidth, Color.RED);
        mBlueSquare = new Square(blueX, blueY, squareWidth, squareWidth, Color.BLUE);
        mInstructionSquare = new Square(middleX, middleY, squareWidth, squareWidth, Color.BLACK);
        mInstructionSquare.hide();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:

                if(mBlueSquare.isTouch(motionEvent.getX(), motionEvent.getY())) {
                    mUserSteps.addStep(GameColours.BLUE);
                }

                if(mRedSquare.isTouch(motionEvent.getX(), motionEvent.getY())) {
                    mUserSteps.addStep(GameColours.RED);
                }

                if(mGameSteps.compareSteps(mUserSteps)) {
                    Toast.makeText(getContext(), "Everything is fine..", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "Everything is not fine..", Toast.LENGTH_LONG).show();
                }

                break;
        }


        return false;
    }
}
