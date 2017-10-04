package lowbob.particles;

import com.sun.javafx.geom.Vec2d;
import lowbob.LowBobSprite;

import java.awt.image.BufferedImage;

/**
 * Created by opuee on 16.09.17.
 */
public class LowBobParticle extends LowBobSprite {

    private Vec2d m_velo;
    private int m_lifetime;

    public LowBobParticle(double x, double y, Vec2d velo, BufferedImage img) {
        super(x,y,0,0);
        this.m_velo = velo;
        this.m_lifetime = 0;

        this.img = img;
    }

    @Override
    public void loadImage() {
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
