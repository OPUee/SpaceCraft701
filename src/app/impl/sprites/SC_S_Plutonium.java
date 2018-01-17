package app.impl.sprites;

import lowbob.LowBobSprite;
import lowbob.illumination.LowBobSimpleLight;
import lowbob.util.ImageAnimator;
import lowbob.util.ImageCreator;

import java.awt.*;

/**
 * Created by opuee on 11.10.17.
 */
public class SC_S_Plutonium extends LowBobSprite {

    private ImageAnimator ai;
    private int counter;

    private LowBobSimpleLight light;

    public SC_S_Plutonium(double x, double y, double width, double height) {
        super(x, y, width, height);

        this.light = new LowBobSimpleLight(-26,-24,80,80,0.5,0.9,new Color(13,0xff, 11));
        this.counter = 0;

        this.addSprite(this.light);
    }

    @Override
    public void loadImage() {
        this.ai = new ImageAnimator(ImageCreator.create("resources/pics/plutonium.png"), 30, true, true);
        this.img = this.ai.next();
    }

    @Override
    public void move() {
        if(counter >= 15) {
            this.img = this.ai.next();
            counter = 0;
        } else {
            counter++;
        }

    }
}
