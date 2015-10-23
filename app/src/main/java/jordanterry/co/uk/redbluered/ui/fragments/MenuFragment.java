package jordanterry.co.uk.redbluered.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jordanterry.co.uk.redbluered.R;
import jordanterry.co.uk.redbluered.interfaces.OnPlayClicked;
import jordanterry.co.uk.redbluered.ui.presenters.MenuPresenter;
import jordanterry.co.uk.redbluered.ui.presenters.MenuPresenterImpl;
import jordanterry.co.uk.redbluered.ui.views.MenuView;

/**
 * Created by jordanterry on 11/10/15.
 */
public class MenuFragment extends Fragment implements MenuView {

    public static final String TAG = MenuFragment.class.getSimpleName();

    @Bind(R.id.play_button) Button mPlayButton;

    private OnPlayClicked mOnPlayClicked;

    private MenuPresenter mMenuPresenter;

    public static MenuFragment newInstance() {
        MenuFragment fragment = new MenuFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMenuPresenter = new MenuPresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnPlayClicked = (OnPlayClicked) context;
        } catch(ClassCastException ce) {
            throw new ClassCastException("Context must implement OnPlayClicked.");
        }
    }



    @OnClick(R.id.play_button)
    public void playGame() {
        mOnPlayClicked.onPlayClicked();
    }
}
