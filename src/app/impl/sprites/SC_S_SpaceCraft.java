package app.impl.sprites;

import com.sun.javafx.geom.Vec2d;
import lowbob.LowBobCollider;
import lowbob.LowBobRuntime;
import lowbob.LowBobSprite;
import lowbob.UI.LowBobTextUI;
import lowbob.audio.LowBobAudio;
import lowbob.illumination.LowBobSimpleLight;
import lowbob.particles.LowBobParticle;
import lowbob.particles.LowBobParticleSystem;
import lowbob.particles.impl.LowBobDirectedBehavior;
import lowbob.util.ImageCreator;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by opuee on 24.04.17.
 */
public class SC_S_SpaceCraft extends LowBobSprite{

	private final double SPEED = 11;
	private final double ACCELERATION = 0.8f;
	private final double LASER_COOLDOWN = 7;
	private final int MARGIN = 100;

	private ACC_State xAcc, yAcc;
	private int lc_cnt;

	private int score;
	private LowBobTextUI score_ui;
	private SC_S_SHWatch def_watch;

	private SC_S_Thurster thurster;
	private LowBobSimpleLight light;
	private LowBobParticleSystem ps;

	private LowBobAudio audio;

	public SC_S_SpaceCraft(double x, double y, double width, double height, int z) {
		super(x, y, width, height, z);

		xAcc = ACC_State.IDLE;
		yAcc = ACC_State.IDLE;

		lc_cnt = 0;
		this.audio = new LowBobAudio("resources/sounds/laser.wav");

		// add thurster
		thurster = new SC_S_Thurster(-16, 18, 15, 7, 3);
		light = new LowBobSimpleLight(-45,2,80,40, 3, 0.7, 1, new Color(0xff, 78, 43));
		this.addSprite(thurster);
		this.addSprite(light);

		// add particlesystem
		LowBobDirectedBehavior pdb = new LowBobDirectedBehavior(new Vec2d(-10,0));

		ps = new LowBobParticleSystem(-8,17, 3, 20,pdb,5, 1) {
			@Override
			protected LowBobParticle instanciate(double x, double y, int z, Vec2d velocity) {
				return new LowBobParticle(x,y,0,0,z,velocity) {
					@Override
					public void loadImage() {
						this.img = new LowBobSimpleLight(0,0,13,13, 3,0.7,1,new Color(255, 133, 6)).getImage();
					}
				};
			}
		};

		ps.Start();
		this.addSprite(ps);

		// add score counter
		score = 0;

		// collider
		this.colliders.add(new LowBobCollider(SC_S_Plutonium.class));
		this.colliders.add(new LowBobCollider(SC_S_Laser_Red.class));
		this.colliders.add(new LowBobCollider(SC_S_EMP.class));
	}

	@Override
	public void loadImage() {
		this.img = ImageCreator.create("resources/pics/spacecraft.png");
	}

	@Override
	public void move() {



		this.vx = calcVelocityX();
		this.vy = calcVelocityY();

		this.x += vx;
		this.y += vy;

		// update thurster
		if(xAcc == ACC_State.NEG) {
			this.removeSprite(thurster);
			this.removeSprite(light);
			this.ps.Stop();
		}
		else {
			if(!this.sprites.contains(thurster)) {
				this.addSprite(thurster);
				this.addSprite(light);
				this.ps.Start();
			}
		}

		// internal counter updates
		if(lc_cnt <= LASER_COOLDOWN)
			lc_cnt++;

		// update score ui
		if(this.score_ui != null)
			this.score_ui.setText(Integer.toString(score));
	}

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		switch(keyEvent.getKeyCode()) {
		case KeyEvent.VK_W:
			yAcc = ACC_State.NEG;
			break;
		case KeyEvent.VK_S:
			yAcc = ACC_State.POS;
			break;
		case KeyEvent.VK_A:
			xAcc = ACC_State.NEG;
			break;
		case KeyEvent.VK_D:
			xAcc = ACC_State.POS;
			break;
		case KeyEvent.VK_SPACE:
			fire();
			break;
		case KeyEvent.VK_M:
			launch_missile();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent keyEvent) {
		switch(keyEvent.getKeyCode()) {

		case KeyEvent.VK_W:
		case KeyEvent.VK_S:
			yAcc = ACC_State.IDLE;
			break;

		case KeyEvent.VK_A:
		case KeyEvent.VK_D:
			xAcc = ACC_State.IDLE;
			break;
		}
	}

	@Override
	public void collide(LowBobSprite lbs) {
		if(lbs instanceof SC_S_Plutonium) {
            score++;
        } else if (lbs instanceof SC_S_Laser_Red) {
		    this.def_watch.decrease();
        } else if (lbs instanceof  SC_S_EMP) {
			this.def_watch.destroy_shield();
		}
		LowBobRuntime.getInstance().removeSprite(lbs);
	}

	// internal member functions
	private double calcVelocityY() {
		double result;

		boolean outOfLowerBounds = (y > (800 - MARGIN));
		boolean outOfUpperBounds = (y < MARGIN);

		if(yAcc == ACC_State.POS && vy < SPEED && !outOfLowerBounds)
			result = vy + ACCELERATION;
		else if(yAcc == ACC_State.NEG && vy > -SPEED && !outOfUpperBounds)
			result = vy - ACCELERATION;
		else if(yAcc == ACC_State.IDLE && vy < 0 && !outOfLowerBounds || outOfUpperBounds)
			result = vy + ACCELERATION;
		else if(yAcc == ACC_State.IDLE && vy > 0 || outOfLowerBounds)
			result = vy - ACCELERATION;
		else
			result = vy;

		return result;
	}

	private double calcVelocityX() {
		double result;

		boolean outOfLowerBounds = (x > (1400 - MARGIN));
		boolean outOfUpperBounds = (x < MARGIN);

		if(xAcc == ACC_State.POS && vx < SPEED && !outOfLowerBounds)
			result = vx + ACCELERATION;
		else if(xAcc == ACC_State.NEG && vx > -SPEED && !outOfUpperBounds)
			result = vx - ACCELERATION;
		else if(xAcc == ACC_State.IDLE && vx < 0 && !outOfLowerBounds || outOfUpperBounds)
			result = vx + ACCELERATION;
		else if(xAcc == ACC_State.IDLE && vx > 0 || outOfLowerBounds)
			result = vx - ACCELERATION;
		else
			result = vx;

		return result;
	}

	private void fire() {
		if(lc_cnt >= LASER_COOLDOWN) {
			LowBobRuntime.getInstance().addSprite(new SC_S_Laser_Green(this.x + 30, this.y + 20, 8, 2, 3));
			lc_cnt = 0;
			//this.audio.play();
		}
	}

	private void launch_missile() {
		LowBobRuntime.getInstance().addSprite(new SC_S_Missile(this.x + 30, this.y + 20, 32, 16, 3));
	}

	public void explode() {
		LowBobRuntime runtime = LowBobRuntime.getInstance();
		this.ps.dispatch();
		runtime.removeSprite(this);
		runtime.addSprite(new SC_S_Explosion(this.x,this.y,0,0, 3));
	}

	private enum ACC_State {
		POS,
		NEG,
		IDLE
	}

	public void setScoreUI(LowBobTextUI score_ui) {
		this.score_ui = score_ui;
	}
	public void setDefWatch(SC_S_SHWatch def_watch) { this.def_watch = def_watch; }

}
