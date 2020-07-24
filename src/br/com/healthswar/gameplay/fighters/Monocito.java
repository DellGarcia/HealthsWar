package br.com.healthswar.gameplay.fighters;

public class Monocito extends Fighter {

	private static final long serialVersionUID = 5435648852120770686L;

	public Monocito() {
		super();
		name = "Mon�cito";
		frontImg = loadImage("../../assets/sprites/anticorpos/monocito.png");
		healthPoints = 100;
		atkPower = 60;
		description = "Possuem um n�cleo redondo ou reniforme e grande citoplasma."
				+ " Eles tornam-se macr�fagos, c�lulas especializadas no processo de fagocitose de v�rus, fungos e bact�rias.";
	}

	
	
}
