package app.impl.panels;

import app.impl.controller.SC_C_BasicStoryLine;
import app.impl.menu.SC_M_GameOver;
import app.impl.menu.SC_M_MenuController;
import app.impl.sprites.*;
import lowbob.LowBobPanel;
import lowbob.LowBobRuntime;
import lowbob.UI.LowBobTextUI;
import lowbob.util.events.LowBobActionEvent;

import java.awt.*;

/**
 * Created by opuee on 24.04.17.
 */
public class SC_P_Debug extends LowBobPanel {

    private SC_M_GameOver gameover_menu;
    private SC_S_SpaceCraft player;
    private SC_S_SHWatch watch;

    @Override
    public void setup() {
        // init panel
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(1400, 800));

        // set skybox
        this.addSprite(new SC_S_Skybox(0, 0, 0, 0, 0));


        // add spacecraft player
        this.player = new SC_S_SpaceCraft(-60,400, 50, 50, 3);
        this.addSprite(this.player);

        // add def watch
        this.watch = new SC_S_SHWatch(20,700,60,60, 10);
        this.addSprite(this.watch);
        this.player.setDefWatch(this.watch);

        LowBobTextUI scscoreui = new LowBobTextUI(200,690,100,70, 0, "Visitor TT1 BRK", Color.BLUE, 70);
        this.addUI(scscoreui);
        this.player.setScoreUI(scscoreui);



        // boomer test
        SC_S_Boomer boomer =  new SC_S_Boomer(1200,200,0,0, 3);
        //this.addSprite(boomer);

        SC_S_BlackHole blackHole = new SC_S_BlackHole(1200, 400, 0,0, 1);
        this.addSprite(blackHole);


        // storyline
        SC_C_BasicStoryLine bsl = new SC_C_BasicStoryLine(0,0,0,0,0);

        LowBobTextUI text = new LowBobTextUI(10,10,600,40, 0, "Visitor TT1 BRK", Color.ORANGE, 20);
        text.setText("Space  Craft 701  alpha  build  0.32");
        this.addUI(text);

        // add MenuController
        this.gameover_menu = new SC_M_GameOver(0,0,0,0, 10);
        this.addSprite(this.gameover_menu);
        this.addSprite(new SC_M_MenuController(0,0,0,0, 10));

        watch.addOnGameOverListener(gameover_event);

    }

    private LowBobActionEvent gameover_event = new LowBobActionEvent() {
        @Override
        public void onAction(Object sender) {
            player.explode();
            gameover_menu.show_menu();
            LowBobRuntime.getInstance().removeSprite(watch);
        }
    };
}
