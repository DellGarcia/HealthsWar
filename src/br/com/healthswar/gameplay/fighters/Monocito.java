package br.com.healthswar.gameplay.fighters;

public class Monocito extends Fighter {

	private static final long serialVersionUID = 5435648852120770686L;

	public Monocito() {
		super();
		name = "Monócito";
		frontImg = loadImage("../../assets/sprites/anticorpos/monocito.png");
		healthPoints = 100;
		atkPower = 60;
		description = "Possuem um núcleo redondo ou reniforme e grande citoplasma."
				+ " Eles tornam-se macrófagos, células especializadas no processo de fagocitose de vírus, fungos e bactérias.";
	}

	
	
}
