package lowbob.UI;

import lowbob.util.ImageCreator;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by opuee on 05.05.17.
 */
public class LowBobButtonUI extends LowBobUI implements MouseListener {

    MouseListener ml;

    public LowBobButtonUI(double x, double y, double width, double height, String img_path) {
        super(x, y, width, height);

        this.img = ImageCreator.create(img_path);
    }

    public void setMouseListener(MouseListener ml) {
        this.ml = ml;
    }

    @Override
    public void loadImage() {
        //pass to level above
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        System.out.println("clicked");
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        System.out.println("pressed");
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        System.out.println("released");
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        System.out.println("entered");
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        System.out.println("exited");
    }
}
