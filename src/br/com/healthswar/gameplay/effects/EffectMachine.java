package br.com.healthswar.gameplay.effects;

import java.util.ArrayList;

import br.com.healthswar.gameplay.items.Item;

public class EffectMachine {

	private ArrayList<Item> items;
	
	private static EffectMachine INSTANCE;
	
	private EffectMachine() {
		items = new ArrayList<Item>();
	}
	
	public static EffectMachine getInstance() {
		if(INSTANCE == null)
			INSTANCE = new EffectMachine();
		
		return INSTANCE;
	}
	
	public static void destroy() {
		INSTANCE = null;
	}
	
	
	public void useItemEffect(Item item) {
		items.add(item);
		item.resolve();
	}
	
	public void resolveEffects() {
		ArrayList<Item> excluded = new ArrayList<Item>();
		for(Item item : items) {
			item.resolve();
			if(item.getDuration() < 0)
				excluded.add(item);
		}
		
		items.removeAll(excluded);
	}
	
}
