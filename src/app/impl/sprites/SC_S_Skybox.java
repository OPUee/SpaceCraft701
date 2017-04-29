package app.impl.sprites;

import java.awt.event.KeyEvent;

import lowbob.LowBobSkyBox;
import lowbob.LowBobSprite;
import lowbob.util.ImageCreator;

public class SC_S_Skybox extends LowBobSprite {

	private LowBobSkyBox sky;
	private int counter;
	
	public SC_S_Skybox(double x, double y, double width, double height) {
		super(x, y, width, height);
	}

	@Override
	public void loadImage() {
		sky = new LowBobSkyBox(ImageCreator.create("src/app/resources/background.png"), 1400, 800, 10);
		this.img = sky.next();
		counter = 0;
	}

	@Override
	public void move() {
		counter++;
		if(counter > 10000)
			counter = 0;
		this.img = sky.next();
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
