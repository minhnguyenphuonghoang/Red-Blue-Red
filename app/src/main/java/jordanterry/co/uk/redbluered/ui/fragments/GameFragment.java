package jordanterry.co.uk.redbluered.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jordanterry.co.uk.redbluered.game.GameControllerImpl;

/**
 * Created by jordanterry on 11/10/15.
 */
public class GameFragment extends Fragment {

    public static final String TAG = GameFragment.class.getSimpleName();

    private GameControllerImpl mGameController;

    public static GameFragment newInstance() {
        GameFragment fragment = new GameFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mGameController = new GameControllerImpl(getContext());
        mGameController.start();
        return mGameController.getGamePanel();
    }



    @Override
    public void onStop() {
        super.onStop();
        try {
            mGameController.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
