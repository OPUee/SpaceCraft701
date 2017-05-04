package lowbob.UI;

import lowbob.LowBobSprite;
import lowbob.util.ImageCreator;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by opuee on 02.05.17.
 */
public class LowBobTextUI extends LowBobUI{

    private String text, content;
    private Color color;

    public LowBobTextUI(double x, double y, double width, double height, Color color) {
        super(x, y, width, height);

        this.text = "test";
        this.content = "";
        this.color = color;
        updateText();
    }

    private void updateText() {
        if(this.content.compareTo(this.text) != 0) {
            System.out.println("update UI");
            this.content = this.text;
            this.img = ImageCreator.createFromString(this.content, (int) this.width, (int) this.height, this.color);
        }
    }

    @Override
    public void loadImage() {
        //passed level above
    }

    @Override
    public void move() {
        updateText();
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

    public void setText(String text) {
        this.text = text;
        updateText();
    }


}
