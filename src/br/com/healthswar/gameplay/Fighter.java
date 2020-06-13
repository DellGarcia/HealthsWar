package br.com.healthswar.gameplay;

import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;

import br.com.healthswar.player.view.main.MainView;

public class Fighter extends Card implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = -851267654637882260L;
	
	private ArrayList<Energy> energies;
	
	protected int healthPoints;
	protected int atkPower;
	
	public Fighter(int id) throws URISyntaxException {
		super(id);
		Random r = new Random();
		healthPoints = r.nextInt(100) + 50;
		atkPower = r.nextInt(30) + 20;
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
	
	public int getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}

	public int getAtkPower() {
		return atkPower;
	}

	public void setAtkPower(int atkPower) {
		this.atkPower = atkPower;
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
				MainView.INSTANCE.showEnemySelector(this);
				break;
				
			case MEMORY:
				
				break;
			case DESCARTE:
				break;
				
			case SELECTOR:
				MainView.INSTANCE.handleSelect(this);
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
