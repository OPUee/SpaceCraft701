package app.impl.ui;

import lowbob.LowBobSprite;
import lowbob.UI.LowBobButtonUI;
import lowbob.UI.LowBobTextUI;
import lowbob.util.ImageCreator;

import java.awt.*;

public class SC_UI_Dialog extends LowBobSprite {

    LowBobButtonUI charakter;
    LowBobButtonUI background;
    LowBobTextUI content;

    public SC_UI_Dialog(int x, int y) {
        super(x, y, 0, 0, 5);

        this.charakter = new LowBobButtonUI(10,10,0,0,6, "resources/pics/char_dummy.png");
        this.background = new LowBobButtonUI(0,0,0,0,5, "resources/pics/background_dummy.png");
        this.content = new LowBobTextUI(-260,10,600,40, 7, "Visitor TT1 BRK", Color.RED, 20);

        this.content.setText("I LIKE THAT SHIT");

        this.addSprite(this.content);
        this.addSprite(this.charakter);
        this.addSprite(this.background);


    }

    @Override
    public void loadImage() {
        this.img = ImageCreator.create("resources/pics/empty.png");
    }
}
