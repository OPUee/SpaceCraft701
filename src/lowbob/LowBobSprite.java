package lowbob;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by opuee on 24.04.17.
 */
public abstract class LowBobSprite {

    protected Image img;
    protected double x, y, vx, vy;

    public LowBobSprite(double x, double y) {
        this.x = x;
        this.y = y;

        loadImage();
    }

    public abstract void loadImage();
    public abstract void move();
    public abstract void keyPressed(KeyEvent keyEvent);
    public abstract void keyReleased(KeyEvent keyEvent);

    // getter and setter
    public double getPosX() {
        return this.x;
    }

    public double getPosY() {
        return this.y;
    }

    public Image getImage() {
        return this.img;
    }
}
