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
    protected double x, y, z, vx, vy, abs_x, abs_y, width, height;
    protected ArrayList<LowBobSprite> sprites;
    protected ArrayList<LowBobCollider> colliders;

    public LowBobSprite(double x, double y, double width, double height, int z) {
        loadImage();

        this.x = x;
        this.y = y;
        this.z = z;
        this.abs_x = x;
        this.abs_y = y;
        this.width = width;
        this.height = height;
        colliders = new ArrayList<LowBobCollider>();
        sprites = new ArrayList<LowBobSprite>();
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

        int index = 0;

        for (LowBobSprite s : this.sprites) {
            if (s.getZValue() > lbs.getZValue())
                break;
            else
                index++;
        }

    	this.sprites.add(index, lbs);
        //this.sprites.add(lbs);
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
    public double getZValue() { return this.z; }
    public double getAbsX() {
        return this.abs_x;
    }
    public double getAbsY() {
        return this.abs_y;
    }
    public void setAbsX(double x) { this.abs_x = x; }
    public void setAbsY(double y) { this.abs_y = y; }
    public double getWidth() { return this.width; }
    public double getHeight() { return this.height; }
    public Image getImage() {
        return this.img;
    }
    public ArrayList<LowBobCollider> getColliders() { return this.colliders; }
    public ArrayList<LowBobSprite> getSprites() { return this.sprites; }

    // sub - z - system
    private static void applySubZValue(LowBobSprite lbs) {

    }
}
