package jordanterry.co.uk.redbluered.ui.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jordanterry.co.uk.redbluered.R;
import jordanterry.co.uk.redbluered.game.listeners.CircleTouchListener;
import jordanterry.co.uk.redbluered.game.views.CircleView;
import jordanterry.co.uk.redbluered.helpers.ResourceHelpers;

/**
 * <p>The MenuActivity is the first Activity the user will visit.</p>
 * <p>Will control the process of going from the menu to the game itself.</p>
 */
public class MenuActivity extends BaseActivity {


    /**
     * A {@link Button} the play button.
     */
    @Bind(R.id.play_button) CircleView mPlayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

        mPlayButton.setOnTouchListener(new CircleTouchListener(
                ResourceHelpers.createTransitionDrawable(MenuActivity.this,
                        R.drawable.oval_blue_empty_play, R.drawable.oval_blue_filled_play),
                new CircleTouchListener.OnCircleTouch() {
                    @Override
                    public void onTouchDown() {

                    }

                    @Override
                    public void onTouchUp() {
                        startGame();
                    }
                }
        ));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GameActivity.REQUEST_CODE) {
            if(resultCode == GameActivity.RESULT_MAIN_MENU) {
                Toast.makeText(this, "Better luck next time.", Toast.LENGTH_SHORT).show();
            } else if(resultCode == GameActivity.RESULT_LEADERBOARDS) {
                Toast.makeText(this, "Leaderboards will go here.", Toast.LENGTH_SHORT).show();
            } else if(resultCode == GameActivity.RESULT_GAME_RESTART) {
                startGame();
            }
        }
    }


    private void startGame() {
        Intent intent = new Intent(this, GameActivity.class);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

            List<Pair<View, String>> pairs = new ArrayList<>();
            View statusBar = findViewById(android.R.id.statusBarBackground);
            View navigationBar = findViewById(android.R.id.navigationBarBackground);
            pairs.add(Pair.create(statusBar, Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME));
            pairs.add(Pair.create(navigationBar, Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME));
            pairs.add(Pair.create((View) mPlayButton, getString(R.string.play_animation)));
            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation(this, pairs.toArray(new Pair[pairs.size()]));
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }

}
