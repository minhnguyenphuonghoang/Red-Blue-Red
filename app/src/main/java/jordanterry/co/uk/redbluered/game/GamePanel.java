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

import javax.inject.Inject;

import jordanterry.co.uk.redbluered.models.Square;
import jordanterry.co.uk.redbluered.models.Steps;

/**
 * Created by jordanterry on 11/10/15.
 */
public class GamePanel extends SurfaceView implements View.OnTouchListener, SurfaceHolder.Callback {

    public static final String TAG = GamePanel.class.getSimpleName();


    private GameEnvironment mGameEnvironment;

    @Inject Steps mGameSteps;
    @Inject Steps mUserSteps;

    @Inject Square mBlueSquare;
    @Inject Square mRedSquare;
    @Inject Square mInstructionSquare;

    @Inject
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
        mInstructionSquare.draw(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        float squareWidth = getWidth() * .25f;
        float squareHalf = squareWidth * .5f;

        mRedSquare.setX((getWidth() * .25f) - squareHalf);
        mRedSquare.setY((getHeight() * .5f) - squareHalf);
        mRedSquare.setHeight(squareWidth);
        mRedSquare.setWidth(squareWidth);
        mRedSquare.setBackgroundColour(GameColours.RED);

        mBlueSquare.setX((getWidth() * .75f) - squareHalf);
        mBlueSquare.setY((getHeight() * .5f) - squareHalf);
        mBlueSquare.setHeight(squareWidth);
        mBlueSquare.setWidth(squareWidth);
        mBlueSquare.setBackgroundColour(GameColours.BLUE);

        mInstructionSquare.setX((getWidth() * .5f) - squareHalf);
        mInstructionSquare.setY((getHeight() * .5f) - squareHalf);
        mInstructionSquare.setHeight(squareWidth);
        mInstructionSquare.setWidth(squareWidth);
        mInstructionSquare.setBackgroundColour(Color.BLACK);
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
                boolean isSquareTouched = false;
                if(mBlueSquare.isTouch(motionEvent.getX(), motionEvent.getY())) {
                    mUserSteps.addStep(GameColours.BLUE);
                    isSquareTouched = true;
                }

                if(mRedSquare.isTouch(motionEvent.getX(), motionEvent.getY())) {
                    mUserSteps.addStep(GameColours.RED);
                    isSquareTouched = true;
                }

                if(isSquareTouched) {
                    if(mGameSteps.compareSteps(mUserSteps)) {
                        Toast.makeText(getContext(), "Everything is fine..", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getContext(), "Everything is not fine..", Toast.LENGTH_LONG).show();
                    }
                }

                break;
        }


        return false;
    }
}
