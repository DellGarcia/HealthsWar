package br.com.healthswar.player.view.main;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import br.com.anonymous.frontend.Button;
import br.com.anonymous.frontend.Label;
import br.com.anonymous.frontend.Panel;
import br.com.healthswar.comunication.MatchRequest;
import br.com.healthswar.comunication.Phases;
import br.com.healthswar.gameplay.Card;
import br.com.healthswar.gameplay.CardView;
import br.com.healthswar.gameplay.FighterField;
import br.com.healthswar.gameplay.FighterSelector;
import br.com.healthswar.gameplay.Player;
import br.com.healthswar.gameplay.fighters.Fighter;
import br.com.healthswar.player.view.View;
import br.com.healthswar.utils.ColorsUtil;
import br.com.healthswar.utils.Fonts;

public class MainViewBase extends View {

	private static final long serialVersionUID = 6543296877886261474L;
	
	protected Player player;
	protected Player opponent;
	
	protected Panel container;

	protected CardView cardView;
	protected FighterSelector fighterSelector;
	
	protected Label lblPhase;
	protected Label myHP;
	protected Label opHP;
	protected Label lblTurn;
	
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
		
		container = new Panel(ColorsUtil.BACKGROUND_COLOR);
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
		cardView.setLocation(getWidth() / 2 - cardView.getWidth() / 2, getHeight() / 2 - cardView.getHeight() / 2);
		container.add(cardView);
		
