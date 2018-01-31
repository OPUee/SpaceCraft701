package app;

import app.impl.panels.SC_P_Debug;
import app.impl.panels.SC_P_Title;
import lowbob.LowBobPanel;
import lowbob.LowBobRuntime;
import lowbob.util.events.PanelChangedEvent;
import lowbob.util.events.PanelChangedEventArgs;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.File;

/**
 * Created by opuee on 24.04.17.
 */
public class SpaceCraft701 extends JFrame implements PanelChangedEvent{

    private LowBobPanel panel;

    public SpaceCraft701() {
        setTitle("Space Craft 701");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void setPanel(LowBobPanel panel) {
        // attach to runtime
        LowBobRuntime.getInstance().setLBP(panel);

        if(this.panel != null)
            this.remove(this.panel);

        this.panel = panel;
        this.panel.addPanelChangedListener(this);

        this.add(panel);

        this.pack();
        this.setLocationRelativeTo(this);
        this.panel.requestFocus();

    }

    //event for changing the panel
    @Override
    public void onPanelChanged(Object sender, PanelChangedEventArgs e) {
        if(e.getPanel() != null)
            this.setPanel(e.getPanel());
        else {
            System.exit(0);
        }
    }

    public static void main(String[] args) {

        initSC107();

    }

    private static void initSC107() {

        // registrate font for game
        try {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources/sys/visitor1.ttf")).deriveFont(30f));
        } catch (IOException |FontFormatException e) {
            //Handle exception
        }


        // initialize game
        LowBobRuntime runtime = LowBobRuntime.getInstance();

        SpaceCraft701 app = new SpaceCraft701();
        LowBobPanel panel = new SC_P_Title();
        Thread thread = new Thread(runtime);

        app.setPanel(panel);
        app.setVisible(true);

        thread.start();
    }
}
