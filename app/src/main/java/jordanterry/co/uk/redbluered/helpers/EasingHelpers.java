package jordanterry.co.uk.redbluered.helpers;

/**
 * Created by jordanterry on 25/10/15.
 */
public class EasingHelpers {

    /**
     * Linear easing helper
     * @param t The current time
     * @param b The start value
     * @param c The change in value
     * @param d The duration
     * @return
     */
    public static float linear(float t, float b, float c, long d) {
        t /= d;
        float p = c * t * t * t + b;
        if(p > c) return c;
        return p;
    }

}
