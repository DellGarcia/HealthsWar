package br.com.healthswar.comunication;

import java.io.Serializable;

public enum MatchRequest implements Serializable {
	
	DRAW_A_CARD,
	SEND_A_FIGHTER,
	USE_AN_ITEM,
	PUT_ENERGY,
	ATACK_THE_OPONENT,
	START_BATTLE,
	END_THE_TURN;
	
}
