package jordanterry.co.uk.redbluered.game.models;

/**
 * <p>The GameEnvironment contains the width and height of the environment the game is being played
 * in.</p>
 * <p>It is used to send the relevant information from the
 * {@link jordanterry.co.uk.redbluered.game.GamePanel} to the
 * {@link jordanterry.co.uk.redbluered.game.GameController}.</p>
 */
public class GameEnvironment {

    /**
     * <p>The height of the environment.</p>
     */
    private float mWidth;
    /**
     * <p>The width of the environement.</p>
     */
    private float mHeight;

    /**
     * <p>Constructor providing the height and width.</p>
     * @param width
     * @param height
     */
    public GameEnvironment(float width, float height) {
        mWidth = width;
        mHeight = height;
    }

    /**
     * Get the width.
     * @return
     */
    public float getWidth() {
        return mWidth;
    }

    /**
     * Get the height.
     * @return
     */
    public float getHeight() {
        return mHeight;
    }

}
