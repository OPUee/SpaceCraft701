package app.impl.sprites;

import lowbob.LowBobCollider;
import lowbob.LowBobRuntime;
import lowbob.LowBobSprite;
import lowbob.UI.LowBobTextUI;
import lowbob.audio.LowBobAudio;
import lowbob.illumination.LowBobSimpleLight;
import lowbob.util.ImageCreator;

import java.awt.*;
import java.awt.event.KeyEvent;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

/**
 * Created by opuee on 24.04.17.
 */
public class SC_S_SpaceCraft extends LowBobSprite{

	private final double SPEED = 8;
	private final double ACCELERATION = 0.4f;
	private final double LASER_COOLDOWN = 7;
	private final int MARGIN = 100;

	private ACC_State xAcc, yAcc;
	private int lc_cnt;

	private int score;
	private LowBobTextUI score_ui;

	private SC_S_Thurster thurster;
	private LowBobSimpleLight light;

	private LowBobAudio audio;

	public SC_S_SpaceCraft(double x, double y, double width, double height) {
		super(x, y, width, height);

		xAcc = ACC_State.IDLE;
		yAcc = ACC_State.IDLE;

		lc_cnt = 0;
		this.audio = new LowBobAudio("resources/sounds/laser.wav");

		// add thurster
		thurster = new SC_S_Thurster(-16, 18, 15, 7);
		light = new LowBobSimpleLight(-18,6,30,30, 0.7, 1, new Color(0xff, 78, 43));
		this.addSprite(thurster);
		this.addSprite(light);

		// add score counter
		score = 0;

		// collider
		this.colliders.add(new LowBobCollider(SC_S_Coin.class));
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
			//this.removeSprite(light);
		}
		else {
			if(!this.sprites.contains(thurster))
				this.addSprite(thurster);
				//this.addSprite(light);
		}

		// internal counter updates
		if(lc_cnt <= LASER_COOLDOWN)
			lc_cnt++;

		// update score ui
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
		if(lbs instanceof SC_S_Coin) {
			score++;
			LowBobRuntime.getInstance().removeSprite(lbs);
		}
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
			LowBobRuntime.getInstance().addSprite(new SC_S_Laser(this.x + 30, this.y + 20, 8, 2));
			lc_cnt = 0;
			this.audio.play();
		}
	}

	private void launch_missile() {
		LowBobRuntime.getInstance().addSprite(new SC_S_Missile(this.x + 30, this.y + 20, 32, 16));
	}

	private enum ACC_State {
		POS,
		NEG,
		IDLE
	}

	public void setScoreUI(LowBobTextUI score_ui) {
		this.score_ui = score_ui;
	}

}
