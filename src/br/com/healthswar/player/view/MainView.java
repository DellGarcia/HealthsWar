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
import br.com.healthswar.gameplay.Fighter;
import br.com.healthswar.gameplay.Deck;
import br.com.healthswar.gameplay.Hand;
import br.com.healthswar.gameplay.Player;
import br.com.healthswar.view.Fonts;

@SuppressWarnings("serial")
public class MainView extends JFrame {

	private Toolkit tk;
	
	private Panel container;
	
	public Player player;
	
	private CardView cardView;
	
	private Label lblPhase;
	private Label myHP;
	private Label opHP;
	
	private Fighter[] fighterField;
	
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
		
		fighterField = new Fighter[5];
		
		init();
		awaitYourTurn().start();
		
		continuousRefresh().start();
		setVisible(true);
	}
	
	
	private void init() {
		try {
			player.setHealthsPoint(player.in.readInt());
			player.setDeck((Deck) player.in.readObject());
			player.setHand((Hand) player.in.readObject());
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		colocarPhase();
		colocarHealthPoint();
		colocarDeck(player.getDeck().getCartas());
		colocarMao(player.getHand().getCartas());
		colocarEndTurn();
	}
	
//	private void atualizarHealtPoints(Label label, int novaVida) {
//		label.setText(""+novaVida);
//	}
	
	private void colocarHealthPoint() {
		myHP = new Label(200, 40, player.getHealthsPoint()+"", Fonts.DESTAQUE, Color.WHITE, container.getBackground(), SwingConstants.CENTER, SwingConstants.CENTER);
		myHP.setLocation(0, 0);
		container.add(myHP);
		
		opHP = new Label(200, 40, player.getHealthsPoint()+"", Fonts.DESTAQUE, Color.WHITE, container.getBackground(), SwingConstants.CENTER, SwingConstants.CENTER);
		opHP.setLocation(container.getWidth() - opHP.getWidth(), 0);
		container.add(opHP);
	}
	
	private void colocarDeck(ArrayList<Carta> cartas) {
		int x = 1920 - 200, y = 1080 - 200;
		for(int i = 0; i < cartas.size(); i++) {
			cartas.get(i).setSize(100, 141);
			cartas.get(i).setLocation(x, y);
			if((i+1) % 3 == 0) {
				x++;
				y++;
			}
			container.add(cartas.get(i));
		}
	}
	
	private void colocarMao(ArrayList<Carta> cartas) {
		int x = container.getWidth() /2 - (cartas.size()*110 - 10)/2;
		for(Carta card: cartas) {
			card.setSize(100, 141);
			card.setLocation(x, 1080 -200);
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
	
	public void colocarCombatentes(Fighter[] fighters) {
		for(int i = 0; i < fighters.length; i++) {
			if(fighters[i] != null) {
				
			}
		}
		
	}
	
	public void colocarMemoria(ArrayList<Fighter> memory) {
		for(int i = 0; i < memory.size(); i++) {
			memory.get(i).setSize(100, 141);
//			memory.get(i).setLocation(x, y);
//			if((i+1) % 3 == 0) {
//				x++;
//				y++;
//			}
//			container.add(cartas.get(i));
		}
	}
	
	public void update() {
		container.removeAll();
		container.repaint();
		container = new Panel(new Color(54,54,54));
		container.setSize(getSize());
		setContentPane(container);
		
		colocarPhase();
		colocarHealthPoint();
		colocarDeck(player.getDeck().getCartas());
		colocarMao(player.getHand().getCartas());
		colocarEndTurn();
	}
	
	public void mostarCardView(Carta card) {
		if(cardView != null) {
			container.remove(cardView);
		}
		cardView = new CardView(card);
		cardView.setLocation(container.getWidth()/2 - cardView.getWidth()/2, container.getHeight()/2 - cardView.getHeight()/2);
		container.add(cardView);
		container.repaint();
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
								// Aqui pode ter uma animacao
								drawCard();
								break;
							case BATTLE_PHASE:
								lblPhase.setText("Your Turn: BATTLE PHASE");
								break;
							case END_PHASE:
								lblPhase.setText("Your Turn: End Phase");
								break;
							case MAIN_PHASE:
								lblPhase.setText("Your Turn: Main Phase");
								break;
						}
						
					} else {
						lblPhase.setText("Opponent Turn");
						awaitYourTurn().start();
						myTurn = false;
					}
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void drawCard() {
		try {
			player.out.writeObject(MatchRequest.DRAW_A_CARD);
			MatchResponse res = (MatchResponse) player.in.readObject();
			if(res == MatchResponse.AVALIBLE_CARD) {
				Carta card = player.getDeck().getCartas().get(0);
				card.setVirado(false);
				card.setLocal(CardLocal.HAND);
				player.getHand().getCartas().add(card);
				player.getDeck().getCartas().remove(0);
				update();
				awaitYourTurn().start();
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}	
	
	private ActionListener endTurn() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(myTurn) {
						player.out.writeObject(MatchRequest.END_THE_TURN);
						btnEndTurn.setVisible(false);
						awaitYourTurn().start();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		};
	}
	
	private Thread continuousRefresh() {
		return new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try {
						container.repaint();
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
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
