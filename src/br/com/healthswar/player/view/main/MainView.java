package br.com.healthswar.player.view.main;

import br.com.healthswar.comunication.MatchResponse;
import br.com.healthswar.comunication.Phases;
import br.com.healthswar.gameplay.Field;
import br.com.healthswar.gameplay.Player;

public final class MainView extends MainViewStructure {

	private static final long serialVersionUID = -6732925043777836792L;
	
	public static MainView INSTANCE;
	
	private MainView(Player player) {
		this.player = player;
		
		this.player.setField((Field) player.read());
		this.opponent = new Player((Field) player.read());
		
		initializeComponents();
		awaitTurn().start();
		
		setVisible(true);
	}
	
	private Thread awaitTurn() {
		return new Thread(new Runnable() {
			
			@Override
			public void run() {
				MatchResponse turn = (MatchResponse) player.read();
				
				verifyMatchStatus(turn);
				
				refreshHealthPoints();
				
				setTurn(turn);
				
				refreshPhase((Phases) player.read());
				
				awaitAction();
					
				awaitTurn().start();
			}
		});
	}
	
	public void awaitAction() {
		MatchResponse res = (MatchResponse) player.read();
		
		switch (res) {
			case AVALIBLE_CARD:
				drawCard();
				break;
				
			case FIGHTER_READY:
				sendFighter();
				break;
				
			case ITEM_USED:
				useItem();
				break;
			
			case ENERGY_READY:
				putEnergy();
				break;
				
			case SUCCESSFUL_ATACK:
				atack();
				break;
				
			case YOUR_TURN:
				setTurn(res);
				break;
				
			default:
				break;
		}

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
		
}
