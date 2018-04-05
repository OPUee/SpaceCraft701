package app.impl.sprites;

import lowbob.LowBobRuntime;
import lowbob.LowBobSprite;
import lowbob.util.ImageCreator;

import java.util.Random;

public class SC_C_BasicStoryLine extends LowBobSprite {

    private Random rnd;

    public SC_C_BasicStoryLine(double x, double y, double width, double height, int z) {
        super(x, y, width, height, z);

        rnd = new Random();
    }

    @Override
    public void loadImage() {
        this.img = ImageCreator.create("resources/pics/empty.png");
    }

    @Override
    public void move() {

    }

    private void spawn(LowBobSprite lbs) {
        LowBobRuntime.getInstance().addSprite(lbs);
    }

    private enum Phase {

        Stage1, // collecting uran
        Stage2, // make your way to the enemy's base
        Stage3  //
    }

}
