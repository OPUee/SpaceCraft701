package app.impl.menu;

import lowbob.LowBobRuntime;
import lowbob.LowBobSprite;
import lowbob.UI.LowBobButtonUI;
import lowbob.util.ImageCreator;
import lowbob.util.events.LowBobMouseEvent;
import lowbob.util.events.PanelChangedEventArgs;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class SC_M_MenuController extends LowBobSprite {

    private boolean suspended;
    private pause_menu menu;
    private boolean toggle_indicator;

    public SC_M_MenuController(double x, double y, double width, double height, int z) {
        super(x, y, width, height, z);

        this.suspended = false;
        this.toggle_indicator = true;

        menu = new pause_menu(0,0,0,0, 0);
    }

    @Override
    public void loadImage() {
        this.img = ImageCreator.create("resources/pics/empty.png");
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                if(this.toggle_indicator)
                    toggle_menu();
                this.toggle_indicator = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                this.toggle_indicator = true;
        }
    }

    private void toggle_menu() {
        if (suspended){
            LowBobRuntime.getInstance().setRuntimeState(LowBobRuntime.RuntimeState.RUNNING);
            this.suspended = false;
            this.removeSprite(menu);
        } else {
            LowBobRuntime.getInstance().setRuntimeState(LowBobRuntime.RuntimeState.SUSPENDED);
            this.suspended = true;
            this.addSprite(menu);
        }
    }

    private LowBobMouseEvent resume_event = new LowBobMouseEvent() {
        @Override
        public void onMouseClicked(Object sender, MouseEvent e) {
            toggle_menu();
        }

        @Override
        public void onMousePressed(Object sender, MouseEvent e) {

        }
    };

    private LowBobMouseEvent paused_event = new LowBobMouseEvent() {
        @Override
        public void onMouseClicked(Object sender, MouseEvent e) {
            LowBobRuntime.getInstance().changePanel(null, new PanelChangedEventArgs(null));
        }

        @Override
        public void onMousePressed(Object sender, MouseEvent e) {

        }
    };

    public class pause_menu extends LowBobSprite {

        private LowBobButtonUI resume_btn;
        private LowBobButtonUI exit_btn;

        public pause_menu(double x, double y, double width, double height, int z) {
            super(x, y, width, height, z);

            resume_btn = new LowBobButtonUI(85,220, 345,70, 1, "resources/pics/resume_btn.png");
            exit_btn = new LowBobButtonUI(85,300, 345,70, 1,"resources/pics/exit_btn.png");

            resume_btn.addMouseListener(resume_event);
            exit_btn.addMouseListener(paused_event);

            this.addSprite(resume_btn);
            this.addSprite(exit_btn);
        }

        @Override
        public void loadImage() {
            this.img = ImageCreator.create("resources/pics/pausemenu_banner.png");
        }
    }
}