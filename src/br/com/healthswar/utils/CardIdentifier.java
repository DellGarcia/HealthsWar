package br.com.healthswar.utils;

public abstract class CardIdentifier {

	private static int ID = 0;
	
	public static int getNewId() {
		return ID++;
	}
	
}
