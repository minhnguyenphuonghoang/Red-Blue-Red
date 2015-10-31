package jordanterry.co.uk.redbluered.game.models;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Looper;

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
    private boolean isDisplayTouched = false;

    private float mTouchDarkEdge;

    private float mTouchNormalEdge;

    private Paint mDarkBackgroundColour;

    private static long ANIMATION_DURATION = 500;



    private float mDisplayStep;
    private boolean isDisplaying = false;
    private boolean isDisplayDisplaying = false;
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
            if(!isDisplaying && !isDisplayDisplaying) {
                canvas.drawCircle(getX(), getY(), mRadius, mBackgroundColour);
            }
            canvas.save();
            if(isDisplayTouched) {
                canvas.clipPath(mPath);
                canvas.drawCircle(mTouchX, mTouchY, mTouchDarkEdge, mDarkBackgroundColour);
                canvas.drawCircle(mTouchX, mTouchY, mTouchNormalEdge, mBackgroundColour);
            }

            if(!isDisplaying && isDisplayDisplaying) {
                canvas.clipPath(mPath);
                canvas.drawCircle(getX(), getY(), mDisplayStep, mBackgroundColour);
            }
            canvas.restore();

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
                isDisplayTouched = true;
                mOnTouchListener.onTouch(mBackgroundColour.getColor());
            }
        }

    }

    @Override
    public void update() {

        if(isDisplaying) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {

                    ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mRadius * 2);
                    valueAnimator.setDuration(250);
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            mDisplayStep = (Float) animation.getAnimatedValue();
                            if(mDisplayStep == mRadius * 2) {
                                isDisplayDisplaying = false;
                            }
                        }
                    });
                    valueAnimator.start();
                    isDisplaying = false;
                }
            });


        }

        if(isTouched) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {

                    ValueAnimator darkAnimator = ValueAnimator.ofFloat(0, mRadius * 2);
                    darkAnimator.setDuration(250);
                    darkAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator darkAnimation) {
                            mTouchDarkEdge = (Float) darkAnimation.getAnimatedValue();
                        }
                    });

                    ValueAnimator lightAnimator = ValueAnimator.ofFloat(0, mRadius * 2);
                    lightAnimator.setDuration(250);
                    lightAnimator.setStartDelay(100);
                    lightAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator lightAnimation) {
                            mTouchNormalEdge = (Float) lightAnimation.getAnimatedValue();
                            if(mTouchNormalEdge == mRadius * 2) {
                                isDisplayTouched = false;
                            }
                        }
                    });
                    darkAnimator.start();
                    lightAnimator.start();
                    isTouched = false;

                }
            });
        }
    }

    @Override
    public void setDisplaying() {
        setVisibility(true);
        isDisplaying = true;
        isDisplayDisplaying = true;
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
