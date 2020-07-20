package br.com.healthswar.gameplay.fighters;

public class Basofilo extends Fighter {

	private static final long serialVersionUID = -1903105171260901722L;

	public Basofilo() {
		super();
		name = "Basófilo";
		frontImg = loadImage("../../assets/card-md.jpg");
		healthPoints = 50;
		atkPower = 40;
		description = "Outro leucócito granulócito, atuam liberando histamina e heparina,"
				+ " substâncias que ajudam na dilatação dos vasos sanguíneos e na anticoagulação, respectivamente."
				+ " Apresentam forma esférica e núcleo irregular.";
	}

}
