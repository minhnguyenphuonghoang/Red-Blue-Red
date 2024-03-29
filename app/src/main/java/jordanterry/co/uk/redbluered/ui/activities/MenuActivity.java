package jordanterry.co.uk.redbluered.ui.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jordanterry.co.uk.redbluered.R;
import jordanterry.co.uk.redbluered.game.listeners.CircleTouchListener;
import jordanterry.co.uk.redbluered.game.views.CircleView;

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




    private void startGame() {
        Intent intent = new Intent(this, GameActivity.class);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

            List<Pair<View, String>> pairs = new ArrayList<>();
            View statusBar = findViewById(android.R.id.statusBarBackground);
            View navigationBar = findViewById(android.R.id.navigationBarBackground);
            pairs.add(Pair.create(statusBar, Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME));
            pairs.add(Pair.create(navigationBar, Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME));
            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation(this, pairs.toArray(new Pair[pairs.size()]));
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }

}
