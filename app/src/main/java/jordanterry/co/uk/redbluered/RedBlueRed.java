package jordanterry.co.uk.redbluered;

import android.app.Application;

/**
 * Created by jordanterry on 13/10/15.
 */
public class RedBlueRed extends Application {


    private ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        mComponent.injectApplication(this);

    }

    public ApplicationComponent getComponent() {
        return mComponent;
    }
}
