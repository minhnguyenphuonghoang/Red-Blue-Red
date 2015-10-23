package jordanterry.co.uk.redbluered.ui.presenters;

/**
 * <p>The Presenter to present a the Game Over dialog.</p>
 */
public interface GameOverPresenter extends Presenter {
    void restartGame();
    void openLeaderboards();
    void mainMenu();
}
