package br.com.healthswar.gameplay;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import br.com.anonymous.frontend.Label;
import br.com.anonymous.frontend.Panel;
import br.com.healthswar.gameplay.energy.Energy;
import br.com.healthswar.gameplay.fighters.Fighter;
import br.com.healthswar.gameplay.items.Item;
import br.com.healthswar.player.view.main.MainView;
import br.com.healthswar.utils.Fonts;

public class CardView extends Panel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 534020604704164574L;

	private ImageIcon frontImg;
	
	private Card card;
	
	private Label lblMessage;
	private Label lblFundo;
	
	public CardView(Card card) {
		super();
		setSize(300, 424);
		
		init();
		setCard(card);
		
		setVisible(false);
		addMouseListener(this);
	}
	
	private void init() {
		lblMessage = new Label(getWidth(), getHeight(), "", Fonts.DESTAQUE, Color.WHITE, new Color(0, 0, 0, 60), SwingConstants.CENTER, SwingConstants.CENTER);
		lblMessage.setVisible(false);
		lblMessage.setOpaque(true);
		this.add(lblMessage);
		
		lblFundo = new Label(getWidth(), getHeight(), "", Fonts.DESTAQUE, Color.WHITE, null, SwingConstants.CENTER, SwingConstants.CENTER);
		this.add(lblFundo);
	}

	public void setCard(Card card) {
		this.card = card;
		
		if(card != null) {
			if(card instanceof Fighter) {
				lblMessage.setText("Enviar");
			}
			else if(card instanceof Energy) {
				lblMessage.setText("Colocar");
			}
			else if(card instanceof Item) {
				lblMessage.setText("Usar");
			}
			
			this.frontImg = card.frontImg;
			lblFundo.setIcon(frontImg);
		}
	}
	
	public Card getCard() {
		return this.card;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(card instanceof Fighter) {
			MainView.INSTANCE.requestSendFighter((Fighter) card);
			setVisible(false);
		}
		
		if(card instanceof Item) {
			MainView.INSTANCE.requestUseItem((Item) card);
			setVisible(false);
		}
		
		if(card instanceof Energy) {
			MainView.INSTANCE.showSelector();
			setVisible(false);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		lblMessage.setVisible(true);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		lblMessage.setVisible(false);
		setVisible(false);
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

}
