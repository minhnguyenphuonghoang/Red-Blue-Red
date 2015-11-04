package jordanterry.co.uk.redbluered.ui.views;

import java.util.List;

/**
 * <p>The interface defining the GameView.</p>
 */
public interface GameView {

    void onGameOver(int level);
    void displayLevel(int colour, int level);
    void displaySteps(List<Integer> colours);


}
