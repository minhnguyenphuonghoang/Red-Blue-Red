package jordanterry.co.uk.redbluered.game.models;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * <p>The Square is the main shape that will be used in the Game.</p>
 */
public class Square extends BaseShape {

    public static final String TAG = Square.class.getSimpleName();

    /**
     * <p>The {@link Paint} to be used when drawing the Square to the screen.</p>
     */
    private Paint mBackgroundColour;

    /**
     * <p>The size of the edges of the Square.</p>
     */
    private float mEdge;

    private OnTouchListener mOnTouchListener;

    private boolean isTouched = false;


    private float mTouchDarkEdge;

    private float mTouchNormalEdge;

    private Paint mDarkBackgroundColour;

    private static long ANIMATION_DURATION = 325;

    public Square(float x, float y, float edge, int colour, int darkColour, OnTouchListener onTouchListener) {
        super(x, y);
        mEdge = edge;
        mTouchDarkEdge = 0;
        mTouchNormalEdge = 0;
        mDarkBackgroundColour = new Paint();
        mDarkBackgroundColour.setColor(darkColour);
        mBackgroundColour = new Paint();
        mBackgroundColour.setColor(colour);
        mOnTouchListener = onTouchListener;
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
            float darkX = getX() + (mEdge * .5f);
            float darkY = getY() + (mEdge * .5f);
            float halfDarkEdge = mTouchDarkEdge * .5f;
            float halfNormalEdge = mTouchNormalEdge * .5f;
            canvas.drawRect(darkX - halfDarkEdge, darkY - halfDarkEdge, darkX + halfDarkEdge, darkY + halfDarkEdge, mDarkBackgroundColour);
            canvas.drawRect(darkX - halfNormalEdge, darkY - halfNormalEdge, darkX + halfNormalEdge, darkY + halfNormalEdge, mBackgroundColour);
        }
    }

    @Override
    public void isTouch(float x, float y) {
        if(!isTouched) {
            if(x > getX() && x < getX() + mEdge && y > getY() && y < getY() + mEdge) {
                isTouched = true;
                mOnTouchListener.onTouch();
            }
        }
    }

    private long mDarkTransitionTime = 0;
    private long mDarkTransitionStart = 0;
    private long mNormalTransitionTime = 0;
    private long mNormalTransitionStart = 0;
    private boolean isTransitionFinished = false;
    @Override
    public void update() {
        if(isTouched) {
            if(mTouchDarkEdge < mEdge) {
                if(mDarkTransitionStart == 0) {
                    mDarkTransitionStart = System.currentTimeMillis();
                }
                mTouchDarkEdge = easing(mDarkTransitionTime, 0, mEdge, ANIMATION_DURATION);
                mDarkTransitionTime = System.currentTimeMillis() - mDarkTransitionStart;
            }

            if(mTouchDarkEdge > mEdge * .4f) {
                if(mTouchNormalEdge < mEdge) {
                    if(mNormalTransitionStart == 0) {
                        mNormalTransitionStart = System.currentTimeMillis();
                    }
                    mTouchNormalEdge = easing(mNormalTransitionTime, 0, mEdge, ANIMATION_DURATION);
                    mNormalTransitionTime = System.currentTimeMillis() - mNormalTransitionStart;
                } else {
                    isTransitionFinished = true;
                }
            }


            if(isTransitionFinished) {
                isTouched = false;
                mTouchDarkEdge = 0;
                mTouchNormalEdge = 0;
                mDarkTransitionTime = 0;
                mDarkTransitionStart = 0;
                mNormalTransitionTime = 0;
                mNormalTransitionStart = 0;
                isTransitionFinished = false;
            }
        }
    }


    /**
     *
     * @param t The current time
     * @param b The start value
     * @param c The change in value
     * @param d The duration
     * @return
     */
    private float easing(float t, float b, float c, long d) {
        t /= d;
        float p = c*t*t*t + b;
        if(p > c) return c;
        return p;
    }


    public interface OnTouchListener {
        void onTouch();
    }

}
