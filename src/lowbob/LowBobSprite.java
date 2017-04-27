package lowbob;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by opuee on 24.04.17.
 */
public abstract class LowBobSprite {

    protected Image img;
    protected double x, y, vx, vy, width, height;
    protected ArrayList<LowBobCollider> colliders;

    public LowBobSprite(double x, double y, double width, double height) {
        loadImage();

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        colliders = new ArrayList<>();
    }

    public abstract void loadImage();
    public abstract void move();
    public abstract void keyPressed(KeyEvent keyEvent);
    public abstract void keyReleased(KeyEvent keyEvent);
    public abstract void collide(LowBobSprite lbs);

    // getter and setter
    public double getPosX() {
        return this.x;
    }
    public double getPosY() {
        return this.y;
    }
    public double getWidth() { return this.width; }
    public double getHeight() { return this.height; }
    public Image getImage() {
        return this.img;
    }
    public ArrayList<LowBobCollider> getColliders() { return this.colliders; }
}
