package lowbob.illumination;

import lowbob.LowBobSprite;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by opuee on 15.09.17.
 */
public class LowBobSimpleLight extends LowBobSprite {

    private int THRESH = 4;

    public LowBobSimpleLight(double x, double y, int width, int height, int z, double intensity, double brightness, Color color) {
        super(x, y, width, height, z);

        //TODO: create image by matrix
        this.img = new BufferedImage((int)width, (int)height, BufferedImage.TYPE_INT_ARGB);
        double[][] mat = createMatrix((int)width, (int)height, intensity, brightness);

        float[] hsb = new float[3];
        Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsb);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // use gaussian matrix for saturation value
                float saturation = 1 - (float)mat[i][j];
                saturation = saturation > 1 ? 1 : saturation;
                saturation = saturation < 0 ? 0 : saturation;
                Color c = Color.getHSBColor(hsb[0], saturation, hsb[2]);

                // use gaussian matrix for RGB alpha value
                int alpha = (int)(mat[i][j] * 255);
                alpha = alpha > 255 ? 255 : alpha;
                c = new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
                this.img.setRGB(i,j,c.getRGB());
            }
        }
    }

    @Override
    public void loadImage() {
        // nothing to do here
    }

    private double[][] createMatrix(int width, int height, double intensity, double brightness)
    {
        double[][] mat = new double[width][height];
        //try {
            //PrintWriter writer = new PrintWriter("/home/opuee/out.csv", "UTF-8");

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    double x = (i * ((double)THRESH / (double)width)) - (THRESH / 2);
                    double y = (j * ((double)THRESH / (double)height)) - (THRESH / 2);

                    x = Math.pow(brightness * x, 2);
                    y = Math.pow(brightness * y, 2);

                    mat[i][j] = intensity * Math.exp(-x  - y);
                    //writer.print(mat[i][j] + ";");
                }
                //writer.print("\n");
            }

            //writer.close();

        /*} catch (Exception ex) {
            System.out.println("shit happens");
            System.out.println(ex.getMessage());
            System.out.println(ex.getLocalizedMessage());
            System.out.println(ex.getStackTrace());
            System.out.println(ex.getCause());


        }*/

        return mat;
    }
}
