/**
 * 
 */
package br.com.healthswar.comunication;

import java.io.Serializable;

/**
 * @author Anonymous - HealthsWar
 *
 */
public enum Response implements Serializable {
	
	MATCH_FOUND(0),
	MATCH_NOT_FOUND(1),
	MATCH_READY(2);
	
	private int responseCode;
	
	Response(int responseCode) {
		this.responseCode = responseCode;
	}
	
	public int getResponseCode() {
		return responseCode;
	}
	
}
