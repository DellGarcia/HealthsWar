package br.com.healthswar.player.view.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingConstants;

import br.com.anonymous.frontend.Button;
import br.com.anonymous.frontend.Label;
import br.com.anonymous.frontend.Panel;
import br.com.healthswar.comunication.MatchRequest;
import br.com.healthswar.comunication.Phases;
import br.com.healthswar.gameplay.Card;
import br.com.healthswar.gameplay.CardView;
import br.com.healthswar.gameplay.Fighter;
import br.com.healthswar.gameplay.FighterField;
import br.com.healthswar.gameplay.FighterSelector;
import br.com.healthswar.gameplay.Player;
import br.com.healthswar.view.Fonts;

public class MainViewBase extends JFrame {

	private static final long serialVersionUID = 6543296877886261474L;
	
	protected final Toolkit tk = Toolkit.getDefaultToolkit();
	
	protected Player player;
	protected Player opponent;
	
	protected Panel container;

	protected CardView cardView;
	protected FighterSelector fighterSelector;
	
	protected Label lblPhase;
	protected Label myHP;
	protected Label opHP;
	
	protected FighterField[] myFighters;
	protected FighterField[] opFighters;
	
	protected boolean myTurn;
	protected Phases phase;
	protected Fighter attacker;
	
	protected Button btnEndTurn, btnBattle;
	
	protected MainViewBase() {
		setTitle("Health's War");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(tk.getScreenSize());
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		container = new Panel(new Color(54,54,54));
		container.setSize(getSize());
		setContentPane(container);
		
		myFighters = new FighterField[5];
		for(int i = 0; i < myFighters.length; i++) {
			myFighters[i] = new FighterField();
		}
		
		opFighters = new FighterField[5];
		for(int i = 0; i < opFighters.length; i++) {
			opFighters[i] = new FighterField();
		}
		
		cardView = new CardView(null);
		cardView.setLocation(container.getWidth()/2 - cardView.getWidth()/2, container.getHeight()/2 - cardView.getHeight()/2);
		container.add(cardView);
		
		fighterSelector = new FighterSelector();
		fighterSelector.setLocation(container.getWidth()/2 - fighterSelector.getWidth()/2, container.getHeight()/2 - fighterSelector.getHeight()/2);
		container.add(fighterSelector);
	}
	
	protected void initializeComponents() {
		colocarPhase();
		colocarHealthPoint();
		colocarDeck();
		colocarMao();
		colocarFighters();
		colocarMemoria();
		colocarDescarte();
		colocarBattle();
		colocarEndTurn();
	}
	
	/**
	 * Construction methods
	 * */
	protected void colocarHealthPoint() {
		myHP = new Label(200, 40, Integer.toString(player.getField().getHealthsPoint()), Fonts.DESTAQUE, Color.WHITE, container.getBackground(), SwingConstants.CENTER, SwingConstants.CENTER);
		myHP.setLocation(0, 0);
		container.add(myHP);
		
		opHP = new Label(200, 40, Integer.toString(player.getField().getHealthsPoint()), Fonts.DESTAQUE, Color.WHITE, container.getBackground(), SwingConstants.CENTER, SwingConstants.CENTER);
		opHP.setLocation(container.getWidth() - opHP.getWidth(), 0);
		container.add(opHP);
	}
	
	protected void colocarDeck() {
		ArrayList<Card> myDeck = player.getField().getDeck();
		ArrayList<Card> opDeck = opponent.getField().getDeck();
		
		int x = 1920 - 200, y = container.getHeight() - 200;
		for(int i = 0; i < myDeck.size(); i++) {
			myDeck.get(i).setSize(100, 141);
			myDeck.get(i).setLocation(x, y);
			if((i+1) % 5 == 0) {
				x++;
				y++;
			}
			container.add(myDeck.get(i));
		}
		
		x = 200; y = 80;
		for(int i = 0; i < opDeck.size(); i++) {
			opDeck.get(i).setSize(100, 141);
			opDeck.get(i).setLocation(x, y);
			if((i+1) % 5 == 0) {
				x--;
				y--;
			}
			container.add(opDeck.get(i));
		}
	}
	
	protected void colocarMao() {
		ArrayList<Card> myHand = player.getField().getHand();
		ArrayList<Card> opHand = opponent.getField().getHand();
		
		int x = container.getWidth() /2 - (myHand.size()*110 - 10)/2;
		for(Card card: myHand) {
			card.setSize(100, 141);
			card.setLocation(x, container.getHeight() - 200);
			card.setVisible(true);
			container.add(card);
			x+=110;
		}
		
		x = container.getWidth() /2 - (opHand.size()*110 - 10)/2;
		for(Card card: opHand) {
			card.setSize(100, 141);
			card.setLocation(x, 80);
			card.setTurned(true);
			card.setVisible(true);
			container.add(card);
			x+=110;
		}
		container.repaint();
	}
	
