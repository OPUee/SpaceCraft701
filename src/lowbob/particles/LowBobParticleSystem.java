package lowbob.particles;

import lowbob.LowBobPanel;
import lowbob.LowBobSprite;
import lowbob.illumination.LowBobSimpleLight;

import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Created by opuee on 16.09.17.
 */
public class LowBobParticleSystem extends LowBobSprite{

    private List<LowBobParticle> m_particles;

    private double m_spawnrate;
    private double m_velocity;


    private int spawncounter;

    public LowBobParticleSystem(double x, double y) {
        super(x, y, 0, 0);

        this.m_particles = new ArrayList<>();
    }

    @Override
    public void loadImage() {
        this.img = new BufferedImage(0,0,BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public void move() {

    }
}
