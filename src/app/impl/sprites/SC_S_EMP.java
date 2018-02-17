package app.impl.sprites;

import lowbob.LowBobSprite;
import lowbob.illumination.LowBobSimpleLight;
import lowbob.util.ImageAnimator;
import lowbob.util.ImageCreator;

import java.awt.*;

public class SC_S_EMP extends LowBobSprite {

    private static int SPEED = -5;

    private ImageAnimator ai_emp;
    private int ai_counter;
    private double slope;

    public SC_S_EMP(double x, double y, double width, double height, double slope) {
        super(x, y, width, height);

        this.ai_counter = 0;
        this.slope = slope;

        LowBobSimpleLight backlight = new LowBobSimpleLight(-13,-13,50,50,.8,1, new Color(240, 0, 255));
        this.addSprite(backlight);
    }

    @Override
    public void loadImage() {
        ai_emp = new ImageAnimator(ImageCreator.create("resources/pics/emp.png"), 27);
        this.img = ai_emp.next();
    }

    @Override
    public void move() {
        this.ai_counter++;

        // animation
        if(this.ai_counter > 5) {
            this.img = ai_emp.next();
            this.ai_counter = 0;
        }

        // movement
        this.x += SPEED;
        this.y += this.slope;


    }
}