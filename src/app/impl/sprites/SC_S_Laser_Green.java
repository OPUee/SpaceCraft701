package app.impl.sprites;

import lowbob.LowBobRuntime;
import lowbob.LowBobSprite;
import lowbob.illumination.LowBobSimpleLight;
import lowbob.util.ImageCreator;

import java.awt.*;

/**
 * Created by opuee on 27.04.17.
 */
public class SC_S_Laser_Green extends LowBobSprite {

    private int SPEED = 20;
    private LowBobSimpleLight light;

    public SC_S_Laser_Green(double x, double y, double width, double height, int z) {
        super(x, y, width, height, z);

        light = new LowBobSimpleLight(-45,-17,100,35, 0,0.6,1.5,new Color(23, 255, 54));
        this.addSprite(light);
    }

    @Override
    public void loadImage() {
    	this.img = ImageCreator.create("resources/pics/laser_green.png");
    }

    @Override
    public void move() {
        x += SPEED;
        
        if(x > 1500) {
        	LowBobRuntime.getInstance().removeSprite(this);
        }
    }
}
