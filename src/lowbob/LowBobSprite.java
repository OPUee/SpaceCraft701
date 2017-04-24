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
    protected ArrayList<LowBobSprite> sprites;

    public LowBobSprite(double x, double y) {
        this.x = x;
        this.y = y;

        this.sprites = null;

        loadImage();
    }

    protected void addSprite(LowBobSprite lbs) {
        if (this.sprites == null)
            this.sprites = new ArrayList<>();

        this.sprites.add(lbs);
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

    public ArrayList<LowBobSprite> getSprites() {
        if (this.sprites == null)
            return null;
        else
            return new ArrayList<>(this.sprites);
    }
}
