package br.com.healthswar.player.view.main;

import javax.swing.JOptionPane;
import br.com.healthswar.comunication.MatchRequest;
import br.com.healthswar.comunication.MatchResponse;
import br.com.healthswar.comunication.Phases;
import br.com.healthswar.gameplay.Card;
import br.com.healthswar.gameplay.CardLocal;
import br.com.healthswar.gameplay.Field;
import br.com.healthswar.gameplay.FighterField;
import br.com.healthswar.gameplay.Player;
import br.com.healthswar.gameplay.energy.Energy;
import br.com.healthswar.gameplay.fighters.Fighter;
import br.com.healthswar.gameplay.items.Item;
import br.com.healthswar.player.view.InitView;

public class MainViewStructure extends MainViewBase {

	private static final long serialVersionUID = 1702140455193280420L;
	
	/* [Match Actions] */
	protected void verifyMatchStatus(MatchResponse turnResponse) {
		if(turnResponse == MatchResponse.END_GAME) {
			MatchResponse response = (MatchResponse) player.read();

			if(response == MatchResponse.YOU_WIN) {
				JOptionPane.showMessageDialog(null, "VOCÊ GANHOU");

			} else if(response == MatchResponse.YOU_LOSE) {
				JOptionPane.showMessageDialog(null, "VOCÊ PERDEU");
			}

			if(JOptionPane.showConfirmDialog(null, "Deseja ter uma revanche?", "Revanche", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
				// Revanche em breve kkk
				new InitView();
				MainView.destroy();
			} else {
				new InitView();
				MainView.destroy();
			}
		}
	}

	protected void drawCard() {
		Player target = getActive();
		Field field = (Field) player.read();
		
		target.getField().getHand().removeHandFromPanel(container);
		container.remove(target.getField().getDeck().get(0));
		target.setField(field);
		
		Deck();
		Hand();
		Fighters();
		
		field = null;
		target = null;
	}

	
	protected void sendFighter() {
		Field campo = (Field) player.read();
		Fighter fighter = (Fighter) player.read();
		FighterField[] field = myTurn?myFighters:opFighters;
		Player target = getActive();
		
		target.getField().getHand().removeHandFromPanel(container);
		
		target.setField(campo);
		
		for(int i = 0; i < field.length; i++) {
			if(field[i].getFighter() == null) {
				field[i].setFighter(fighter);
				break;
			}
		}	
		
		Hand();
		Fighters();
		
		campo = null;
		fighter = null;
		field = null;
		target = null;
	}

	protected void useItem() {
		Field field = (Field) player.read();
		Player target = (myTurn)? player : opponent;

		target.getField().getHand().removeHandFromPanel(container);
		target.setField(field);

		for(int i = 0; i < myFighters.length; i++) 
			myFighters[i].setFighter(field.getFighters()[i]);
		
		Fighters();
		Discard();
		Hand();
		
		target = null;
		field = null;
	}

	protected void putEnergy() {
		Field field = (Field) player.read();
		Fighter fighter = (Fighter) player.read();
		Player target = getActive();
		FighterField[] fighters = myTurn?myFighters:opFighters;
		
		target.getField().getHand().removeHandFromPanel(container);
		target.setField(field);
		
		for(FighterField fi: fighters) {
			if(fi.getFighter().id == fighter.id) {
				fi.setFighter(fighter);
				break;
			}
		}
		
		Fighters();
		Hand();
		Discard();
		
		fighter = null;
		field = null;
		target = null;
		fighters = null;
	}
	
	protected void attack() {
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
			
			Memory();
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
		player.write(player.getField());
	}	
	
	public void requestSendFighter(Fighter fighter) {
		if(myTurn) {
			player.write(MatchRequest.SEND_A_FIGHTER);
			player.write(player.getField());
			player.write(fighter);
		}
	}
	
	public void requestUseItem(Item item) {
		if(myTurn) {
			player.write(MatchRequest.USE_AN_ITEM);
			player.write(player.getField());
			player.write(item);
		}
	}
	
	public void requestPutEnergy(Fighter fighter) {
		if(myTurn) {
			Energy energy = (Energy)cardView.getCard();
			player.write(MatchRequest.PUT_ENERGY);
			player.write(player.getField());
			player.write(fighter);
			player.write(energy);
		}
	}
	
	public void requestAtack(Fighter attacker, Fighter target) {
		if(myTurn) {
			player.write(MatchRequest.ATTACK_THE_OPPONENT);
			player.write(attacker);
			player.write(target);
		}
	}

	
	public void requestAttack(Fighter attacker, Fighter target) {
		if(myTurn) {
			player.write(MatchRequest.ATTACK_THE_OPPONENT);
			player.write(attacker);
			player.write(target);
		}
	}

	/* [Display Methods] */
	public void showCardView(Card card) {
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
				requestAttack(attacker,(Fighter) target);
				break;
			default:
				break;
		}
	}

	protected void refreshPhase(Phases phase) {
		this.phase = phase;
		int turn = (Integer) player.read();
		String titleMsg = ((myTurn)? "Your Turn: " : "Opponent Turn: ") + phase;

		lblTurn.setText("Turno: " + turn);
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
