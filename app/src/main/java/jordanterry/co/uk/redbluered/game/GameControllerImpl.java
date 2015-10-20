package jordanterry.co.uk.redbluered.game;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import javax.inject.Inject;

import jordanterry.co.uk.redbluered.game.models.GameEnvironment;
import jordanterry.co.uk.redbluered.game.models.GameObject;
import jordanterry.co.uk.redbluered.game.models.Square;
import jordanterry.co.uk.redbluered.game.models.Steps;
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


    private Steps mGameColours = new Steps();
    private Steps mUserColours = new Steps();

    private boolean isAddStep = false;

    private int mLevel;

    private long mChangeStepTime = 0;
    private long mDisplayStep = 0;
    private static final long STEP_DISPLAY_TIME = 1000;


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
        }

        if(mRedSquare.isTouch(x, y)) {
            checkUserClick(GameColours.RED);
        }
    }

    @Override
    public void onReady(GameEnvironment gameEnvironment) {

        mLevel = 0;


        float squareWidth = gameEnvironment.getWidth() * .25f;
        float squareHalf = squareWidth * .5f;

        mRedSquare = new Square((gameEnvironment.getWidth() * .25f) - squareHalf, (gameEnvironment.getHeight() * .5f) - squareHalf, squareWidth, GameColours.RED);

        mBlueSquare = new Square((gameEnvironment.getWidth() * .75f) - squareHalf, (gameEnvironment.getHeight() * .5f) - squareHalf, squareWidth, GameColours.BLUE);

        mInstructionSquare = new Square((gameEnvironment.getWidth() * .5f) - squareHalf, (gameEnvironment.getHeight() * .5f) - squareHalf, squareWidth, Color.BLACK);
        mInstructionSquare.setVisibility(false);

        addStep();

        start();
    }


    @Override
    public void updateState() {



        if(isAddStep && !mInstructionSquare.isVisible()) {
            mInstructionSquare.setVisibility(true);
            mRedSquare.setVisibility(false);
            mBlueSquare.setVisibility(false);
        } else if(!isAddStep && mInstructionSquare.isVisible()) {
            mInstructionSquare.setVisibility(false);
            mRedSquare.setVisibility(true);
            mBlueSquare.setVisibility(true);
        }


        if(isAddStep) {

            for (int i = 0; i < mGameColours.getSteps().size(); i++) {

                if(i == mDisplayStep && System.currentTimeMillis() < mChangeStepTime) {
                    mInstructionSquare.setBackground(mGameColours.getColour(i));
                } else if(i == mDisplayStep && System.currentTimeMillis() > mChangeStepTime) {
                    mChangeStepTime = System.currentTimeMillis() + STEP_DISPLAY_TIME;
                    mDisplayStep++;
                } else if(i == mDisplayStep - 1 && i == mGameColours.getSteps().size() - 1 && mChangeStepTime < System.currentTimeMillis()) {
                    isAddStep = false;
                }
            }

        }


    }

    @Override
    public void drawState() {
        GameObject[] gameObjects = new GameObject[3];
        gameObjects[0] = mRedSquare;
        gameObjects[1] = mBlueSquare;
        gameObjects[2] = mInstructionSquare;
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
