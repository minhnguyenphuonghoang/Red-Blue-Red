package jordanterry.co.uk.redbluered;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jordanterry on 14/10/15.
 */
@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides @Singleton public Application providesApplication() {
        return mApplication;
    }

}
