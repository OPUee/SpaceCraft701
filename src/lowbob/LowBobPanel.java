package lowbob;

import javax.swing.*;
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

        this.sprites = new ArrayList<>();
    }

    private void draw(Graphics g, ArrayList<LowBobSprite> sprites, double x, double y) {
        if (sprites == null)
            return;

        for(Iterator<LowBobSprite> s = sprites.iterator(); s.hasNext();) {
            LowBobSprite sprite = s.next();

            // draw sprite
            g.drawImage(sprite.getImage(), (int)(sprite.getPosX() + x), (int)(sprite.getPosY() + y), this);
            Toolkit.getDefaultToolkit().sync();
            
            draw(g, sprite.getSprites(), sprite.getPosX(), sprite.getPosY());
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g, this.sprites, 0.0f, 0.0f);
    }

    // getter and setter
    public void addSprite(LowBobSprite lbs) {
        this.sprites.add(lbs);
    }

    public void removeSprite(LowBobSprite lbs) {
        this.sprites.remove(lbs);
    }

    public ArrayList<LowBobSprite> getSprites() {
        if (this.sprites == null)
            return null;
        else
            return new ArrayList<>(this.sprites);
    }

}
