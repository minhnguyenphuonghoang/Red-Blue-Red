package jordanterry.co.uk.redbluered.game.models;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * <p>The Square is the main shape that will be used in the Game.</p>
 */
public class Rectangle extends BaseShape {

    /**
     * <p>The {@link Paint} to be used when drawing the Square to the screen.</p>
     */
    private Paint mBackgroundColour;

    /**
     * <p>The width of the Rectangle.</p>
     */
    private float mWidth;

    /**
     * <p>The height of the Rectangle.</p>
     */
    private float mHeight;

    public Rectangle(float x, float y, float width, float height, int colour) {
        super(x, y);
        mWidth = width;
        mHeight = height;
        mBackgroundColour = new Paint();
        mBackgroundColour.setColor(colour);
    }

    /**
     * <p>Set the width of the rectangle.</p>
     * @param width
     */
    public void setWidth(float width) {
        mWidth = width;
    }

    /**
     * <p>Set the height of the rectangle.</p>
     * @param height
     */
    public void setHeight(float height) {
        mHeight = height;
    }


    @Override
    public void setBackground(int color) {
        mBackgroundColour.setColor(color);
    }

    @Override
    public void draw(Canvas canvas) {
        if(isVisible()) {
            canvas.drawRect(getX(), getY(), getX() + mWidth, getY() + mHeight, mBackgroundColour);
        }
    }

    @Override
    public void isTouch(float x, float y) {

    }

    @Override
    public void update() {

    }


}
