package lowbob;

import javafx.embed.swing.JFXPanel;
import lowbob.util.events.PanelChangedEventArgs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by opuee on 24.04.17.
 */
public class LowBobRuntime implements Runnable {

    private static LowBobRuntime instance = null;

    private Lock lock;

    private final int DELAY = 25;
    private LowBobPanel lbp;

    private RuntimeState runtimestate;

    public enum RuntimeState {
        RUNNING,
        SUSPENDED
    }

    // Singleton impl
    public static LowBobRuntime getInstance() {
        if(instance == null)
            instance = new LowBobRuntime();

        return instance;
    }

    private LowBobRuntime() {
        // instantiate fx panel for initialisation process
        // later needed while playing sound in javafx -.-'
        final JFXPanel fxPanel = new JFXPanel();
        this.runtimestate = RuntimeState.RUNNING;
        this.lock = new ReentrantLock();
    }

    public void setRuntimeState(RuntimeState state) { this.runtimestate = state; }

    public void setLBP(LowBobPanel lbp) {
        this.lock.lock();
        this.lbp = lbp;
        this.lock.unlock();
    }
    public void changePanel(Object sender, PanelChangedEventArgs e) {
        this.lbp.changePanel(sender, e);
    }
    public void addSprite(LowBobSprite sprite) {
        this.lbp.addSprite(sprite);
    }
    public void removeSprite(LowBobSprite sprite) {this.lbp.removeSprite(sprite);}
    public void addUI(LowBobSprite sprite) { this.lbp.addUI(sprite); }
    public void removeUI(LowBobSprite sprite) { this.lbp.removeUI(sprite); }

    private void update(ArrayList<LowBobSprite> sprites) {
        for(int i = 0; i < sprites.size(); i++)
        {
            sprites.get(i).move();
            update(sprites.get(i).getSprites());
        }
    }

    private void collide(LowBobSprite sprite) {
        ArrayList<LowBobSprite> list = sprite.shrink();

        for(int i = 0; i < list.size(); i++) {
            for(int j = i + 1; j < list.size(); j++) {
                for(int c = 0; c < list.get(i).colliders.size(); c++)
                {
                    LowBobCollider collider = list.get(i).colliders.get(c);

                    if(collider.getType() == list.get(j).getClass()) {
                        if(isColliding(list.get(i), list.get(j)))
                            list.get(i).collide(list.get(j));
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

            this.lock.lock();

            if (this.runtimestate == RuntimeState.RUNNING)
                update(lbp.getSprites());
            collide(lbp.getGDelements());
            lbp.repaint();

            this.lock.unlock();


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
}
