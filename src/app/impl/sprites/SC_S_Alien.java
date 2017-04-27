package app.impl.sprites;

import lowbob.LowBobCollider;
import lowbob.LowBobRuntime;
import lowbob.LowBobSprite;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Created by opuee on 27.04.17.
 */
public class SC_S_Alien extends LowBobSprite {


    public SC_S_Alien(double x, double y, double width, double height) {
        super(x, y, width, height);

        this.colliders.add(new LowBobCollider(SC_S_Missile.class));
    }

    @Override
    public void loadImage() {
        this.img = new ImageIcon("src/app/resources/alien.png").getImage();
    }

    @Override
    public void move() {

        this.x -= 2;

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void collide(LowBobSprite lbs) {
        LowBobRuntime.getInstance().removeSprite(this);
    }
}
