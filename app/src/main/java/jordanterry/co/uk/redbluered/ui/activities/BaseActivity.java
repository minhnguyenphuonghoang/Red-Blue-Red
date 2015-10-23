package jordanterry.co.uk.redbluered.ui.activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * <p>The BaseActivity will provide methods that all other Activites will need to use.</p>
 */
public class BaseActivity extends AppCompatActivity {


    /**
     * <p>Add a provided Fragment to the containerId using the tag.
     * The {@link android.app.FragmentTransaction} will be added to the back stack using the tag.</p>
     * @param contentId
     * @param fragment
     * @param tag
     */
    protected void addFragment(int contentId, Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction()
                .add(contentId, fragment, tag)
                .addToBackStack(tag)
                .commit();
    }


    /**
     * <p>Add a provided Fragment to the containerId using the tag.
     * The {@link android.app.FragmentTransaction} will not be added to the back stack.</p>
     * @param contentId
     * @param fragment
     * @param tag
     */
    protected void addFragmentWithoutBackStack(int contentId, Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction()
                .add(contentId, fragment, tag)
                .commit();
    }

}
