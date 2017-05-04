package app.impl.sprites;

import lowbob.LowBobRuntime;
import lowbob.LowBobSprite;
import lowbob.util.ImageCreator;

import java.awt.event.KeyEvent;

/**
 * Created by opuee on 27.04.17.
 */
public class SC_S_Laser extends LowBobSprite {

    private int SPEED = 20;

    public SC_S_Laser(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public void loadImage() {
    	this.img = ImageCreator.create("resources/laser_red.png");
    }

    @Override
    public void move() {
        x += SPEED;
        
        if(x > 2000) {
        	LowBobRuntime.getInstance().removeSprite(this);
        }
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
