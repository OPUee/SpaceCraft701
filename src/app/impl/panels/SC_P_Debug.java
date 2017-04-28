package app.impl.panels;

import app.impl.sprites.SC_S_Alien;
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
        SC_S_SpaceCraft player = new SC_S_SpaceCraft(40,120, 50, 30);
        player.addSprite(new SC_S_Thurster(-16, 18, 15, 7));
        this.addSprite(player);
        
        // add alien enemy
        SC_S_Alien alien = new SC_S_Alien(1500, 400, 50, 26);
        alien.addSprite(new SC_S_Alien_Thurster(45, 8, 20, 14, alien));
        this.addSprite(alien);
    }
}
