package jordanterry.co.uk.redbluered.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jordanterry.co.uk.redbluered.ui.presenters.GamePresenter;
import jordanterry.co.uk.redbluered.ui.views.GameView;

/**
 * <p>The GameFragment implements {@link GameView}.</p>
 */
public class GameFragment extends Fragment implements GameView {

    public static final String TAG = GameFragment.class.getSimpleName();

    /**
     * <p>The {@link GamePresenter} object.</p>
     */
    private GamePresenter mGamePresenter;

    /**
     * <p>Create a new instance of the GameFragment object.</p>
     * @return
     */
    public static GameFragment newInstance() {
        GameFragment fragment = new GameFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // mGamePresenter = new GamePresenterImpl(getContext(), this);
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

    @Override
    public void onGameOver() {

    }
}
