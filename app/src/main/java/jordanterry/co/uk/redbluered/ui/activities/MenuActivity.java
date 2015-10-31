package jordanterry.co.uk.redbluered.ui.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import jordanterry.co.uk.redbluered.R;
import jordanterry.co.uk.redbluered.interfaces.OnPlayClicked;
import jordanterry.co.uk.redbluered.ui.fragments.MenuFragment;

/**
 * <p>The MenuActivity is the first Activity the user will visit.</p>
 * <p>Will control the process of going from the menu to the game itself.</p>
 */
public class MenuActivity extends BaseActivity implements OnPlayClicked {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        addFragmentWithoutBackStack(R.id.content, MenuFragment.newInstance(), MenuFragment.TAG);

    }

    @Override
    public void onPlayClicked() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivityForResult(intent, GameActivity.REQUEST_CODE);
    }

    @Override
    public void onPlayClicked(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation(this, view, getString(R.string.play_animation));
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
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
                onPlayClicked();
            }
        }
    }
}
