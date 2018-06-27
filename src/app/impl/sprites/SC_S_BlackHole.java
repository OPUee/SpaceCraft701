package app.impl.sprites;

import com.sun.javafx.geom.Vec2d;
import lowbob.LowBobRuntime;
import lowbob.LowBobSprite;
import lowbob.particles.LowBobParticle;
import lowbob.particles.LowBobParticleSystem;
import lowbob.particles.impl.LowBobDirectedBehavior;
import lowbob.util.ImageCreator;

public class SC_S_BlackHole extends LowBobSprite {

    private LowBobParticleSystem m_ps;
    private int m_cnt;

    public SC_S_BlackHole(double x, double y, double width, double height, int z) {
        super(x, y, width, height, z);

        LowBobDirectedBehavior pdb = new LowBobDirectedBehavior(new Vec2d(-200,0));

        m_ps = new LowBobParticleSystem(20,0, 4, 1000, pdb, 10,1) {
            @Override
            protected LowBobParticle instanciate(double x, double y, int z, Vec2d velocity) {
                return new SC_S_Drone(x,y, 14, 14,z,velocity);
            }
        };

        m_ps.Start();
        this.addSprite(m_ps);


        this.m_cnt = 0;
    }

    @Override
    public void loadImage() {
        this.img = ImageCreator.create("resources/pics/blackhole.png");
    }

    @Override
    public void move() {
        if (this.m_cnt >= 100) {
            this.m_ps.Stop();
            this.m_ps.dispatch();
            LowBobRuntime.getInstance().removeSprite(this);
        }
        this.m_cnt++;
    }

}
