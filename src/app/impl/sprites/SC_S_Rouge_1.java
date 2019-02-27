package app.impl.sprites;

import lowbob.LowBobSprite;
import lowbob.util.ImageCreator;

public class SC_S_Rouge_1 extends LowBobSprite {

    public SC_S_Rouge_1(double x, double y, double width, double height, int z) {
        super(x, y, width, height, z);

        this.addSprite(new SC_S_Shield_Generator(-75,-2,0,0,2));
    }

    @Override
    public void loadImage() {
        this.img = ImageCreator.create("resources/pics/rogue_02.png");
    }

    @Override
    public void move() {

    }

}
