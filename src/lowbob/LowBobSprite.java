package lowbob;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by opuee on 24.04.17.
 */
public abstract class LowBobSprite {

    protected BufferedImage img;
    protected double x, y, vx, vy, width, height;
    protected ArrayList<LowBobSprite> sprites;
    protected ArrayList<LowBobCollider> colliders;

    public LowBobSprite(double x, double y, double width, double height) {
        loadImage();

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        colliders = new ArrayList<>();
        sprites = new ArrayList<>();
    }

    // member functions
    public abstract void loadImage();
    public void move() {}
    public void collide(LowBobSprite lbs) {}

    // KeyListener
    public void keyTyped(KeyEvent keyEvent) {}
    public void keyPressed(KeyEvent keyEvent) {}
    public void keyReleased(KeyEvent keyEvent){}

    //MouseListener
    public void mouseClicked(MouseEvent mouseEvent) {}
    public void mousePressed(MouseEvent mouseEvent) {}
    public void mouseReleased(MouseEvent mouseEvent) {}
    public void mouseEntered(MouseEvent mouseEvent) {}
    public void mouseExited(MouseEvent mouseEvent) {}

    // getter and setter
    public void addSprite(LowBobSprite lbs) {
    	this.sprites.add(lbs);
    }
    public void removeSprite(LowBobSprite lbs) {
    	this.sprites.remove(lbs);
    }
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
    public ArrayList<LowBobSprite> getSprites() { return this.sprites; }
}
