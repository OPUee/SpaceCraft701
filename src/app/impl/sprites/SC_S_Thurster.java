package app.impl.sprites;

import java.awt.event.KeyEvent;

import lowbob.LowBobSprite;
import lowbob.util.ImageAnimator;
import lowbob.util.ImageCreator;

public class SC_S_Thurster extends LowBobSprite {
	
	private ImageAnimator ai_thurster;
	private int ai_counter;

	public SC_S_Thurster(double x, double y, double width, double height) {
		super(x, y, width, height);
		
		this.ai_counter = 0;
	}

	@Override
	public void loadImage() {
		ai_thurster = new ImageAnimator(ImageCreator.create("src/app/resources/flame.png"), 15);
		this.img = ai_thurster.next();
	}

	@Override
	public void move() {		
		ai_counter++;
		// update image
		if(ai_counter > 1000)
			ai_counter = 0;
		if((ai_counter % 5) == 0)
			this.img = ai_thurster.next();
		
	}

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		
	}

	@Override
	public void keyReleased(KeyEvent keyEvent) {
		
	}

	@Override
	public void collide(LowBobSprite lbs) {
		
	}

}