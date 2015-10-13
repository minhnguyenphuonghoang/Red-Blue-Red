package jordanterry.co.uk.redbluered.models;

import java.util.ArrayList;

/**
 * Created by jordanterry on 12/10/15.
 */
public class Steps {

    public static final String TAG = Steps.class.getSimpleName();

    private ArrayList<Integer> mSteps;

    public Steps() {
        mSteps = new ArrayList<>();
    }


    public ArrayList<Integer> getSteps() {
        return mSteps;
    }

    public void addStep(int step) {
        mSteps.add(step);
    }

    public boolean compareSteps(Steps compareSteps) {

        for (int i = 0; i < compareSteps.getSteps().size(); i++) {
            if(mSteps.get(i).intValue() != compareSteps.getSteps().get(i).intValue()) {
                return false;
            }
        }
        return true;
    }


}
