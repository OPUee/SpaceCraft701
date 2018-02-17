package app.impl.sprites;

import java.awt.event.KeyEvent;

import lowbob.LowBobSkyBox;
import lowbob.LowBobSprite;
import lowbob.util.ImageCreator;

public class SC_S_Skybox extends LowBobSprite {

	private LowBobSkyBox sky;
	private int counter;
	
	public SC_S_Skybox(double x, double y, double width, double height, int z) {
		super(x, y, width, height, z);
	}

	@Override
	public void loadImage() {
		sky = new LowBobSkyBox(ImageCreator.create("resources/pics/background.png"), 1400, 800, 5);
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
}
