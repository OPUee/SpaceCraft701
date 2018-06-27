package lowbob.particles;

import com.sun.javafx.geom.Vec2d;
import lowbob.LowBobSprite;

import java.awt.image.BufferedImage;

/**
 * Created by opuee on 16.09.17.
 */
public abstract class LowBobParticle extends LowBobSprite {

    protected Vec2d m_velo;
    protected int m_lifetime;

    public LowBobParticle(double x, double y, int width, int height, int z, Vec2d velo) {
        super(x,y,width,height, z);
        this.m_velo = velo;
        this.m_lifetime = 0;
    }

    public void setPos(Vec2d pos) {
        this.x = pos.x;
        this.y = pos.y;

        this.m_lifetime++;
    }

    public int getLifeTime() {
        return this.m_lifetime;
    }

    public Vec2d getVelo() {
        return this.m_velo;
    }

}
