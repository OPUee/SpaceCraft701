package lowbob;

import lowbob.UI.LowBobUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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

        this.sprites = new ArrayList<LowBobSprite>();
        this.ui = new ArrayList<LowBobUI>();

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                for (int i = 0; i < sprites.size(); i++) {
                    sprites.get(i).keyTyped(keyEvent);
                }
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                for (int i = 0; i < sprites.size(); i++) {
                    sprites.get(i).keyPressed(keyEvent);
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                for (int i = 0; i < sprites.size(); i++) {
                    sprites.get(i).keyReleased(keyEvent);
                }
            }
        });

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
            	for (int i = 0; i < ui.size(); i++) {
            		LowBobUI elem = ui.get(i);
            		
            		if(collides(elem, mouseEvent.getPoint()))
            			elem.mouseClicked(mouseEvent);
            	}
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
            	for (int i = 0; i < ui.size(); i++) {
            		LowBobUI elem = ui.get(i);
            		
            		if(collides(elem, mouseEvent.getPoint()))
            			elem.mousePressed(mouseEvent);
            	}
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
            	for (int i = 0; i < ui.size(); i++) {
            		LowBobUI elem = ui.get(i);
            		
            		if(collides(elem, mouseEvent.getPoint()))
            			elem.mouseReleased(mouseEvent);
            	}
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
            	
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
            	// 
            }
            
            private boolean collides(LowBobUI elem, Point p) {
            	return 	!(p.x > (elem.x + elem.width) ||
                        p.y > (elem.y + elem.height));
            }
        });
    }

    private void draw_sprites(Graphics g, ArrayList<LowBobSprite> sprites, double x, double y) {
        if (sprites == null)
            return;

        for (int i = 0; i < sprites.size(); i++) {
            LowBobSprite sprite = sprites.get(i);

            // draw sprite
            g.drawImage(sprite.getImage(), (int)(sprite.getPosX() + x), (int)(sprite.getPosY() + y), this);
            Toolkit.getDefaultToolkit().sync();

            draw_sprites(g, sprite.getSprites(), sprite.getPosX(), sprite.getPosY());
        }
    }

    private void draw_ui(Graphics g, ArrayList<LowBobUI> elements, double x, double y) {
        if (elements == null)
            return;

        for (int i = 0; i < elements.size(); i++) {
            LowBobUI element = elements.get(i);

            // draw ui
            g.drawImage(element.getImage(), (int)(element.getPosX() + x), (int)(element.getPosY() + y), this);
            Toolkit.getDefaultToolkit().sync();
            
            draw_ui(g, element.getUIElements(), element.getPosX(), element.getPosY());
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
            return new ArrayList<LowBobSprite>(this.sprites);
    }

}