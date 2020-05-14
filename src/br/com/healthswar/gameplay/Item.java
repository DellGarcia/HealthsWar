package br.com.healthswar.gameplay;

import java.awt.event.MouseEvent;
import java.net.URISyntaxException;

import br.com.healthswar.player.view.MainView;

public class Item extends Card  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5422150266430590077L;

	public Item(int id) throws URISyntaxException {
		super(id);
		frontImg = loadImage("../assets/item-sm.png");
		super.repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {

	}


	@Override
	public void mousePressed(MouseEvent e) {

	}


	@Override
	public void mouseReleased(MouseEvent e) {
		switch (local) {
			case DECK:
				
				break;
	
			case HAND:
				if(!turned) 
					MainView.INSTANCE.mostarCardView(this);
				break;
				
			case FIELD:
				
				break;
				
			case MEMORY:
				
				break;
			case DESCARTE:
				break;
			case SELECTOR:
				break;
			default:
				break;
		}
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		switch (local) {
			case DECK:
				
				break;
	
			case HAND:
				if(!turned)
					setLocation(getX(), getY() - 20);
				break;
				
			case FIELD:
				
				break;
				
			case MEMORY:
				
				break;
			case DESCARTE:
				break;
			case SELECTOR:
				break;
			default:
				break;
		}
	}


	@Override
	public void mouseExited(MouseEvent e) {
		switch (local) {
			case DECK:
				
				break;
	
			case HAND:
				if(!turned)
					setLocation(getX(), getY() + 20);
				break;
				
			case FIELD:
				
				break;
				
			case MEMORY:
				
				break;
			case DESCARTE:
				break;
			case SELECTOR:
				break;
			default:
				break;
		}
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
