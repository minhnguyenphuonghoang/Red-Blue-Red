package jordanterry.co.uk.redbluered.game.models;

/**
 * Created by jordanterry on 20/10/15.
 */
public class GameEnvironment {

    private float mWidth;
    private float mHeight;

    public GameEnvironment(float width, float height) {
        mWidth = width;
        mHeight = height;
    }

    public float getWidth() {
        return mWidth;
    }

    public float getHeight() {
        return mHeight;
    }

}
