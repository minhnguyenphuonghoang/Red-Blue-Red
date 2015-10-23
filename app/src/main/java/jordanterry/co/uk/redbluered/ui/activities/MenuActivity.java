package jordanterry.co.uk.redbluered.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import jordanterry.co.uk.redbluered.R;
import jordanterry.co.uk.redbluered.interfaces.OnPlayClicked;
import jordanterry.co.uk.redbluered.ui.fragments.GameFragment;
import jordanterry.co.uk.redbluered.ui.fragments.MenuFragment;

public class MenuActivity extends BaseActivity implements OnPlayClicked {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        addFragment(R.id.content, MenuFragment.newInstance(), MenuFragment.TAG);

    }

    @Override
    public void onPlayClicked() {

        addFragment(R.id.content, GameFragment.newInstance(), GameFragment.TAG);
    }



    private void hideStatusBar() {
        getSupportActionBar().hide();
    }
}
