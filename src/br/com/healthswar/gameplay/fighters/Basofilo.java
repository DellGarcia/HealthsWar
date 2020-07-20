package br.com.healthswar.gameplay.fighters;

public class Basofilo extends Fighter {

	private static final long serialVersionUID = -1903105171260901722L;

	public Basofilo() {
		super();
		name = "Bas�filo";
		frontImg = loadImage("../../assets/card-md.jpg");
		healthPoints = 50;
		atkPower = 40;
		description = "Outro leuc�cito granul�cito, atuam liberando histamina e heparina,"
				+ " subst�ncias que ajudam na dilata��o dos vasos sangu�neos e na anticoagula��o, respectivamente."
				+ " Apresentam forma esf�rica e n�cleo irregular.";
	}

}
