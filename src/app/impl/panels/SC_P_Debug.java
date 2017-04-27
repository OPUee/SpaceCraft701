package app.impl.panels;

import app.impl.sprites.SC_S_Alien;
import app.impl.sprites.SC_S_SpaceCraft;
import lowbob.LowBobPanel;
import java.awt.*;

/**
 * Created by opuee on 24.04.17.
 */
public class SC_P_Debug extends LowBobPanel {

    public SC_P_Debug() {
        // init panel
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(1400, 800));

        this.addSprite(new SC_S_SpaceCraft(40,120, 50, 30));
        this.addSprite(new SC_S_Alien(800,400, 50, 26));
    }
}
