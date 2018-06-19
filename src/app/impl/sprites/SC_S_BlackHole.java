package app.impl.sprites;

import com.sun.javafx.geom.Vec2d;
import lowbob.LowBobSprite;
import lowbob.illumination.LowBobSimpleLight;
import lowbob.particles.LowBobParticleSystem;
import lowbob.particles.impl.LowBobDirectedBehavior;
import lowbob.util.ImageCreator;

import java.awt.*;

public class SC_S_BlackHole extends LowBobSprite {

    private LowBobParticleSystem ps;

    public SC_S_BlackHole(double x, double y, double width, double height, int z) {
        super(x, y, width, height, z);

        // add particlesystem
        LowBobSimpleLight light = new LowBobSimpleLight(0,0,13,13, 3,0.7,1,new Color(255, 133, 6));
        LowBobDirectedBehavior pdb = new LowBobDirectedBehavior(new Vec2d(-10,0));
        ps = new LowBobParticleSystem(-8,17, 3, light,20,pdb,5, 1);
        ps.Start();
        this.addSprite(ps);
    }

    @Override
    public void loadImage() {
        this.img = ImageCreator.create("resources/pics/blackhole.png");
    }
}
