package jordanterry.co.uk.redbluered.game.models;

import android.graphics.Canvas;

/**
 * <p>The GameObject describes an object that will be drawn to the screen by the game.</p>
 */
public interface GameObject {

    /**
     * <p>Set the background color of the GameObject.</p>
     * @param color
     */
    void setBackground(int color);

    /**
     * Draw the GameObject using a {@link Canvas}.
     * @param canvas
     */
    void draw(Canvas canvas);

    /**
     * <p>Set the visibility of the GameObject.</p>
     * @param visible
     */
    void setVisibility(boolean visible);

    /**
     * <p>Set the x-coordinate of the GameObject.</p>
     * @param x
     */
    void setX(float x);

    /**
     * <p>Set the y-coordinate of the GameObject.</p>
     * @param y
     */
    void setY(float y);

    /**
     * <p>Get the y-coordinates of the GameObject.</p>
     * @return
     */
    float getX();

    /**
     * <p>Get the x-coordinate of the GameObject.</p>
     * @return
     */
    float getY();

    /**
     * <p>Get the visibility of the GameObject.</p>
     * @return
     */
    boolean isVisible();

    /**
     * <p>Should return true if the touch is within the bounds of the GameObject.</p>
     * @param x x-coordinate of the touch
     * @param y y-coordinate of the touch
     */
    void isTouch(float x, float y);

    /**
     * <p>Called to update the state of the GameObject.</p>
     */
    void update();


}
