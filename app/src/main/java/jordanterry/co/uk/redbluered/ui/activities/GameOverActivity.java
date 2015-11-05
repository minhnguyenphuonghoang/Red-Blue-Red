package jordanterry.co.uk.redbluered.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import butterknife.Bind;
import butterknife.ButterKnife;
import jordanterry.co.uk.redbluered.R;
import jordanterry.co.uk.redbluered.game.listeners.CircleTouchListener;
import jordanterry.co.uk.redbluered.game.views.CircleView;
import jordanterry.co.uk.redbluered.helpers.ResourceHelpers;

/**
 * Created by jordanterry on 14/10/15.
 */
public class GameOverActivity extends BaseActivity {

    public static final String TAG = GameOverActivity.class.getSimpleName();


    public static int RESTART_GAME = 10001;

    @Bind(R.id.replay_button) CircleView mReplayButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        ButterKnife.bind(this);

        mReplayButton.setOnTouchListener(new CircleTouchListener(
                ResourceHelpers.createTransitionDrawable(this,
                        R.drawable.oval_red_empty_replay, R.drawable.oval_red_filled_replay),
                new CircleTouchListener.OnCircleTouch() {
                    @Override
                    public void onTouchDown() {

                    }

                    @Override
                    public void onTouchUp() {

                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        intent.putExtras(bundle);
                        setResult(RESULT_OK, intent);

                        finish();
                    }
                }
        ));

    }



}
