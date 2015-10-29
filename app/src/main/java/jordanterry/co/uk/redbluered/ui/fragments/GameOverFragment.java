package jordanterry.co.uk.redbluered.ui.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import butterknife.ButterKnife;
import butterknife.OnClick;
import jordanterry.co.uk.redbluered.R;
import jordanterry.co.uk.redbluered.ui.presenters.GameOverPresenter;
import jordanterry.co.uk.redbluered.ui.presenters.GameOverPresenterImpl;
import jordanterry.co.uk.redbluered.ui.views.GameOverView;

/**
 * Created by jordanterry on 23/10/15.
 */
public class GameOverFragment extends DialogFragment implements GameOverView {

    public static final String TAG = GameOverFragment.class.getSimpleName();

    private GameOverPresenter mGameOverPresenter;

    private static String KEY_LEVEL = "KEY_LEVEL";
    private int mLevel;

    public static GameOverFragment newInstance(int level) {
        GameOverFragment fragment = new GameOverFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_LEVEL, level);
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        mGameOverPresenter = new GameOverPresenterImpl(getActivity(), this);
        dialog.setCancelable(false);
        return dialog;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(savedInstanceState == null) {
            mLevel = getArguments().getInt(KEY_LEVEL);
        } else {
            mLevel = savedInstanceState.getInt(KEY_LEVEL);
        }
        View view = inflater.inflate(R.layout.fragment_game_over, container);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.button_leaderboard) void onLeaderboardClick() {
        mGameOverPresenter.openLeaderboards();
    }

    @OnClick(R.id.button_quit) void onQuitClick() {
        mGameOverPresenter.mainMenu();
    }

    @OnClick(R.id.button_replay) void onReplayClick() {
        mGameOverPresenter.restartGame();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        mGameOverPresenter.mainMenu();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_LEVEL, mLevel);
        super.onSaveInstanceState(outState);
    }
}
