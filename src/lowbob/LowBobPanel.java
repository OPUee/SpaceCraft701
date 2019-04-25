package lowbob;

import lowbob.util.ImageCreator;
import lowbob.util.events.PanelChangedEvent;
import lowbob.util.events.PanelChangedEventArgs;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by opuee on 24.04.17.
 */
public abstract class LowBobPanel extends JPanel {

    private LowBobSprite root;
    private LowBobSprite gd;
    private LowBobSprite ui;
    private ArrayList<PanelChangedEvent> m_panelchangedevent;

    public LowBobPanel() {
        setFocusable(true);
        setDoubleBuffered(true);


        // --- initialise basic world tree ---

        this.root = new LowBobSprite(0,0,0,0, 0) {
            @Override
            public void loadImage() {
                this.img = ImageCreator.create("resources/pics/empty.png");
            }
        };

        this.gd = new LowBobSprite(0,0,0,0, 0) {
            @Override
            public void loadImage() {
                this.img = ImageCreator.create("resources/pics/empty.png");
            }
        };

        this.ui = new LowBobSprite(0,0,0,0, 0) {
            @Override
            public void loadImage() {
                this.img = ImageCreator.create("resources/pics/empty.png");
            }
        };

        root.addSprite(gd);
        root.addSprite(ui);

        // -----------------------------------

        this.m_panelchangedevent = new ArrayList<>();

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                propagadeKeyTyped(keyEvent, root);
            }

            private void propagadeKeyTyped(KeyEvent keyEvent, LowBobSprite sprite) {
                for (int i = 0; i < sprite.getSprites().size(); i++) {
                    LowBobSprite subsprite = sprite.getSprites().get(i);
                    subsprite.keyTyped(keyEvent);
                    propagadeKeyTyped(keyEvent, subsprite);
                }
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                propagadeKeyPressed(keyEvent, root);
            }

            private void propagadeKeyPressed(KeyEvent keyEvent, LowBobSprite sprite) {
                for (int i = 0; i < sprite.getSprites().size(); i++) {
                    LowBobSprite subsprite = sprite.getSprites().get(i);
                    subsprite.keyPressed(keyEvent);
                    propagadeKeyPressed(keyEvent, subsprite);
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                propagadeKeyReleased(keyEvent, root);
            }

            private void propagadeKeyReleased(KeyEvent keyEvent, LowBobSprite sprite) {
                for (int i = 0; i < sprite.getSprites().size(); i++) {
                    LowBobSprite subsprite = sprite.getSprites().get(i);
                    subsprite.keyReleased(keyEvent);
                    propagadeKeyReleased(keyEvent, subsprite);
                }
            }
        });

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                propagadeMouseClicked(mouseEvent, root);
            }

            private void propagadeMouseClicked(MouseEvent mouseEvent, LowBobSprite sprite)
            {
                for (int i = 0; i < sprite.getSprites().size(); i++) {
                    LowBobSprite subsprite = sprite.getSprites().get(i);

                    if(collides(subsprite, mouseEvent.getPoint()))
                        subsprite.mouseClicked(mouseEvent);

                    propagadeMouseClicked(mouseEvent, subsprite);
                }
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                propagadeMouseReleased(mouseEvent, root);
            }

            private void propagadeMouseReleased(MouseEvent mouseEvent, LowBobSprite sprite)
            {
                for (int i = 0; i < sprite.getSprites().size(); i++) {
                    LowBobSprite subsprite = sprite.getSprites().get(i);

                    if(collides(subsprite, mouseEvent.getPoint()))
                        subsprite.mouseReleased(mouseEvent);

                    propagadeMouseReleased(mouseEvent, subsprite);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // nothing to do right now
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                // nothing to do right now
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                // nothing to do right now
            }
            
            private boolean collides(LowBobSprite elem, Point p) {
            	return 	!(p.x > (elem.x + elem.width) ||
                        p.y > (elem.y + elem.height) ||
                        p.x < elem.x ||
                        p.y < elem.y);
            }
        });
    }

    public abstract void setup();

    private void draw_sprites(Graphics g, ArrayList<LowBobSprite> sprites, double x, double y) {
        if (sprites == null)
            return;

        for (int i = 0; i < sprites.size(); i++) {
            LowBobSprite sprite = sprites.get(i);

            // draw sprite
            sprite.setAbsX(sprite.getPosX() + x);
            sprite.setAbsY(sprite.getPosY() + y);
            g.drawImage(sprite.getImage(), (int)(sprite.getPosX() + x), (int)(sprite.getPosY() + y), this);
            Toolkit.getDefaultToolkit().sync();

            draw_sprites(g, sprite.getSprites(), x + sprite.getPosX(), y + sprite.getPosY());
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw_sprites(g, this.gd.getSprites(), 0.0f, 0.0f);
        draw_sprites(g, this.ui.getSprites(), 0.0f, 0.0f);
    }

    // getter and setter
    public void addSprite(LowBobSprite lbs) {
        this.gd.addSprite(lbs);
    }
    public void addUI(LowBobSprite lbs) {
        this.ui.addSprite(lbs);
    }
    public void removeSprite(LowBobSprite lbs) {
        this.gd.removeSprite(lbs);
    }
    public void removeUI(LowBobSprite lbs) {
        this.ui.removeSprite(lbs);
    }
    public LowBobSprite getUIelements(){
        return this.ui;
    }
    public LowBobSprite getGDelements(){
        return this.gd;
    }
    public LowBobSprite getAllelements(){
        return this.root;
    }

    public ArrayList<LowBobSprite> getSprites() {
        if (this.root.getSprites() == null)
            return null;
        else
            return new ArrayList<LowBobSprite>(this.root.getSprites());
    }

    public void addPanelChangedListener(PanelChangedEvent listener) {
        this.m_panelchangedevent.add(listener);
    }

    public void changePanel(Object sender, PanelChangedEventArgs e) {
        for(PanelChangedEvent event : this.m_panelchangedevent) {
            event.onPanelChanged(sender, e);
        }
    }

}