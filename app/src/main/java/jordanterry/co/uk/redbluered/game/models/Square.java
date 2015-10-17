package jordanterry.co.uk.redbluered.game.models;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by jordanterry on 11/10/15.
 */
public class Square {

    private Paint mBackgroundColour;

    private float mX;

    private float mY;


    private float mEdge;

    private boolean isHidden = false;


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



    public void setPosition(float x, float y) {
        mX = x;
        mY = y;
    }

    public void setEdge(float edge) {
        mEdge = edge;
    }


    public void setBackgroundColour(int colour) {
        mBackgroundColour.setColor(colour);
    }

    public void draw(Canvas canvas) {
        if(!isHidden) {
            canvas.drawRect(mX, mY, mX + mEdge, mY + mEdge, mBackgroundColour);
        }
    }

    public void hide() {
        isHidden = true;
    }

    public void show() {
        isHidden = false;
    }

    public boolean isVisible() {
        return !isHidden;
    }

    public boolean isTouch(float x, float y) {
        return x > mX && x < mX + mEdge && y > mY && y < mY + mEdge;
    }


}
