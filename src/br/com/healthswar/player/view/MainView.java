package br.com.healthswar.player.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingConstants;

import br.com.dellgarcia.frontend.Button;
import br.com.dellgarcia.frontend.Label;
import br.com.dellgarcia.frontend.Panel;
import br.com.healthswar.comunication.MatchRequest;
import br.com.healthswar.comunication.MatchResponse;
import br.com.healthswar.comunication.Phases;
import br.com.healthswar.gameplay.CardLocal;
import br.com.healthswar.gameplay.CardView;
import br.com.healthswar.gameplay.Card;
import br.com.healthswar.gameplay.Energy;
import br.com.healthswar.gameplay.Fighter;
import br.com.healthswar.gameplay.FighterField;
import br.com.healthswar.gameplay.FighterSelector;
import br.com.healthswar.gameplay.Item;
import br.com.healthswar.gameplay.Field;
import br.com.healthswar.gameplay.Player;
import br.com.healthswar.view.Fonts;

@SuppressWarnings("serial")
public class MainView extends JFrame {

	private Toolkit tk;
	
	private Panel container;
	
	public Player player;
	public Player opponent;
	
	private CardView cardView;
	private FighterSelector fighterSelector;
	
	private Label lblPhase;
	private Label myHP;
	private Label opHP;
	
	private FighterField[] myFighters;
	private FighterField[] opFighters;
	
	private boolean myTurn;
	
	private Button btnEndTurn, btnBattle;
	
	public static MainView INSTANCE;
	
	private MainView(Player p) {
		tk = Toolkit.getDefaultToolkit();
		player = p;
		
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
		
		init();
		awaitTurn().start();
		
		setVisible(true);
	}
	
	
	private void init() {
		player.setField((Field) player.read());
		opponent = new Player((Field) player.read());
		
		cardView = new CardView(null);
		cardView.setLocation(container.getWidth()/2 - cardView.getWidth()/2, container.getHeight()/2 - cardView.getHeight()/2);
		container.add(cardView);
		
		fighterSelector = new FighterSelector();
		fighterSelector.setLocation(container.getWidth()/2 - fighterSelector.getWidth()/2, container.getHeight()/2 - fighterSelector.getHeight()/2);
		container.add(fighterSelector);
		
		colocarPhase();
		colocarHealthPoint();
		colocarDeck();
		colocarMao();
		colocarFighters();
		colocarMemoria(player.getField().getMemory(), container.getHeight() - 400);
		colocarMemoria(opponent.getField().getMemory(), 280);
		colocarDescarte(player.getField().getDescarte(), container.getHeight() - 400);
		colocarDescarte(player.getField().getDescarte(), 280);
		colocarBattle();
		colocarEndTurn();
	}
	
//	private void atualizarHealtPoints(Label label, int novaVida) {
//		label.setText(""+novaVida);
//	}
	
	/**
	 * Construction methods
	 * */
	private void colocarHealthPoint() {
		myHP = new Label(200, 40, player.getField().getHealthsPoint()+"", Fonts.DESTAQUE, Color.WHITE, container.getBackground(), SwingConstants.CENTER, SwingConstants.CENTER);
		myHP.setLocation(0, 0);
		container.add(myHP);
		
		opHP = new Label(200, 40, player.getField().getHealthsPoint()+"", Fonts.DESTAQUE, Color.WHITE, container.getBackground(), SwingConstants.CENTER, SwingConstants.CENTER);
		opHP.setLocation(container.getWidth() - opHP.getWidth(), 0);
		container.add(opHP);
	}
	
