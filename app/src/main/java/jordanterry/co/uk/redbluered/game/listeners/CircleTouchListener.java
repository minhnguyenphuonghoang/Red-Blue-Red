package jordanterry.co.uk.redbluered.game.listeners;

import android.graphics.drawable.TransitionDrawable;
import android.view.MotionEvent;
import android.view.View;

import jordanterry.co.uk.redbluered.game.views.CircleView;

/**
 * Created by jordanterry on 03/11/15.
 */
public class CircleTouchListener implements View.OnTouchListener {

    private TransitionDrawable mTransitionDrawable;
    private OnCircleTouch mOnCircleTouch;


    public CircleTouchListener(TransitionDrawable transitionDrawable, OnCircleTouch onCircleTouch) {
        mTransitionDrawable = transitionDrawable;
        mOnCircleTouch = onCircleTouch;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        CircleView circleView = (CircleView) view;
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                circleView.tapDown();
                mOnCircleTouch.onTouchDown();
                return true;
            case MotionEvent.ACTION_UP:
                circleView.tapUp();
                mOnCircleTouch.onTouchUp();
                return true;
        }
        return false;
    }



    public interface OnCircleTouch {
        void onTouchDown();
        void onTouchUp();
    }
}
