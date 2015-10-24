package jordanterry.co.uk.redbluered.game;

import android.graphics.Color;

/**
 * Created by jordanterry on 13/10/15.
 */
public class GameColours {

    public static final int RED = Color.parseColor("#F44336");
    public static final int LIGHT_RED = Color.parseColor("#FFEBEE");
    public static final int DARK_RED = Color.parseColor("#B71C1C");
    public static final int BLUE = Color.parseColor("#2196F8");
    public static final int LIGHT_BLUE = Color.parseColor("#E3F2FD");
    public static final int DARK_BLUE = Color.parseColor("#0D47A1");
    public static final int GREY = Color.parseColor("#ebebeb");

    public static int randomColour() {
        if(Math.random() < .5) {
            return BLUE;
        }
        return RED;
    }

}
