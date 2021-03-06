package lowbob;

import java.awt.image.BufferedImage;

import lowbob.util.ImageCreator;

public class LowBobSkyBox {
	private BufferedImage img;
	private int width, height;
	double speed, counter;
	
	public LowBobSkyBox(BufferedImage img, int width, int height, double speed) {
		this.img = img;
		this.width = width;
		this.height = height;
		this.speed = speed;
		
		this.counter = 0;		
	}
	
	public BufferedImage next() {
		BufferedImage result;
		counter += speed;
		
		if(counter >= this.img.getWidth())
			counter = 0;
		
		if(counter > (this.img.getWidth() - width))
		{
			int offset = this.img.getWidth() - (int)counter;
			int endpoint = this.width - offset;
			
			result = ImageCreator.combine(this.img.getSubimage((int)counter, 0, offset, this.height),
					this.img.getSubimage(0, 0, endpoint, this.height)); 
		} else {
			result = this.img.getSubimage((int)counter, 0, this.width, this.height);
		}
		
		
		return result;
	}
}
