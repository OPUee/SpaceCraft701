package app.impl.sprites;

import com.sun.javafx.geom.Vec2d;
import lowbob.LowBobCollider;
import lowbob.LowBobRuntime;
import lowbob.LowBobSprite;
import lowbob.particles.LowBobParticle;
import lowbob.util.ImageCreator;

public class SC_S_Drone extends LowBobParticle {

    public SC_S_Drone(double x, double y, int width, int height, int z, Vec2d velo) {
        super(x, y, width, height, z, velo);

        this.colliders.add(new LowBobCollider(SC_S_Laser_Green.class));
    }

    @Override
    public void loadImage() {
        this.img = ImageCreator.create("resources/pics/drone.png");
    }

    @Override
    public void collide(LowBobSprite lbs) {
        if (lbs instanceof SC_S_Laser_Green) {
            this.m_lifetime = 10000;
            SC_S_Explosion exp = new SC_S_Explosion(this.abs_x, this.abs_y,0,0,3);
            LowBobRuntime.getInstance().addSprite(exp);
        }
    }
}
