package br.com.healthswar.player.model;

import java.io.Serializable;

public class Person implements Serializable {

	private static final long serialVersionUID = -2583885168862452678L;
	
	private int id;
	private String name;
	private String email;
	private String password;

	private int victories;
	private int defeats;

	public String getName() {
		return name;
	}

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getEmail() {
		return email;
	}

	public final void setEmail(String email) {
		this.email = email;
	}

	public final String getPassword() {
		return password;
	}

	public final void setPassword(String password) {
		this.password = password;
	}

	public int getVitorias() {
		return victories;
	}

	public final void setVitorias(int vitorias) {
		this.victories = vitorias;
	}

	public int getDerrotas() {
		return defeats;
	}

	public void setDerrotas(int derrotas) {
		this.defeats = derrotas;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", victories=" + victories + ", defeats=" + defeats + "]";
	}

}
