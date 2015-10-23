package jordanterry.co.uk.redbluered.game;

/**
 * <p>The GameTimer interface that will be implemented by a {@link Thread}to run the game.</p>
 */
public interface GameTimer {
    /**
     * <p>Start the Timer.</p>
     */
    void startTimer();

    /**
     * <p>Stop the Timer.</p>
     * @throws InterruptedException
     */
    void stopTimer() throws InterruptedException;
}
