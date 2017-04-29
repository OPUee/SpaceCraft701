package lowbob.util;

import java.awt.image.BufferedImage;

public class ImageAnimator {
	
	private BufferedImage img;
	private int img_size, counter, frames, iter;
	private boolean loop, revert, expired;
	
	public ImageAnimator(BufferedImage img, int img_size) {
		this.img = img;
		this.img_size = img_size;
		
		this.loop = true;
		this.revert = false;
		this.expired = false;
		this.iter = 1;
		this.counter = -1;
		frames = img.getWidth() / img_size;
	}
	
	public ImageAnimator(BufferedImage img, int img_size, boolean loop, boolean revert) {
		this.img = img;
		this.img_size = img_size;
		
		this.loop = loop;
		this.revert = revert;
		this.expired = false;
		this.iter = 1;
		this.counter = -1;
		frames = img.getWidth() / img_size;
	}
	
	public BufferedImage next() {
		counter += iter;
		if(counter < 0 || counter >= frames)
        {
            if(revert) {
                iter = -iter;
                counter += 2 * iter; // update counter to valid state
            }
            else if(loop) {
                counter = 0;
            }
            else {
                expired = true;
                return this.img.getSubimage((frames - 1) * img_size, 0, img_size, img.getHeight());
            }
        }
		return this.img.getSubimage(counter * img_size, 0, img_size, img.getHeight());
	}
	
	public boolean isExpired() {
		return this.expired;
	}

}
