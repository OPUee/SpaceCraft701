package app.impl.sprites;

import lowbob.LowBobCollider;
import lowbob.LowBobRuntime;
import lowbob.LowBobSprite;
import lowbob.util.ImageCreator;
import lowbob.util.ValueAnimator;

import java.awt.event.KeyEvent;

/**
 * Created by opuee on 28.04.17.
 */
public class SC_S_Star extends LowBobSprite{

    private boolean isHitten;
    private ValueAnimator va_idle, va_hitten;

    public SC_S_Star(double x, double y, double width, double height) {
        super(x, y, width, height);

        this.colliders.add(new LowBobCollider(SC_S_Laser.class));

        isHitten = false;
        va_idle = new ValueAnimator(y, y + 20, 20);
    }

    @Override
    public void loadImage() {
    	this.img = ImageCreator.create("src/app/resources/star.png");
    }

    @Override
    public void move() {
        if(isHitten) {
            if(va_hitten.isExpired())
                LowBobRuntime.getInstance().removeSprite(this);
            else
                y = va_hitten.next();

        } else {
            y = va_idle.next();
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void collide(LowBobSprite lbs) {
        if(!isHitten) {
            isHitten = true;
            va_hitten = new ValueAnimator(y, y - 100, 50, false, false);
        }
    }
}
