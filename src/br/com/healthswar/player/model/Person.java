package br.com.healthswar.player.model;

public class Person {

	private int id;
	private String name;
	private String email;
	private String password;

	private int victories;
	private int defeats;

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getVitorias() {
		return victories;
	}

	public void setVitorias(int vitorias) {
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
