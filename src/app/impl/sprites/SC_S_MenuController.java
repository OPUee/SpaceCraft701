package app.impl.sprites;

import lowbob.LowBobRuntime;
import lowbob.LowBobSprite;
import lowbob.UI.LowBobButtonUI;
import lowbob.util.ImageCreator;
import lowbob.util.events.LowBobMouseEvent;
import lowbob.util.events.PanelChangedEventArgs;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class SC_S_MenuController extends LowBobSprite {

    private boolean suspended;
    private pause_menu menu;

    public SC_S_MenuController(double x, double y, double width, double height) {
        super(x, y, width, height);

        this.suspended = false;

        menu = new pause_menu(0,0,0,0);
    }

    @Override
    public void loadImage() {
        this.img = ImageCreator.create("resources/pics/empty.png");
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                toggle_menu();
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
            LowBobRuntime.getInstance().getLBP().changePanel(null, null);
        }

        @Override
        public void onMousePressed(Object sender, MouseEvent e) {

        }
    };

    private class pause_menu extends LowBobSprite {

        LowBobButtonUI resume_btn;
        LowBobButtonUI exit_btn;

        public pause_menu(double x, double y, double width, double height) {
            super(x, y, width, height);

            resume_btn = new LowBobButtonUI(85,220, 345,70, "resources/pics/resume_btn.png");
            exit_btn = new LowBobButtonUI(85,300, 345,70, "resources/pics/exit_btn.png");

            resume_btn.addMouseListener(new LowBobMouseEvent() {
                @Override
                public void onMouseClicked(Object sender, MouseEvent e) {
                    System.out.println("resumed!");
                }

                @Override
                public void onMousePressed(Object sender, MouseEvent e) {

                }
            });
            exit_btn.addMouseListener(paused_event);




    }

        @Override
        public void loadImage() {
            this.img = ImageCreator.create("resources/pics/pausemenu_banner.png");
        }

        public void show() {
            LowBobRuntime runtime = LowBobRuntime.getInstance();
            runtime.addUI(resume_btn);
            runtime.addUI(exit_btn);
        }
    }
}