package app.impl.sprites;

import lowbob.LowBobSprite;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Created by opuee on 24.04.17.
 */
public class SC_S_Missile extends LowBobSprite {

    private final int SPEED = 15;
    private final double ACCELE = .3;

    public SC_S_Missile(double x, double y, double width, double height) {
        super(x,y,width,height);
    }

    @Override
    public void loadImage() {
        this.img = new ImageIcon("src/app/resources/missile.png").getImage();
    }

    @Override
    public void move() {

        if(vx < SPEED)
            vx += ACCELE;

        x += vx;
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
