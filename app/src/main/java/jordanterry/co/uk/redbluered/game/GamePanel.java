package jordanterry.co.uk.redbluered.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import jordanterry.co.uk.redbluered.game.models.Square;
import jordanterry.co.uk.redbluered.game.models.Steps;


/**
 * Created by jordanterry on 11/10/15.
 */
public class GamePanel extends SurfaceView implements View.OnTouchListener, SurfaceHolder.Callback {

    public static final String TAG = GamePanel.class.getSimpleName();

    private GameEnvironment mGameEnvironment;
    private boolean isAddStep = false;

    public interface OnGameInteraction {
        void onGameOver();
    }

    private OnGameInteraction mOnGameInteraction;

    private Square mBlueSquare;
    private Square mRedSquare;
    private Square mInstructionSquare;
    private Steps mGameColours;
    private Steps mUserColours;

    private int mLevel;

    public GamePanel(Context context, OnGameInteraction onGameInteraction) {
        super(context);
        init();
        mOnGameInteraction = onGameInteraction;
    }

    private void init() {
        getHolder().addCallback(this);
        setClickable(true);
        setOnTouchListener(this);
        mGameEnvironment = new GameEnvironment(this);
        mBlueSquare = new Square();
        mRedSquare = new Square();
        mInstructionSquare = new Square();

        mGameColours = new Steps();
        mUserColours = new Steps();
        mLevel = 0;
        addStep();

    }

    public void start() {
        mGameEnvironment.startGame();
        mGameEnvironment.start();
    }

    public void stop() throws InterruptedException {
        mGameEnvironment.stopGame();
        mGameEnvironment.join();
    }

    public void showNewStep() {
        isAddStep = true;
    }

    public void update() {
        if(isAddStep && !mInstructionSquare.isVisible()) {
            mInstructionSquare.show();
            mRedSquare.hide();
            mBlueSquare.hide();
        } else if(!isAddStep && mInstructionSquare.isVisible()) {
            mInstructionSquare.hide();
            mRedSquare.show();
            mBlueSquare.show();
        }


        if(isAddStep) {
            mInstructionSquare.setBackgroundColour(mGameColours.getColour(0));
        }
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

        mRedSquare.setPosition((getWidth() * .25f) - squareHalf, (getHeight() * .5f) - squareHalf);
        mRedSquare.setEdge(squareWidth);
        mRedSquare.setBackgroundColour(GameColours.RED);

        mBlueSquare.setPosition((getWidth() * .75f) - squareHalf, (getHeight() * .5f) - squareHalf);
        mBlueSquare.setEdge(squareWidth);
        mBlueSquare.setBackgroundColour(GameColours.BLUE);

        mInstructionSquare.setPosition((getWidth() * .5f) - squareHalf, (getHeight() * .5f) - squareHalf);
        mInstructionSquare.setEdge(squareWidth);
        mInstructionSquare.setBackgroundColour(Color.BLACK);
        mInstructionSquare.hide();

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
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:

                if(mBlueSquare.isTouch(motionEvent.getX(), motionEvent.getY())) {
                    checkUserClick(GameColours.BLUE);
                }

                if(mRedSquare.isTouch(motionEvent.getX(), motionEvent.getY())) {
                    checkUserClick(GameColours.RED);
                }

                break;
        }


        return false;
    }


    private void checkUserClick(int colour) {
        mUserColours.addStep(colour);
        if (mGameColours.compareSteps(mUserColours)) {
            addStep();
        } else {
            mOnGameInteraction.onGameOver();
        }
    }

    private void addStep() {
        mLevel++;
        addNewColour();
        showNewStep();
    }

    private void addNewColour() {
        mGameColours.addStep(GameColours.randomColour());
    }
}
