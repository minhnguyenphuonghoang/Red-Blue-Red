package jordanterry.co.uk.redbluered.game;

import android.content.Context;

import java.util.ArrayList;

import jordanterry.co.uk.redbluered.R;
import jordanterry.co.uk.redbluered.game.models.Circle;
import jordanterry.co.uk.redbluered.game.models.GameEnvironment;
import jordanterry.co.uk.redbluered.game.models.GameObject;
import jordanterry.co.uk.redbluered.game.models.Rectangle;
import jordanterry.co.uk.redbluered.game.models.Square;
import jordanterry.co.uk.redbluered.game.models.Square.OnTouchListener;
import jordanterry.co.uk.redbluered.game.models.Steps;
import jordanterry.co.uk.redbluered.game.models.Text;
import jordanterry.co.uk.redbluered.ui.presenters.GameJourneyPresenter;


/**
 * <p>An implementation of the {@link GameController} interface.</p>
 */
public class GameControllerImpl implements GamePanel.OnGameInteraction, GameController, OnTouchListener {

    public static final String TAG = GameControllerImpl.class.getSimpleName();

    /**
     * <p>The Context of the game.</p>
     */
    private Context mContext;


    /**
     * <p>The background of the game. Will be used to draw the background colour. Rather than
     * drawing onto the Canvas.</p>
     */
    private GameObject mBackgroundRectangle;


    /**
     * <p>The Blue coloured square.</p>
     */
    private GameObject mBlueSquare;

    /**
     * <p>The Red coloured Square.</p>
     */
    private GameObject mRedSquare;

    /**
     * <p>The {@link Text} object that will be used to display the level.</p>
     */
    private Text mLevelText;

    /**
     * <p>The coloured steps that the user must follow.</p>
     */
    private Steps mGameColours = new Steps();

    /**
     * <p>The steps that the user has selected.</p>
     */
    private Steps mUserColours = new Steps();

    /**
     * <p>Boolean indicating if a new step is being added.</p>
     */
    private boolean isAddStep = false;

    /**
     * <p>The GameEnvironment that the user is in.</p>
     */
    private GameEnvironment mGameEnvironment;

    /**
     * <p>The current level of the game.</p>
     */
    private int mLevel;

    /**
     * <p>The time that the last step was added to the game.</p>
     */
    private long mChangeStepTime = 0;

    /**
     * <p>The step of the game being displayed.</p>
     */
    private long mDisplayStep = 0;

    /**
     * <p>The time that the displaying of the game will be delayed until.</p>
     */
    private long mDelayTime = 0;

    /**
     * <p>The duration of time a step will be displayed for.</p>
     */
    private static final long STEP_DISPLAY_TIME = 800;

    /**
     * <p>The duration of time the swapping between a step will be delayed for.</p>
     */
    private static final long STEP_DELAY_TIME = 200;


    /**
     * <p>The {@link GamePanel} that will controlling the visual element of the game.</p>
     */
    private GamePanel mGameSurface;

    /**
     * <p>The {@link GameTimer} that will control the progress of the game.</p>
     */
    private GameTimer mGameTimer;
    /**
     * <p>A boolean indicating if the game is ready to be drawn just yet.</p>
     */
    private boolean isReady = false;


    /**
     * <p>The Game Journey Presenter.</p>
     */
    private GameJourneyPresenter mGameJourneyPresenter;




    public GameControllerImpl(Context context, GameJourneyPresenter gameJourneyPresenter) {
        mContext = context;
        mGameSurface = new GamePanel(context, this);
        mGameTimer = new GameTimerImpl(this);
        mGameJourneyPresenter = gameJourneyPresenter;
    }

