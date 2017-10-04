package lowbob.particles;

import com.sun.javafx.geom.Vec2d;
import lowbob.LowBobSprite;
import lowbob.LowBobRuntime;

import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Created by opuee on 16.09.17.
 */
public class LowBobParticleSystem extends LowBobSprite{

    private BufferedImage m_sprite;
    private Random m_rnd;
    private LowBobParticleBehavior m_particlebehavior;
    private List<LowBobParticle> m_particles;
    private int m_spawntime;
    private int m_maxlifetime;
    private int m_spawncounter;
    private float m_velovariant;

    public LowBobParticleSystem(double x, double y, LowBobSprite sprite, int maxlifetime,
                                LowBobParticleBehavior particlebehavior, int spawntime) {
        super(x, y, 0, 0);

        this.m_rnd = new Random();
        this.m_particles = new ArrayList<>();
        this.m_spawncounter = 0;
        this.m_velovariant = 2;

        this.m_particlebehavior = particlebehavior;
        this.m_sprite = (BufferedImage) sprite.getImage();
        this.m_maxlifetime = maxlifetime;
        this.m_spawntime = spawntime;
    }

    @Override
    public void loadImage() {
        // create empty image
        this.img = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public void move() {

        // create new particle
        if(m_spawncounter >= this.m_spawntime) {
            createParticle();
            this.m_spawncounter = 0;
        } else {
            this.m_spawncounter++;
        }

        // update listed particles
        for (int i = 0; i < this.m_particles.size(); i++) {
            LowBobParticle sprite = this.m_particles.get(i);
            int lifetime = sprite.getLifeTime();

            if (lifetime >= m_maxlifetime) {
                // remove sprite from system
                this.m_particles.remove(sprite);
                LowBobRuntime.getInstance().removeSprite(sprite);
            } else {
                Vec2d curVelo = m_particlebehavior.update(sprite.getVelo(), ((float)lifetime / (float)m_maxlifetime));

                curVelo.x = curVelo.x + sprite.getPosX();
                curVelo.y = curVelo.y + sprite.getPosY();

                sprite.setPos(curVelo);
            }
        }
    }

    private void createParticle() {
        Vec2d velocity = new Vec2d();

        velocity.x = -this.m_velovariant + this.m_rnd.nextFloat() * (this.m_velovariant * 2);
        velocity.y = -this.m_velovariant + this.m_rnd.nextFloat() * (this.m_velovariant * 2);

        LowBobParticle particle = new LowBobParticle(velocity, m_sprite);

        // add particle to system
        this.m_particles.add(particle);
        LowBobRuntime.getInstance().addSprite(particle);

    }

    public void setVelocityVariant(float value) {
        this.m_velovariant = value;
    }
}
