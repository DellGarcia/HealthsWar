package br.com.healthswar.comunication.authentication;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LoginRequest implements Serializable{
	
	public static final int MAKE_LOGIN = 0;
	
	private int requestCode;
	private String email;
	private String senha;
	
	private LoginRequest(int requestCode) {
		this.requestCode = requestCode;
	}

	public int getRequestCode() {
		return requestCode;
	}

	public void setRequestCode(int requestCode) {
		this.requestCode = requestCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
