package lowbob;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by opuee on 24.04.17.
 */
public class LowBobRuntime extends KeyAdapter implements Runnable {

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

    private void update(ArrayList<LowBobSprite> sprites) {
        if (sprites == null)
            return;

        for(Iterator<LowBobSprite> s = sprites.iterator(); s.hasNext();) {
            LowBobSprite sprite = s.next();

            // move sprite
            sprite.move();
        }
    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {

            update(lbp.getSprites());
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

    // implementation of KeyAdapter
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        keyPressed(keyEvent, lbp.getSprites());
    }

    private void keyPressed(KeyEvent keyEvent, ArrayList<LowBobSprite> sprites) {
        if (sprites == null)
            return;

        for(Iterator<LowBobSprite> s = sprites.iterator(); s.hasNext();) {
            LowBobSprite sprite = s.next();

            sprite.keyPressed(keyEvent);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        keyReleased(keyEvent, lbp.getSprites());
    }

    private void keyReleased(KeyEvent keyEvent, ArrayList<LowBobSprite> sprites) {
        if (sprites == null)
            return;

        for(Iterator<LowBobSprite> s = sprites.iterator(); s.hasNext();) {
            LowBobSprite sprite = s.next();

            sprite.keyReleased(keyEvent);
        }
    }
}
