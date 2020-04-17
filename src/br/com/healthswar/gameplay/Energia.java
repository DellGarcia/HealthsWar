package br.com.healthswar.gameplay;

import java.awt.event.MouseEvent;

import br.com.healthswar.player.view.MainView;

public class Energia extends Carta  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1138132937122501319L;

	public Energia() {
		super();
		this.frontImg = "src/br/com/healthswar/assets/energy-sm.jpg";
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
				MainView.INSTANCE.mostarCardView(this);
				break;
				
			case FIELD:
				
				break;
				
			case MEMORY:
				
				break;
		}
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		switch (local) {
			case DECK:
				
				break;
	
			case HAND:
				setLocation(getX(), getY() - 20); 
				break;
				
			case FIELD:
				
				break;
				
			case MEMORY:
				
				break;
		}
	}


	@Override
	public void mouseExited(MouseEvent e) {
		switch (local) {
			case DECK:
				
				break;
	
			case HAND:
				setLocation(getX(), getY() + 20); 
				break;
				
			case FIELD:
				
				break;
				
			case MEMORY:
				
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
