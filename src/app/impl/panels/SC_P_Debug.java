package app.impl.panels;

import app.impl.sprites.SC_S_Alien;
import app.impl.sprites.SC_S_AlienSpawner;
import app.impl.sprites.SC_S_Alien_Thurster;
import app.impl.sprites.SC_S_Coin;
import app.impl.sprites.SC_S_Skybox;
import app.impl.sprites.SC_S_SpaceCraft;
import app.impl.sprites.SC_S_Star;
import app.impl.sprites.SC_S_Thurster;
import lowbob.LowBobPanel;
import java.awt.*;
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
        this.addSprite(new SC_S_Skybox(0, 0, 0, 0));

        // add spacecraft player
        this.addSprite(new SC_S_SpaceCraft(40,400, 50, 50));
        
        // add alien spawner
        this.addSprite(new SC_S_AlienSpawner(0, 0, 0, 0));
    }
}
