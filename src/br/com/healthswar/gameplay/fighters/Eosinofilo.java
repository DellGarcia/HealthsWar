package br.com.healthswar.gameplay.fighters;

public class Eosinofilo extends Fighter {

	private static final long serialVersionUID = 641833719859387013L;

	public Eosinofilo() {
		super();
		name = "Eosinófilo";
		frontImg = loadImage("../../assets/sprites/anticorpos/eusinofilo.png");
		healthPoints = 50;
		atkPower = 40;
		description = "São células que também possuem grânulos"
				+ " e estão relacionadas com a fagocitose dos complexos antígeno-anticorpo."
				+ " Sua forma é esférica e o núcleo, bilobado."
				+ " O número dessas células é aumentado durante respostas a infecções parasitárias e reações alérgicas.";
	}

}
