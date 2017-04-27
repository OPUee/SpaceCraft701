package app.impl.sprites;

import lowbob.LowBobCollider;
import lowbob.LowBobRuntime;
import lowbob.LowBobSprite;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Created by opuee on 24.04.17.
 */
public class SC_S_SpaceCraft extends LowBobSprite{

    private final double SPEED = 5;
    private final double ACCELE = .2;

    private boolean up, down, fired;

    public SC_S_SpaceCraft(int x, int y, int width, int height) {
        super(x, y, width, height);

        this.colliders.add(new LowBobCollider(SC_S_Alien.class));

        // initialize member
        up = false;
        down = false;
        fired = false;
    }

    public void fire() {
        LowBobRuntime.getInstance().addSprite(new SC_S_Missile(this.x, this.y, 32, 16));
        fired = true;
    }

    @Override
    public void loadImage() {
        this.img = new ImageIcon("src/app/resources/spacecraft.png").getImage();
    }

    @Override
    public void move() {
        //smoothing for velocity;
        if(up) {
            vy -= ACCELE;
            if(vy <= -SPEED)
                vy = -SPEED;
        }
        else {
            if(vy < 0)
                vy += ACCELE;
        }

        if(down) {
            vy += ACCELE;
            if(vy >= SPEED)
                vy  = SPEED;
        }
        else {
            if(vy > 0)
                vy -= ACCELE;
        }

        // set absolute position
        this.x += this.vx;
        this.y += this.vy;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()) {
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
            case KeyEvent.VK_SPACE:
                if(!fired)
                fire();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()) {
            case KeyEvent.VK_UP:
                up = false;
            case KeyEvent.VK_DOWN:
                down = false;
            case KeyEvent.VK_SPACE:
                fired = false;
        }
    }

    @Override
    public void collide(LowBobSprite lbs) {
        System.out.println("intersection");
    }
}
