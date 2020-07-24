package br.com.healthswar.gameplay.items;

public class Vaccine extends Item {

	private static final long serialVersionUID = -8481454111349995139L;

	public Vaccine() {
		super();
		name = "Vacina";
		description = "Diminui o Ataque dos antigenos pela metade por 2 turnos.";
	}

	@Override
	public void resolve() {
		System.out.println("Ativou");
	}
	
}
