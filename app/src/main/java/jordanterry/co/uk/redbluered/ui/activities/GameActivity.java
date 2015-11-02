package jordanterry.co.uk.redbluered.ui.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import jordanterry.co.uk.redbluered.R;
import jordanterry.co.uk.redbluered.game.GameColours;
import jordanterry.co.uk.redbluered.game.views.CircleView;
import jordanterry.co.uk.redbluered.ui.fragments.GameOverFragment;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

        mGamePlayPresenter = new GamePlayPresenterImpl(this);

        mLeftCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGamePlayPresenter.clickButton(GameColours.BLUE);
            }
        });

        mRightCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGamePlayPresenter.clickButton(GameColours.RED);
            }
        });

    }


    @Override
    protected void onPause() {
        super.onPause();

    }


    @Override
    public void onGameOver(int level) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag(GameOverFragment.TAG);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        GameOverFragment newFragment = GameOverFragment.newInstance(level);
        newFragment.show(ft, GameOverFragment.TAG);
    }

    @Override
    public void displayLevel(int level) {

    }
}
