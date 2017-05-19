package lowbob.UI;

import lowbob.LowBobSprite;

import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by opuee on 02.05.17.
 */
public abstract class LowBobUI extends LowBobSprite {
    public LowBobUI(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public void mouseClicked(MouseEvent mouseEvent) {}
    public void mousePressed(MouseEvent mouseEvent) {}
    public void mouseReleased(MouseEvent mouseEvent) {}
    public void mouseEntered(MouseEvent mouseEvent) {}
    public void mouseExited(MouseEvent mouseEvent) {}
}
