package lowbob.UI;

import lowbob.util.ImageCreator;
import lowbob.util.events.LowBobMouseEvent;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by opuee on 05.05.17.
 */
public class LowBobButtonUI extends LowBobUI {

    private ArrayList<LowBobMouseEvent> m_mouselistener;

    public LowBobButtonUI(double x, double y, double width, double height, int z, String img_path) {
        super(x, y, width, height, z);

        this.m_mouselistener = new ArrayList<>();

        this.img = ImageCreator.create(img_path);
    }

    public void addMouseListener(LowBobMouseEvent listener) {
        this.m_mouselistener.add(listener);
    }

    @Override
    public void loadImage() {
        //pass to level above
    }
    
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
    	for (LowBobMouseEvent event : this.m_mouselistener) {
    	    event.onMouseClicked(this, mouseEvent);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        for (LowBobMouseEvent event : this.m_mouselistener) {
            event.onMousePressed(this, mouseEvent);
        }
    }
}
