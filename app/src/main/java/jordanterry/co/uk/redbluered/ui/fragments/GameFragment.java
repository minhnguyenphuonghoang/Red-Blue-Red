package jordanterry.co.uk.redbluered.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jordanterry.co.uk.redbluered.ui.presenters.GamePresenter;
import jordanterry.co.uk.redbluered.ui.presenters.GamePresenterImpl;
import jordanterry.co.uk.redbluered.ui.views.GameView;

/**
 * Created by jordanterry on 11/10/15.
 */
public class GameFragment extends Fragment implements GameView {

    public static final String TAG = GameFragment.class.getSimpleName();

    private GamePresenter mGamePresenter;

    public static GameFragment newInstance() {
        GameFragment fragment = new GameFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGamePresenter = new GamePresenterImpl(getContext(), this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mGamePresenter.startGame();
        return mGamePresenter.getGameSurface();
    }

    @Override
    public void onStop() {
        super.onStop();
        mGamePresenter.onStop();
    }
}
