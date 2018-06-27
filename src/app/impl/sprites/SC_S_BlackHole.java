package app.impl.sprites;

import com.sun.javafx.geom.Vec2d;
import lowbob.LowBobSprite;
import lowbob.illumination.LowBobSimpleLight;
import lowbob.particles.LowBobParticle;
import lowbob.particles.LowBobParticleSystem;
import lowbob.particles.impl.LowBobDirectedBehavior;
import lowbob.util.ImageCreator;

import java.awt.*;

public class SC_S_BlackHole extends LowBobSprite {

    private LowBobParticleSystem ps;

    public SC_S_BlackHole(double x, double y, double width, double height, int z) {
        super(x, y, width, height, z);

        LowBobDirectedBehavior pdb = new LowBobDirectedBehavior(new Vec2d(-10,0));

        ps = new LowBobParticleSystem(20,0, 4, 1000, pdb, 10,1) {
            @Override
            protected LowBobParticle instanciate(double x, double y, int z, Vec2d velocity) {
                return new SC_S_Drone(x,y, 14, 14,z,velocity);
            }
        };

        ps.Start();
        this.addSprite(ps);
    }

    @Override
    public void loadImage() {
        this.img = ImageCreator.create("resources/pics/blackhole.png");
    }
}
