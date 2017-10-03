package lowbob.particles.impl;

import com.sun.javafx.geom.Vec2d;
import lowbob.particles.LowBobParticle;
import lowbob.particles.LowBobParticleBehavior;

import java.awt.*;

/**
 * Created by opuee on 16.09.17.
 */
public class LowBobDirectedBehavior implements LowBobParticleBehavior {

    private Vec2d m_direction;

    public LowBobDirectedBehavior(Vec2d direction) {
        this.m_direction = direction;
    }

    @Override
    public Vec2d update(Vec2d From, float offset) {
        Vec2d result = new Vec2d();

        result.x = From.x + ((this.m_direction.x - From.x) * offset);
        result.y = From.y + ((this.m_direction.y - From.y) * offset);

        return result;
    }
}
