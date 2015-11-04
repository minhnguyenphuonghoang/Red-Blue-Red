package jordanterry.co.uk.redbluered.helpers;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;

/**
 * Created by jordanterry on 03/11/15.
 */
public class ResourceHelpers {


    public static Drawable getDrawable(Context context, int resource) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            return context.getDrawable(resource);
        } else {
            return context.getResources().getDrawable(resource);
        }
    }


    public static TransitionDrawable createTransitionDrawable(Context context, int... drawables) {
        Drawable backgrounds[] = new Drawable[drawables.length];
        for (int i = 0; i < drawables.length; i++) {
            backgrounds[i] = getDrawable(context, drawables[i]);
        }
        return new TransitionDrawable(backgrounds);
    }
}
