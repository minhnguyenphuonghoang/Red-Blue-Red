package jordanterry.co.uk.redbluered.game.models;

/**
 * <p>BaseShape implements some methods from {@link GameObject} that will likely remain the same
 * across different implementations of {@link GameObject}.</p>
 */
public abstract class BaseShape implements GameObject {

    /**
     * <p>Boolean indicating if the shape is visible.</p>
     */
    private boolean isVisible = true;

    /**
     * <p>The x-coordinate of the shape.</p>
     */
    private float mX;

    /**
     * <p>The y-coordinate of the shape.</p>
     */
    private float mY;

    /**
     * <p>Contructor providing the x and y-coordinates of the shape.</p>
     * @param x
     * @param y
     */
    public BaseShape(float x, float y) {
        mX = x;
        mY = y;
    }

    @Override
    public void setVisibility(boolean visible) {
        isVisible = visible;
    }

    @Override
    public void setX(float x) {
        mX = x;
    }

    @Override
    public void setY(float y) {
        mY = y;
    }

    @Override
    public float getX() {
        return mX;
    }

    @Override
    public float getY() {
        return mY;
    }

    @Override
    public boolean isVisible() {
        return isVisible;
    }
}
