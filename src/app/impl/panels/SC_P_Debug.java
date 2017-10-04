package app.impl.panels;

import app.impl.sprites.SC_S_Alien;
import app.impl.sprites.SC_S_AlienSpawner;
import app.impl.sprites.SC_S_Alien_Thurster;
import app.impl.sprites.SC_S_Coin;
import app.impl.sprites.SC_S_Skybox;
import app.impl.sprites.SC_S_SpaceCraft;
import app.impl.sprites.SC_S_Star;
import app.impl.sprites.SC_S_Thurster;
import com.sun.javafx.geom.Vec2d;
import lowbob.LowBobPanel;
import lowbob.LowBobRuntime;
import lowbob.LowBobSprite;
import lowbob.UI.LowBobButtonUI;
import lowbob.UI.LowBobTextUI;
import lowbob.illumination.LowBobSimpleLight;
import lowbob.particles.LowBobParticle;
import lowbob.particles.LowBobParticleSystem;
import lowbob.particles.impl.LowBobDirectedBehavior;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

/**
 * Created by opuee on 24.04.17.
 */
public class SC_P_Debug extends LowBobPanel {
	
	private Random rnd;

    public SC_P_Debug() {
    	rnd = new Random();
    	
        // init panel
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(1400, 800));
        
        // set skybox
        //this.addSprite(new SC_S_Skybox(0, 0, 0, 0));

        // add spacecraft player
        SC_S_SpaceCraft player = new SC_S_SpaceCraft(40,400, 50, 50);
        this.addSprite(player);
        
        // add alien spawner
        //this.addSprite(new SC_S_AlienSpawner(0, 0, 0, 0));

        LowBobTextUI scscoreui = new LowBobTextUI(1300,760,100,40, "Visitor TT1 BRK", Color.BLUE, 40);
        //this.addUI(scscoreui);
        player.setScoreUI(scscoreui);
        //LowBobButtonUI btn = new LowBobButtonUI(100,100,50,50,"resources/pics/red.png");
        //this.addUI(btn);

        //LowBobTextUI text = new LowBobTextUI(10,10,600,40, "Visitor TT1 BRK", Color.ORANGE, 20);
        //text.setText("Space  Craft 701  alpha  build  0.32");
        //this.addUI(text);

        //LowBobSimpleLight light = new LowBobSimpleLight(0,0,10,10,1, 1,new Color(255, 115, 17));
        //this.addSprite(light);

        //LowBobDirectedBehavior pdb = new LowBobDirectedBehavior(new Vec2d(0,5));
        //LowBobParticleSystem ps = new LowBobParticleSystem(200,200, light, 80, pdb, 5);
        //ps.setVelocityVariant(3);
        //this.addSprite(ps);
    }
}
