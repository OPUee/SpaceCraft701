package app.impl.menu;

import app.impl.panels.SC_P_Debug;
import lowbob.LowBobRuntime;
import lowbob.LowBobSprite;
import lowbob.UI.LowBobButtonUI;
import lowbob.util.ImageCreator;
import lowbob.util.events.LowBobMouseEvent;
import lowbob.util.events.PanelChangedEventArgs;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class SC_M_GameOver extends LowBobSprite {

    private gameover_menu menu;

    public SC_M_GameOver(double x, double y, double width, double height, int z) {
        super(x, y, width, height, z);

        this.menu = new gameover_menu(0,0,0,0, 0);
    }

    @Override
    public void loadImage() {
        this.img = ImageCreator.create("resources/pics/empty.png");
    }

    public void show_menu() {
        this.addSprite(menu);
    }

    private LowBobMouseEvent restart_event = new LowBobMouseEvent() {
        @Override
        public void onMouseClicked(Object sender, MouseEvent e) {
            LowBobRuntime.getInstance().changePanel(null, new PanelChangedEventArgs(new SC_P_Debug()));
        }

        @Override
        public void onMousePressed(Object sender, MouseEvent e) {

        }
    };

    private LowBobMouseEvent exit_event = new LowBobMouseEvent() {
        @Override
        public void onMouseClicked(Object sender, MouseEvent e) {
            LowBobRuntime.getInstance().changePanel(null, new PanelChangedEventArgs(null));
        }

        @Override
        public void onMousePressed(Object sender, MouseEvent e) {

        }
    };

    private class gameover_menu extends LowBobSprite {

        private LowBobButtonUI restart_btn;
        private LowBobButtonUI exit_btn;

        public gameover_menu(double x, double y, double width, double height, int z) {
            super(x, y, width, height, z);

            restart_btn = new LowBobButtonUI(530, 370, 345, 70, 1, "resources/pics/restart_btn.png");
            exit_btn = new LowBobButtonUI(530, 450, 345, 70, 1, "resources/pics/exit_btn.png");

            restart_btn.addMouseListener(restart_event);
            exit_btn.addMouseListener(exit_event);

            this.addSprite(restart_btn);
            this.addSprite(exit_btn);

        }

        @Override
        public void loadImage() {
            this.img = ImageCreator.create("resources/pics/gameover_banner.png");
        }
    }
}
