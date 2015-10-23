package jordanterry.co.uk.redbluered.game.models;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by jordanterry on 11/10/15.
 */
public class Square extends BaseShape {

    /**
     *
     */
    private Paint mBackgroundColour;

    /**
     *
     */
    private float mEdge;

    public Square(float x, float y, float edge, int colour) {
        super(x, y);
        mEdge = edge;
        mBackgroundColour = new Paint();
        mBackgroundColour.setColor(colour);
    }

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
