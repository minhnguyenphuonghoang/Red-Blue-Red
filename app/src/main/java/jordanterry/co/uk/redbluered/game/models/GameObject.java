package jordanterry.co.uk.redbluered.game.models;

import android.graphics.Canvas;

/**
 * Created by jordanterry on 20/10/15.
 */
public interface GameObject {

    void setBackground(int color);

    void draw(Canvas canvas);

    void setVisibility(boolean visible);

    boolean isVisible();

    boolean isTouch(float x, float y);


}
