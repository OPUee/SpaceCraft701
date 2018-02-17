package app.impl.sprites;

import java.awt.event.KeyEvent;
import java.util.Random;

import lowbob.LowBobRuntime;
import lowbob.LowBobSprite;
import lowbob.util.ImageCreator;

public class SC_S_AlienSpawner extends LowBobSprite{

	private Random rnd;
	private int counter;
	
	public SC_S_AlienSpawner(double x, double y, double width, double height, int z) {
		super(x, y, width, height, z);
		
		this.rnd = new Random();
		this.counter = 0;
	}

	@Override
	public void loadImage() {
		this.img = ImageCreator.create("resources/pics/empty.png");
	}

	@Override
	public void move() {
		counter++;
		if(counter >= 120)
		{
			spawn_alien();
			counter = 0;
		}
	}
	
	private void spawn_alien() {
		int ypos = rnd.nextInt(600) + 100;
		LowBobRuntime.getInstance().addSprite(new SC_S_Alien(1500, ypos, 50, 26, 3));
	}
}
