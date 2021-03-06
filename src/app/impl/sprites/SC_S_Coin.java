package app.impl.sprites;

import java.awt.event.KeyEvent;

import lowbob.LowBobCollider;
import lowbob.LowBobRuntime;
import lowbob.LowBobSprite;
import lowbob.util.ImageAnimator;
import lowbob.util.ImageCreator;

public class SC_S_Coin extends LowBobSprite {
	
	ImageAnimator ia;
	int counter;

	public SC_S_Coin(double x, double y, double width, double height, int z) {
		super(x, y, width, height, z);
		
		counter = 0;
	}

	@Override
	public void loadImage() {
		ia = new ImageAnimator(ImageCreator.create("resources/pics/coin.png"), 14);
		this.img = ia.next();
	}

	@Override
	public void move() {
		counter++;
		if(counter > 1000)
			counter = 0;
		
		if((counter % 10) == 0)
			this.img = ia.next();		
	}
}
