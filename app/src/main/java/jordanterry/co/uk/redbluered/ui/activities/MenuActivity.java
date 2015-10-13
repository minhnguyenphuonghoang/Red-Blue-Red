package jordanterry.co.uk.redbluered.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import jordanterry.co.uk.redbluered.R;
import jordanterry.co.uk.redbluered.interfaces.OnPlayClicked;
import jordanterry.co.uk.redbluered.ui.fragments.GameFragment;
import jordanterry.co.uk.redbluered.ui.fragments.MenuFragment;

public class MenuActivity extends AppCompatActivity implements OnPlayClicked {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        getSupportFragmentManager().beginTransaction()
                .add(R.id.content, MenuFragment.newInstance(), MenuFragment.TAG)
                .commit();


    }

    @Override
    public void onPlayClicked() {
        hideStatusBar();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content, GameFragment.newInstance(), GameFragment.TAG)
                .commit();
    }



    private void hideStatusBar() {
        getSupportActionBar().hide();
    }
}
