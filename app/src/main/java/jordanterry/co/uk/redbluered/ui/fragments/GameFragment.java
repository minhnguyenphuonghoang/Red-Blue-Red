package jordanterry.co.uk.redbluered.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import jordanterry.co.uk.redbluered.R;
import jordanterry.co.uk.redbluered.game.GamePanel;

/**
 * Created by jordanterry on 11/10/15.
 */
public class GameFragment extends Fragment {

    public static final String TAG = GameFragment.class.getSimpleName();

    @Bind(R.id.game)
    GamePanel mGame;

    public static GameFragment newInstance() {
        GameFragment fragment = new GameFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        ButterKnife.bind(this, view);
        mGame.start();
        return view;
    }



    @Override
    public void onStop() {
        super.onStop();
        try {
            mGame.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
