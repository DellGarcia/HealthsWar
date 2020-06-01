package br.com.healthswar.comunication.authentication;

import java.io.Serializable;

public enum LoginResponse implements Serializable{

	VALID_LOGIN(0),
	INVALID_LOGIN(1);
	
	public int responseCode;
	
	private LoginResponse(int responseCode) {
		this.responseCode = responseCode;
	}
	
}
