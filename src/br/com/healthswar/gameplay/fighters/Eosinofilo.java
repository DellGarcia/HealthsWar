package br.com.healthswar.gameplay.fighters;

public class Eosinofilo extends Fighter {

	private static final long serialVersionUID = 641833719859387013L;

	public Eosinofilo() {
		super();
		name = "Eosin�filo";
		frontImg = loadImage("../../assets/sprites/anticorpos/eusinofilo.png");
		healthPoints = 50;
		atkPower = 40;
		description = "S�o c�lulas que tamb�m possuem gr�nulos"
				+ " e est�o relacionadas com a fagocitose dos complexos ant�geno-anticorpo."
				+ " Sua forma � esf�rica e o n�cleo, bilobado."
				+ " O n�mero dessas c�lulas � aumentado durante respostas a infec��es parasit�rias e rea��es al�rgicas.";
	}

}
