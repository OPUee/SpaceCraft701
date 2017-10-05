package lowbob.util.events;

import java.awt.event.MouseEvent;

/**
 * Created by opuee on 05.10.17.
 */
public interface LowBobMouseEvent {

    public void onMouseClicked(Object sender, MouseEvent e);
    public void onMousePressed(Object sender, MouseEvent e);
}
