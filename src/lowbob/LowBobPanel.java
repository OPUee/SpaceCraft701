package lowbob;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by opuee on 24.04.17.
 */
public abstract class LowBobPanel extends JPanel {

    private ArrayList<LowBobSprite> sprites;

    public LowBobPanel() {
        setFocusable(true);
        setDoubleBuffered(true);

        this.sprites = null;
    }

    private void draw(Graphics g, ArrayList<LowBobSprite> sprites) {
        if (sprites == null)
            return;

        for(Iterator<LowBobSprite> s = sprites.iterator(); s.hasNext();) {
            LowBobSprite sprite = s.next();

            // draw sprite
            g.drawImage(sprite.getImage(), (int)sprite.getPosX(), (int)sprite.getPosY(), this);
            Toolkit.getDefaultToolkit().sync();

            // iterate through sceen
            draw(g, sprite.getSprites());
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g, this.sprites);
    }

    // getter and setter
    public void addSprite(LowBobSprite lbs) {
        if (this.sprites == null)
            this.sprites = new ArrayList<>();

        this.sprites.add(lbs);
    }

    public ArrayList<LowBobSprite> getSprites() {
        if (this.sprites == null)
            return null;
        else
            return new ArrayList<>(this.sprites);
    }

}
