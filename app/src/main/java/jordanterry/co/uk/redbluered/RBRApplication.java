package jordanterry.co.uk.redbluered;

import android.app.Application;

/**
 * Created by jordanterry on 13/10/15.
 */
public class RBRApplication extends Application {


    private static RBRApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static RBRApplication getInstance() {
        return mInstance;
    }

}
