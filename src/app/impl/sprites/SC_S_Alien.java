package app.impl.sprites;

import com.sun.javafx.geom.Vec2d;
import lowbob.LowBobCollider;
import lowbob.LowBobRuntime;
import lowbob.LowBobSprite;
import lowbob.illumination.LowBobSimpleLight;
import lowbob.particles.LowBobParticleSystem;
import lowbob.particles.impl.LowBobDirectedBehavior;
import lowbob.util.ImageCreator;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by opuee on 27.04.17.
 */
public class SC_S_Alien extends LowBobSprite {

    private int m_health;
    private LowBobParticleSystem sparks;

    public SC_S_Alien(double x, double y, double width, double height) {
        super(x, y, width, height);

        this.colliders.add(new LowBobCollider(SC_S_Missile.class));
        this.colliders.add(new LowBobCollider(SC_S_Laser.class));
        
        this.addSprite(new SC_S_Alien_Thurster(45, 8, 20, 14));

        this.m_health = 5;

        // initialize particle system for spark bursts
        LowBobSimpleLight spark = new LowBobSimpleLight(0,0,10,10,1,1,new Color(0, 81,0xff));
        LowBobDirectedBehavior pdb = new LowBobDirectedBehavior(new Vec2d(0,10));
        sparks = new LowBobParticleSystem(0,0,spark,20,pdb,-1,5);
        this.addSprite(sparks);
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
        runtime.removeSprite(lbs);

        if(this.m_health <= 0) {
            // die!!
            runtime.addSprite(new SC_S_Explosion(this.x + 10, this.y + 10, 30, 30));
            runtime.addSprite(new SC_S_Coin(this.x + 10, this.y + 10, 14, 14));
            runtime.removeSprite(this);
        } else {
            this.sparks.Start();
            this.m_health--;
        }
    }
}
