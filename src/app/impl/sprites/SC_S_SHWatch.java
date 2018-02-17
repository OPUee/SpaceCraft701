package app.impl.sprites;

import lowbob.LowBobSprite;
import lowbob.UI.LowBobButtonUI;
import lowbob.util.ImageCreator;
import lowbob.util.events.LowBobActionEvent;

import java.util.ArrayList;
import java.util.List;

public class SC_S_SHWatch extends LowBobSprite {

    private static int DEF_VALUE = 5;
    private static int REGAIN = 100;

    private LowBobButtonUI hIcon, sIcon, hBar[], sBar[];
    private int health_val, shield_val;
    private int regain_counter;
    private boolean values_changed;

    private List<LowBobActionEvent> actionevents;

    public SC_S_SHWatch(double x, double y, double width, double height, int z) {
        super(x, y, width, height, z);

        this.actionevents = new ArrayList<>();

        // add UI to watch
        hIcon = new LowBobButtonUI(0,35,32,32, 0, "resources/pics/health_icon.png");
        sIcon = new LowBobButtonUI(0,0,32,32, 0, "resources/pics/shield_icon.png");
        hBar = new LowBobButtonUI[DEF_VALUE];
        sBar = new LowBobButtonUI[DEF_VALUE];

        this.addSprite(hIcon);
        this.addSprite(sIcon);

        for (int i = 0; i < DEF_VALUE; i++) {
            hBar[i] = new LowBobButtonUI(i * 25 + 45,35,22,32, 0, "resources/pics/health.png");
            sBar[i] = new LowBobButtonUI(i * 25 + 45,0,22,32, 0, "resources/pics/shield.png");

            this.addSprite(hBar[i]);
            this.addSprite(sBar[i]);
        }

        this.health_val = DEF_VALUE;
        this.shield_val = DEF_VALUE;

        this.regain_counter = 0;
        this.values_changed = false;

    }

    @Override
    public void loadImage() {
        this.img = ImageCreator.create("resources/pics/empty.png");
    }


    @Override
    public void move() {
        this.regain_counter++;

        if(this.regain_counter >= REGAIN) {
            this.regain_counter = 0;
            if (this.shield_val < DEF_VALUE) {
                this.shield_val++;
                this.values_changed = true;
            }
        }

        // update UIreturn 0;
        if (this.values_changed) {
            for (int i = 0; i < DEF_VALUE; i++) {
                this.removeSprite(hBar[i]);
                this.removeSprite(sBar[i]);

                if (this.health_val > i)
                    this.addSprite(hBar[i]);
                if (this.shield_val > i)
                    this.addSprite(sBar[i]);
            }

            if (this.health_val <= 0) {
                //YOU ARE DEAD
                for(LowBobActionEvent e : this.actionevents) {
                    e.onAction(this);
                }
            }

            this.values_changed = false;
        }


    }


    public int getHealth_val() {return this.health_val;}
    public int getShield_val() {return this.shield_val;}

    public void decrease() {
        if (this.shield_val > 0) {
            this.shield_val--;
            this.values_changed = true;
        } else if (this.health_val > 0) {
            this.health_val--;
            this.values_changed = true;
        }
    }

    public void addOnGameOverListener(LowBobActionEvent e) {
        this.actionevents.add(e);
    }
}
