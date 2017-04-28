package lowbob.util;

/**
 * Created by opuee on 27.04.17.
 */
public class ValueAnimator {

    private double from, to, offset;
    private int frames, counter, iter;
    private boolean loop,revert, expired;

    public ValueAnimator(double from, double to, int frames) {

        this.from = from;
        this.to = to;
        this.frames = frames;

        loop = true;
        revert = true;
        expired = false;

        iter = 1;
        counter = -1;
        offset = (to - from) / frames;
    }

    public ValueAnimator(double from, double to, int frames, boolean loop, boolean revert) {

        this.from = from;
        this.to = to;
        this.frames = frames;

        this.loop = loop;
        this.revert = revert;
        expired = false;

        iter = 1;
        counter = -1;
        offset = (to - from) / frames;
    }

    public double next() {

        counter += iter;
        if(counter < 0 || counter > frames)
        {
            if(revert) {
                iter = -iter;
                counter += 2 * iter; // update counter to valid state
            }
            else if(loop) {
                counter = 0;
            }
            else {
                expired = true;
                return 0.0f;
            }
        }

        return from + counter * offset;
    }

    public boolean isExpired() {
        return this.expired;
    }

}
