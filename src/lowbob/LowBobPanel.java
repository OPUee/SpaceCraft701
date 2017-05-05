package lowbob;

import lowbob.UI.LowBobUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by opuee on 24.04.17.
 */
public abstract class LowBobPanel extends JPanel {

    private ArrayList<LowBobSprite> sprites;
    private ArrayList<LowBobUI> ui;

    public LowBobPanel() {
        setFocusable(true);
        setDoubleBuffered(true);

        this.sprites = new ArrayList<>();
        this.ui = new ArrayList<>();
    }

    private void draw_sprites(Graphics g, ArrayList<LowBobSprite> sprites, double x, double y) {
        if (sprites == null)
            return;

        for(Iterator<LowBobSprite> s = sprites.iterator(); s.hasNext();) {
            LowBobSprite sprite = s.next();

            // draw sprite
            g.drawImage(sprite.getImage(), (int)(sprite.getPosX() + x), (int)(sprite.getPosY() + y), this);
            Toolkit.getDefaultToolkit().sync();

            draw_sprites(g, sprite.getSprites(), sprite.getPosX(), sprite.getPosY());
        }
    }

    private void draw_ui(Graphics g, ArrayList<LowBobUI> elements, double x, double y) {
        if (elements == null)
            return;

        for(Iterator<LowBobUI> e = elements.iterator(); e.hasNext();) {
            LowBobUI element = e.next();

            // draw sprite
            g.drawImage(element.getImage(), (int)(element.getPosX() + x), (int)(element.getPosY() + y), this);
            Toolkit.getDefaultToolkit().sync();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw_sprites(g, this.sprites, 0.0f, 0.0f);
        draw_ui(g, this.ui, 0.0f, 0.0f);
    }

    // getter and setter
    public void addSprite(LowBobSprite lbs) {
        this.sprites.add(lbs);
    }
    public void removeSprite(LowBobSprite lbs) {
        this.sprites.remove(lbs);
    }
    public void addUI(LowBobUI lbu) { this.ui.add(lbu); }
    public void removeUI(LowBobUI lbu) { this.ui.remove(lbu); }

    public ArrayList<LowBobSprite> getSprites() {
        if (this.sprites == null)
            return null;
        else
            return new ArrayList<>(this.sprites);
    }

}
