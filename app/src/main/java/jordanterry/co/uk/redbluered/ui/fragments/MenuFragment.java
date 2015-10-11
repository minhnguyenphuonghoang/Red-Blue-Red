package jordanterry.co.uk.redbluered.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by jordanterry on 11/10/15.
 */
public class MenuFragment extends Fragment {

    public static final String TAG = MenuFragment.class.getSimpleName();

    public static MenuFragment newInstance() {
        MenuFragment fragment = new MenuFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }



}
