package jordanterry.co.uk.redbluered;

import dagger.Component;

/**
 * Created by jordanterry on 14/10/15.
 */
@Component(
        modules = ApplicationModule.class
)
public interface ApplicationComponent {
    RBRApplication injectApplication(RBRApplication redBlueRed);
}