		fighterSelector = new FighterSelector();
		fighterSelector.setLocation(getWidth() / 2 - fighterSelector.getWidth() / 2, getHeight() / 2 - fighterSelector.getHeight() / 2);
		container.add(fighterSelector);
	}
	
	protected void initializeComponents() {
		Phase();
		Turn();
		HealthPoint();
		Deck();
		Hand();
		Fighters();
		Memory();
		Discard();
		Battle();
		EndTurn();
	}

	protected void Phase() {
		lblPhase = new Label(
				400, 40,
				null, Fonts.TITLE,
				ColorsUtil.LETTERS_COLOR, container.getBackground(),
				SwingConstants.CENTER, SwingConstants.CENTER);
		lblPhase.setLocation((getWidth() - lblPhase.getWidth()) / 2, 30);

		container.add(lblPhase);
	}

	protected void Turn() {
		lblTurn = new Label(
				200, 40,
				null, Fonts.TITLE,
				ColorsUtil.LETTERS_COLOR, container.getBackground(),
				SwingConstants.CENTER, SwingConstants.CENTER);
		lblTurn.setLocation((getWidth() - lblTurn.getWidth()) / 2, 0);

		container.remove(lblTurn);
		container.add(lblTurn);
	}

	protected void HealthPoint() {
		myHP = new Label(200, 40, Integer.toString(player.getField().getHealthsPoint()),
				Fonts.DESTAQUE, ColorsUtil.LETTERS_COLOR, container.getBackground(),
				SwingConstants.CENTER, SwingConstants.CENTER);
		myHP.setLocation(0, 0);

		opHP = new Label(200, 40, Integer.toString(player.getField().getHealthsPoint()),
				Fonts.DESTAQUE, ColorsUtil.LETTERS_COLOR, container.getBackground(),
				SwingConstants.CENTER, SwingConstants.CENTER);
		opHP.setLocation(getWidth() - opHP.getWidth(), 0);

		
		container.remove(myHP);
		container.remove(opHP);
		container.add(myHP);
		container.add(opHP);
	}
	
	protected void Deck() {
		ArrayList<Card> myDeck = player.getField().getDeck();
		ArrayList<Card> opDeck = opponent.getField().getDeck();
		
		int x = 1920 - 200, y = getHeight() - 200;
		for(int i = 0; i < myDeck.size(); i++) {
			myDeck.get(i).setSize(100, 141);
			myDeck.get(i).setLocation(x, y);
			if((i + 1) % 5 == 0) {
				x++;
				y++;
			}
			container.remove(myDeck.get(i));
			container.add(myDeck.get(i));
		}
		
		x = 200; y = 80;
		for(int i = 0; i < opDeck.size(); i++) {
			opDeck.get(i).setSize(100, 141);
			opDeck.get(i).setLocation(x, y);
			if((i + 1) % 5 == 0) {
				x--;
				y--;
			}
			container.remove(opDeck.get(i));
			container.add(opDeck.get(i));
		}
	}
	
	protected void Hand() {
		ArrayList<Card> myHand = player.getField().getHand();
		ArrayList<Card> opHand = opponent.getField().getHand();
		
		int x = getWidth() / 2 - (myHand.size() * 110 - 10) / 2;
		for(Card card: myHand) {
			card.setSize(100, 141);
			card.setLocation(x, getHeight() - 200);
			card.setVisible(true);
			container.add(card);
			x += 110;
		}
		
		x = getWidth() / 2 - (opHand.size() * 110 - 10) / 2;
		for(Card card: opHand) {
			card.setSize(100, 141);
			card.setLocation(x, 80);
			card.setTurned(true);
			card.setVisible(true);
			container.add(card);
			x += 110;
		}
		container.repaint();
	}

	protected void Fighters() {
		int x = getWidth() / 2 - (5 * 120 - 20) / 2;
		for (FighterField myFighter : myFighters) {
			myFighter.setLocation(x, getHeight() - 400);
			myFighter.energyCounter.setLocation(x, getHeight() - 250);
			container.add(myFighter);
			container.add(myFighter.energyCounter);
			x += 120;
		}

		x = getWidth() / 2 - (5 * 120 - 20) / 2;
		for (FighterField opFighter : opFighters) {
			opFighter.setLocation(x, 280);
			opFighter.energyCounter.setLocation(x, 220);
			container.add(opFighter);
			container.add(opFighter.energyCounter);
			x += 120;
		}
	}

	protected void Memory() {
		ArrayList<Fighter> myMemory = player.getField().getMemory();
		ArrayList<Fighter> opMemory = opponent.getField().getMemory();

		int x = getWidth()/2 - (7 * 120 - 20) / 2, y = getHeight() - 400;
		for(int i = 0; i < myMemory.size(); i++) {
			myMemory.get(i).setSize(100, 141);
			myMemory.get(i).setLocation(x, y);

			if((i + 1) % 5 == 0) {
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
	
	protected void Battle() {
		btnBattle = new Button(
				160, 50,
				new Color(154, 26, 26), Color.WHITE,
				Fonts.NORMAL, "Battle",
				new Color(125, 15, 15), 3,
				new Color(65, 10, 10),
				Color.WHITE, 45);
		btnBattle.setLocation(getWidth() - btnBattle.getWidth() - 20,
				getHeight() / 2 - 50 / 2 - 50);
		btnBattle.setVisible(false);
		btnBattle.addActionListener(startBattle());
		container.add(btnBattle);
	}
	
	protected void EndTurn() {
		btnEndTurn = new Button(
				btnBattle.getWidth(), btnBattle.getHeight(),
				new Color(154, 26, 26), Color.WHITE,
				Fonts.NORMAL, "End Turn",
				new Color(125, 15, 15), 3,
				new Color(65, 10, 10),
				Color.WHITE, 45);

		btnEndTurn.setLocation(btnBattle.getX(),
				getHeight() / 2 - 50 / 2 + 50);
		btnEndTurn.setVisible(false);
		btnEndTurn.addActionListener(endTurn());
		container.add(btnEndTurn);
	}
	
	protected void Discard() {
		ArrayList<Card> myDiscard = player.getField().getDescarte();
		ArrayList<Card> opDiscard = opponent.getField().getDescarte();
		
		int x = getWidth() / 2 + (6 * 120) / 2, y = getHeight() - 400;
		for(int i = myDiscard.size() - 1; i >= 0 ; i--) {
			myDiscard.get(i).setSize(100, 141);
			myDiscard.get(i).setLocation(x, y);

			if((i + 1) % 3 == 0) {
				x += 2;
				y += 2;
			}

			container.add(myDiscard.get(i));
		}
		
		y = 280;
		for(int i = opDiscard.size() - 1; i >= 0; i--) {
			opDiscard.get(i).setSize(100, 141);
			opDiscard.get(i).setLocation(x, y);
			if((i + 1) % 3 == 0) {
				x += 2;
				y += 2;
			}

			container.add(opDiscard.get(i));
		}
		
		myDiscard = null;
		opDiscard = null;
	}

	public Player getActive() {
		return myTurn?player:opponent;
	}
	
	/** [Actions Listeners] */
	private ActionListener startBattle() {
		return ActionEvent -> {
			if(myTurn) {
				player.write(MatchRequest.START_BATTLE);
				btnBattle.setVisible(false);
			}
		};
	}

	private ActionListener endTurn() {
		return ActionEvent -> {
			if(myTurn) {
				player.write(MatchRequest.END_THE_TURN);
				btnEndTurn.setVisible(false);
				btnBattle.setVisible(false);
			}
		};
	}
}
