package lowbob;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by opuee on 24.04.17.
 */
public class LowBobRuntime implements Runnable, MouseListener, KeyListener {

    private static LowBobRuntime instance = null;

    private final int DELAY = 25;
    private LowBobPanel lbp;

    // Singleton impl
    public static LowBobRuntime getInstance() {
        if(instance == null)
            instance = new LowBobRuntime();

        return instance;
    }
    private LowBobRuntime() {}

    public void setLBP(LowBobPanel lbp) {
        this.lbp = lbp;
    }
    public void addSprite(LowBobSprite sprite) {
        this.lbp.addSprite(sprite);
    }
    public void removeSprite(LowBobSprite sprite) {this.lbp.removeSprite(sprite);}

    private void update(ArrayList<LowBobSprite> sprites) {
        for(Iterator<LowBobSprite> s = sprites.iterator(); s.hasNext();) {
            LowBobSprite sprite = s.next();

            sprite.move();
            update(sprite.getSprites());
        }
    }

    private void collide(ArrayList<LowBobSprite> sprites) {
        for(Iterator<LowBobSprite> s = sprites.iterator(); s.hasNext();) {
            LowBobSprite sprite = s.next();

            // calculate colliders for each sprite individual

            for(Iterator<LowBobSprite> cs = sprites.iterator(); cs.hasNext();) {
                LowBobSprite colliding_sprite = cs.next();
                ArrayList<LowBobCollider> colliders = sprite.getColliders();

                for(Iterator<LowBobCollider> c = colliders.iterator(); c.hasNext();) {
                    LowBobCollider collider = c.next();

                    if(collider.getType() == colliding_sprite.getClass()) {
                        if(isColliding(sprite, colliding_sprite))
                            sprite.collide(colliding_sprite);
                    }
                }
            }
        }
    }

    private boolean isColliding(LowBobSprite s1, LowBobSprite s2) {

        return !(s2.x > (s1.x + s1.width) ||
                (s2.x + s2.width) < s1.x ||
                s2.y > (s1.y + s1.height) ||
                (s2.y + s2.height) < s1.y);
    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {

            update(lbp.getSprites());
            collide(lbp.getSprites());
            lbp.repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("Interrupted: " + e.getMessage());
            }

            beforeTime = System.currentTimeMillis();
        }
    }


    // implementation of KeyListener
    @Override
    public void keyTyped(KeyEvent keyEvent) {
        for(Iterator<LowBobSprite> s = lbp.getSprites().iterator(); s.hasNext();) {
            LowBobSprite sprite = s.next();

            sprite.keyTyped(keyEvent);
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        for(Iterator<LowBobSprite> s = lbp.getSprites().iterator(); s.hasNext();) {
            LowBobSprite sprite = s.next();

            sprite.keyPressed(keyEvent);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        for(Iterator<LowBobSprite> s = lbp.getSprites().iterator(); s.hasNext();) {
            LowBobSprite sprite = s.next();

            sprite.keyReleased(keyEvent);
        }
    }

    // implementation of MouseListener
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        for(Iterator<LowBobSprite> s = lbp.getSprites().iterator(); s.hasNext();) {
            LowBobSprite sprite = s.next();

            sprite.mouseClicked(mouseEvent);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        for(Iterator<LowBobSprite> s = lbp.getSprites().iterator(); s.hasNext();) {
            LowBobSprite sprite = s.next();

            sprite.mousePressed(mouseEvent);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        for(Iterator<LowBobSprite> s = lbp.getSprites().iterator(); s.hasNext();) {
            LowBobSprite sprite = s.next();

            sprite.mouseReleased(mouseEvent);
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        for(Iterator<LowBobSprite> s = lbp.getSprites().iterator(); s.hasNext();) {
            LowBobSprite sprite = s.next();

            sprite.mouseEntered(mouseEvent);
        }
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        for(Iterator<LowBobSprite> s = lbp.getSprites().iterator(); s.hasNext();) {
            LowBobSprite sprite = s.next();

            sprite.mouseExited(mouseEvent);
        }
    }
}
