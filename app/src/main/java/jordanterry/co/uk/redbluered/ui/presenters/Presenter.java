package jordanterry.co.uk.redbluered.ui.presenters;

/**
 * <p>The basic Presenter that contains methods that will be used in the relevant Views onPause,
 * onResume and onStop methods.</p>
 */
public interface Presenter {
    /**
     * <p>The onPause method. Should be called when a view is being paused.</p>
     */
    void onPause();

    /**
     * <p>The onResume method. Should be called when a view is being resumed.</p>
     */
    void onResume();

    /**
     * <p>The onStop method. Should be called when a view is being stopped.</p>
     */
    void onStop();
}
