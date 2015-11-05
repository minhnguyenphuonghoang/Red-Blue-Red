package jordanterry.co.uk.redbluered.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jordanterry on 03/11/15.
 */
public class GameStatusController {

    private List<Integer> mGameColours;

    private List<Integer> mStepColours;

    private int mLevel;

    public GameStatusController() {
        mGameColours = new ArrayList<>();
        mStepColours = new ArrayList<>();
        mLevel = 0;

    }

    public List<Integer> getGameColours() {
        return mGameColours;
    }

    public List<Integer> getUserColours() {
        return mStepColours;
    }

    public void addUserStep(int colour) {
        mStepColours.add(colour);
    }

    public boolean isUserCorrect() {
        for (int i = 0; i < mStepColours.size(); i++) {
            if(mStepColours.get(i).intValue() != mGameColours.get(i).intValue()) {
                return false;
            }
        }
        return true;
    }

    public int getLevel() {
        return mLevel;
    }

    public void addNewStep() {
        mLevel++;
        mGameColours.add(GameColours.randomColour());
    }

    public void resetUserClicks() {
        mStepColours = new ArrayList<>();
    }

    public void resetGameSteps() {
        mGameColours = new ArrayList<>();
    }

    public void resetLevel() {
        mLevel = 0;
    }

}
