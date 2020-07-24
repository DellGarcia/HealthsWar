package br.com.healthswar.comunication;

import java.io.Serializable;

public enum Request implements Serializable {
	REGISTER_PLAYER,
	UPDATE_PLAYER,
	DELETE_PLAYER,
	SELECT_PLAYER,
	SELECT_ALL_PLAYERS,
	PLAY_A_SOLO_MATCH,
	PLAY_A_DUO_MATCH
}
