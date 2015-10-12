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

    public Square(float x, float y, float width, float height, int colour) {
        mX = x;
        mY = y;
        mWidth = width;
        mHeight = height;
        mBackgroundColour = new Paint();
        mBackgroundColour.setColor(colour);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(mX, mY, mX + mWidth, mY + mHeight, mBackgroundColour);
    }


}
