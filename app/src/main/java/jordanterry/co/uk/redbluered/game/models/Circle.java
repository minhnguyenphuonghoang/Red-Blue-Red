package jordanterry.co.uk.redbluered.game.models;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import jordanterry.co.uk.redbluered.helpers.EasingHelpers;

/**
 * <p>The Square is the main shape that will be used in the Game.</p>
 */
public class Circle extends BaseShape {

    public static final String TAG = Circle.class.getSimpleName();

    /**
     * <p>The {@link Paint} to be used when drawing the Square to the screen.</p>
     */
    private Paint mBackgroundColour;

    /**
     * <p>The size of the edges of the Square.</p>
     */
    private float mRadius;

    private OnTouchListener mOnTouchListener;

    private boolean isTouched = false;


    private float mTouchDarkEdge;

    private float mTouchNormalEdge;

    private Paint mDarkBackgroundColour;

    private static long ANIMATION_DURATION = 500;

    private float mWhiteEdge;
    private float mBlueEdge;
    private long mWhiteTransitionStart = 0;
    private long mWhiteTransitionTime = 0;
    private long mBlueTransitionTime = 0;
    private long mBlueTransitionStart = 0;


    private long mDarkTransitionTime = 0;
    private long mDarkTransitionStart = 0;
    private long mNormalTransitionTime = 0;
    private long mNormalTransitionStart = 0;
    private boolean isTransitionFinished = false;

    private boolean isDisplaying = false;
    private boolean isHiding = false;

    private Path mPath;

    private float mClickSize = 0;

    private float mTouchX;
    private float mTouchY;
    private Paint mWhitePaint;

    public Circle(float x, float y, float radius, int colour, int darkColour, OnTouchListener onTouchListener) {
        super(x, y);
        mRadius = radius;
        mTouchDarkEdge = 0;
        mTouchNormalEdge = 0;
        mDarkBackgroundColour = new Paint();
        mDarkBackgroundColour.setColor(darkColour);
        mBackgroundColour = new Paint();
        mBackgroundColour.setColor(colour);
        mOnTouchListener = onTouchListener;
        mWhitePaint = new Paint();
        mWhitePaint.setColor(Color.WHITE);
        mPath = new Path();
        mPath.addCircle(getX(), getY(), mRadius, Path.Direction.CW);
    }

    /**
     * <p>Set the edge of the square outside of the constructor.</p>
     * <p>Could be used if the size of the Square is going to be changed.</p>
     * @param radius
     */
    public void setRadius(float radius) {
        mRadius = radius;
    }


    @Override
    public void setBackground(int color) {
        mBackgroundColour.setColor(color);
    }

    @Override
    public void draw(Canvas canvas) {
        if(isVisible()) {
            if(!isDisplaying) {
                canvas.drawCircle(getX(), getY(), mRadius, mBackgroundColour);
            }
            if(isTouched) {
                canvas.save();
                canvas.clipPath(mPath);

                canvas.drawCircle(mTouchX, mTouchY, mTouchDarkEdge, mDarkBackgroundColour);
                canvas.drawCircle(mTouchX, mTouchY, mTouchNormalEdge, mBackgroundColour);

                canvas.restore();

            }
            if(isDisplaying) {
                canvas.save();
                canvas.clipPath(mPath);

                canvas.drawCircle(getX(), getY(), mBlueEdge, mBackgroundColour);

                canvas.restore();
            }
            if(isHiding) {

                canvas.save();
                canvas.clipPath(mPath);

                canvas.drawCircle(getX(), getY(), mWhiteEdge, mWhitePaint);

                canvas.restore();
            }
        }
    }

    @Override
    public void isTouch(float x, float y) {

        if(!isTouched) {
            if(((getX() - x) * (getX() - x)) + ((getY() - y) * (getY() - y)) < mRadius * mRadius) {
                mTouchX = x;
                mTouchY = y;
                mClickSize = mRadius * 3;
                isTouched = true;
                mOnTouchListener.onTouch(mBackgroundColour.getColor());
            }
        }

    }

    @Override
    public void update() {


        if(isDisplaying) {

            if (mBlueTransitionStart == 0) {
                mBlueTransitionStart = System.currentTimeMillis();
            }
            if (mBlueTransitionStart + ANIMATION_DURATION > System.currentTimeMillis()) {
                mBlueEdge = EasingHelpers.linear(mBlueTransitionTime, 0, mRadius * 2, ANIMATION_DURATION);
                mBlueTransitionTime = System.currentTimeMillis() - mBlueTransitionStart;
            } else {
                isDisplaying = false;
            }
        } else {
            mBlueTransitionStart = 0;
            mBlueTransitionTime = 0;
            mBlueEdge = 0;
        }



        if(isHiding) {

            if (mWhiteTransitionStart == 0) {
                mWhiteTransitionStart = System.currentTimeMillis();
            }
            if (mWhiteTransitionStart + ANIMATION_DURATION > System.currentTimeMillis()) {
                mWhiteEdge = EasingHelpers.linear(mWhiteTransitionTime, mRadius * 2, 0, ANIMATION_DURATION);
                mWhiteTransitionTime = System.currentTimeMillis() - mWhiteTransitionStart;
            } else {
                isHiding = false;
            }
        } else {
            mWhiteTransitionStart = 0;
            mWhiteTransitionStart = 0;
            mWhiteEdge = 0;
        }

        if(isDisplaying) {

            if (mWhiteTransitionStart == 0) {
                mWhiteTransitionStart = System.currentTimeMillis();
            }
            if (mWhiteTransitionStart + ANIMATION_DURATION > System.currentTimeMillis()) {
                mWhiteEdge = EasingHelpers.linear(mWhiteTransitionTime, 0, mRadius * 2, ANIMATION_DURATION);
                mWhiteTransitionTime = System.currentTimeMillis() - mWhiteTransitionStart;
            } else {
                isDisplaying = false;
            }
        } else {
            mWhiteTransitionStart = 0;
            mWhiteTransitionTime = 0;
            mWhiteEdge = 0;
        }

        if(isTouched) {

            if (mDarkTransitionStart == 0) {
                mDarkTransitionStart = System.currentTimeMillis();
            }

            if (mDarkTransitionStart != 0 && mDarkTransitionStart + ANIMATION_DURATION > System.currentTimeMillis()) {
                mTouchDarkEdge = EasingHelpers.linear(mDarkTransitionTime, 0, mClickSize, ANIMATION_DURATION);
                mDarkTransitionTime = System.currentTimeMillis() - mDarkTransitionStart;
            }

            if(mTouchDarkEdge > mRadius * .2f) {

                if(mNormalTransitionStart == 0) {
                    mNormalTransitionStart = System.currentTimeMillis();
                }

                if (mNormalTransitionStart != 0 && mNormalTransitionStart + ANIMATION_DURATION > System.currentTimeMillis()) {
                    mTouchNormalEdge = EasingHelpers.linear(mNormalTransitionTime, 0, mClickSize, ANIMATION_DURATION);
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

    @Override
    public void setDisplaying() {
        setVisibility(true);
        isDisplaying = true;
    }

    @Override
    public void setHiding() {
        setVisibility(false);
        isHiding = true;
    }

    public interface OnTouchListener {
        void onTouch(int colour);
    }

}
