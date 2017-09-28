package lowbob.particles;

import com.sun.javafx.geom.Vec2d;

/**
 * Created by opuee on 16.09.17.
 */
public interface LowBobParticleBehavior {

    public Vec2d update(LowBobParticle particle);
}
