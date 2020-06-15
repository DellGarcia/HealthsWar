package br.com.healthswar.player.view.main;

import br.com.healthswar.comunication.MatchRequest;
import br.com.healthswar.comunication.MatchResponse;
import br.com.healthswar.comunication.Phases;
import br.com.healthswar.gameplay.Card;
import br.com.healthswar.gameplay.CardLocal;
import br.com.healthswar.gameplay.Energy;
import br.com.healthswar.gameplay.Fighter;
import br.com.healthswar.gameplay.FighterField;
import br.com.healthswar.gameplay.Item;
import br.com.healthswar.gameplay.Player;

public class MainViewStructure extends MainViewBase {

	private static final long serialVersionUID = 1702140455193280420L;

	/** Match Actions */
		protected void drawCard() {
			Player target = myTurn?player:opponent;
			
			Card card = target.getField().getDeck().get(0);
			card.setTurned(false);
			card.setLocal(CardLocal.HAND);
			target.getField().getHand().add(card);
			target.getField().getDeck().remove(0);
			container.remove(card);
			colocarDeck();
			colocarMao();
		}
	
		
		protected void sendFighter() {
			Fighter fighter = (Fighter) player.read();
			FighterField[] field = myTurn?myFighters:opFighters;
			Player target = myTurn?player:opponent;
			
			for(int i = 0; i < field.length; i++) {
				if(field[i].getFighter() == null) {
					field[i].setFighter(fighter);
					target.getField().getFighters()[i] = fighter;
					Card c = target.getField().getHand().remove(fighter);
					container.remove(c);
					colocarMao();
					colocarFighters();
					break;
				}
			}
		}
		
		protected void useItem() {
			Item item = (Item) player.read();
			Player target = myTurn?player:opponent;
			
			target.getField().getDescarte().add(item);
			container.remove(target.getField().getHand().remove(item));
			
			colocarDescarte();
			colocarMao();
		}
		
		protected void putEnergy() {
			Fighter fighter = (Fighter) player.read();
			Energy energy = (Energy) player.read();
			Player target = myTurn?player:opponent;
			FighterField[] fighters = myTurn?myFighters:opFighters;
			
			for(Fighter lutador: target.getField().getFighters()) {
				if(lutador.id == fighter.id) {
					container.remove(target.getField().getHand().remove(energy));
					target.getField().getDescarte().add(energy);
					lutador = fighter;
					break;
				}
			}
			
			for(FighterField fi: fighters) {
				if(fi.getFighter().id == fighter.id) {
					fi.setFighter(fighter);
					break;
				}
			}
			
			colocarFighters();
			colocarMao();
			colocarDescarte();
		}
		
		protected void atack() {
			Fighter attacker = (Fighter) player.read();
			Fighter fighterTarget = (Fighter) player.read();
			
			if(fighterTarget.getHealthPoints() < 0) {
				(!myTurn?player:opponent)
					.getField().setDamage(fighterTarget.getHealthPoints());
				
				FighterField[] targetField = (!myTurn?myFighters:opFighters);
				for(int i = 0; i < targetField.length; i++) {
					if(targetField[i].getFighter() != null) {
						if(targetField[i].getFighter().id == fighterTarget.id) {
							targetField[i].setFighter(null);
						}
					}
				}
				
				fighterTarget.setLocal(CardLocal.MEMORY);
				(myTurn?player:opponent).getField().getMemory().add(fighterTarget);
				(!myTurn?player:opponent).getField().removeFighter(fighterTarget);
				
				colocarMemoria();
			}
			
			for(FighterField fi: myTurn?myFighters:opFighters) {
				if(fi.getFighter() != null) {
					if(fi.getFighter().id == attacker.id) {
						fi.setFighter(attacker);
						break;
					}
				}
			}
			
			for(FighterField fi: !myTurn?myFighters:opFighters) {
				if(fi.getFighter() != null) {
					if(fi.getFighter().id == fighterTarget.id) {
						fi.setFighter(fighterTarget);
						break;
					}
				}	
			}
		}
	
	/** Request Player Actions */ 
		protected void resquestDrawCard() {
			player.write(MatchRequest.DRAW_A_CARD);
		}	
		
		public void requestSendFighter(Fighter fighter) {
			if(myTurn) {
				player.write(MatchRequest.SEND_A_FIGHTER);
				player.write(fighter);
			}
		}
		
		public void requestUseItem(Item item) {
			if(myTurn) {
				player.write(MatchRequest.USE_AN_ITEM);
				player.write(item);
			}
		}
		
		public void requestPutEnergy(Fighter fighter) {
			if(myTurn) {
				Energy energy = (Energy)cardView.getCard();
				player.write(MatchRequest.PUT_ENERGY);
				player.write(fighter);
				player.write(energy);
			}
		}
		
		public void requestAtack(Fighter attacker, Fighter target) {
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
					requestPutEnergy((Fighter) target);
					break;
	
				case BATTLE_PHASE:
					requestAtack(attacker,(Fighter) target);
					break;
					
				default:
					break;
			}
		}
		
		protected void refreshPhase(Phases phase) {
			this.phase = phase; 
			String titleMsg = (myTurn?"Your Turn: ":"Opponent Turn: ") + phase;
			lblPhase.setText(titleMsg.replace("_", " "));
			
			if(myTurn) {
				switch (phase) {
					case DRAW_PHASE:
						resquestDrawCard();
						break;
					case BATTLE_PHASE:
						btnBattle.setVisible(false);
						break;
					default:
						break;
				}
			}
		}
		
		protected void setTurn(MatchResponse response) {
			if(response == MatchResponse.YOUR_TURN) {
				btnEndTurn.setVisible(true);
				btnBattle.setVisible(true);
				myTurn = true;
			} else if(response == MatchResponse.OPPONENT_TURN) {
				myTurn = false;
			}
		}
		
		protected void refreshHealthPoints() {
			myHP.setText(Integer.toString(player.getField().getHealthsPoint()));
			opHP.setText(Integer.toString(opponent.getField().getHealthsPoint()));
		}
		
}
