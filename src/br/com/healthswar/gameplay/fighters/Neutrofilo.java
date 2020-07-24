package br.com.healthswar.gameplay.fighters;

public class Neutrofilo extends Fighter {

	private static final long serialVersionUID = -2755521347600345102L;

	public Neutrofilo() {
		super();
		name = "Neutrófilo";
		frontImg = loadImage("../../assets/sprites/anticorpos/neutrofilo.png");
		healthPoints = 60;
		atkPower = 50;
		description = "São os tipos mais numerosos."
				+ " Exibem forma esférica e um núcleo geralmente trilobado."
				+ " Essas células realizam fagocitose,"
				+ " podem deixar os vasos sanguíneos e penetrar nos tecidos,"
				+ " exercendo assim a sua função de proteção do organismo.";
		
	}

}
