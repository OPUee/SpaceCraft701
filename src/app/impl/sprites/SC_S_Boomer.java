package app.impl.sprites;

import lowbob.LowBobSprite;
import lowbob.util.ImageAnimator;
import lowbob.util.ImageCreator;

/**
 * Created by opuee on 10.10.17.
 */
public class SC_S_Boomer extends LowBobSprite {

    private ImageAnimator m_imageAnimator;
    private int counter;

    public SC_S_Boomer(double x, double y, double width, double height) {
        super(x, y, width, height);

        counter = 0;
    }

    @Override
    public void loadImage() {
        this.m_imageAnimator = new ImageAnimator(ImageCreator.create("resources/pics/boomer.png"),36,true,true);
        this.img = m_imageAnimator.next();
    }

    @Override
    public void move() {
        if (counter >= 10) {
            this.img = this.m_imageAnimator.next();
            counter = 0;
        } else {
            counter++;
        }
    }
}
