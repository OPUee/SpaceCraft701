package lowbob.particles;

import com.sun.javafx.geom.Vec2d;
import lowbob.LowBobPanel;
import lowbob.LowBobSprite;
import lowbob.illumination.LowBobSimpleLight;

import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Created by opuee on 16.09.17.
 */
public class LowBobParticleSystem extends LowBobSprite{

    private BufferedImage m_sprite;
    private Random m_rnd;
    private LowBobParticleBehavior m_particlebehavior;
    private int m_spawntime;
    private int m_maxlifetime;

    private int m_spawncounter;

    public LowBobParticleSystem(double x, double y, BufferedImage sprite, int maxlifetime,
                                LowBobParticleBehavior particlebehavior, int spawntime) {
        super(x, y, 0, 0);

        this.m_rnd = new Random();
        this.m_spawncounter = 0;

        this.m_particlebehavior = particlebehavior;
        this.m_sprite = sprite;
        this.m_maxlifetime = maxlifetime;
        this.m_spawntime = spawntime;
    }

    @Override
    public void loadImage() {
        // create empty image
        this.img = new BufferedImage(0,0,BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public void move() {
        if(m_spawncounter >= this.m_spawntime) {
            for (int i = 0; i < this.sprites.size(); i++) {
                LowBobParticle sprite = (LowBobParticle) this.sprites.get(i);
                int lifetime = sprite.getLifeTime();

                if (lifetime >= m_maxlifetime)
                    this.removeSprite(sprite);
                else {
                    Vec2d curVelo = m_particlebehavior.update(sprite.getVelo(), (lifetime / m_maxlifetime));

                    curVelo.x += sprite.getPosX();
                    curVelo.y += sprite.getPosY();

                    sprite.setPos(curVelo);
                }
            }

            this.m_spawncounter = 0;
        } else {
            this.m_spawncounter++;
        }
    }

    private void createParticle() {
        Vec2d velocity = new Vec2d(m_rnd.nextFloat(), m_rnd.nextFloat());
        LowBobParticle particle = new LowBobParticle(velocity, m_sprite);

        this.addSprite(particle);

    }
}
