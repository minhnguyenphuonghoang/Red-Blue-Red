package jordanterry.co.uk.redbluered.game;

/**
 * Created by jordanterry on 20/10/15.
 */
public interface GameTimer {
    void startTimer();
    void stopTimer() throws InterruptedException;
}
