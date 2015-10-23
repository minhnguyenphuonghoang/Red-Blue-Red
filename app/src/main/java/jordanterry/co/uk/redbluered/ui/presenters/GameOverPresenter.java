package jordanterry.co.uk.redbluered.ui.presenters;

/**
 * <p>The Presenter to present a the Game Over dialog.</p>
 */
public interface GameOverPresenter extends Presenter {
    /**
     * <p>Restart the game.</p>
     */
    void restartGame();

    /**
     * <p>Open leaderboards.</p>
     */
    void openLeaderboards();

    /**
     * <p>Go to the main menu.</p>
     */
    void mainMenu();
}
