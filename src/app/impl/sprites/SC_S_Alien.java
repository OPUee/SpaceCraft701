package app.impl.sprites;

import lowbob.LowBobCollider;
import lowbob.LowBobRuntime;
import lowbob.LowBobSprite;
import lowbob.util.ImageCreator;

import java.awt.event.KeyEvent;

/**
 * Created by opuee on 27.04.17.
 */
public class SC_S_Alien extends LowBobSprite {


    public SC_S_Alien(double x, double y, double width, double height) {
        super(x, y, width, height);

        this.colliders.add(new LowBobCollider(SC_S_Missile.class));
        this.colliders.add(new LowBobCollider(SC_S_laser.class));
    }

    @Override
    public void loadImage() {
    	this.img = ImageCreator.create("src/app/resources/alien.png");
    }

    @Override
    public void move() {
        this.x -= 2;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void collide(LowBobSprite lbs) {
        LowBobRuntime runtime = LowBobRuntime.getInstance();
        runtime.removeSprite(this);
        runtime.removeSprite(lbs);
    }
}
