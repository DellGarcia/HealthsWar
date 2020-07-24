package br.com.healthswar.gameplay.fighters;

public class Neutrofilo extends Fighter {

	private static final long serialVersionUID = -2755521347600345102L;

	public Neutrofilo() {
		super();
		name = "Neutr�filo";
		frontImg = loadImage("../../assets/sprites/anticorpos/neutrofilo.png");
		healthPoints = 60;
		atkPower = 50;
		description = "S�o os tipos mais numerosos."
				+ " Exibem forma esf�rica e um n�cleo geralmente trilobado."
				+ " Essas c�lulas realizam fagocitose,"
				+ " podem deixar os vasos sangu�neos e penetrar nos tecidos,"
				+ " exercendo assim a sua fun��o de prote��o do organismo.";
		
	}

}
