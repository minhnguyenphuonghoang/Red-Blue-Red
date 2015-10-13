package jordanterry.co.uk.redbluered.models;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by jordanterry on 11/10/15.
 */
public class Square {

    private Paint mBackgroundColour;

    private float mX;

    private float mY;

    private float mWidth;

    private float mHeight;

    private boolean isHidden = false;


    public Square() {
        mBackgroundColour = new Paint();
    }

    public Square(float x, float y, float width, float height, int colour) {
        mX = x;
        mY = y;
        mWidth = width;
        mHeight = height;
        mBackgroundColour = new Paint();
        mBackgroundColour.setColor(colour);
    }


    public void setX(float x) {
        mX = x;
    }

    public void setY(float y) {
        mY = y;
    }

    public void setWidth(float width) {
        mWidth = width;
    }

    public void setHeight(float height) {
        mHeight = height;
    }

    public void setBackgroundColour(int colour) {
        mBackgroundColour.setColor(colour);
    }

    public void draw(Canvas canvas) {
        if(!isHidden) {
            canvas.drawRect(mX, mY, mX + mWidth, mY + mHeight, mBackgroundColour);
        }
    }

    public void hide() {
        isHidden = true;
    }

    public void show() {
        isHidden = false;
    }

    public boolean isTouch(float x, float y) {
        return x > mX && x < mX + mWidth && y > mY && y < mY + mHeight;
    }


}
