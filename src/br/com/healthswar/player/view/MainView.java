package br.com.healthswar.player.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
import br.com.healthswar.gameplay.Carta;
import br.com.healthswar.gameplay.Energy;
import br.com.healthswar.gameplay.Fighter;
import br.com.healthswar.gameplay.FighterField;
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
	
	private Label lblPhase;
	private Label myHP;
	private Label opHP;
	
	private FighterField[] myFighters;
	private FighterField[] opFighters;
	
	private boolean myTurn;
	
	private Button btnEndTurn;
	
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
		awaitYourTurn().start();
		
		setVisible(true);
	}
	
	
	private void init() {
		try {
			player.setField((Field) player.in.readObject());
			opponent = new Player((Field) player.in.readObject());
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		cardView = new CardView(null);
		cardView.setLocation(container.getWidth()/2 - cardView.getWidth()/2, container.getHeight()/2 - cardView.getHeight()/2);
		container.add(cardView);
		
		colocarPhase();
		colocarHealthPoint();
		colocarDeck(player.getField().getDeck().getCartas(), opponent.getField().getDeck().getCartas());
		colocarMao(player.getField().getHand().getCartas(), opponent.getField().getHand().getCartas());
		colocarFighters();
		colocarMemoria(player.getField().getMemoria(), container.getHeight() - 400);
		colocarMemoria(opponent.getField().getMemoria(), 280);
		colocarEndTurn();
		container.repaint();
	}
	
//	private void atualizarHealtPoints(Label label, int novaVida) {
//		label.setText(""+novaVida);
//	}
	
	private void colocarHealthPoint() {
		myHP = new Label(200, 40, player.getField().getHealthsPoint()+"", Fonts.DESTAQUE, Color.WHITE, container.getBackground(), SwingConstants.CENTER, SwingConstants.CENTER);
		myHP.setLocation(0, 0);
		container.add(myHP);
		
		opHP = new Label(200, 40, player.getField().getHealthsPoint()+"", Fonts.DESTAQUE, Color.WHITE, container.getBackground(), SwingConstants.CENTER, SwingConstants.CENTER);
		opHP.setLocation(container.getWidth() - opHP.getWidth(), 0);
		container.add(opHP);
	}
	
	private void colocarDeck(ArrayList<Carta> myDeck, ArrayList<Carta> opDeck) {
		int x = 1920 - 200, y = 1080 - 200;
		for(int i = 0; i < myDeck.size(); i++) {
			myDeck.get(i).setSize(100, 141);
			myDeck.get(i).setLocation(x, y);
			if((i+1) % 4 == 0) {
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
	
	private void colocarMao(ArrayList<Carta> myHand, ArrayList<Carta> opHand) {
		int x = container.getWidth() /2 - (myHand.size()*110 - 10)/2;
		for(Carta card: myHand) {
			card.setSize(100, 141);
			card.setLocation(x, container.getHeight() - 200);
			card.setVisible(true);
			container.add(card);
			x+=110;
		}
		
		x = container.getWidth() /2 - (opHand.size()*110 - 10)/2;
		for(Carta card: opHand) {
			card.setSize(100, 141);
			card.setLocation(x, 80);
			card.setVirado(true);
			card.setVisible(true);
			container.add(card);
			x+=110;
		}
	}
	
	private void colocarPhase() {
		lblPhase = new Label(
				300, 40,
				"", new Font("Verdana", Font.PLAIN, 20),
				Color.WHITE, container.getBackground(),
				SwingConstants.CENTER, SwingConstants.CENTER
		);
		lblPhase.setLocation(container.getWidth()/2 - lblPhase.getWidth()/2, 20);
		container.add(lblPhase);
	}
	
	private void colocarEndTurn() {
		btnEndTurn = new Button(
				container.getWidth() - 170, container.getHeight()/2 - 50/2,
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
			container.add(myFighters[i]);
			x += 120;
		}
		
		x = container.getWidth()/2 - (5*120-20)/2;
		for(int i = 0; i < opFighters.length; i++) {
			opFighters[i].setLocation(x, 280);
			container.add(opFighters[i]);
			x += 120;
		}
	}
	
	public void colocarMemoria(ArrayList<Fighter> memory, int height) {
		int x = container.getWidth()/2 - (6*120-20)/2, y = height;
		for(int i = 0; i < memory.size(); i++) {
			memory.get(i).setSize(100, 141);
			memory.get(i).setLocation(x, y);
			if((i+1) % 3 == 0) {
				x++;
				y++;
			}
			container.add(memory.get(i));
		}
	}
	
	public void mostarCardView(Carta card) {
		cardView.setCard(card);
		cardView.setVisible(true);
		cardView.repaint();
	}
	
	/**
	 * Player Actions
	 * */
	private Thread awaitYourTurn() {
		return new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					MatchResponse response = (MatchResponse) player.in.readObject();
					
					if(response == MatchResponse.YOUR_TURN) {
						Phases phase = (Phases) player.in.readObject();
						
						btnEndTurn.setVisible(true);
						myTurn = true;
						
						switch (phase) {
							case DRAW_PHASE:
								lblPhase.setText("Your Turn: DRAW PHASE");
								drawCard();
								break;
							case BATTLE_PHASE:
								lblPhase.setText("Your Turn: BATTLE PHASE");
								break;
							case END_PHASE:
								lblPhase.setText("Your Turn: END PHASE");
								break;
							case MAIN_PHASE:
								lblPhase.setText("Your Turn: MAIN PHASE");
								awaitMainAction();
								break;
						}
						
					} else {
						awaitOpponentAction();
					}
						
					awaitYourTurn().start();
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void awaitOpponentAction() throws ClassNotFoundException, IOException {
		myTurn = false;
		Phases phase = (Phases) player.in.readObject();
		
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
		
		MatchResponse res = (MatchResponse) player.in.readObject();
		
		switch (res) {
			case AVALIBLE_CARD:
				Carta card = opponent.getField().getDeck().getCartas().get(0);
				card.setVirado(false);
				card.setLocal(CardLocal.HAND);
				opponent.getField().getHand().getCartas().add(card);
				opponent.getField().getDeck().getCartas().remove(0);
				colocarMao(player.getField().getHand().getCartas(), opponent.getField().getHand().getCartas());
				break;
			case FIGHTER_READY:
				Fighter fighter = (Fighter) player.in.readObject();
				for(int i = 0; i < opFighters.length; i++) {
					if(opFighters[i].getFighter() == null) {
						opFighters[i].setFighter(fighter);
						break;
					}
				}
				break;
			case IMPOSSIBLE_TO_USE:
				break;
			case ITEM_USED:
				break;
			case NO_CARDS:
				break;
			case NO_FIGHTER:
				break;
			case OPPONENT_TURN:
				break;
			case SUCCESSFUL_ATACK:
				break;
			case YOUR_TURN:
				myTurn = true;
				break;
			default:
				break;
		}

	}
		
	public void drawCard() {
		try {
			player.out.writeObject(MatchRequest.DRAW_A_CARD);
			MatchResponse res = (MatchResponse) player.in.readObject();
			if(res == MatchResponse.AVALIBLE_CARD) {
				Carta card = player.getField().getDeck().getCartas().get(0);
				card.setVirado(false);
				card.setLocal(CardLocal.HAND);
				player.getField().getHand().getCartas().add(card);
				player.getField().getDeck().getCartas().remove(0);
				colocarMao(player.getField().getHand().getCartas(), opponent.getField().getHand().getCartas());
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}	
	
	private void awaitMainAction() {
		try {
			MatchResponse response = (MatchResponse) player.in.readObject();
			
			switch (response) {
				case FIGHTER_READY:
					Fighter fighter = (Fighter) player.in.readObject();
					for(int i = 0; i < myFighters.length; i++) {
						if(myFighters[i].getFighter() == null) {
							myFighters[i].setFighter(fighter);
							break;
						}
					}
					break;
	
				case ITEM_USED:
					break;
					
				default:
					break;
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean sendFighter(Fighter fighter) {
		if(myTurn) {
			try {
				player.out.writeObject(MatchRequest.SEND_A_FIGHTER);
				player.out.writeObject(fighter);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public void useItem(Item item) {
		
	}
	
	public void putEnergy(Energy energy) {
		
	}
	
	private ActionListener endTurn() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(myTurn) {
						player.out.writeObject(MatchRequest.END_THE_TURN);
						btnEndTurn.setVisible(false);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		};
	}
	
	/**
	 * Singletown methods
	 */
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
	
	
}
