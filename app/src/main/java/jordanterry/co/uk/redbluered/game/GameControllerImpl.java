package jordanterry.co.uk.redbluered.game;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import java.util.ArrayList;

import javax.inject.Inject;

import jordanterry.co.uk.redbluered.game.models.GameEnvironment;
import jordanterry.co.uk.redbluered.game.models.GameObject;
import jordanterry.co.uk.redbluered.game.models.Square;
import jordanterry.co.uk.redbluered.game.models.Steps;
import jordanterry.co.uk.redbluered.game.models.Text;
import jordanterry.co.uk.redbluered.ui.activities.MenuActivity;


/**
 * The GameController class contains the Game.
 */
public class GameControllerImpl implements GamePanel.OnGameInteraction, GameController {

    public static final String TAG = GameControllerImpl.class.getSimpleName();

    /**
     * The Context of the game
     */
    @Inject Context mContext;




    private GameObject mBlueSquare;
    private GameObject mRedSquare;
    private GameObject mInstructionSquare;
    private GameObject mAnimateSquare;


    private Steps mGameColours = new Steps();
    private Steps mUserColours = new Steps();

    private boolean isAddStep = false;
    private boolean isAnimateOldStep = false;
    private float mOldStepTransition = 10;

    private float mWidth;
    private float mHeight;

    private int mLevel;

    private long mChangeStepTime = 0;
    private long mDisplayStep = 0;
    private static final long STEP_DISPLAY_TIME = 1000;

    private Text mLevelText;

    private GamePanel mGameSurface;

    private GameTimer mGameTimer;

    @Inject
    public GameControllerImpl(Context context) {
        mGameSurface = new GamePanel(context, this);
        mGameTimer = new GameTimerImpl(this);
    }

    public GamePanel getGamePanel() {
        return mGameSurface;
    }

    @Override
    public void start() {
        mGameTimer.startTimer();
    }

    @Override
    public void stop() throws InterruptedException {
        mGameTimer.stopTimer();
    }


    @Override
    public void onGameOver() {
        Intent intent = new Intent(mContext, MenuActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public void onClick(float x, float y) {
        if(mBlueSquare.isTouch(x, y)) {
            checkUserClick(GameColours.BLUE);
        } else if(mRedSquare.isTouch(x, y)) {
            checkUserClick(GameColours.RED);
        }
    }

    @Override
    public void onReady(GameEnvironment gameEnvironment) {

        mLevel = 0;

        mWidth = gameEnvironment.getWidth();
        mHeight = gameEnvironment.getHeight();

        float squareWidth = mWidth * .25f;
        float squareHalf = squareWidth * .5f;

        mRedSquare = new Square((mWidth * .25f) - squareHalf, (mHeight * .5f) - squareHalf, squareWidth, GameColours.RED);

        mBlueSquare = new Square((mWidth * .75f) - squareHalf, (mHeight * .5f) - squareHalf, squareWidth, GameColours.BLUE);

        mInstructionSquare = new Square((mWidth * .5f) - squareHalf, (mHeight * .5f) - squareHalf, squareWidth, Color.BLACK);
        mInstructionSquare.setVisibility(false);

        mAnimateSquare = new Square((mWidth * .5f) - squareHalf, (mHeight * .5f) - squareHalf, squareWidth, Color.BLACK);
        mAnimateSquare.setVisibility(false);

        float textWidth = Text.measureText(String.valueOf(mLevel));

        mLevelText = new Text((mWidth * .5f) - (textWidth * .5f), mHeight * .15f, String.valueOf(mLevel), GameColours.RED);




        mOldStepTransition = mWidth * .01f;

        addStep();

        start();
    }


    @Override
    public void updateState() {

        float textWidth = Text.measureText(String.valueOf(mLevel));

        mLevelText.setText(String.valueOf(mLevel));
        mLevelText.setX((mWidth * .5f) - (textWidth * .5f));

        if(isAddStep && !mInstructionSquare.isVisible()) {
            mInstructionSquare.setVisibility(true);
            mAnimateSquare.setVisibility(false);
            mRedSquare.setVisibility(false);
            mBlueSquare.setVisibility(false);
        } else if(!isAddStep && mInstructionSquare.isVisible()) {
            mInstructionSquare.setVisibility(false);
            mAnimateSquare.setVisibility(false);
            mRedSquare.setVisibility(true);
            mBlueSquare.setVisibility(true);
        }


        if(isAddStep) {


            for (int i = 0; i < mGameColours.getSteps().size(); i++) {

                if(i == mDisplayStep && System.currentTimeMillis() < mChangeStepTime) {
                    mInstructionSquare.setBackground(mGameColours.getColour(i));
                    if(i != 0) {
                        mAnimateSquare.setBackground(mGameColours.getColour(i - 1));
                        isAnimateOldStep = true;
                    }
                } else if(i == mDisplayStep && System.currentTimeMillis() > mChangeStepTime) {
                    isAnimateOldStep = false;
                    mAnimateSquare.setVisibility(false);
                    mAnimateSquare.setX((mWidth * .5f) - ((mWidth * .25f) * .5f));
                    mChangeStepTime = System.currentTimeMillis() + STEP_DISPLAY_TIME;
                    mDisplayStep++;
                } else if(i == mDisplayStep - 1 && i == mGameColours.getSteps().size() - 1 && mChangeStepTime < System.currentTimeMillis()) {
                    isAddStep = false;
                }
            }

        }

        if(isAnimateOldStep) {
            mAnimateSquare.setVisibility(true);
            mAnimateSquare.setX(mAnimateSquare.getX() - mOldStepTransition);
        }


    }

    @Override
    public void drawState() {
        ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

        gameObjects.add(mRedSquare);
        gameObjects.add(mBlueSquare);
        gameObjects.add(mInstructionSquare);
        gameObjects.add(mAnimateSquare);
        gameObjects.add(mLevelText);
        mGameSurface.drawState(gameObjects);
    }



    private void checkUserClick(int colour) {
        mUserColours.addStep(colour);
        if (mGameColours.compareSteps(mUserColours)) {
            if(mGameColours.getSteps().size() == mUserColours.getSteps().size()) {
                addStep();
            }
        } else {
            onGameOver();
        }
    }

    private void addStep() {
        mLevel++;
        mUserColours = new Steps();
        addNewColour();
        displaySteps();
    }

    private void displaySteps() {
        isAddStep = true;
        mChangeStepTime = System.currentTimeMillis() + STEP_DISPLAY_TIME;
        mDisplayStep = 0;
    }

    private void addNewColour() {
        mGameColours.addStep(GameColours.randomColour());
    }


}
