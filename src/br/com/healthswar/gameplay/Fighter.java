package br.com.healthswar.gameplay;

import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;

import br.com.healthswar.player.view.MainView;

public class Fighter extends Carta implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = -851267654637882260L;
	
	private ArrayList<Energy> energies;
	
	protected int healthPoints;
	protected int atkPower;
	
	public Fighter(int id) throws URISyntaxException {
		super(id);
		frontImg = loadImage("../assets/card-sm.jpg");
		this.energies = new ArrayList<Energy>();
		super.repaint();
	}

	public ArrayList<Energy> getEnergies() {
		return energies;
	}

	public void setEnergies(ArrayList<Energy> energies) {
		this.energies = energies;
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
				if(!virado) 
					MainView.INSTANCE.mostarCardView(this);
				break;
				
			case FIELD:
				
				break;
				
			case MEMORY:
				
				break;
			case DESCARTE:
				break;
				
			case SELECTOR:
				MainView.INSTANCE.putEnergy(this);
				MainView.INSTANCE.hideSelector();
				this.local = CardLocal.FIELD;
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
				if(!virado)
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
				if(!virado)
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
