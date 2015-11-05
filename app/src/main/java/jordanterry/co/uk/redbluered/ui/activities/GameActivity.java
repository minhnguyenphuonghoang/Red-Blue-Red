package jordanterry.co.uk.redbluered.ui.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import jordanterry.co.uk.redbluered.R;
import jordanterry.co.uk.redbluered.game.GameColours;
import jordanterry.co.uk.redbluered.game.listeners.CircleTouchListener;
import jordanterry.co.uk.redbluered.game.views.CircleView;
import jordanterry.co.uk.redbluered.helpers.ResourceHelpers;
import jordanterry.co.uk.redbluered.ui.presenters.GamePlayPresenter;
import jordanterry.co.uk.redbluered.ui.presenters.GamePlayPresenterImpl;
import jordanterry.co.uk.redbluered.ui.views.GameView;

/**
 * Created by jordanterry on 14/10/15.
 */
public class GameActivity extends BaseActivity implements GameView {

    public static final String TAG = GameActivity.class.getSimpleName();

    public static int REQUEST_CODE = 1;

    public static int RESULT_MAIN_MENU = 2;

    public static int RESULT_GAME_RESTART = 3;

    public static int RESULT_LEADERBOARDS = 4;


    @Bind(R.id.left_circle) CircleView mLeftCircle;
    @Bind(R.id.right_circle) CircleView mRightCircle;


    private GamePlayPresenter mGamePlayPresenter;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

        mHandler = new Handler();
        mGamePlayPresenter = new GamePlayPresenterImpl(this);

        mRightCircle.setOnTouchListener(new CircleTouchListener(
                ResourceHelpers.createTransitionDrawable(this,
                        R.drawable.oval_red_empty, R.drawable.oval_red_filled),
                new CircleTouchListener.OnCircleTouch() {
                    @Override
                    public void onTouchDown() {

                    }

                    @Override
                    public void onTouchUp() {
                        mGamePlayPresenter.clickButton(GameColours.RED);
                    }
                }
        ));


        mLeftCircle.setOnTouchListener(new CircleTouchListener(
                ResourceHelpers.createTransitionDrawable(this,
                        R.drawable.oval_blue_empty, R.drawable.oval_blue_filled),
                new CircleTouchListener.OnCircleTouch() {
                    @Override
                    public void onTouchDown() {

                    }

                    @Override
                    public void onTouchUp() {
                        mGamePlayPresenter.clickButton(GameColours.BLUE);
                    }
                }
        ));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGamePlayPresenter.onResume();
    }

    @Override
    public void onGameOver(int level) {

        Intent intent = new Intent(this, GameOverActivity.class);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            List<Pair<View, String>> pairs = new ArrayList<>();
            pairs.add(Pair.create((View) mRightCircle, getString(R.string.play_animation)));
            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation(this, pairs.toArray(new Pair[pairs.size()]));
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }

    }





    @Override
    public void displayLevel(int colour, int level) {
        Toast.makeText(this, "You clicked the wrong colour..", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displaySteps(List<Integer> colours) {
        Timer t = new Timer();
        long displayTime = 850;
        long hideTime = 500;
        long futureTime = displayTime;
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                hideBoth();
            }
        }, hideTime);
        futureTime += hideTime;

        for (int i = 0; i < colours.size(); i++) {
            if(colours.get(i) == GameColours.RED) {

                t.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        showRed();
                    }
                }, futureTime);
                futureTime += displayTime;

            } else if(colours.get(i) == GameColours.BLUE) {

                t.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        showBlue();
                    }
                }, futureTime);
                futureTime += displayTime;

            }
            if (i == colours.size() - 1) {
                t.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        showBoth();
                    }
                }, futureTime);
            } else {
                t.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        hideBoth();
                    }
                }, futureTime);
                futureTime += hideTime;
            }
        }

    }


    private void showRed() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mLeftCircle.hide();
                if(mRightCircle.isHidden()) {
                    mRightCircle.show();
                } else {
                    mRightCircle.hideAndShow();
                }
            }
        });
    }

    private void showBlue() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mRightCircle.hide();
                if(mLeftCircle.isHidden()) {
                    mLeftCircle.show();
                } else {
                    mLeftCircle.hideAndShow();
                }
            }
        });
    }

    private void hideBoth() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(!mLeftCircle.isHidden()) {
                    mLeftCircle.hide();
                }
                if(!mRightCircle.isHidden()) {
                    mRightCircle.hide();
                }
            }
        });
    }

    private void showBoth() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mLeftCircle.isHidden()) {
                    mLeftCircle.show();
                }
                if(mRightCircle.isHidden()) {
                    mRightCircle.show();
                }
            }
        });
    }


}
