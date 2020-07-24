package br.com.healthswar.gameplay.items;

public class HighImmunity extends Item {

	private static final long serialVersionUID = 8445349697921104083L;

	public HighImmunity() {
		super();
		name = "Imunidade Alta";
		description = "Reduz o ataque dos virus em 10 por 3 turnos";
	}

	@Override
	public void resolve() {
		System.out.println("Ativou");
	}
	
}
