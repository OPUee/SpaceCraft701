package app.impl.sprites;

import lowbob.LowBobSprite;
import lowbob.util.ImageCreator;

public class SC_S_Shield_Generator extends LowBobSprite {

    public SC_S_Shield_Generator(double x, double y, double width, double height, int z) {
        super(x, y, width, height, z);
    }

    @Override
    public void loadImage() {
        this.img = ImageCreator.create("resources/pics/shield_generator.png");
    }

}
