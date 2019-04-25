package app.impl.ui;

import lowbob.LowBobSprite;
import lowbob.UI.LowBobButtonUI;
import lowbob.util.ImageCreator;

public class SC_UI_Dialog extends LowBobSprite {

    LowBobButtonUI charakter;
    LowBobButtonUI background;

    public SC_UI_Dialog() {
        super(0, 0, 0, 0, 5);

        this.charakter = new LowBobButtonUI(200,200,0,0,6, "resources/pics/char_dummy.png");
        this.background = new LowBobButtonUI(200,200,0,0,5, "resources/pics/background_dummy.png");

        this.addSprite(this.charakter);
        this.addSprite(this.background);

    }

    @Override
    public void loadImage() {
        this.img = ImageCreator.create("resources/pics/empty.png");
    }
}
