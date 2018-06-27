package app.impl.sprites;

import com.sun.javafx.geom.Vec2d;
import lowbob.LowBobCollider;
import lowbob.LowBobRuntime;
import lowbob.LowBobSprite;
import lowbob.illumination.LowBobSimpleLight;
import lowbob.particles.LowBobParticle;
import lowbob.particles.LowBobParticleSystem;
import lowbob.particles.impl.LowBobDirectedBehavior;
import lowbob.util.ImageCreator;

import java.awt.*;

/**
 * Created by opuee on 27.04.17.
 */
public class SC_S_Alien extends LowBobSprite {

    private static int SHOOTING_RATE = 80;
    private static int SHAKING_NUM = 200;
    private static int SHAKING_INT = 50;
    private static int SPEED = 6;

    private int health;
    private LowBobParticleSystem sparks;
    private int shooting_counter;

    private int shaking_counter;
    private double shacking_values[];
    private double org_y;

    public SC_S_Alien(double x, double y, double width, double height, int z) {
        super(x, y, width, height, z);

        this.colliders.add(new LowBobCollider(SC_S_Missile.class));
        this.colliders.add(new LowBobCollider(SC_S_Laser.class));
        
        this.addSprite(new SC_S_Alien_Thurster(45, 8, 20, 14, 1));

        this.health = 3;
        this.shooting_counter = 0;
        this.shaking_counter = 0;
        this.org_y = y;

        //create shaking values
        this.shacking_values = new double[SHAKING_NUM];
        double offset = 2 * Math.PI / SHAKING_NUM;
        for (int i = 0; i < SHAKING_NUM; i++) {
            shacking_values[i] = Math.cos(i * offset) * SHAKING_INT;
        }

        // initialize particle system for spark bursts
        LowBobDirectedBehavior pdb = new LowBobDirectedBehavior(new Vec2d(0,10));

        sparks = new LowBobParticleSystem(0,0, 0,30,pdb,-1,7) {
            @Override
            protected LowBobParticle instanciate(double x, double y, int z, Vec2d velocity) {
                return new LowBobParticle(x,y,0,0,z,velocity) {
                    @Override
                    public void loadImage() {
                        this.img = new LowBobSimpleLight(0,0,20,20, 0,1.1,1.6,new Color(0, 81,0xff)).getImage();
                    }
                };
            }
        };

        this.addSprite(sparks);
    }

    @Override
    public void loadImage() {
    	this.img = ImageCreator.create("resources/pics/alien.png");
    }

    @Override
    public void move() {
        this.shooting_counter++;

        this.x -= SPEED;
        // despawn
        if(this.x < -50)
        	LowBobRuntime.getInstance().removeSprite(this);

        if(this.shooting_counter >= SHOOTING_RATE){
            this.shooting_counter = 0;
            shoot();
        }

        // shaking
        this.shaking_counter++;
        this.shaking_counter = this.shaking_counter % SHAKING_NUM;
        this.y = this.org_y + this.shacking_values[this.shaking_counter];


    }

    @Override
    public void collide(LowBobSprite lbs) {
        LowBobRuntime runtime = LowBobRuntime.getInstance();
        runtime.removeSprite(lbs);

        if(this.health <= 0) {
            // die!!
            runtime.addSprite(new SC_S_Plutonium(this.x + 10, this.y, 30, 32, 3));
            runtime.addSprite(new SC_S_Explosion(this.x + 10, this.y + 10, 30, 30, 3));
            this.sparks.dispatch();
            runtime.removeSprite(this);
        } else {
            this.sparks.Start();
            this.health--;
        }
    }

    private void shoot(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    LowBobRuntime runtime = LowBobRuntime.getInstance();
                    LowBobRuntime.getInstance().addSprite(new SC_S_Alien_Laser(x,y + 10,8,2, 3));
                    Thread.sleep(160);
                    LowBobRuntime.getInstance().addSprite(new SC_S_Alien_Laser(x,y + 10,8,2, 3));
                    Thread.sleep(160);
                    LowBobRuntime.getInstance().addSprite(new SC_S_Alien_Laser(x,y + 10,8,2, 3));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
