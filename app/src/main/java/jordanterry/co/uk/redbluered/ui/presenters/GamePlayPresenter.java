package jordanterry.co.uk.redbluered.ui.presenters;

/**
 * Created by jordanterry on 30/10/15.
 */
public interface GamePlayPresenter {


    void clickButton(int colour);

    void addNewLevel();

    void keepPlaying();

    void onResume();

    void onPause();

    void reset();
}
