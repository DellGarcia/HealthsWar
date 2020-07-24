package br.com.healthswar.gameplay.items;

public class RenewingForces extends Item {

	private static final long serialVersionUID = -2936209461050110677L;

	public RenewingForces() {
		super();
		name = "Renovar Forcas";
		description = "Embaralhe sua mão e compre 5 cartas";
	}

	@Override
	public void resolve() {
		System.out.println("Ativou");
	}
	
}
