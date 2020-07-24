package br.com.healthswar.gameplay.decks;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import br.com.healthswar.gameplay.Card;
import br.com.healthswar.gameplay.energy.Energy;
import br.com.healthswar.gameplay.fighters.Basofilo;
import br.com.healthswar.gameplay.fighters.Eosinofilo;
import br.com.healthswar.gameplay.fighters.Monocito;
import br.com.healthswar.gameplay.fighters.Neutrofilo;
import br.com.healthswar.gameplay.items.HealthyEating;
import br.com.healthswar.gameplay.items.RegularPhysicalActivity;
import br.com.healthswar.gameplay.items.Ferver;
import br.com.healthswar.gameplay.items.HighImmunity;
import br.com.healthswar.gameplay.items.RenewingForces;
import br.com.healthswar.gameplay.items.Serum;
import br.com.healthswar.gameplay.items.Vaccine;

public final class ImmuneSystem extends ArrayList<Card> {

	private static final long serialVersionUID = 7408717304009750403L;
	
	public ImmuneSystem() {
		AddCard(Neutrofilo.class, 5);
		AddCard(Monocito.class, 2);
		AddCard(Eosinofilo.class, 4);
		AddCard(Basofilo.class, 4);
		
		AddCard(Ferver.class, 20);
//		AddCard(HealthyEating.class, 3);
//		AddCard(Vaccine.class, 2);
//		AddCard(Serum.class, 5);
//		AddCard(RenewingForces.class, 2);
//		AddCard(RegularPhysicalActivity.class, 3);
//		AddCard(HighImmunity.class, 3);
		
		AddCard(Energy.class, 25);
	}
	
	private void AddCard(Class<? extends Card> classe, int qtd) {
		for(int i = 0; i < qtd; i++) {
			try { add(classe.getDeclaredConstructor().newInstance()); }
			catch (InstantiationException | IllegalAccessException |
					IllegalArgumentException | InvocationTargetException |
					NoSuchMethodException | SecurityException e) { e.printStackTrace(); }
		}
	}
	
}
