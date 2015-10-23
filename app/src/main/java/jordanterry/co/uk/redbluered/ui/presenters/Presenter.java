package jordanterry.co.uk.redbluered.ui.presenters;

/**
 * <p>The basic Presenter that contains methods that will be used in the relevant Views onPause,
 * onResume and onStop methods.</p>
 */
public interface Presenter {
    void onPause();
    void onResume();
    void onStop();
}