    @Override
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
        mGameJourneyPresenter.gameOver();
    }

    @Override
    public void onClick(float x, float y) {
        mBlueSquare.isTouch(x, y);
        mRedSquare.isTouch(x, y);
    }

    @Override
    public void onReady(GameEnvironment gameEnvironment) {
        mGameEnvironment = gameEnvironment;
        isReady = true;
        mLevel = 0;
        float width = gameEnvironment.getWidth();
        float height = gameEnvironment.getHeight();
        float squareWidth = width * .125f;
        float squareHalf = squareWidth * .5f;
        mBackgroundRectangle = new Rectangle(0, 0, gameEnvironment.getWidth(),
                gameEnvironment.getHeight(), GameColours.GREY);
        mRedSquare = new Circle((width * .25f), (height * .5f), squareWidth,
                GameColours.RED, GameColours.DARK_RED, new Circle.OnTouchListener() {
            @Override
            public void onTouch(int colour) {
                // checkUserClick(colour);
            }
        });

        mBlueSquare = new Circle((width * .75f), (height * .5f), squareWidth,
                GameColours.BLUE, GameColours.DARK_BLUE, new Circle.OnTouchListener() {
            @Override
            public void onTouch(int colour) {
                // checkUserClick(colour);
            }
        });
        float textWidth = Text.measureText(String.valueOf(mLevel));
        float textSize = mContext.getResources().getDimension(R.dimen.level_text_size);
        mLevelText = new Text((width * .5f) - (textWidth * .5f), height * .5f, String.valueOf(mLevel), GameColours.RED, textSize);
        addStep();
        start();
    }


    @Override
    public void updateState() {

        if(isReady) {
            mBlueSquare.update();
            mRedSquare.update();

            if(isAddStep) {
                float textWidth = Text.measureText(String.valueOf(mLevel));
                mLevelText.setText(String.valueOf(mLevel));
                mLevelText.setX((mGameEnvironment.getWidth() * .5f) - (textWidth * .5f));
                mLevelText.setVisibility(true);
                long currentTime = System.currentTimeMillis();
                if(currentTime > mDelayTime) {
                    mDelayTime = 0;
                    for (int i = 0; i < mGameColours.getSteps().size(); i++) {
                        if(i == mDisplayStep && currentTime < mChangeStepTime) {
                            if(mGameColours.getColour(i) == GameColours.RED) {
                                mRedSquare.setVisibility(true);
                                mBlueSquare.setVisibility(false);
                            } else if(mGameColours.getColour(i) == GameColours.BLUE) {
                                mBlueSquare.setVisibility(true);
                                mRedSquare.setVisibility(false);
                            }
                        } else if(i == mDisplayStep && currentTime > mChangeStepTime) {
                            mBlueSquare.setVisibility(false);
                            mRedSquare.setVisibility(false);
                            mDelayTime = currentTime + STEP_DELAY_TIME;
                            mChangeStepTime = currentTime + STEP_DISPLAY_TIME
                                    + STEP_DELAY_TIME;
                            mDisplayStep++;
                        } else if(i == mDisplayStep - 1 && i == mGameColours.getSteps().size() - 1
                                && mChangeStepTime < currentTime) {
                            isAddStep = false;
                        }
                    }
                }

            } else {
                mLevelText.setVisibility(false);
                mBlueSquare.setVisibility(true);
                mRedSquare.setVisibility(true);
            }

        }

    }

    @Override
    public void drawState() {
        ArrayList<GameObject> gameObjects = new ArrayList<>();

        gameObjects.add(mBackgroundRectangle);
        gameObjects.add(mRedSquare);
        gameObjects.add(mBlueSquare);
        gameObjects.add(mLevelText);

        mGameSurface.drawState(gameObjects);
    }


    /**
     * <p>When a user has clicked a {@link Square} confirm that it is the right colour to have been
     * clicked.</p>
     * @param colour int representing the colour.
     */
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

    /**
     * <p>Add a new step to the game, increment the levels and begin displaying the stpes.</p>
     */
    private void addStep() {
        mLevel++;
        mUserColours = new Steps();
        addNewColour();
        displaySteps();
    }

    /**
     * <p>Display the next steps process.</p>
     */
    private void displaySteps() {
        isAddStep = true;
        mChangeStepTime = System.currentTimeMillis() + STEP_DISPLAY_TIME;
        mDisplayStep = 0;
    }

    /**
     * <p>Add a new colour the GameColours {@link Steps} object.</p>
     */
    private void addNewColour() {
        mGameColours.addStep(GameColours.randomColour());
    }


    @Override
    public void onTouch(int colour) {
        checkUserClick(colour);
    }
}
