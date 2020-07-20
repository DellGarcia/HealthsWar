package br.com.healthswar.gameplay.items;

public class HealthyEating extends Item {

	private static final long serialVersionUID = 3914956652985198514L;
	
	public HealthyEating() {
		super();
		name = "Alimentação Saudável";
		description = "A boa alimentação do corpo, disponibilizou mais energia para o combate, pegue 3 ATP do seu Deck.";
	}

	@Override
	public void resolve() {
		System.out.println("Ativou");
	}
	
	
}
