package jordanterry.co.uk.redbluered.game.models;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * <p>A text object will display an String of Text to the screen.</p>
 */
public class Text extends BaseShape {


    /**
     * <p>The {@link String} to be displayed.</p>
     */
    private String mText;

    /**
     * <p>The Paint to be used to draw the shape.</p>
     */
    private Paint mPaint;

    /**
     * <p>The size of the text to be drawn.</p>
     */
    private float mTextSize;

    /**
     * <p>The constructor providing the coordinates, the text value colour and size.</p>
     * @param x
     * @param y
     * @param text
     * @param color
     * @param textSize
     */
    public Text(float x, float y, String text, int color, float textSize) {
        super(x, y);
        mText = text;
        mTextSize = textSize;
        mPaint = new Paint();
        mPaint.setColor(color);
        mPaint.setTextSize(mTextSize);
    }

    /**
     * <p>Set the text size.</p>
     * @param text
     */
    public void setText(String text) {
        mText = text;
    }

    @Override
    public void setBackground(int color) {
        mPaint.setColor(color);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawText(mText, getX(), getY(), mPaint);
    }

    @Override
    public void isTouch(float x, float y) {

    }

    @Override
    public void update() {

    }


    /**
     * <p>This is used to measure the size of the text when it is being drawn to the screen.</p>
     * @return
     */
    public float measureText() {
        return mPaint.measureText(mText);
    }

}
