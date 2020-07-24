package br.com.healthswar.gameplay.items;

import java.awt.event.MouseEvent;

import br.com.healthswar.gameplay.Card;
import br.com.healthswar.player.view.main.MainView;

public abstract class Item extends Card  {

	private static final long serialVersionUID = 6383545494201764091L;
	
	protected int duration;
	protected boolean applied;
	
	public Item() {
		super();
		frontImg = loadImage("../../assets/item-md.png");
		setImage();
	}
	
	public abstract void resolve();
	
	@Override
	public void mouseClicked(MouseEvent e) {}


	@Override
	public void mousePressed(MouseEvent e) {}


	@Override
	public void mouseReleased(MouseEvent e) {
		switch (local) {
			case DECK:
				
				break;
	
			case HAND:
				if(!isTurned()) 
					MainView.INSTANCE.showCardView(this);
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
				if(!isTurned())
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
				if(!isTurned())
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
	public void mouseDragged(MouseEvent e) {}


	@Override
	public void mouseMoved(MouseEvent e) {}

	public int getDuration() {
		return duration;
	}

	public void reduceDuration() {
		this.duration--;
	}
	
	public void setApplied(boolean applied) {
		this.applied = applied;
	}

}
