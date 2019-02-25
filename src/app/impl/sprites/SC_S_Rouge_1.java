package app.impl.sprites;

import lowbob.LowBobSprite;
import lowbob.util.ImageCreator;

public class SC_S_Rouge_1 extends LowBobSprite {

    public SC_S_Rouge_1(double x, double y, double width, double height, int z) {
        super(x, y, width, height, z);
    }

    @Override
    public void loadImage() {
        this.img = ImageCreator.create("resources/pics/rogue_01.png");
    }
}
