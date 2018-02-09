package app.impl.panels;

import app.impl.sprites.*;
import lowbob.LowBobPanel;
import lowbob.UI.LowBobButtonUI;
import lowbob.UI.LowBobTextUI;
import lowbob.util.events.LowBobActionEvent;
import lowbob.util.events.LowBobMouseEvent;
import lowbob.util.events.PanelChangedEventArgs;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * Created by opuee on 24.04.17.
 */
public class SC_P_Debug extends LowBobPanel {

    private SC_M_GameOver gameover_menu;

    @Override
    public void setup() {
        // init panel
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(1400, 800));

        // set skybox
        this.addSprite(new SC_S_Skybox(0, 0, 0, 0));


        // add spacecraft player
        SC_S_SpaceCraft player = new SC_S_SpaceCraft(-60,400, 50, 50);
        this.addSprite(player);

        // add def watch
        SC_S_SHWatch watch = new SC_S_SHWatch(20,700,60,60);
        this.addSprite(watch);
        player.setDefWatch(watch);

        LowBobTextUI scscoreui = new LowBobTextUI(200,690,100,70, "Visitor TT1 BRK", Color.BLUE, 70);
        this.addUI(scscoreui);
        player.setScoreUI(scscoreui);



        // add alien spawner
        this.addSprite(new SC_S_AlienSpawner(0, 0, 0, 0));

        LowBobTextUI text = new LowBobTextUI(10,10,600,40, "Visitor TT1 BRK", Color.ORANGE, 20);
        text.setText("Space  Craft 701  alpha  build  0.32");
        this.addUI(text);

        // add MenuController
        this.gameover_menu = new SC_M_GameOver(0,0,0,0);
        this.addSprite(this.gameover_menu);
        this.addSprite(new SC_S_MenuController(0,0,0,0));

        watch.addOnGameOverListener(gameover_event);

    }

    private LowBobActionEvent gameover_event = new LowBobActionEvent() {
        @Override
        public void onAction(Object sender) {
            gameover_menu.show_menu();
        }
    };
}
