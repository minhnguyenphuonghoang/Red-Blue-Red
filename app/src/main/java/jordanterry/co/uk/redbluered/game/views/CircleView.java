package jordanterry.co.uk.redbluered.game.views;

import android.animation.Animator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;

/**
 * Created by jordanterry on 30/10/15.
 */
public class CircleView extends LinearLayout {

    private boolean isHidden;


    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        isHidden = false;
    }


    public void hide() {
        isHidden = true;
        animate().scaleY(0).scaleX(0).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(150);
    }


    public void hide(final OnAnimationListener animationListener) {
        isHidden = true;
        animate().scaleY(0).scaleX(0).setInterpolator(new AccelerateDecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animationListener.onComplete();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        }).setDuration(150);
    }

    public void show() {
        isHidden = false;
        animate().scaleY(1).scaleX(1).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(150);
    }

    public void show(final OnAnimationListener animationListener) {
        isHidden = false;
        animate().scaleY(1).scaleX(1).setInterpolator(new AccelerateDecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animationListener.onComplete();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        }).setDuration(150);
    }

    public void hideAndShow() {
        isHidden = false;
        animate().scaleY(0).scaleX(0).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(150).start();
        animate().setStartDelay(150).scaleY(0).scaleX(0).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(150).start();
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void tapDown() {
        animate().scaleY(.8f).scaleX(.8f).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(75);
    }


    public void tapUp() {
        animate().scaleY(1).scaleX(1).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(50);
    }

    public interface OnAnimationListener {
        void onComplete();
    }

}
