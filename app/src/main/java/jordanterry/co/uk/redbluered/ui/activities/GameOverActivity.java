package jordanterry.co.uk.redbluered.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import butterknife.Bind;
import butterknife.ButterKnife;
import jordanterry.co.uk.redbluered.R;
import jordanterry.co.uk.redbluered.game.listeners.CircleTouchListener;
import jordanterry.co.uk.redbluered.game.views.CircleView;

/**
 * Created by jordanterry on 14/10/15.
 */
public class GameOverActivity extends BaseActivity {

    public static final String TAG = GameOverActivity.class.getSimpleName();

    @Bind(R.id.replay_button) CircleView mReplayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        ButterKnife.bind(this);

        mReplayButton.setOnTouchListener(new CircleTouchListener(
                new CircleTouchListener.OnCircleTouch() {
                    @Override
                    public void onTouchDown() {

                    }

                    @Override
                    public void onTouchUp() {

                        mReplayButton.hide(new CircleView.OnAnimationListener() {
                            @Override
                            public void onComplete() {

                                Intent intent = new Intent();
                                Bundle bundle = new Bundle();
                                intent.putExtras(bundle);
                                setResult(RESULT_OK, intent);
                                finish();
                                overridePendingTransition(0, 0);
                            }
                        });

                    }
                }
        ));

    }



}
