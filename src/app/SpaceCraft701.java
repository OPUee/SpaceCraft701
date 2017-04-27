package app;

import app.impl.panels.SC_P_Debug;
import app.impl.sprites.SC_S_Missile;
import lowbob.LowBobCollider;
import lowbob.LowBobPanel;
import lowbob.LowBobRuntime;

import javax.swing.*;

/**
 * Created by opuee on 24.04.17.
 */
public class SpaceCraft701 extends JFrame {

    private LowBobPanel panel;

    public SpaceCraft701() {
        setTitle("Space Craft 701");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void setPanel(LowBobPanel panel) {
        if(this.panel != null)
            this.remove(this.panel);

        this.panel = panel;
        this.add(panel);

        this.pack();
        this.setLocationRelativeTo(this);
    }

    public static void main(String[] args) {

        initSC107();

    }

    private static void initSC107() {
        LowBobRuntime runtime = LowBobRuntime.getInstance();

        SpaceCraft701 app = new SpaceCraft701();
        LowBobPanel panel = new SC_P_Debug();
        Thread thread = new Thread(runtime);

        runtime.setLBP(panel);

        app.setPanel(panel);
        app.setVisible(true);

        panel.addKeyListener(runtime);

        thread.start();
    }

}
