package lowbob.UI;

import lowbob.LowBobSprite;
import lowbob.util.ImageCreator;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by opuee on 02.05.17.
 */
public class LowBobTextUI extends LowBobSprite{

    private String text, content, fontfamily;
    private Color color;
    private int fontsize;

    public LowBobTextUI(double x, double y, double width, double height, int z, String fontfamily, Color color, int fontsize) {
        super(x, y, width, height, z);

        this.text = "test";
        this.content = "";
        this.fontfamily = fontfamily;
        this.color = color;
        this.fontsize = fontsize;
        updateText();
    }

    private void updateText() {
        if(this.content.compareTo(this.text) != 0) {
            this.content = this.text;
            this.img = ImageCreator.createFromString(this.content, (int) this.width, (int) this.height, this.fontfamily, this.color, this.fontsize);
        }
    }

    @Override
    public void loadImage() {
        //passed level above
    }

    public void setText(String text) {
        this.text = text;
        updateText();
    }


}
