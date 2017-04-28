package app.impl.sprites;

import java.awt.event.KeyEvent;

import lowbob.LowBobSprite;
import lowbob.util.ImageAnimator;
import lowbob.util.ImageCreator;

public class SC_S_Skybox extends LowBobSprite {

	private ImageAnimator ai_sky;
	private int counter;
	
	public SC_S_Skybox(double x, double y, double width, double height) {
		super(x, y, width, height);
	}

	@Override
	public void loadImage() {
		ai_sky = new ImageAnimator(ImageCreator.create("src/app/resources/background.png"), 1400);
		this.img = ai_sky.next();
		counter = 0;
	}

	@Override
	public void move() {
		/*counter++;
		if(counter > 10000)
			counter = 0;
		if(counter % 10 == 0)
			this.img = ai_sky.next();*/
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
