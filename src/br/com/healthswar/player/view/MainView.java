package br.com.healthswar.player.view;

import br.com.healthswar.comunication.MatchRequest;
import br.com.healthswar.comunication.MatchResponse;
import br.com.healthswar.comunication.Phases;
import br.com.healthswar.gameplay.CardLocal;
import br.com.healthswar.gameplay.Card;
import br.com.healthswar.gameplay.Energy;
import br.com.healthswar.gameplay.Fighter;
import br.com.healthswar.gameplay.FighterField;
import br.com.healthswar.gameplay.Item;
import br.com.healthswar.gameplay.Player;

@SuppressWarnings("serial")
public final class MainView extends MainViewBase {

	public static MainView INSTANCE;
	
	private MainView(Player player) {
		this.player = player;
		
		initializeComponents();
		awaitTurn().start();
		
		setVisible(true);
	}
	
	/** Player Actions */
	private Thread awaitTurn() {
		return new Thread(new Runnable() {
			
			@Override
			public void run() {
				MatchResponse response = (MatchResponse) player.read();
				
				refreshHealtPoints();
				
				if(response == MatchResponse.YOUR_TURN) {
					phase = (Phases) player.read();
					btnEndTurn.setVisible(true);
					btnBattle.setVisible(true);
					myTurn = true;
					
					switch (phase) {
						case DRAW_PHASE:
							lblPhase.setText("Your Turn: DRAW PHASE");
							drawCard();
							break;
						case MAIN_PHASE:
							lblPhase.setText("Your Turn: " + phase);
							awaitMainAction();
							break;
						case BATTLE_PHASE:
							lblPhase.setText("Your Turn: BATTLE PHASE");
							btnBattle.setVisible(false);
							awaitMainAction();
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
						player.getField().getFighters()[i] = fighter;
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
				colocarDescarte();
				colocarMao();
				break;
				
			case ENERGY_READY:
				fighter = (Fighter) player.read();
				energy = (Energy) player.read();
				
				for(Fighter lutador: player.getField().getFighters()) {
					if(lutador.id == fighter.id) {
						container.remove(player.getField().getHand().remove(energy));
						player.getField().getDescarte().add(energy);
						lutador = fighter;
						break;
					}
				}
				
				for(FighterField fi: myFighters) {
					if(fi.getFighter().id == fighter.id) {
						fi.setFighter(fighter);
						break;
					}
				}
				
				colocarFighters();
				colocarMao();
				colocarDescarte();
				break;
				
			case SUCCESSFUL_ATACK:
				Fighter attacker = (Fighter) player.read();
				Fighter target = (Fighter) player.read();
				
				target.setHealthPoints(target.getHealthPoints() - attacker.getAtkPower());
				if(target.getHealthPoints() <= 0) {
					opponent.getField().setDamage(target.getHealthPoints());
				}
				
				for(FighterField fi: myFighters) {
					if(fi.getFighter().id == attacker.id) {
						fi.setFighter(attacker);
						break;
					}
				}
				
				for(FighterField fi: opFighters) {
					if(fi.getFighter() != null) {
						if(fi.getFighter().id == target.id) {
							fi.setFighter(target);
							break;
						}
					}	
				}
				break;
				
			default:
				break;
		}
	}
	
	public void awaitOpponentAction() {
		myTurn = false;
		phase = (Phases) player.read();
		lblPhase.setText("Opponent Turn: " + phase);
		
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
						opponent.getField().getFighters()[i] = fighter;
						Card c = opponent.getField().getHand().remove(fighter);
						container.remove(c);
						colocarMao();
						colocarFighters();
						break;
					}
				}
				break;
				
			case ITEM_USED:
				item = (Item) player.read();
				opponent.getField().getDescarte().add(item);
				container.remove(opponent.getField().getHand().remove(item));
				colocarDescarte();
				colocarMao();
				break;
			
			case ENERGY_READY:
				fighter = (Fighter) player.read();
				energy = (Energy) player.read();
				
				for(Fighter lutador: opponent.getField().getFighters()) {
					if(lutador.id == fighter.id) {
						container.remove(opponent.getField().getHand().remove(energy));
						opponent.getField().getDescarte().add(energy);
						lutador = fighter;
						break;
					}
				}
				
				for(FighterField fi: opFighters) {
					if(fi.getFighter().id == fighter.id) {
						fi.setFighter(fighter);
						break;
					}
				}
				
				colocarFighters();
				colocarMao();
				colocarDescarte();
				break;
				
			case SUCCESSFUL_ATACK:
				Fighter attacker = (Fighter) player.read();
				Fighter target = (Fighter) player.read();
				
				target.setHealthPoints(target.getHealthPoints() - attacker.getAtkPower());
				if(target.getHealthPoints() <= 0) {
					player.getField().setDamage(target.getHealthPoints());
				}
				
				for(FighterField fi: opFighters) {
					if(fi.getFighter().id == attacker.id) {
						fi.setFighter(attacker);
						break;
					}
				}
				
				for(FighterField fi: myFighters) {
					if(fi.getFighter() != null) {
						if(fi.getFighter().id == target.id) {
							fi.setFighter(target);
							break;
						}
					}	
				}
				break;
				
			case YOUR_TURN:
				myTurn = true;
				break;
				
			default:
				break;
		}

	}
	
	/** Fire Player Actions */ 
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
		
		public void atack(Fighter attacker, Fighter target) {
			if(myTurn) {
				player.write(MatchRequest.ATACK_THE_OPONENT);
				player.write(attacker);
				player.write(target);
			}
		}
	
	/** Display methods */
		public void mostarCardView(Card card) {
			cardView.setCard(null);
			cardView.setCard(card);
			cardView.setVisible(true);
			cardView.repaint();
		}
		
		public void showSelector() {
			if(myTurn)
				fighterSelector.setSelector(myFighters);
		}
		
		public void showEnemySelector(Fighter fighter) {
			if(myTurn) {
				fighterSelector.setSelector(opFighters);
				this.attacker = fighter;
			}		
		}
		
		public void hideSelector() {
			this.fighterSelector.setVisible(false);
		}
		
		public void handleSelect(Card target) {
			switch (phase) {
				case MAIN_PHASE:
					putEnergy((Fighter) target);
					break;
	
				case BATTLE_PHASE:
					atack(attacker,(Fighter) target);
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
