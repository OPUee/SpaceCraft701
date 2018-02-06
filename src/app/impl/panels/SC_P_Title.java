package app.impl.panels;

import lowbob.LowBobPanel;
import lowbob.UI.LowBobButtonUI;
import lowbob.UI.LowBobTextUI;
import lowbob.util.events.LowBobMouseEvent;
import lowbob.util.events.PanelChangedEventArgs;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by opuee on 05.10.17.
 */
public class SC_P_Title extends LowBobPanel implements LowBobMouseEvent {

    @Override
    public void setup() {
        // init panel
        setBackground(new Color(0x02, 17, 33));
        setPreferredSize(new Dimension(700, 800));

        LowBobButtonUI background = new LowBobButtonUI(0,0,700,800, "resources/pics/title_background.png");
        LowBobButtonUI img_title = new LowBobButtonUI(100,100,168,102,"resources/pics/title_big.png");
        LowBobButtonUI start_btn = new LowBobButtonUI(120,500,471,111,"resources/pics/start_btn.png");
        LowBobTextUI txt_info = new LowBobTextUI(125,430,470,40,"Visitor TT1 BRK",Color.red,19);

        start_btn.addMouseListener(this);

        txt_info.setText("SpaceCraft development alpha build 0.73");

        this.addUI(background);
        this.addUI(img_title);
        this.addUI(start_btn);
        this.addUI(txt_info);
    }

    @Override
    public void onMouseClicked(Object sender, MouseEvent e) {
        // start game
        this.changePanel(this, new PanelChangedEventArgs(new SC_P_Debug()));
    }

    @Override
    public void onMousePressed(Object sender, MouseEvent e) {

    }
}
