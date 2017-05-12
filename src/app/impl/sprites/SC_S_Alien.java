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
        this.colliders.add(new LowBobCollider(SC_S_Laser.class));
        
        this.addSprite(new SC_S_Alien_Thurster(45, 8, 20, 14));
    }

    @Override
    public void loadImage() {
    	this.img = ImageCreator.create("resources/pics/alien.png");
    }

    @Override
    public void move() {
        this.x -= 7;
        if(this.x < -100)
        	LowBobRuntime.getInstance().removeSprite(this);
    }

    @Override
    public void collide(LowBobSprite lbs) {
        LowBobRuntime runtime = LowBobRuntime.getInstance();
        runtime.removeSprite(this);
        runtime.removeSprite(lbs);
        runtime.addSprite(new SC_S_Explosion(this.x + 10, this.y + 10, 30, 30));
        runtime.addSprite(new SC_S_Coin(this.x + 10, this.y + 10, 14, 14));
    }
}
