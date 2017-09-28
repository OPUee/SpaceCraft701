package lowbob.particles;

import com.sun.javafx.geom.Vec2d;
import lowbob.LowBobSprite;

/**
 * Created by opuee on 16.09.17.
 */
public class LowBobParticle {

    private Vec2d m_velo;
    private LowBobSprite m_sprite;

    public LowBobParticle(Vec2d velo, LowBobSprite sprite) {
        this.m_velo = velo;
        this.m_sprite = sprite;
    }

    public Vec2d getVelo() {
        return this.m_velo;
    }

    public LowBobSprite getSprite() {
        return this.m_sprite;
    }
}
