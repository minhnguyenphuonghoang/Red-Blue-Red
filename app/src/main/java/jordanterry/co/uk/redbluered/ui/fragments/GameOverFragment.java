package jordanterry.co.uk.redbluered.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jordanterry.co.uk.redbluered.R;

/**
 * Created by jordanterry on 23/10/15.
 */
public class GameOverFragment extends DialogFragment {

    public static final String TAG = GameOverFragment.class.getSimpleName();

    public static GameOverFragment newInstance() {
        GameOverFragment fragment = new GameOverFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_over, container);
    }

}
