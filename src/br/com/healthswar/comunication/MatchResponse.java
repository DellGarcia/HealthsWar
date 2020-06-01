package br.com.healthswar.comunication;

import java.io.Serializable;

public enum MatchResponse implements Serializable {
	
	AVALIBLE_CARD,
	NO_CARDS,
	FIGHTER_READY,
	NO_FIGHTER,
	ITEM_USED,
	IMPOSSIBLE_TO_USE,
	ENERGY_READY,
	BATTLE_STARTED,
	SUCCESSFUL_ATACK,
	ATACK_FAILED,
	YOUR_TURN,
	OPPONENT_TURN;
	
}
