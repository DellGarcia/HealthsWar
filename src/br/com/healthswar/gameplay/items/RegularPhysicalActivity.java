package br.com.healthswar.gameplay.items;

public class RegularPhysicalActivity extends Item {

	private static final long serialVersionUID = -4143773854696346233L;

	public RegularPhysicalActivity() {
		super();
		name = "Ativiadade Física Regular";
		description = "A prática de exercicios tornou o corpo mais forte, compre 2 cartas.";
	}

	@Override
	public void resolve() {
		System.out.println("Ativou");
	}
	
}
