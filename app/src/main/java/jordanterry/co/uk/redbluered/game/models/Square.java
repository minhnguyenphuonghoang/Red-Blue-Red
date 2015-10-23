package jordanterry.co.uk.redbluered.game.models;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * <p>The Square is the main shape that will be used in the Game.</p>
 */
public class Square extends BaseShape {

    /**
     * <p>The {@link Paint} to be used when drawing the Square to the screen.</p>
     */
    private Paint mBackgroundColour;

    /**
     * <p>The size of the edges of the Square.</p>
     */
    private float mEdge;

    public Square(float x, float y, float edge, int colour) {
        super(x, y);
        mEdge = edge;
        mBackgroundColour = new Paint();
        mBackgroundColour.setColor(colour);
    }

    /**
     * <p>Set the edge of the square outside of the constructor.</p>
     * <p>Could be used if the size of the Square is going to be changed.</p>
     * @param edge
     */
    public void setEdge(float edge) {
        mEdge = edge;
    }


    @Override
    public void setBackground(int color) {
        mBackgroundColour.setColor(color);
    }

    @Override
    public void draw(Canvas canvas) {
        if(isVisible()) {
            canvas.drawRect(getX(), getY(), getX() + mEdge, getY() + mEdge, mBackgroundColour);
        }
    }

    @Override
    public boolean isTouch(float x, float y) {
        return x > getX() && x < getX() + mEdge && y > getY() && y < getY() + mEdge;
    }


}
