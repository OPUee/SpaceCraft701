package app.impl.sprites;

import lowbob.LowBobRuntime;
import lowbob.LowBobSprite;
import lowbob.util.ImageCreator;

import java.awt.event.KeyEvent;

public class SC_S_MenuController extends LowBobSprite {

    public SC_S_MenuController(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public void loadImage() {
        this.img = ImageCreator.create("resources/pics/empty.png");
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                LowBobRuntime.getInstance().setRuntimeState(LowBobRuntime.RuntimeState.SUSPENDED);
        }
    }
}
