package lowbob.util;

import java.awt.image.BufferedImage;

public class ImageAnimator {
	
	private BufferedImage img;
	private int img_size, counter, frames;
	
	public ImageAnimator(BufferedImage img, int img_size) {
		this.img = img;
		this.img_size = img_size;
		
		this.counter = -1;
		frames = img.getWidth() / img_size;
	}
	
	public BufferedImage next() {
		counter++;
		if(counter >= frames)
			counter = 0;
		return this.img.getSubimage(counter * img_size, 0, img_size, img.getHeight());
	}

}
