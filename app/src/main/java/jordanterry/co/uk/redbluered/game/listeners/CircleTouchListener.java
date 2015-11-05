package jordanterry.co.uk.redbluered.game.listeners;

import android.graphics.drawable.TransitionDrawable;
import android.view.MotionEvent;
import android.view.View;

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

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
            //    view.setBackground(mTransitionDrawable);
              //  mTransitionDrawable.startTransition(200);
                view.animate().scaleX(.87f).scaleY(.87f).setDuration(100);
                mOnCircleTouch.onTouchDown();
                return true;
            case MotionEvent.ACTION_UP:
    //                mTransitionDrawable.reverseTransition(400);
                view.animate().scaleX(1).scaleY(1).setDuration(150);

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
