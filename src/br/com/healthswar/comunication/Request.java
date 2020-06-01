/**
 * 
 */
package br.com.healthswar.comunication;

import java.io.Serializable;

/**
 * @author Anonymous - HealthsWar
 *
 */
public enum Request implements Serializable {
	
	PLAY_A_SOLO_MATCH(0),
	PLAY_A_DUO_MATCH(1),
	PLAY_A_SQUAD_MATCH(2);
	
	public int requestCode;
	
	Request(int requestCode) {
		this.requestCode = requestCode;
	}
	
}
