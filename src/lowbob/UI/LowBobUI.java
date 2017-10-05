package lowbob.UI;

import lowbob.LowBobSprite;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Created by opuee on 02.05.17.
 */
public abstract class LowBobUI extends LowBobSprite {
	protected ArrayList<LowBobUI> elements;
	
    public LowBobUI(double x, double y, double width, double height) {
        super(x, y, width, height);
        
        this.elements = new ArrayList<>();
    }

    public void mouseClicked(MouseEvent mouseEvent) {}
    public void mousePressed(MouseEvent mouseEvent) {}
    public void mouseReleased(MouseEvent mouseEvent) {}
    public void mouseEntered(MouseEvent mouseEvent) {}
    public void mouseExited(MouseEvent mouseEvent) {}
    
    public ArrayList<LowBobUI> getUIElements() {
    	return new ArrayList<>(this.elements);
    }
    
    
    // override not supported functionality
    @Override
    public void addSprite(LowBobSprite lbs) {
    	throw new NotImplementedException();
    } 
    
    @Override
    public void removeSprite(LowBobSprite lbs) {
    	throw new NotImplementedException();
    }
    
    /*@Override
    public ArrayList<LowBobSprite> getSprites() { 
    	throw new NotImplementedException();
    }*/
}