	private void colocarDeck() {
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
	
	private void colocarMao() {
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
	
	private void colocarPhase() {
		lblPhase = new Label(
				400, 40,
				"", new Font("Verdana", Font.PLAIN, 20),
				Color.WHITE, container.getBackground(),
				SwingConstants.CENTER, SwingConstants.CENTER
		);
		lblPhase.setLocation(container.getWidth()/2 - lblPhase.getWidth()/2, 20);
		container.add(lblPhase);
	}
	
	private void colocarBattle() {
		btnBattle = new Button(
				container.getWidth() - 170, container.getHeight()/2 - 50/2 - 50,
				150, 50,
				Color.DARK_GRAY, Color.WHITE,
				Fonts.NORMAL, "Battle",
				Color.BLACK, 1,
				new Color(65,105,225), Color.WHITE);
		btnBattle.setVisible(false);
		btnBattle.addActionListener(startBattle());
		container.add(btnBattle);
	}
	
	private void colocarEndTurn() {
		btnEndTurn = new Button(
				container.getWidth() - 170, container.getHeight()/2 - 50/2 + 50,
				150, 50,
				Color.DARK_GRAY, Color.WHITE,
				Fonts.NORMAL, "End Turn",
				Color.BLACK, 1,
				new Color(65,105,225), Color.WHITE);
		btnEndTurn.setVisible(false);
		btnEndTurn.addActionListener(endTurn());
		container.add(btnEndTurn);
	}
	
	private void colocarFighters() {
		int x = container.getWidth()/2 - (5*120-20)/2;
		for(int i = 0; i < myFighters.length; i++) {
			myFighters[i].setLocation(x, container.getHeight() - 400);
			myFighters[i].energyCounter.setLocation(x, container.getHeight() - 250);
			if(myFighters[i].getFighter() != null) 
				myFighters[i].energyCounter.setText(Integer.toString(myFighters[i].getFighter().getEnergies().size()));
			container.add(myFighters[i]);
			container.add(myFighters[i].energyCounter);
			x += 120;
		}
		
		x = container.getWidth()/2 - (5*120-20)/2;
		for(int i = 0; i < opFighters.length; i++) {
			opFighters[i].setLocation(x, 280);
			opFighters[i].energyCounter.setLocation(x, 220);
			if(opFighters[i].getFighter() != null)
				opFighters[i].energyCounter.setText(Integer.toString(opFighters[i].getFighter().getEnergies().size()));
			container.add(opFighters[i]);
			container.add(opFighters[i].energyCounter);
			x += 120;
		}
	}
	
	private void colocarMemoria(ArrayList<Fighter> memory, int height) {
		int x = container.getWidth()/2 - (6*120-20)/2, y = height;
		for(int i = 0; i < memory.size(); i++) {
			memory.get(i).setSize(100, 141);
			memory.get(i).setLocation(x, y);
			if((i+1) % 5 == 0) {
				x++;
				y++;
			}
			container.add(memory.get(i));
		}
	}
	
	private void colocarDescarte(ArrayList<Card> descarte, int height) {
		int x = container.getWidth()/2 + (6*120)/2, y = height;
		for(int i = 0; i < descarte.size(); i++) {
			descarte.get(i).setSize(100, 141);
			descarte.get(i).setLocation(x, y);
			if((i+1) % 3 == 0) {
				x+=2;
				y+=2;
			}
			container.add(descarte.get(i));
		}
	}
	
	/** Player Actions */
	private Thread awaitTurn() {
		return new Thread(new Runnable() {
			
			@Override
			public void run() {
				MatchResponse response = (MatchResponse) player.read();
				
				if(response == MatchResponse.YOUR_TURN) {
					Phases phase = (Phases) player.read();
					btnEndTurn.setVisible(true);
					btnBattle.setVisible(true);
					myTurn = true;
					
					switch (phase) {
						case DRAW_PHASE:
							lblPhase.setText("Your Turn: DRAW PHASE");
							drawCard();
							break;
						case MAIN_PHASE:
							lblPhase.setText("Your Turn: MAIN PHASE");
							awaitMainAction();
							break;
						case BATTLE_PHASE:
							lblPhase.setText("Your Turn: BATTLE PHASE");
							btnBattle.setVisible(false);
							break;
						case END_PHASE:
							lblPhase.setText("Your Turn: END PHASE");
							break;
					}
					
				} else if(response == MatchResponse.OPPONENT_TURN){
					awaitOpponentAction();
				} 
					
				awaitTurn().start();
			}
		});
	}
	
	public void awaitOpponentAction() {
		Phases phase = (Phases) player.read();
		myTurn = false;
		
		switch (phase) {
			case DRAW_PHASE:
				lblPhase.setText("Opponent Turn: DRAW PHASE");
				break;
			case BATTLE_PHASE:
				lblPhase.setText("Opponent Turn: BATTLE PHASE");
				break;
			case END_PHASE:
				lblPhase.setText("Opponent Turn: END PHASE");
				break;
			case MAIN_PHASE:
				lblPhase.setText("Opponent Turn: MAIN PHASE");
				break;
		}
		
		MatchResponse res = (MatchResponse) player.read();
		
		Fighter fighter;
		Energy energy;
		Item item;
		
		switch (res) {
			case AVALIBLE_CARD:
				Card card = opponent.getField().getDeck().get(0);
				card.setTurned(false);
				card.setLocal(CardLocal.HAND);
				opponent.getField().getHand().add(card);
				opponent.getField().getDeck().remove(0);
				container.remove(card);
				colocarMao();
				break;
				
			case FIGHTER_READY:
				fighter = (Fighter) player.read();
				for(int i = 0; i < opFighters.length; i++) {
					if(opFighters[i].getFighter() == null) {
						opFighters[i].setFighter(fighter);
						opponent.getField().getFighter()[i] = fighter;
						Card c = opponent.getField().getHand().remove(fighter);
						container.remove(c);
						colocarMao();
						break;
					}
				}
				break;
				
			case ITEM_USED:
				item = (Item) player.read();
				opponent.getField().getDescarte().add(item);
				container.remove(opponent.getField().getHand().remove(item));
				colocarDescarte(opponent.getField().getDescarte(), 280);
				colocarMao();
				break;
			
			case ENERGY_READY:
				fighter = (Fighter) player.read();
				energy = (Energy) player.read();
				
				for(Fighter lutador: opponent.getField().getFighter()) {
					if(lutador.id == fighter.id) {
						container.remove(opponent.getField().getHand().remove(energy));
						lutador = fighter;
						break;
					}
				}
				
				for(FighterField fi: myFighters) {
					if(fi.getFighter() == fighter) {
						fi.setFighter(fighter);
						break;
					}
				}
				
				colocarFighters();
				colocarMao();
				break;
				
			case SUCCESSFUL_ATACK:
				break;
				
			case YOUR_TURN:
				myTurn = true;
				break;
				
			case BATTLE_STARTED:
				break;
				
			default:
				break;
		}

	}
		
	public void drawCard() {
		player.write(MatchRequest.DRAW_A_CARD);
		MatchResponse res = (MatchResponse) player.read();
		if(res == MatchResponse.AVALIBLE_CARD) {
			Card card = player.getField().getDeck().get(0);
			card.setTurned(false);
			card.setLocal(CardLocal.HAND);
			player.getField().getHand().add(card);
			player.getField().getDeck().remove(0);
			container.remove(card);
			colocarDeck();
			colocarMao();
		}
	}	
	
	private void awaitMainAction() {
		MatchResponse response = (MatchResponse) player.read();
		
		Fighter fighter;
		Energy energy;
		Item item;
		
		switch (response) {
			case FIGHTER_READY:
				fighter = (Fighter) player.read();
				for(int i = 0; i < myFighters.length; i++) {
					if(myFighters[i].getFighter() == null) {
						myFighters[i].setFighter(fighter);
						player.getField().getFighter()[i] = fighter;
						Card c = player.getField().getHand().remove(fighter);
						container.remove(c);
						colocarMao();
						break;
					}
				}
				break;

			case ITEM_USED:
				item = (Item) player.read();
				player.getField().getDescarte().add(item);
				container.remove(player.getField().getHand().remove(item));
				colocarDescarte(player.getField().getDescarte(), container.getHeight() - 400);
				colocarMao();
				break;
				
			case ENERGY_READY:
				fighter = (Fighter) player.read();
				energy = (Energy) player.read();
				
				for(Fighter lutador: player.getField().getFighter()) {
					if(lutador.id == fighter.id) {
						container.remove(player.getField().getHand().remove(energy));
						lutador = fighter;
						break;
					}
				}
				
				System.out.println(fighter.getEnergies().size());
				for(FighterField fi: myFighters) {
					if(fi.getFighter() == fighter) {
						fi.setFighter(fighter);
						break;
					}
				}
				
				colocarFighters();
				colocarMao();
				break;
				
			default:
				break;
		}
	}
	
	public boolean sendFighter(Fighter fighter) {
		if(myTurn) {
			player.write(MatchRequest.SEND_A_FIGHTER);
			player.write(fighter);
			return true;
		}
		return false;
	}
	
	public void useItem(Item item) {
		if(myTurn) {
			player.write(MatchRequest.USE_AN_ITEM);
			player.write(item);
		}
	}
	
	public void putEnergy(Fighter fighter) {
		if(myTurn) {
			Energy energy = (Energy)cardView.getCard();
			player.write(MatchRequest.PUT_ENERGY);
			player.write(fighter);
			player.write(energy);
		}
	}
	
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
	
	/** Singleton methods */
	public static MainView getInstance(Player player) {
		if(INSTANCE == null) {
			INSTANCE = new MainView(player);
		}
		return INSTANCE;
	}
	
	public static void destroy() {
		INSTANCE.dispose();
		INSTANCE = null;
	}
	
	/** Display methods */
	public void mostarCardView(Card card) {
		cardView.setCard(null);
		cardView.setCard(card);
		cardView.setVisible(true);
		cardView.repaint();
	}
	
	public CardView getCardView() {
		return this.cardView;
	}
	
	public void showSelector() {
		if(myTurn)
			fighterSelector.setSelector(myFighters);
	}
	
	public void hideSelector() {
		this.fighterSelector.setVisible(false);
	}
}
