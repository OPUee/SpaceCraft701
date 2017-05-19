package lowbob.UI;

import lowbob.util.ImageCreator;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by opuee on 05.05.17.
 */
public class LowBobButtonUI extends LowBobUI {

    public LowBobButtonUI(double x, double y, double width, double height, String img_path) {
        super(x, y, width, height);

        this.img = ImageCreator.create(img_path);
    }

    @Override
    public void loadImage() {
        //pass to level above
    }
}
