package br.com.healthswar.gameplay.items;

public class Serum extends Item {

	private static final long serialVersionUID = -6321505831257768793L;

	public Serum() {
		super();
		name = "Soro";
		description = "Aumenta o ataque em 30 nesse turno.";
	}

	@Override
	public void resolve() {
		System.out.println("Ativou");
	}
	
	
}
