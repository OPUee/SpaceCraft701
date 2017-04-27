package app.impl.sprites;

import lowbob.LowBobSprite;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Created by opuee on 27.04.17.
 */
public class SC_S_laser extends LowBobSprite {

    private int SPEED = 20;

    public SC_S_laser(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public void loadImage() {
        this.img = new ImageIcon("src/app/resources/laser_red.png").getImage();
    }

    @Override
    public void move() {
        x += SPEED;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void collide(LowBobSprite lbs) {

    }
}
