package jordanterry.co.uk.redbluered.game.models;

import java.util.ArrayList;

/**
 * <p>A class to contain the current progress of a player or the set of steps within the game.</p>
 * <p>Two Steps objects can be compared against one another to determine if the user is right or
 * wrong.</p>
 */
public class Steps {

    public static final String TAG = Steps.class.getSimpleName();

    /**
     * <p>A list of the colours represented by an Integer.</p>
     */
    private ArrayList<Integer> mSteps;

    /**
     * <p>The constructor that initialises the steps.</p>
     */
    public Steps() {
        mSteps = new ArrayList<>();
    }

    /**
     * <p>Get the list of colors.</p>
     * @return
     */
    public ArrayList<Integer> getSteps() {
        return mSteps;
    }

    /**
     * <p>Add a new step to the list of colors.</p>
     * @param color
     */
    public void addStep(int color) {
        mSteps.add(color);
    }


    /**
     * <p>Compare this Steps object against another Steps object.</p>
     * @param compareSteps
     * @return {@link Boolean} true if matches, false if doesn't match.
     */
    public boolean compareSteps(Steps compareSteps) {
        if(compareSteps.getSteps().size() > 0 && mSteps.size() > 0) {

            for (int i = 0; i < compareSteps.getSteps().size(); i++) {
                if (mSteps.get(i).intValue() != compareSteps.getSteps().get(i).intValue()) {
                    return false;
                }
            }

            return true;

        } else {
            return false;
        }
    }


    /**
     * <p>Get the color from a cetain position in the Steps.</p>
     * @param position
     * @return
     */
    public int getColour(int position) {
        return mSteps.get(position);
    }


}
