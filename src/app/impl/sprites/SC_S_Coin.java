package app.impl.sprites;

import java.awt.event.KeyEvent;

import lowbob.LowBobCollider;
import lowbob.LowBobSprite;
import lowbob.util.ImageAnimator;
import lowbob.util.ImageCreator;

public class SC_S_Coin extends LowBobSprite {
	
	ImageAnimator ia;
	int counter;

	public SC_S_Coin(double x, double y, double width, double height) {
		super(x, y, width, height);
		
		counter = 0;
		
		this.colliders.add(new LowBobCollider(SC_S_laser.class));
	}

	@Override
	public void loadImage() {
		ia = new ImageAnimator(ImageCreator.create("src/app/resources/coin.png"), 14);
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

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent keyEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collide(LowBobSprite lbs) {
		// TODO Auto-generated method stub
		
	}

}
