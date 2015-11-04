package jordanterry.co.uk.redbluered.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jordanterry.co.uk.redbluered.R;
import jordanterry.co.uk.redbluered.game.GameColours;
import jordanterry.co.uk.redbluered.game.views.CircleView;
import jordanterry.co.uk.redbluered.ui.presenters.GamePlayPresenter;
import jordanterry.co.uk.redbluered.ui.presenters.GamePlayPresenterImpl;
import jordanterry.co.uk.redbluered.ui.views.GameView;

/**
 * Created by jordanterry on 31/10/15.
 */
public class GameFragment extends Fragment implements GameView {

    @Bind(R.id.left_circle) CircleView mLeftCircle;
    @Bind(R.id.right_circle) CircleView mRightCircle;
    private GamePlayPresenter mGamePlayPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_layout, container);

        mGamePlayPresenter = new GamePlayPresenterImpl(this);
        ButterKnife.bind(this, view);
        return view;
    }


    @OnClick(R.id.left_circle) void onLeftClick() {
        mGamePlayPresenter.clickButton(GameColours.BLUE);
    }

    @OnClick(R.id.right_circle) void onRightClick() {
        mGamePlayPresenter.clickButton(GameColours.RED);
    }

    @Override
    public void onGameOver(int level) {

    }

    @Override
    public void displayLevel(int colour, int level) {

    }

    @Override
    public void displaySteps(List<Integer> colours) {

    }

}
