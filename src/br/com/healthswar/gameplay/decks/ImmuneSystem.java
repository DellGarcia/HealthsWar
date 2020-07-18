package br.com.healthswar.gameplay.decks;

import java.util.ArrayList;

import br.com.healthswar.gameplay.Card;
import br.com.healthswar.gameplay.energy.Energy;
import br.com.healthswar.gameplay.fighters.Basofilo;
import br.com.healthswar.gameplay.fighters.Eosinofilo;
import br.com.healthswar.gameplay.fighters.Monocito;
import br.com.healthswar.gameplay.fighters.Neutrofilo;
import br.com.healthswar.gameplay.items.AlimentacaoSaudavel;
import br.com.healthswar.gameplay.items.AtividadeFisicaRegular;
import br.com.healthswar.gameplay.items.Febre;
import br.com.healthswar.gameplay.items.ImunidadeAlta;
import br.com.healthswar.gameplay.items.RenovarForcas;
import br.com.healthswar.gameplay.items.Soro;
import br.com.healthswar.gameplay.items.Vacina;

public final class ImmuneSystem extends ArrayList<Card> {

	private static final long serialVersionUID = 7408717304009750403L;
	
	public ImmuneSystem() {
		AddCard(new Neutrofilo(), 5);
		AddCard(new Monocito(), 2);
		AddCard(new Eosinofilo(), 4);
		AddCard(new Basofilo(), 4);
		
		AddCard(new Febre(), 2);
		AddCard(new AlimentacaoSaudavel(), 3);
		AddCard(new Vacina(), 2);
		AddCard(new Soro(), 4);
		AddCard(new RenovarForcas(), 2);
		AddCard(new AtividadeFisicaRegular(), 3);
		AddCard(new ImunidadeAlta(), 3);
		
		AddCard(new Energy(), 25);
	}
	
	private void AddCard(Card card, int qtd) {
		for(int i = 0; i < qtd; i++) 
			add(card);
	}
	
}
