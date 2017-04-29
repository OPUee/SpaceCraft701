package lowbob.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageCreator {

	public static BufferedImage create(String path) {
		try {
			return (BufferedImage)ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static BufferedImage combine(BufferedImage img1, BufferedImage img2) {
		// this algorithm is from:
		// http://stackoverflow.com/questions/20826216/copy-two-buffered-image-into-one-image-side-by-side
		// thanks a lot!
		
	    //do some calculate first
	    int wid = img1.getWidth()+img2.getWidth();
	    int height = Math.max(img1.getHeight(),img2.getHeight());
	    //create a new buffer and draw two image into the new image
	    BufferedImage newImage = new BufferedImage(wid,height, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = newImage.createGraphics();
	    Color oldColor = g2.getColor();
	    //fill background
	    g2.setPaint(Color.WHITE);
	    g2.fillRect(0, 0, wid, height);
	    //draw image
	    g2.setColor(oldColor);
	    g2.drawImage(img1, null, 0, 0);
	    g2.drawImage(img2, null, img1.getWidth(), 0);
	    g2.dispose();
	    return newImage;
	}
}
