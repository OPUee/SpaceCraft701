package app.impl.sprites;

import lowbob.LowBobCollider;
import lowbob.LowBobRuntime;
import lowbob.LowBobSprite;
import lowbob.util.ImageAnimator;
import lowbob.util.ImageCreator;

import java.util.Random;

/**
 * Created by opuee on 10.10.17.
 */
public class SC_S_Boomer extends LowBobSprite {

    private static int SPEED = -2;
    private static int SLOPE = 1;

    private static int HP = 10;

    private ImageAnimator ai_booomer;
    private int counter, offset;
    private Random rnd;

    public SC_S_Boomer(double x, double y, double width, double height, int z) {
        super(x, y, width, height, z);

        rnd = new Random();

        this.counter = 0;
        this.offset = rnd.nextInt(20);

        this.colliders.add(new LowBobCollider(SC_S_Laser_Green.class));
    }

    @Override
    public void loadImage() {
        this.ai_booomer = new ImageAnimator(ImageCreator.create("resources/pics/boomer.png"),36,true,true);
        this.img = ai_booomer.next();
    }

    @Override
    public void move() {

        this.counter++;

        // animation
        if (this.counter % 10 == 0) {
            this.img = this.ai_booomer.next();
        }

        // movement
        this.x += SPEED;
        this.y += SLOPE;

        if (this.counter % 100 == 0) {
            SLOPE = -SLOPE;
            shoot();
        }

        // reset counter
        if (this.counter > 10000) {
            this.counter = 0;
        }
    }

    @Override
    public void collide(LowBobSprite lbs) {
        LowBobRuntime.getInstance().removeSprite(lbs);

        this.HP--;

        if (this.HP <= 0) {
            LowBobRuntime.getInstance().removeSprite(this);
            LowBobRuntime.getInstance().addSprite(new SC_S_Explosion(this.getAbsX(), this.getAbsY(), 0,0,3));
        }
    }

    private void shoot() {
        LowBobRuntime runtime = LowBobRuntime.getInstance();
        LowBobRuntime.getInstance().addSprite(new SC_S_EMP(x,y + 10,27,27, 3, 0));
        LowBobRuntime.getInstance().addSprite(new SC_S_EMP(x + 10,y + 10,27,27, 3, 0.5));
        LowBobRuntime.getInstance().addSprite(new SC_S_EMP(x + 10,y + 10,27,27, 3, -0.5));
        LowBobRuntime.getInstance().addSprite(new SC_S_EMP(x + 20,y + 10,27,27, 3, 1));
        LowBobRuntime.getInstance().addSprite(new SC_S_EMP(x + 20,y + 10,27,27, 3, -1));
    }
}
