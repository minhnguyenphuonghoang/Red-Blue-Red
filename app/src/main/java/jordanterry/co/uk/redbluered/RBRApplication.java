package jordanterry.co.uk.redbluered;

import android.app.Application;

/**
 * <p>The main Application classs. Contains a static instance of itself.</p>
 */
public class RBRApplication extends Application {


    private static RBRApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    /**
     * <p>Get the current instance of the RBRApplication.</p>
     * @return
     */
    public static RBRApplication getInstance() {
        return mInstance;
    }

}
