package jordanterry.co.uk.redbluered.game.models;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by jordanterry on 11/10/15.
 */
public class Square implements GameObject {

    private Paint mBackgroundColour;

    private float mX;

    private float mY;


    private float mEdge;

    private boolean isVisible = true;


    public Square() {
        mBackgroundColour = new Paint();
    }

    public Square(float x, float y, float edge, int colour) {
        mX = x;
        mY = y;
        mEdge = edge;
        mBackgroundColour = new Paint();
        mBackgroundColour.setColor(colour);
    }


    @Override
    public void setBackground(int color) {
        mBackgroundColour.setColor(color);
    }

    @Override
    public void draw(Canvas canvas) {
        if(isVisible) {
            canvas.drawRect(mX, mY, mX + mEdge, mY + mEdge, mBackgroundColour);
        }
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

    @Override
    public boolean isTouch(float x, float y) {
        return x > mX && x < mX + mEdge && y > mY && y < mY + mEdge;
    }


}