	protected void colocarPhase() {
		lblPhase = new Label(
				400, 40,
				"", new Font("Verdana", Font.PLAIN, 20),
				Color.WHITE, container.getBackground(),
				SwingConstants.CENTER, SwingConstants.CENTER
		);
		lblPhase.setLocation(container.getWidth()/2 - lblPhase.getWidth()/2, 20);
		container.add(lblPhase);
	}
	
	protected void colocarBattle() {
		btnBattle = new Button(
				150, 50,
				Color.DARK_GRAY, Color.WHITE,
				Fonts.NORMAL, "Battle",
				Color.BLACK, 1,
				new Color(65,105,225), Color.WHITE);
		btnBattle.setLocation(container.getWidth() - 170, container.getHeight()/2 - 50/2 - 50);
		btnBattle.setVisible(false);
		btnBattle.addActionListener(startBattle());
		container.add(btnBattle);
	}
	
	protected void colocarEndTurn() {
		btnEndTurn = new Button(
				150, 50,
				Color.DARK_GRAY, Color.WHITE,
				Fonts.NORMAL, "End Turn",
				Color.BLACK, 1,
				new Color(65,105,225), Color.WHITE);
		btnEndTurn.setLocation(container.getWidth() - 170, container.getHeight()/2 - 50/2 + 50);
		btnEndTurn.setVisible(false);
		btnEndTurn.addActionListener(endTurn());
		container.add(btnEndTurn);
	}
	
	protected void colocarFighters() {
		int x = container.getWidth()/2 - (5*120-20)/2;
		for(int i = 0; i < myFighters.length; i++) {
			myFighters[i].setLocation(x, container.getHeight() - 400);
			myFighters[i].energyCounter.setLocation(x, container.getHeight() - 250);
			container.add(myFighters[i]);
			container.add(myFighters[i].energyCounter);
			x += 120;
		}
		
		x = container.getWidth()/2 - (5*120-20)/2;
		for(int i = 0; i < opFighters.length; i++) {
			opFighters[i].setLocation(x, 280);
			opFighters[i].energyCounter.setLocation(x, 220);
			container.add(opFighters[i]);
			container.add(opFighters[i].energyCounter);
			x += 120;
		}
		
	}
	
	protected void colocarMemoria() {
		ArrayList<Fighter> myMemory = player.getField().getMemory();
		ArrayList<Fighter> opMemory = opponent.getField().getMemory();
		
		int x = container.getWidth()/2 - (7*120-20)/2, y = container.getHeight() - 400;
		for(int i = 0; i < myMemory.size(); i++) {
			myMemory.get(i).setSize(100, 141);
			myMemory.get(i).setLocation(x, y);
			if((i+1) % 5 == 0) {
				x++;
				y++;
			}
			container.add(myMemory.get(i));
		}
		
		y = 280;
		for(int i = 0; i < opMemory.size(); i++) {
			opMemory.get(i).setSize(100, 141);
			opMemory.get(i).setLocation(x, y);
			if((i+1) % 5 == 0) {
				x++;
				y++;
			}
			container.add(opMemory.get(i));
		}
		
	}
	
	protected void colocarDescarte() {
		ArrayList<Card> myDiscard = player.getField().getDescarte();
		ArrayList<Card> opDiscard = opponent.getField().getDescarte();
		
		int x = container.getWidth()/2 + (6*120)/2, y = container.getHeight() - 400;
		for(int i = myDiscard.size() - 1; i >= 0 ; i--) {
			myDiscard.get(i).setSize(100, 141);
			myDiscard.get(i).setLocation(x, y);
			if((i+1) % 3 == 0) {
				x+=2;
				y+=2;
			}
			container.add(myDiscard.get(i));
		}
		
		y = 280;
		for(int i = opDiscard.size() - 1; i >= 0; i--) {
			opDiscard.get(i).setSize(100, 141);
			opDiscard.get(i).setLocation(x, y);
			if((i+1) % 3 == 0) {
				x+=2;
				y+=2;
			}
			container.add(opDiscard.get(i));
		}
	}
	
	/** Actions Listeners */
		private ActionListener startBattle() {
			return new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(myTurn) {
						player.write(MatchRequest.START_BATTLE);
						btnBattle.setVisible(false);
					}
				}
			};
		}
		
		private ActionListener endTurn() {
			return new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(myTurn) {
						player.write(MatchRequest.END_THE_TURN);
						btnEndTurn.setVisible(false);
						btnBattle.setVisible(false);
					}
				}
			};
		}
		
}
