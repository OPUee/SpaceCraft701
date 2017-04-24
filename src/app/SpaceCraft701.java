package app;

import app.impl.panels.SC_P_Debug;
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

        SpaceCraft701 app = new SpaceCraft701();
        LowBobPanel panel = new SC_P_Debug();
        LowBobRuntime runtime = new LowBobRuntime(panel);
        Thread thread = new Thread(runtime);

        app.setPanel(panel);
        app.setVisible(true);

        panel.addKeyListener(runtime);

        thread.start();

    }

}
