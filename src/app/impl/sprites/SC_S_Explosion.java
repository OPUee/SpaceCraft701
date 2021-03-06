package app.impl.sprites;

import java.awt.event.KeyEvent;

import lowbob.LowBobRuntime;
import lowbob.LowBobSprite;
import lowbob.util.ImageAnimator;
import lowbob.util.ImageCreator;

public class SC_S_Explosion extends LowBobSprite {

	private ImageAnimator ai;
	private int counter;
	
	public SC_S_Explosion(double x, double y, double width, double height, int z) {
		super(x, y, width, height, z);
		
		counter = 0;
	}

	@Override
	public void loadImage() {
		ai = new ImageAnimator(ImageCreator.create("resources/pics/explosion.png"), 30, false, false);
		this.img = ai.next();
	}

	@Override
	public void move() {
		counter ++;
		
		if(ai.isExpired())
			LowBobRuntime.getInstance().removeSprite(this);
		if(counter >= 1000)
			counter = 0;
		if(counter % 2 == 0)
			this.img = ai.next();
	}
}
