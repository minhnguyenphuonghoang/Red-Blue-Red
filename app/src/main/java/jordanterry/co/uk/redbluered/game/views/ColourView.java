package jordanterry.co.uk.redbluered.game.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jordanterry on 30/10/15.
 */
public class ColourView extends View {
    public ColourView(Context context) {
        super(context);
    }

    public ColourView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ColourView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ColourView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
