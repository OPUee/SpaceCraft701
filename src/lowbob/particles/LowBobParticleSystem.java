package lowbob.particles;

import com.sun.javafx.geom.Vec2d;
import lowbob.LowBobSprite;
import lowbob.LowBobRuntime;

import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Created by opuee on 16.09.17.
 */
public abstract class LowBobParticleSystem extends LowBobSprite{

    private BufferedImage m_sprite;
    private Random m_rnd;
    private LowBobParticleBehavior m_particlebehavior;
    private List<LowBobParticle> m_particles;
    private int m_spawntime;
    private int m_maxlifetime;
    private int m_spawncounter;
    private float m_velovariant;
    private int m_spawncount;

    private boolean isRunning;
    private boolean isDispatched;

    public LowBobParticleSystem(double x, double y, int z, int maxlifetime,
                                LowBobParticleBehavior particlebehavior, int spawntime, int spawncount) {
        super(x, y, 0, 0, z);

        this.m_rnd = new Random();
        this.m_particles = new ArrayList<>();
        this.m_spawncounter = 0;
        this.m_velovariant = 2;
        this.isRunning = false;
        this.isDispatched = false;

        this.m_particlebehavior = particlebehavior;
        this.m_maxlifetime = maxlifetime;
        this.m_spawntime = spawntime;
        this.m_spawncount = spawncount;
    }

    @Override
    public void loadImage() {
        // create empty image
        this.img = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public void move() {

        //special case for single particle drop
        if(this.m_spawntime < 0 && this.isRunning) {
            createParticle();
            this.isRunning = false;
        }

        // create new particle
        if(isRunning) {
            if (m_spawncounter >= this.m_spawntime) {
                createParticle();
                this.m_spawncounter = 0;
            } else {
                this.m_spawncounter++;
            }
        }

        // update listed particles
        for (int i = 0; i < this.m_particles.size(); i++) {
            LowBobParticle sprite = this.m_particles.get(i);
            int lifetime = sprite.getLifeTime();

            if (lifetime >= m_maxlifetime) {
                // remove sprite fro<m system
                this.m_particles.remove(sprite);
                LowBobRuntime.getInstance().removeSprite(sprite);
            } else {
                Vec2d curVelo = m_particlebehavior.update(sprite.getVelo(), ((float)lifetime / (float)m_maxlifetime));

                curVelo.x += sprite.getPosX();
                curVelo.y += sprite.getPosY();

                sprite.setPos(curVelo);
            }
        }

        // delete itself if dispatched and finished
        if(this.isDispatched && (this.m_particles.size() == 0))
            LowBobRuntime.getInstance().removeSprite(this);

    }

    private void createParticle() {
        for(int i = 0; i < this.m_spawncount; i++) {
            Vec2d velocity = new Vec2d();

            velocity.x = -this.m_velovariant + this.m_rnd.nextFloat() * (this.m_velovariant * 2);
            velocity.y = -this.m_velovariant + this.m_rnd.nextFloat() * (this.m_velovariant * 2);

            LowBobParticle particle = instanciate(this.abs_x, this.abs_y, 0, velocity);

            // add particle to system
            this.m_particles.add(particle);
            LowBobRuntime.getInstance().addSprite(particle);
        }

    }

    protected abstract LowBobParticle instanciate(double x, double y, int z, Vec2d velocity);

    public void setVelocityVariant(float value) {
        this.m_velovariant = value;
    }

    public void dispatch() {
        this.Stop();
        this.isDispatched = true;

        LowBobRuntime.getInstance().addSprite(this);
    }

    public void Start() {
        if(!this.isDispatched)
            this.isRunning = true;
    }
    public void Stop() {
        this.isRunning = false;
    }
}
